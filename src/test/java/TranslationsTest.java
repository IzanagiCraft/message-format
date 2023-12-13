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

import com.izanagicraft.messages.StaticMessagePlaceholders;
import com.izanagicraft.messages.Translations;
import com.izanagicraft.messages.WrappedString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * message-format; PACKAGE_NAME:TranslationTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 13.12.2023
 */
public class TranslationsTest {

    // Constants for test data
    private static final String TEST_LANG_NAME = "testLang";
    private static final String TEST_KEY = "greeting";
    private static final String TEST_VALUE = "Hello, ${0}!";

    // Initialization before running tests
    @BeforeAll
    static void initTranslations() {
        // Set up default replacements
        Map<String, Object> defaultReplacements = new HashMap<>();
        defaultReplacements.put("0", "World");

        // Load the test language file
        File testFile = new File("src/test/resources/testLang.properties");

        // Check if the test file exists
        assertTrue(testFile.exists(), "Test file does not exist: " + testFile.getPath());

        // Initialize translations with the test language file
        Translations.init(defaultReplacements, testFile);
    }

    // Test translation with a single argument
    @Test
    void translateWithArgs() {
        String translated = Translations.translate(WrappedString.of(TEST_LANG_NAME), TEST_KEY, "John");
        assertEquals("Hello, John!", translated, "Translation with single argument failed.");
    }

    // Test translation with an array of arguments
    @Test
    void translateWithArrayArgs() {
        String translated = Translations.translate(WrappedString.of(TEST_LANG_NAME), TEST_KEY, new String[]{"John"});
        assertEquals("Hello, John!", translated, "Translation with array argument failed.");
    }

    // Test translation without arguments
    @Test
    void translateWithoutArgs() {
        String translated = Translations.translate(WrappedString.of(TEST_LANG_NAME), TEST_KEY);
        assertEquals(TEST_VALUE.replace("${0}", "World"), translated, "Translation without arguments failed.");
    }

    // Test translation of a non-existent key
    @Test
    void translateNonExistentKey() {
        String nonExistentKey = "nonExistentKey";
        String translated = Translations.translate(WrappedString.of(TEST_LANG_NAME), nonExistentKey);
        assertEquals(nonExistentKey, translated, "Translation of non-existent key should return the key itself.");
    }

    // Test translation with locale fallback
    @Test
    void translateWithLocaleFallback() {
        // Ensure the test language file is not available to force fallback
        Translations.getTranslations().remove(TEST_LANG_NAME);

        // Perform translation with locale fallback
        String translated = Translations.translate(WrappedString.of(TEST_LANG_NAME), TEST_KEY);
        String fallbackTranslation = Translations.translate(TEST_KEY);
        assertEquals(fallbackTranslation, translated, "Translation with locale fallback failed.");
    }

    // Test translation with an invalid locale
    @Test
    void translateWithInvalidLocale() {
        WrappedString invalidLangName = WrappedString.of("invalidLang");
        String translated = Translations.translate(invalidLangName, TEST_KEY);
        assertEquals(TEST_VALUE.replace("${0}", "World"), translated, "Translation with invalid locale should use default.");
    }

    // Test fast formatting with placeholders
    @Test
    void fastFormatWithPlaceholders() {
        // Set up replacements for placeholders
        Map<String, Object> replacements = new HashMap<>();
        replacements.put("0", "John");
        replacements.put("1", "Doe");

        // Perform fast formatting with placeholders
        String formatted = StaticMessagePlaceholders.fastFormat("Hello, ${0} ${1}!", replacements);
        assertEquals("Hello, John Doe!", formatted, "Fast format with placeholders failed.");
    }

    // Test fast formatting without placeholders
    @Test
    void fastFormatWithoutPlaceholders() {
        String input = "Hello, World!";
        String formatted = StaticMessagePlaceholders.fastFormat(input, new HashMap<>());
        assertEquals(input, formatted, "Fast format without placeholders should return the input.");
    }

}
