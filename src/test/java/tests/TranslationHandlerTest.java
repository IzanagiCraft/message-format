package tests;

import com.izanagicraft.messages.translations.TranslationHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * message-format; tests:TranslationHandlerTest
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 04.01.2024
 */
public class TranslationHandlerTest {

    private static TranslationHandler translationHandler;

    @BeforeAll
    static void initTranslationHandler() {
        // Load the test language file
        File testFile = new File("src/test/resources/lang.properties");

        // Check if the test file exists
        assertTrue(testFile.exists(), "Test file does not exist: " + testFile.getPath());

        // Initialize TranslationHandler with the test language files
        translationHandler = new TranslationHandler(testFile);

        // Initialize translations with the test language file
        Map<String, Object> defaultReplacements = createDefaultReplacements();

        translationHandler.init(defaultReplacements, testFile);
    }

    private static Map<String, Object> createDefaultReplacements() {
        // Create and return default replacements
        Map<String, Object> defaultReplacements = new HashMap<>();
        defaultReplacements.put("prefix", "[PREFIX]");
        return defaultReplacements;
    }

    @Test
    void testTranslateWithDefaultReplacements() {
        // Test translating with default replacements
        String translated = translationHandler.translate("greeting");
        assertEquals("[PREFIX] Hello, null!", translated);
    }

    @Test
    void testTranslateWithArgs() {
        // Test translating with placeholders and arguments
        String translated = translationHandler.translate("greeting", "John");
        assertEquals("[PREFIX] Hello, John!", translated);
    }

    @Test
    void testTranslateWithLocale() {
        // Test translating with a specific locale
        String translated = translationHandler.translate(Locale.US, "greeting", "Alice");
        assertEquals("[PREFIX] Hello, Alice!", translated);
    }

    @Test
    void testTranslateWithInvalidLocale() {
        // Test translating with an invalid locale, should fall back to default
        String translated = translationHandler.translate(new Locale("invalid"), "greeting", "Bob");
        assertEquals("[PREFIX] Hello, Bob!", translated);
    }

    @Test
    void testTranslateWithMissingKey() {
        // Test translating a key that does not exist in the language file
        String translated = translationHandler.translate("nonexistent.key", "arg1", "arg2");
        assertEquals("nonexistent.key", translated);
    }

    @Test
    void testGetPlaceholderHandler() {
        // Test getting the MessagePlaceholderHandler instance
        assertNotNull(translationHandler.getPlaceholderHandler());
    }

    @Test
    void testInitWithDefaultReplacements() {
        // Test initializing with default replacements
        TranslationHandler handlerWithDefaults = new TranslationHandler(new File("src/test/resources/lang.properties"));
        assertNotNull(handlerWithDefaults.getDefaultReplacements());
    }
}

