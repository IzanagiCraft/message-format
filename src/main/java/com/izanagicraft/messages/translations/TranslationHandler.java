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

import com.izanagicraft.messages.placeholders.MessagePlaceholderHandler;
import com.izanagicraft.messages.placeholders.StaticMessagePlaceholders;
import com.izanagicraft.messages.strings.WrappedString;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * message-format; com.izanagicraft.messages.translations:TranslationHandler
 * <p>
 * Utility class for handling translations with placeholders.
 * <p>
 * The {@code TranslationHandler} class provides methods to manage translations
 * for different locales with support for placeholders.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * TranslationHandler translations = new TranslationHandler();
 * translations.init(new File("en_US.properties"), new File("es_ES.properties"));
 * String translatedText = translations.translate("greeting", "John");
 * }
 * </pre>
 * </p>
 * <p>
 * Note: The class follows the singleton pattern, and instances should
 * be created using the provided constructors or by calling the {@code init} method.
 * </p>
 * <p>
 * This class is not meant to be instantiated directly; instead, use the provided
 * constructors or the {@code init} method to initialize the translations.
 * </p>
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 13.12.2023
 */
public class TranslationHandler {

    private MessagePlaceholderHandler placeholderHandler = new MessagePlaceholderHandler();

    /**
     * A map that stores translations for different locales.
     * The keys are locale codes, and the values are Properties objects containing translations.
     */
    private Map<String, Properties> translations = new ConcurrentHashMap<>();
    /**
     * The fallback Properties object used when a translation is not available for a specific locale.
     */
    private Properties fallback;

    /**
     * Default constructor for the Translations class.
     * <p>
     * Creates an instance of the Translations class without initializing translations.
     * To initialize translations, use the {@code init} method.
     * </p>
     */
    public TranslationHandler() {
        // Empty constructor
    }

    /**
     * Constructor for the Translations class with language properties files.
     * <p>
     * Creates an instance of the Translations class and initializes translations
     * using the provided language properties files.
     * </p>
     *
     * @param files Language properties files to load.
     */
    public TranslationHandler(File... files) {
        init(null, files);
    }


    /**
     * Constructor for the Translations class with default replacements and language properties files.
     * <p>
     * Creates an instance of the Translations class and initializes translations
     * using the provided default replacements and language properties files.
     * </p>
     *
     * @param defaultReplacements Default replacement map for placeholders.
     * @param files               Language properties files to load.
     */
    public TranslationHandler(Map<String, Object> defaultReplacements, File... files) {
        init(defaultReplacements, files);
    }


    /**
     * Gets the map of translations for different locales.
     *
     * @return The map of translations with locale codes as keys and Properties objects as values.
     */
    public Map<String, Properties> getTranslations() {
        return translations;
    }

    /**
     * Gets the fallback Properties object.
     *
     * @return The fallback Properties object.
     */
    public Properties getFallback() {
        return fallback;
    }

    /**
     * Gets the default replacements used by the Formatter for placeholder substitution.
     *
     * @return A map of default replacements with String keys and Object values.
     * These replacements are used by the {@link StaticMessagePlaceholders} class during text formatting.
     */
    public Map<String, Object> getDefaultReplacements() {
        return StaticMessagePlaceholders.getDefaultReplacements();
    }

    /**
     * Load language properties from a file and process them.
     *
     * @param properties The properties object to load into.
     * @param file       The file to load properties from.
     */
    void loadLang(Properties properties, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            // Iterate through the loaded properties and modify the values as needed
            for (String propName : properties.stringPropertyNames()) {
                String propValue = properties.getProperty(propName);
                if (!propValue.startsWith("'")) continue;
                // Remove single quotes from the property value
                propValue = propValue.replace("'", "");
                properties.setProperty(propName, propValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the translations with default replacements and language files.
     *
     * @param files Language properties files to load.
     */
    public void init(File... files) {
        init(null, files);
    }

    /**
     * Initialize the translations with default replacements and language files.
     *
     * @param defaultReplacements Default replacement map for placeholders.
     * @param files               Language properties files to load.
     */
    public void init(Map<String, Object> defaultReplacements, File... files) {
        StaticMessagePlaceholders.addDefaultReplacements(defaultReplacements);

        // Load each language properties file
        for (File file : files) {
            // Skip directories, only process individual files
            if (file.isDirectory()) continue;

            // Get the name of the current file
            String fileName = file.getName();

            if (fileName.contains("platform")) continue;

            // Create a new properties object to store language data
            Properties properties = new Properties();

            // Extract the language name from the file name
            String langName = fileName.substring(0, fileName.lastIndexOf('.'));

            // Check if the file is a language properties file and not a platform-specific one
            if (fileName.endsWith(".properties")) {
                // Load language properties from the file
                loadLang(properties, file);
            }
            // If the file is not a valid language properties file, continue to the next iteration
            else continue;

            // Add the loaded language properties to the translations map
            translations.put(langName, properties);

            if (fallback == null) {
                fallback = new Properties();
                fallback = properties;
            }
        }

        // Set the default fallback properties based on the system's default locale
        if (translations.containsKey(Locale.getDefault().getLanguage())) {
            fallback = translations.get(Locale.getDefault().getLanguage());
        }
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key  The translation key.
     * @param args Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(String key, Object... args) {
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(fallback.getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key  The translation key.
     * @param args Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(String key, String... args) {
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(fallback.getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param key The translation key.
     * @return Translated and formatted text.
     */
    public String translate(String key) {
        return placeholderHandler.fastFormat(fallback.getProperty(key, key), getDefaultReplacements());
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @param args   Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(Locale locale, String key, Object... args) {
        if (!translations.containsKey(locale.getLanguage())) return translate(key, args);
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(translations.get(locale.getLanguage()).getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @param args   Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(Locale locale, String key, String... args) {
        if (!translations.containsKey(locale.getLanguage())) return translate(key, args);
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(translations.get(locale.getLanguage()).getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param locale The locale to translate in.
     * @param key    The translation key.
     * @return Translated and formatted text.
     */
    public String translate(Locale locale, String key) {
        if (!translations.containsKey(locale.getLanguage())) return translate(key);
        return placeholderHandler.fastFormat(translations.get(locale.getLanguage()).getProperty(key, key), getDefaultReplacements());
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @param args     Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(WrappedString langName, String key, Object... args) {
        if (!translations.containsKey(langName)) return translate(key, args);
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(translations.get(langName).getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @param args     Arguments for placeholders.
     * @return Translated and formatted text.
     */
    public String translate(WrappedString langName, String key, String... args) {
        if (!translations.containsKey(langName)) return translate(key, args);
        Map<String, Object> replace = new HashMap<>(getDefaultReplacements());
        for (int i = 0; i < args.length; i++) {
            replace.put("" + i, args[i]);
        }
        return placeholderHandler.fastFormat(translations.get(langName).getProperty(key, key), replace);
    }

    /**
     * Translate a key using default replacements and fallback properties.
     *
     * @param langName The language name to translate in.
     * @param key      The translation key.
     * @return Translated and formatted text.
     */
    public String translate(WrappedString langName, String key) {
        if (!translations.containsKey(langName)) return translate(key);
        return placeholderHandler.fastFormat(translations.get(langName).getProperty(key, key), getDefaultReplacements());
    }

    /**
     * Gets the MessagePlaceholderHandler instance used for managing placeholders in message formatting.
     *
     * @return The MessagePlaceholderHandler instance used for managing placeholders.
     */
    public MessagePlaceholderHandler getPlaceholderHandler() {
        return placeholderHandler;
    }
}
