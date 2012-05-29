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

import java.util.Map;

/**
 * Common interface to be implemented by classes which provide replacements for certain txt2tags events.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 * @param <T>
 *            data type used to represent the replacement information
 */
public interface Txt2TagsReplacements<T> {

    /**
     * Returns a map containing the event emitted by the parser as key and the assigned replacement of type {@code T} as
     * value.
     * 
     * @return never returns {@code null}
     */
    Map<Txt2TagsEvents, T> getReplacements();

    /**
     * Returns the replacement for given event or {@code null} if there is no replacement for given event.
     * 
     * @param event
     * @return
     */
    T get(Txt2TagsEvents event);
}
