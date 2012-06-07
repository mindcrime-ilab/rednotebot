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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import de.akquinet.android.androlog.Log;

/**
 * This is the main activity of the Rednotebot application.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class RedNotebotActivity extends FragmentActivity {

    /*
     * (non-Javadoc)
     * 
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize logging
        Log.init(getApplicationContext());
        Log.v(LogTags.APPLICATION, "Application starting...");

        // setup ui
        setContentView(R.layout.main);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about: {
                return false;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
