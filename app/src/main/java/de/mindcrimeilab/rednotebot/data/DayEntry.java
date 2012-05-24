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
package de.mindcrimeilab.rednotebot.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Class represents an entry for a certain day containing the content of the entry as well as the associated tags. It
 * will also provide some utility methods to ease access to data parts.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class DayEntry implements Comparable<DayEntry>{
    /** day of month*/
    private int day;
    /** associated tags */
    private Set<String> tags;
    /** text content of entry */
    private String content;
    
    /**
     * ctor()
     * Constructs a new day entry for a given day in month.
     * @param day day in month the entry is created for.
     */
    
    public DayEntry(int day) {
        this.day = day;
        this.tags = new HashSet<String>();
        this.content = null;
    }
    
    /**
     * Sets the content of the entry.
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Returns content of enty
     * @return
     */
    public String getContent() {
        return content;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(DayEntry another) {
        final int ret;
        if(null == another) {
            ret = 1;
        }
        else {
            ret = this.day - another.day;
        }
        return ret;
    }
    
    
}
