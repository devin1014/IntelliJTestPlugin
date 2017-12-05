package selectorgenerator;/*
 * Copyright 2013 Inmite s.r.o. (www.inmite.eu).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * selector generator. Constants for various stuff used in whole plugin.
 *
 * @author David Vávra (david@inmite.eu)
 */
class Constants
{
    static final String NORMAL = "_normal";
    private static final String FOCUSED = "_focused";
    private static final String PRESSED = "_pressed";
    private static final String SELECTED = "_selected";
    private static final String CHECKED = "_checked";
    private static final String DISABLED = "_disabled";
    private static final String HOVERED = "_hovered";
    private static final String CHECKABLE = "_checkable";
    private static final String ACTIVATED = "_activated";
    private static final String WINDOW_FOCUSED = "_window_focused";
    static String[] SUFFIXES = new String[]{NORMAL, PRESSED, FOCUSED, SELECTED, CHECKED, DISABLED, HOVERED, CHECKABLE, ACTIVATED, WINDOW_FOCUSED};
    static Pattern VALID_FOLDER_PATTERN = Pattern.compile("^drawable(-[a-zA-Z0-9]+)*$");
    static String EXPORT_FOLDER = "drawable";
    static HashMap<String, State> sMapping;

    static
    {
        // mapping from file suffixes into android attributes and their default values
        sMapping = new HashMap<>();
        sMapping.put(FOCUSED, new State("state_focused", false));
        sMapping.put(PRESSED, new State("state_pressed", false));
        sMapping.put(SELECTED, new State("state_selected", false));
        sMapping.put(CHECKED, new State("state_checked", false));
        sMapping.put(DISABLED, new State("state_enabled", true));
        sMapping.put(HOVERED, new State("state_hovered", false));
        sMapping.put(CHECKABLE, new State("state_checkable", false));
        sMapping.put(ACTIVATED, new State("state_activated", false));
        sMapping.put(WINDOW_FOCUSED, new State("state_window_focused", false));
    }

    static class State
    {
        String attributeName;

        boolean defaultValue;

        State(String attributeName, boolean defaultValue)
        {
            this.attributeName = attributeName;

            this.defaultValue = defaultValue;
        }
    }

}
