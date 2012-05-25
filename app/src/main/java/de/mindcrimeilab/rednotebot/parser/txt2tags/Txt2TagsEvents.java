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

/**
 * This enumeration defines all events which could be emitted during the parsing process of a txt2tag formated input.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum Txt2TagsEvents {
    //@formatter:off
    /** indicates the start of a document */
    DOCUMENT_START, 
    /** indicates the end of a document */
    DOCUMENT_END, 
    /** character '<'; needed for HTML quoting*/
    CHAR_LT, 
    /** character '>'; needed for HTML quoting */
    CHAR_GT, 
    /** character '&'; needed for HTML quoting*/
    CHAR_AMP, 
    /** character '%'; needed for HTML quoting */
    CHAR_PERCENT, 
    /** start of an unordered list marked by '*' */
    UNORDERED_LIST_OPEN, 
    /** end of an unordered list. list is closed by at least two line breaks */
    UNORDERED_LIST_CLOSE, 
    /** start of an ordered list */
    LIST_OPEN, 
    /** end of an ordered list. list is closed by at least two line breaks */
    LIST_CLOSE, 
    /** item in an ordered / unordered list */
    LIST_ITEM, 
    /** marker for a level one heading; single line surrounded by '=' */
    HEADING1,
    /** marker for a level one heading; single line surrounded by '==' */
    HEADING2, 
    /** marker for a level one heading; single line surrounded by '===' */
    HEADING3, 
    /** marker for a level one heading; single line surrounded by '====' */
    HEADING4, 
    /** marker for a level one heading; single line surrounded by '=====' */
    HEADING5, 
    /** horizontal line bar; single line having a sequence of at least 20 '=' or '-'chars */
    BAR, 
    /** start of a verbatim section '```' */
    VERBATIM_OPEN, 
    /** end of a verbatim section '```' */
    VERBATIM_CLOSE, 
    /** marker for a bold text; string surrounded by '**' */
    BOLD, 
    /** marker for an italic text; string surrounded by '//' */
    ITALIC, 
    /** marker for an underlinded text; string surrounded by '__' */
    UNDERLINE, 
    /** marker for an URL formatted string */
    URL, 
    /** marker for a monospaced text; string surrounded by '``' */
    MONOSPACE, 
    /** line break '\n','\r\n' or '\r' */
    LINEBREAK, 
    /** inclusion of an image '["" */
    IMAGE;
    //@formatter:on
}
