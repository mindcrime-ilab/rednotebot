/*
 * RedNotebot - Mobile dairy, journal, notekeeping tool. Copyright (C) 2012 Michael Engelhardt
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package de.mindcrimeilab.rednotebot.parser.txt2tags;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A regular expression based parser to read txt2tags formatted input and transform it into another representations.
 * Supported substitutions are
 * <ul>
 * <li>plain text {@code PlainReplacements}</li>
 * <li>HtML {@code HtmlReplacements}</li>
 * </ul>
 * 
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class RETxt2TagsParser {

    /*
     * Precompiled patterns to match certain constructs in a txt2tags format
     */
/** matches '<' */
    private final static Pattern vhtmlLt = Pattern.compile("<");

    /** matches '>' */
    private final static Pattern vhtmlGt = Pattern.compile(">");

    /** matches '&' */
    private final static Pattern vhtmlAmp = Pattern.compile("&"); // TODO refine expression not machting entities(?)

    /** matches '%' */
    private final static Pattern vhtmlPercent = Pattern.compile("%");

    /** matches unordered lists and items */
    private final static Pattern vUnorderedListOpen = Pattern.compile("^( *)-(.*)");

    // private final static Pattern vListClose = Pattern.compile("^( *)([-+:])\\s*$");
    /** matches a level 1 heading */
    private final static Pattern vHeading1 = Pattern.compile("^ *([=]{1})([^=](|.*[^=]))\\1(\\[([\\w-]*)\\])?\\s*$");

    /** matches a level 2 heading */
    private final static Pattern vHeading2 = Pattern.compile("^ *([=]{2})([^=](|.*[^=]))\\1(\\[([\\w-]*)\\])?\\s*$");

    /** matches a level 3 heading */
    private final static Pattern vHeading3 = Pattern.compile("^ *([=]{3})([^=](|.*[^=]))\\1(\\[([\\w-]*)\\])?\\s*$");

    /** matches a level 4 heading */
    private final static Pattern vHeading4 = Pattern.compile("^ *([=]{4})([^=](|.*[^=]))\\1(\\[([\\w-]*)\\])?\\s*$");

    /** matches a level 5 heading */
    private final static Pattern vHeading5 = Pattern.compile("^ *([=]{5})([^=](|.*[^=]))\\1(\\[([\\w-]*)\\])?\\s*$");

    /** matches either a thick or a thin horizontal line */
    private final static Pattern vBar = Pattern.compile("^(\\s*)([_=-]{20,})\\s*$");

    /** matches a verbatim text */
    private final static Pattern vVerbatim = Pattern.compile("^```\\s*$");

    /** matches a bold text */
    private final static Pattern vBold = Pattern.compile("(\\*{2})([^\\s](|.*?[^\\s])\\**)(\\*{2})");

    /** matches an italic text */
    private final static Pattern vItalic = Pattern.compile("(\\/{2})([^\\s](|.*?[^\\s])\\/*)(\\/{2})");

    /** matches a monospaced text */
    private final static Pattern vMonospace = Pattern.compile("(\\`{2})([^\\s](|.*?[^\\s])\\`*)(\\`{2})");

    /** matches an underlined text */
    private final static Pattern VUnderline = Pattern.compile("(\\_{2})([^\\s](|.*?[^\\s])\\_*)(\\_{2})");

    /** matches some kind of URL */
    private final static Pattern vUrl = Pattern.compile("((\\w+:\\/\\/)[-a-zA-Z0-9:@;?&=\\/%\\+\\.\\*!'\\(\\),\\$_\\{\\}\\^~\\[\\]`#|]+)");

    /** matches an image placeholder */
    private final static Pattern vImg = Pattern.compile("\\[\"{2}([^\\s]|.*?[^\\s])\"{2}\\.(png|jpe?g|gif|eps|bmp)\\]");

    /** matches a commented line */
    private final static Pattern vComment = Pattern.compile("^%");

    /** matcher instance to perform regexp operations */
    private final Matcher matcher;

    /**
     * ctor()
     * 
     * Constructs a new parser object having an already initialized matcher. At construction time the matcher is
     * configured to match only bold expressions. To optimize parsing there is only one matcher instance which is
     * reseted and reconfigured for the use with other patterns as need.
     */
    public RETxt2TagsParser() {
        matcher = vBold.matcher("");
    }

    /**
     * Reads a txt2tags formatted string and converts the input to an HTML compliant string.
     * 
     * @param in
     *            txt2tags formatted string
     * @return HTMLized string
     * @throws IOException
     *             if reading from the string fails.
     */
    public String parseToHtml(String in) throws IOException {
        return parse(in, HtmlReplacements.INSTANCE);
    }

    /**
     * Converts a given input string into an output representation applying all matching replacements to it.Therefore
     * the {@code parse} method reads the string line-wise applying all possible modifications to the active line.
     * Please note that the order of replacements is imported for the parser to work probably.
     * 
     * @param in
     * @param replacements
     * @return
     * @throws IOException
     */

    private String parse(String in, Txt2TagsReplacements<? extends String> replacements) throws IOException {
        // put input into buffered reader to optimize reading
        final BufferedReader reader = new BufferedReader(new StringReader(in));

        // initialize buffer to append the converted input to
        final StringBuilder result = new StringBuilder();

        // emit start of document - this could add any preamble or headers to the result document
        result.append(replacements.get(Txt2TagsEvents.DOCUMENT_START));

        /*
         * SECTION: Parsing flag initialization
         * 
         * List of flags:
         * 
         * - skipLine: indicates that the active line should not be appended to output
         * 
         * - verbatimMode: indicates that there should be not subsitutions; only sanitizing for the output format if
         * necessary
         * 
         * - listMode: indicates wether the ontent is append to a list structure or not
         */
        boolean skipLine = false; // skip line off
        boolean verbatimMode = false;// verbatim mode off
        boolean listMode = false; // list mode off

        /*
         * SECTION: Parsing
         */

        // save nesting level of lists
        int nestingLevel = 0;

        // current line which is parsed
        String line;
        while (null != (line = reader.readLine())) {
            /*
             * Whole line subsitutions ORDER IS IMPORTANT!
             */

            /*
             * General we can ignore commented lines beside we are in verbatim mode. If we are in verbatim mode we are
             * expected to output the commented line as well.
             */
            if (-1 != line.indexOf("%") && !verbatimMode) {
                matcher.reset(line);
                matcher.usePattern(vComment);
                if (matcher.find()) {
                    continue; // no further processing need - line will not be part of the output
                }
            }

            /*
             * Sanitize the input for target output for target language, e.g. convert entities.
             */
            line = substitute(line, "&", vhtmlAmp, replacements.get(Txt2TagsEvents.CHAR_AMP));
            line = substitute(line, "<", vhtmlLt, replacements.get(Txt2TagsEvents.CHAR_LT));
            line = substitute(line, ">", vhtmlGt, replacements.get(Txt2TagsEvents.CHAR_GT));
            line = substitute(line, "%", vhtmlPercent, replacements.get(Txt2TagsEvents.CHAR_PERCENT));
            line = substitute(line, vUrl, replacements.get(Txt2TagsEvents.URL), vImg);

            /*
             * Thick and thin lines
             */
            if (-1 != line.indexOf("====================") || -1 != line.indexOf("--------------------")) {
                matcher.reset(line);
                matcher.usePattern(vBar);
                if (matcher.find()) {
                    line = matcher.replaceAll(replacements.get(Txt2TagsEvents.BAR));
                    skipLine = true;
                }
            }
            /*
             * Headings
             */
            else if (line.trim().startsWith("=")) {
                Pattern[] patterns = new Pattern[] { vHeading1, vHeading2, vHeading3, vHeading4, vHeading5};
                // @formatter:off
                String[] reps = new String[] { 
                        replacements.get(Txt2TagsEvents.HEADING1), 
                        replacements.get(Txt2TagsEvents.HEADING2), 
                        replacements.get(Txt2TagsEvents.HEADING3), 
                        replacements.get(Txt2TagsEvents.HEADING4),
                        replacements.get(Txt2TagsEvents.HEADING5)};
                // @formatter:on
                for (int i = 0; i < patterns.length; ++i) {
                    matcher.reset(line);
                    matcher.usePattern(patterns[i]);
                    if (matcher.find()) {
                        line = matcher.replaceAll(reps[i]);
                        skipLine = true;
                        break;
                    }
                }
            }
            /*
             * Verbatim text
             */
            else if (line.startsWith("```")) {
                matcher.reset(line);
                matcher.usePattern(vVerbatim);
                if (matcher.find()) {
                    line = matcher.replaceAll((!verbatimMode) ? replacements.get(Txt2TagsEvents.VERBATIM_OPEN) : replacements.get(Txt2TagsEvents.VERBATIM_CLOSE));
                    verbatimMode = !verbatimMode;
                    skipLine = true;
                }
            }
            /*
             * Lists Lists might have a number of whitespace characters in front of the list marker char
             */
            else if (-1 != line.indexOf("-")) {
                matcher.reset(line);
                matcher.usePattern(vUnorderedListOpen);
                if (matcher.find()) {
                    if (!listMode) result.append(replacements.get(Txt2TagsEvents.UNORDERED_LIST_OPEN));
                    listMode = true;

                    int oldNestingLevel = nestingLevel;
                    nestingLevel = line.length() - line.trim().length();
                    if (nestingLevel > oldNestingLevel) result.append(replacements.get(Txt2TagsEvents.UNORDERED_LIST_OPEN));
                    else if (nestingLevel < oldNestingLevel) {
                        for (int i = oldNestingLevel; i > nestingLevel; --i) {
                            result.append(replacements.get(Txt2TagsEvents.UNORDERED_LIST_CLOSE));
                        }
                    }

                    line = matcher.replaceAll(replacements.get(Txt2TagsEvents.LIST_ITEM));

                }
            }
            /*
             * Close all eventually open lists
             */
            else if (line.trim().length() == 0) {
                listMode = false;
                for (int i = nestingLevel; i >= 0; i--) {
                    line += replacements.get(Txt2TagsEvents.UNORDERED_LIST_CLOSE);
                }
            }
            /* ************************************************************* */
            /*
             * Inline replacements order is important!
             */
            if (!skipLine && !verbatimMode) {
                // order is important!
                line = substitute(line, "[\"\"", vImg, replacements.get(Txt2TagsEvents.IMAGE));
                line = substitute(line, "**", vBold, replacements.get(Txt2TagsEvents.BOLD));
                line = substitute(line, "//", vItalic, replacements.get(Txt2TagsEvents.ITALIC), vUrl);
                line = substitute(line, "``", vMonospace, replacements.get(Txt2TagsEvents.MONOSPACE));
                line = substitute(line, "__", VUnderline, replacements.get(Txt2TagsEvents.UNDERLINE));
            }

            /*
             * Generate output for line
             */
            result.append(line);
            if (!listMode) result.append(replacements.get(Txt2TagsEvents.LINEBREAK));
            skipLine = false;
        }
        // emit document end event in order to allow to put some footer or addenda
        result.append(replacements.get(Txt2TagsEvents.DOCUMENT_END));

        return result.toString();
    }

    /**
     * Overloaded version of {@code substitute} method
     * 
     * @param line
     *            input line
     * @param tag
     *            tag /event to substitute
     * @param regexp
     *            regular expression representing the certain tag / event
     * @param replacement
     *            replacement for tag / event
     * @return substituted version of input string
     */
    private String substitute(String line, final String tag, final Pattern regexp, final String replacement) {
        return substitute(line, tag, regexp, replacement, null);
    }

    /**
     * Overloaded version of {@code substitute} method
     * 
     * @param line
     *            input line
     * @param regexp
     *            tag /event to substitute
     * @param replacement
     *            replacement for tag / event
     * @param filter
     *            regular expression to match portions of the which should be excluded from subustitution
     * @return substituted version of input string
     */
    private String substitute(String line, final Pattern regexp, final String replacement, Pattern filter) {
        return substitute(line, null, regexp, replacement, filter);
    }

    /**
     * Replaces all matches of the given regular expression specified by {@code regexp} by the given replacement taking
     * care to ignore possible filtered values.
     * 
     * @param line
     *            input line
     * @param tag
     *            tag /event to substitute
     * @param regexp
     *            regular expression representing the certain tag / event
     * @param replacement
     *            replacement for tag / event
     * @param filter
     *            regular expression to match portions of the which should be excluded from subustitution
     * @return substituted version of input string
     */
    private String substitute(String line, final String tag, final Pattern regexp, final String replacement, Pattern filter) {
        if (null == tag || -1 != line.indexOf(tag)) {
            matcher.reset(line);
            if (null != filter) matcher.usePattern(filter);
            if (!matcher.find() || null == filter) {
                matcher.reset(line);
                matcher.usePattern(regexp);
                matcher.region(0, line.length());
                line = matcher.replaceAll(replacement);
            }
        }
        return line;
    }

}
