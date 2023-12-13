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

package com.izanagicraft.messages.placeholders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * message-format; com.izanagicraft.messages.placeholders:MessagePlaceholderHandler
 * <p>
 * A utility class for handling placeholders in messages.
 * Manages default replacements and provides methods for fast formatting with placeholders.
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 13.12.2023
 */
public class MessagePlaceholderHandler {

    private Map<String, Object> defaultReplacements;

    /**
     * Constructs a new MessagePlaceholderHandler with an empty set of default replacements.
     */
    public MessagePlaceholderHandler() {
        this.defaultReplacements = new ConcurrentHashMap<>();
    }

    /**
     * Fast format a string with defaultReplacement placeholders.
     *
     * @param format The format string with placeholders
     * @return Formatted string replaced with {@link #getDefaultReplacements()}
     */
    public String fastFormat(String format) {
        return fastFormat(format, Map.of());
    }

    /**
     * Fast format a string with given values for placeholders.
     *
     * @param format The format string with placeholders.
     * @param values Values to replace placeholders.
     * @return Formatted string.
     */
    public String fastFormat(String format, Map<String, Object> values) {
        // Create a copy of the default replacements to avoid modifying the original map
        Map<String, Object> replacements = new HashMap<>(getDefaultReplacements());

        // Add values to the replacements map, but only for keys that do not already exist
        values.forEach((key, value) -> replacements.putIfAbsent(key, value));

        // Create a StringBuilder to modify the format string
        StringBuilder formatter = new StringBuilder(format);

        // Create a list to store the replacement values
        List<Object> valueList = new ArrayList<>();

        // Create a matcher to find placeholders in the format string
        Matcher matcher = Pattern.compile("\\$\\{(\\w+)}").matcher(format);

        // Iterate through the format string and find placeholders
        while (matcher.find()) {
            // Extract the placeholder key from the match
            String key = matcher.group(1);

            // Create the format key in the format "${key}"
            String formatKey = String.format("${%s}", key);

            // Find the index of the format key in the formatter
            int index = formatter.indexOf(formatKey);

            // If the format key is found
            if (index != -1) {
                // Replace the format key with "%s" for formatting
                formatter.replace(index, index + formatKey.length(), "%s");

                // Add the corresponding value to the value list
                valueList.add(values.get(key));
            }
        }

        // Use String.format to replace placeholders with values and return the formatted string
        return String.format(formatter.toString(), valueList.toArray());
    }

    /**
     * Add or update default replacements for placeholders.
     *
     * @param additionalReplacements The additional replacements to add or update.
     */
    public void addDefaultReplacements(Map<String, Object> additionalReplacements) {
        if (additionalReplacements != null) {
            // Update the default replacements with the additional replacements
            this.defaultReplacements.putAll(additionalReplacements);
        }
    }

    /**
     * Get the default replacements for placeholders.
     *
     * @return The default replacements.
     */
    public Map<String, Object> getDefaultReplacements() {
        return this.defaultReplacements;
    }

    /**
     * Set the default replacements for placeholders.
     *
     * @param defaultReplacements The default replacements to set.
     */
    public void setDefaultReplacements(Map<String, Object> defaultReplacements) {
        if (defaultReplacements != null) {
            // Set the default replacements for placeholders
            this.defaultReplacements = new ConcurrentHashMap<>(defaultReplacements);
        } else {
            this.defaultReplacements = new ConcurrentHashMap<>();
        }
    }
}
