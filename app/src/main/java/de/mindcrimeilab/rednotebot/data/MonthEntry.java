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

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class represents a entry for a month containing a list of {@code DayEntry} objects each for every annotated day in
 * the month.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class MonthEntry {
    /** using a sorted set in order to have the day entries always in the correct order */
    private final SortedSet<DayEntry> dayEntries;
    
    /**
     * ctor()
     * construct a new month entry
     */
    public MonthEntry() {
        super();
        this.dayEntries = new TreeSet<DayEntry>();
    }

    /**
     * Adds a new day entry to the month.
     * @param dayEntry
     * @return true if the the month entry was modified otherwise false
     * @see Set.add()
     */
    public boolean add(final DayEntry dayEntry) {
        return this.dayEntries.add(dayEntry);
    }
}
