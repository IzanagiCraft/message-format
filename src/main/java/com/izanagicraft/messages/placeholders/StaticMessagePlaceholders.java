/*
 * ▪  ·▄▄▄▄• ▄▄▄·  ▐ ▄  ▄▄▄·  ▄▄ • ▪   ▄▄· ▄▄▄   ▄▄▄· ·▄▄▄▄▄▄▄▄
 * ██ ▪▀·.█▌▐█ ▀█ •█▌▐█▐█ ▀█ ▐█ ▀ ▪██ ▐█ ▌▪▀▄ █·▐█ ▀█ ▐▄▄·•██
 * ▐█·▄█▀▀▀•▄█▀▀█ ▐█▐▐▌▄█▀▀█ ▄█ ▀█▄▐█·██ ▄▄▐▀▀▄ ▄█▀▀█ ██▪  ▐█.▪
 * ▐█▌█▌▪▄█▀▐█ ▪▐▌██▐█▌▐█ ▪▐▌▐█▄▪▐█▐█▌▐███▌▐█•█▌▐█ ▪▐▌██▌. ▐█▌·
 * ▀▀▀·▀▀▀ • ▀  ▀ ▀▀ █▪ ▀  ▀ ·▀▀▀▀ ▀▀▀·▀▀▀ .▀  ▀ ▀  ▀ ▀▀▀  ▀▀▀
 *
 *
 *    @@@@@
 *    @@* *@@
 *      @@@  @@@
 *         @@@  @@ @@@       @@@@@@@@@@@
 *           @@@@@@@@   @@@@@@@@@@@@@@@@@@@@@
 *            @@@    @@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *               #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *               #@@@   @@                 @@  @@@@  @@@@
 *                @@@@      @@@      @@@@      @@@@   @@@
 *                @@@@@@                     @@@@@@    @@
 *                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                   @@@@@@@@@@@@@@@@@@@@@@@@@@@
 *                     @@@@@@@@@@@@@@@@@@@@@@@
 *                       @@@@@@@@@@@@@@@@@@@
 *                           @@@@@@@@@@@
 *
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

package com.izanagicraft.messages.placeholders;

import java.util.Map;

/**
 * message-format; com.izanagicraft.messages.placeholders:StaticMessagePlaceholders
 * <p>
 * Utility class for handling placeholders in messages.
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 13.12.2023
 */
public class StaticMessagePlaceholders {

    private static MessagePlaceholderHandler placeholderHandler = new MessagePlaceholderHandler();

    // instantiation prevention
    private StaticMessagePlaceholders() {
    }

    /**
     * Fast format a string with defaultReplacement placeholders.
     *
     * @param format The format string with placeholders
     * @return Formatted string replaced with {@link StaticMessagePlaceholders#getDefaultReplacements()}
     */
    public static String fastFormat(String format) {
        return placeholderHandler.fastFormat(format);
    }

    /**
     * Fast format a string with given values for placeholders.
     *
     * @param format The format string with placeholders.
     * @param values Values to replace placeholders.
     * @return Formatted string.
     */
    public static String fastFormat(String format, Map<String, Object> values) {
        return placeholderHandler.fastFormat(format, values);
    }

    /**
     * Add or update default replacements for placeholders.
     *
     * @param additionalReplacements The additional replacements to add or update.
     */
    public static void addDefaultReplacements(Map<String, Object> additionalReplacements) {
        placeholderHandler.addDefaultReplacements(additionalReplacements);
    }

    /**
     * Get the default replacements for placeholders.
     *
     * @return The default replacements.
     */
    public static Map<String, Object> getDefaultReplacements() {
        return placeholderHandler.getDefaultReplacements();
    }

    /**
     * Set the default replacements for placeholders.
     *
     * @param defaultReplacements The default replacements to set.
     */
    public static void setDefaultReplacements(Map<String, Object> defaultReplacements) {
        placeholderHandler.setDefaultReplacements(defaultReplacements);
    }

}
