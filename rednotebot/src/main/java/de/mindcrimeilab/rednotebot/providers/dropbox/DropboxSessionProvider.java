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

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.Session.AccessType;

import de.mindcrimeilab.rednotebot.PreferenceKeys;

/**
 * Enumeration provides central (singleton) access to a Dropbox session.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public enum DropboxSessionProvider implements OnSharedPreferenceChangeListener {
    /** single instance of {@code DropboxSessionProvider} */
    INSTANCE;

    /* -------------------------------------------------------------- */
    /*
     * ADD YOUR API KEY AND SECRET HERE /* --------------------------------------------------------------
     */
    /** Dropbox API key */
    private final static String APP_KEY = "PUT_YOUR_KEY_HERE";

    /** Dropbix API secret */
    private final static String APP_SECRET = "PUT_YOUR_SECRET_HERE";

    /** set access to Dropbox to full access */
    private final static AccessType ACCESS_TYPE = AccessType.DROPBOX;

    /**
     * Get initialized instance of the official Dropbox API.
     * @return
     */
    public DropboxAPI<Session> getDropboxAccess() {
        if( null == dropboxAccess ) {
            init();
        }
        return this.dropboxAccess;
    }
    
    /**
     * Returns the current session.
     * @return initialized session
     */
    public Session getSession() {
        if( null == session) {
            init();
        }
        return session;
    }
    
    /**
     * Checks if there are all preconditions to establish a session successfully fulfilled.
     * @return true if all necessary preconditions fulfilled
     */
    public boolean isConfigured() {
        return TextUtils.isEmpty(sessionKey) || TextUtils.isEmpty(sessionSecret);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(android.content.
     * SharedPreferences, java.lang.String)
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String preferenceKey) {
        if (PreferenceKeys.DROPBOX_KEY.toString().equals(preferenceKey)) {
            sessionKey = sharedPreferences.getString(preferenceKey, null);
        }
        else if (PreferenceKeys.DROPBOX_SECRET.toString().equals(preferenceKey)) {
            sessionSecret = sharedPreferences.getString(preferenceKey, null);
        }
    }

    /* -------------------------------------------------------------- */
    private DropboxSessionProvider() {
        // nothing to do here for now
    }

    /** Key of established session */
    private String sessionKey;

    /** Secret of established session */
    private String sessionSecret;

    /** Reference to Dropbox provided API */
    private DropboxAPI<Session> dropboxAccess;

    /** Reference to Dropbox session */
    private Session session;

    /**
     * Initializes Dropbox session and API reference
     */
    private void init() {
        if (isConfigured()) {
            this.session = new AndroidAuthSession(new AppKeyPair(APP_KEY, APP_SECRET), ACCESS_TYPE, new AccessTokenPair(sessionKey, sessionSecret));
            this.dropboxAccess = new DropboxAPI<Session>(this.session);
        }
    }

}
