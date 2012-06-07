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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration provides a singleton implementation of the {@code Txt2TagsReplacements} interface for substitutions to
 * plain (unformatted) text. This class is immutable.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum PlainTextReplacements implements Txt2TagsReplacements<String> {
    INSTANCE;

    /*
     * (non-Javadoc)
     * 
     * @see de.mindcrimeilab.rednotebot.parser.txt2tags.Txt2TagsReplacements#getReplacements()
     */
    public Map<Txt2TagsEvents, String> getReplacements() {
        return Collections.unmodifiableMap(map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.mindcrimeilab.rednotebot.parser.txt2tags.Txt2TagsReplacements#get(de.mindcrimeilab.rednotebot.parser.txt2tags
     * .Txt2TagsEvents)
     */
    public String get(Txt2TagsEvents event) {
        return map.get(event);
    }

    /**
     * ctor()
     * 
     * initializes a map containing plain (unformatted) replacements for a set of {@code Txt2TagsEvents}.
     */
    private PlainTextReplacements() {
        this.map = new HashMap<Txt2TagsEvents, String>();
        map.put(Txt2TagsEvents.CHAR_LT, "<");
        map.put(Txt2TagsEvents.CHAR_GT, ">");
        map.put(Txt2TagsEvents.CHAR_AMP, "&");
        map.put(Txt2TagsEvents.UNORDERED_LIST_OPEN, "");
        map.put(Txt2TagsEvents.UNORDERED_LIST_CLOSE, "");
        map.put(Txt2TagsEvents.HEADING1, "$2");
        map.put(Txt2TagsEvents.HEADING2, "$2");
        map.put(Txt2TagsEvents.HEADING3, "$2");
        map.put(Txt2TagsEvents.HEADING4, "$2");
        map.put(Txt2TagsEvents.HEADING5, "$2");
        map.put(Txt2TagsEvents.BAR, "$1");
        map.put(Txt2TagsEvents.VERBATIM_OPEN, "");
        map.put(Txt2TagsEvents.VERBATIM_CLOSE, "");
        map.put(Txt2TagsEvents.BOLD, "$2");
        map.put(Txt2TagsEvents.ITALIC, "$2");
        map.put(Txt2TagsEvents.UNDERLINE, "$2");
        map.put(Txt2TagsEvents.URL, "$1");
        map.put(Txt2TagsEvents.MONOSPACE, "$2");
        map.put(Txt2TagsEvents.LINEBREAK, "\n");
        map.put(Txt2TagsEvents.DOCUMENT_START, "");
        map.put(Txt2TagsEvents.DOCUMENT_END, "");

    }

    private Map<Txt2TagsEvents, String> map;
}
