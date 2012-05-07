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
package de.mindcrimeilab.rednotebot;

/**
 * Enumeration grouping all used preference keys used in the application.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum PreferenceKeys {
    // @formatter:off
    /** Data provider in use */
    DATAPROVIDER("dataProvider"),
    
    /** Data / journal director */
    DIRECTORY("directory"),
    
    /** Dropbox switch */
    ENABLE_DROPBOX("enableDropbox"),
    
    /** Dropbox session key */
    DROPBOX_KEY("dropboxKey"),
    
    /** Dropbox session secret */
    DROPBOX_SECRET("dropboxSecret");
    // @formatter:on
    private PreferenceKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    private final String key;
}
