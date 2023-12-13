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

package com.izanagicraft.messages.translations;

import com.izanagicraft.messages.placeholders.StaticMessagePlaceholders;
import com.izanagicraft.messages.strings.WrappedString;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * message-format; com.izanagicraft.messages.translations:GlobalTranslations
 * <p>
 * Utility class for handling translations with placeholders.
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * GlobalTranslations.init(new File("en_US.properties"), new File("es_ES.properties"));
 * String translatedText = GlobalTranslations.translate("greeting", "John");
 * }
 * </pre>
 * <p>
 * This class provides static methods to interact with the TranslationHandler,
 * which manages translations for different locales with support for placeholders.
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 13.12.2023
 */
public final class GlobalTranslations {

    private static TranslationHandler translationHandler = new TranslationHandler();

    // instantiation prevention
    private GlobalTranslations() {
    }

    /**
     * Gets the TranslationHandler instance.
     *
     * @return The TranslationHandler instance.
     */
    public static TranslationHandler getTranslationHandler() {
        return translationHandler;
    }

    /**
     * Gets the map of translations for different locales.
     *
     * @return The map of translations with locale codes as keys and Properties objects as values.
     */
    public static Map<String, Properties> getTranslations() {
        return translationHandler.getTranslations();
    }

    /**
     * Gets the fallback Properties object.
     *
     * @return The fallback Properties object.
     */
    public static Properties getFallback() {
        return translationHandler.getFallback();
    }

    /**
     * Gets the default replacements used by the Formatter for placeholder substitution.
     *
     * @return A map of default replacements with String keys and Object values.
     * These replacements are used by the {@link StaticMessagePlaceholders} class during text formatting.
     */
    public static Map<String, Object> getDefaultReplacements() {
        return translationHandler.getDefaultReplacements();
    }

    /**
     * Load language properties from a file and process them.
     *
     * @param properties The properties object to load into.
     * @param file       The file to load properties from.
     */
    private static void loadLang(Properties properties, File file) {
        translationHandler.loadLang(properties, file);
    }

    /**
     * Initialize the translations with default replacements and language files.
     *
     * @param files Language properties files to load.
     */
    public static void init(File... files) {
        translationHandler.init(files);
    }

    /**
     * Initialize the translations with default replacements and language files.
     *
     * @param defaultReplacements Default replacement map for placeholders.
     * @param files               Language properties files to load.
     */
    public static void init(Map<String, Object> defaultReplacements, File... files) {
        translationHandler.init(defaultReplacements, files);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key  The translation key.
     * @param args Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(String key, Object... args) {
        return translationHandler.translate(key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key  The translation key.
     * @param args Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(String key, String... args) {
        return translationHandler.translate(key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key The translation key.
     * @return Translated and formatted text.
     */
    public static String translate(String key) {
        return translationHandler.translate(key);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @param args   Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(Locale locale, String key, Object... args) {
        return translationHandler.translate(locale, key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @param args   Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(Locale locale, String key, String... args) {
        return translationHandler.translate(locale, key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @return Translated and formatted text.
     */
    public static String translate(Locale locale, String key) {
        return translationHandler.translate(locale, key);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @param args     Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(WrappedString langName, String key, Object... args) {
        return translationHandler.translate(langName, key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @param args     Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public static String translate(WrappedString langName, String key, String... args) {
        return translationHandler.translate(langName, key, args);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @return Translated and formatted text.
     */
    public static String translate(WrappedString langName, String key) {
        return translationHandler.translate(langName, key);
    }

}
