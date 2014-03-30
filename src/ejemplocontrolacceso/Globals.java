/*
 * Copyright (C) 2014 Javier
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ejemplocontrolacceso;

import java.util.ResourceBundle;

/**
 *
 * @author Javier
 */
public class Globals {
    
    public static final String PROPERTIES_FILE_NAME = "app.properties";
    public static final String PROPERTY_KEY_LANGUAGE = "language";
    public static final String PROPERTY_KEY_PASSWORD = "password";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String[] LANGUAGES = {"en", "es"};
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final boolean PASSWORD_REQUIRE_DIGIT = true;
    public static final boolean PASSWORD_REQUIRE_LOWERCASE = true;
    public static final boolean PASSWORD_REQUIRE_UPPERCASE = true;
    public static final boolean PASSWORD_REQUIRE_SPECIALCHAR = true;
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("res/strings");    
    
}
