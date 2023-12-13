/*
 * Copyright (c) 2023 - present | sanguine6660 <sanguine6660@gmail.com>
 * Copyright (c) 2023 - present | izanagicraft.com <contact@izanagicraft.com>
 * Copyright (c) 2023 - present | izanagicraft.com team and contributors
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.izanagicraft.messages;

/**
 * message-format; com.izanagicraft.messages:WrappedString
 * <p>
 * The WrappedString class represents a simple wrapper around a String value.
 * It provides methods to create, access, and modify the underlying String.
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 13.12.2023
 */
public class WrappedString {

    /**
     * The wrapped String value.
     */
    private String value;

    /**
     * Constructs a new WrappedString object with the specified String value.
     *
     * @param value The String value to be wrapped.
     */
    public WrappedString(String value) {
        this.value = value;
    }

    /**
     * Creates a new WrappedString object with the specified String value.
     *
     * @param str The String value to be wrapped.
     * @return A new WrappedString object wrapping the given String value.
     */
    public static WrappedString of(String str) {
        return new WrappedString(str);
    }

    /**
     * Returns the String representation of this WrappedString.
     *
     * @return The String value of this WrappedString.
     */
    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Gets the current String value of this WrappedString.
     *
     * @return The current String value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets a new String value for this WrappedString.
     *
     * @param value The new String value to be set.
     */
    public void setValue(String value) {
        this.value = value;
    }

}
