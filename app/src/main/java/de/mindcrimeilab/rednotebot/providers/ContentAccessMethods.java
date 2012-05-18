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
package de.mindcrimeilab.rednotebot.providers;

/**
 * Enumeration describing the various content access method e.g. Dropbox or local file storage.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum ContentAccessMethods {
    LOCAL_STORAGE, DROPBOX;

    /**
     * Converts a {@code String} to a {@code ContentAccessMethods} identifier. This method will default to {@value
     * ContentAccessMethods.LOCAL_STORAGE} if the parsing of the string will fail.
     * 
     * @param value
     * @return
     */
    static ContentAccessMethods from(final String value) {
        final ContentAccessMethods ret;
        if ("0".equals(value)) ret = LOCAL_STORAGE;
        else if ("1".equals(value)) ret = DROPBOX;
        else ret = LOCAL_STORAGE;
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.format("%s[%s]", ContentAccessMethods.class, name());
    }
}
