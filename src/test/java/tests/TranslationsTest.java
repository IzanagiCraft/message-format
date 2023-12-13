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

package tests;


import com.izanagicraft.messages.Translations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * message-format; tests:TranslationTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 13.12.2023
 */
public class TranslationsTest {

    @BeforeAll
    static void initTranslations() {
        // Load the test language file
        File testFile = new File("src/test/resources/lang.properties");

        // Check if the test file exists
        assertTrue(testFile.exists(), "Test file does not exist: " + testFile.getPath());

        // Initialize translations with the test language file
        Map<String, Object> defaultReplacements = createDefaultReplacements();
        Translations.init(defaultReplacements, testFile);
    }

    private static Map<String, Object> createDefaultReplacements() {
        // Create and return default replacements
        Map<String, Object> defaultReplacements = new HashMap<>();
        defaultReplacements.put("prefix", "[PREFIX]");
        return defaultReplacements;
    }

    @Test
    void testInit() {
        // Ensure translations are initialized
        assertNotNull(Translations.getTranslations());
        assertFalse(Translations.getTranslations().isEmpty());

        // Ensure fallback is set
        assertNotNull(Translations.getFallback());
    }

    @Test
    void testTranslateGreeting() {
        // Test translating the 'greeting' key with a placeholder
        String translated = Translations.translate("greeting", "John");
        assertEquals("[PREFIX] Hello, John!", translated);
    }

    @Test
    void testTranslateGreetingWithLocale() {
        // Test translating the 'greeting' key with a specific locale
        String translated = Translations.translate(Locale.US, "greeting", "Alice");
        assertEquals("[PREFIX] Hello, Alice!", translated);
    }

    @Test
    void testTranslateIterator() {
        // Test translating the 'iterator' key with a numeric placeholder
        for (int i = 0; i < 5; i++) {
            int placeholderValue = 42;
            String translated = Translations.translate("iterator", placeholderValue);
            String expected = "[PREFIX] Current Iteration Index " + placeholderValue + ".";
            assertEquals(expected, translated);
        }
    }

    @Test
    void testTranslateIteratorWithLocale() {
        Locale locale = Locale.US;
        // Test translating the 'iterator' key with a specific locale and numeric placeholder
        for (int i = 0; i < 5; i++) {
            int placeholderValue = i;
            String translated = Translations.translate(locale, "iterator", placeholderValue);
            String expected = "[PREFIX] Current Iteration Index " + placeholderValue + ".";
            assertEquals(expected, translated);
        }
    }

    @Test
    void testTranslateWithMissingKey() {
        // Test translating a key that does not exist in the language file
        String translated = Translations.translate("nonexistent.key", "arg1", "arg2");
        assertEquals("nonexistent.key", translated);
    }

    @Test
    void testTranslateWithDefaultReplacements() {
        // Test translating with default replacements
        String translated = Translations.translate("greeting");
        assertEquals("[PREFIX] Hello, null!", translated);
    }

    @Test
    void testTranslateWithInvalidLocale() {
        // Test translating with an invalid locale, should fall back to default
        String translated = Translations.translate(new Locale("invalid"), "greeting", "Bob");
        assertEquals("[PREFIX] Hello, Bob!", translated);
    }

}
