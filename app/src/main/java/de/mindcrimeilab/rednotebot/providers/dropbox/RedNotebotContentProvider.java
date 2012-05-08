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
package de.mindcrimeilab.rednotebot.providers.dropbox;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Content provider to access various sources of journal entries.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class RedNotebotContentProvider extends ContentProvider implements OnSharedPreferenceChangeListener {

    /** URI matcher */
    private final static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    
    /** constant for month uris */
    private final static int MONTH = 1;

    /** constant for day uris */
    private final static int DAY = 2;
    
    /**
     * static initialization of URI matcher
     */
    static {
        URI_MATCHER.addURI("de.mindcrimeilab.rednotebot", "month", MONTH);
        URI_MATCHER.addURI("de.mindcrimeilab.rednotebot", "day", DAY);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.
     * SharedPreferences, java.lang.String)
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#getType(android.net.Uri)
     */
    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#onCreate()
     */
    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String,
     * java.lang.String[], java.lang.String)
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String,
     * java.lang.String[])
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
