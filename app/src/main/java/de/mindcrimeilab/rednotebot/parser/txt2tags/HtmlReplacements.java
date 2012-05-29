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
 * Enumeration provides a singleton implementation of the {@code Txt2TagsReplacements} interface for HTML substitutions.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum HtmlReplacements implements Txt2TagsReplacements<String> {
    /** singleton holder for one instance of this class */
    INSTANCE;

    /*
     * (non-Javadoc)
     * 
     * @see de.mindcrimeilab.rednotebot.parser.txt2tags.Txt2TagsReplacements#getReplacements()
     */
    public Map<Txt2TagsEvents, String> getReplacements() {
        return Collections.unmodifiableMap(this.map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.mindcrimeilab.rednotebot.parser.txt2tags.Txt2TagsReplacements#get(de.mindcrimeilab.rednotebot.parser.txt2tags
     * .Txt2TagsEvents)
     */
    public String get(Txt2TagsEvents event) {
        return this.map.get(event);
    }
    
    /**
     * ctor
     * initializes a map containing HTML replacements for a set of {@code Txt2TagsEvents}.
     */
    private HtmlReplacements() {
        map = new HashMap<Txt2TagsEvents, String>();
        map.put(Txt2TagsEvents.CHAR_LT, "&lt;");
        map.put(Txt2TagsEvents.CHAR_GT, "&gt;");
        map.put(Txt2TagsEvents.CHAR_AMP, "&amp;");
        map.put(Txt2TagsEvents.CHAR_PERCENT, "&#37;");
        map.put(Txt2TagsEvents.UNORDERED_LIST_OPEN, "<ul>");
        map.put(Txt2TagsEvents.UNORDERED_LIST_CLOSE, "</ul>");
        map.put(Txt2TagsEvents.LIST_ITEM, "<li>$2</li>");
        map.put(Txt2TagsEvents.HEADING1, "<h1>$2<a id='$5' /></h1>");
        map.put(Txt2TagsEvents.HEADING2, "<h2>$2<a id='$5' /></h2>");
        map.put(Txt2TagsEvents.HEADING3, "<h3>$2<a id='$5' /></h3>");
        map.put(Txt2TagsEvents.HEADING4, "<h4>$2<a id='$5' /></h4>");
        map.put(Txt2TagsEvents.HEADING5, "<h5>$2<a id='$5' /></h5>");
        map.put(Txt2TagsEvents.BAR, "<hr />");
        map.put(Txt2TagsEvents.VERBATIM_OPEN, "<pre>");
        map.put(Txt2TagsEvents.VERBATIM_CLOSE, "</pre>");
        map.put(Txt2TagsEvents.BOLD, "<b>$2</b>");
        map.put(Txt2TagsEvents.ITALIC, "<i>$2</i>");
        map.put(Txt2TagsEvents.UNDERLINE, "<u>$2</u>");
        map.put(Txt2TagsEvents.URL, "<a href=\"$1\">$1</a>");
        map.put(Txt2TagsEvents.MONOSPACE, "<tt>$2</tt>");
        map.put(Txt2TagsEvents.LINEBREAK, "<br />");
        map.put(Txt2TagsEvents.DOCUMENT_START, "<html>");
        map.put(Txt2TagsEvents.DOCUMENT_END, "</html>");
        map.put(Txt2TagsEvents.IMAGE, "<img src=\"$1.$2\" alt=\"$1.$2\" />");
    }
    
    private final Map<Txt2TagsEvents, String> map;

}
