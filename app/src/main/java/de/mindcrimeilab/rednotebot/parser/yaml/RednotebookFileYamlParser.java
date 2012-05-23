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
package de.mindcrimeilab.rednotebot.parser.yaml;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import de.akquinet.android.androlog.Log;
import de.mindcrimeilab.rednotebot.LogTags;
import de.mindcrimeilab.rednotebot.data.DayEntry;
import de.mindcrimeilab.rednotebot.data.MonthEntry;


/**
 * Parser to read a month entry in the format of RedNotebook/RedNotebot turning the plain YAML description into a
 * structered object of type {@code MonthEntry}.
 * 
 * @author Michael Engelhardt <me@mindcrime-ilab.de>
 * 
 */
public class RednotebookFileYamlParser {

    /**
     * Parse YAML styled input stream to a {@code MonthEntry} object.
     * @param is - Inputstream to read data from
     * @return will never return {@code null}
     */
    public static MonthEntry parse(final InputStream is) {
        final MonthEntry monthEntry = new MonthEntry();
        
        //wrap input stream to optimize reading
        final BufferedInputStream bis = new BufferedInputStream(is);
        try {
            final Yaml yaml = new Yaml();
            // parser returns a map structure of yaml property and properties content
            final Map<Integer,Map<?,?>> map = (Map) yaml.load(bis);
            final Set<Map.Entry<Integer,Map<?,?>>> keySet = map.entrySet();
            for(Iterator<Map.Entry<Integer,Map<?,?>>> it = keySet.iterator();it.hasNext(); ) {
                final Map.Entry<Integer, Map<?,?>> entry = it.next();
                final DayEntry d = parse(entry);
                monthEntry.add(d);
                it.remove();
            }
        }
        catch(RuntimeException rt) {
            Log.e(LogTags.FILE_PARSER, String.format("Cannot parse or open file: %s.", rt.getMessage()),rt);
        }
        return monthEntry;
    }
    
    private static DayEntry parse(final Map.Entry<Integer,Map<?,?>> mapEntry) {
        final DayEntry dayEntry = new DayEntry(mapEntry.getKey());
        final Map<?,?> m = mapEntry.getValue();
        dayEntry.setContent((String) m.get("text"));
        return dayEntry;
    }
}
