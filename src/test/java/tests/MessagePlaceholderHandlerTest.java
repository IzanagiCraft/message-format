package tests;

import com.izanagicraft.messages.placeholders.MessagePlaceholderHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * message-format; tests:MessagePlaceholderHandlerTest
 *
 * @author <a href="https://github.com/sanguine6660">@sanguine6660</a>
 * @since 04.01.2024
 */
public class MessagePlaceholderHandlerTest {

    private MessagePlaceholderHandler placeholderHandler;

    @BeforeEach
    void setUp() {
        // Initialize the MessagePlaceholderHandler before each test
        placeholderHandler = new MessagePlaceholderHandler();
    }

    @Test
    void testFastFormatWithDefaultReplacements() {
        // Perform a test with default replacements
        String result = placeholderHandler.fastFormat("Hello ${name}");
        assertEquals("Hello null", result);
    }

    @Test
    void testFastFormatWithValues() {
        // Perform a test with specified values
        Map<String, Object> values = new HashMap<>();
        values.put("name", "John");
        values.put("age", 25);

        String result = placeholderHandler.fastFormat("Hello ${name}, your age is ${age}", values);
        assertEquals("Hello John, your age is 25", result);
    }

    @Test
    void testAddDefaultReplacements() {
        // Perform a test by adding default replacements
        Map<String, Object> replacements = new HashMap<>();
        replacements.put("greeting", "Hi");

        placeholderHandler.addDefaultReplacements(replacements);

        assertEquals(replacements, placeholderHandler.getDefaultReplacements());
    }

    @Test
    void testSetDefaultReplacements() {
        // Perform a test by setting default replacements
        Map<String, Object> replacements = new HashMap<>();
        replacements.put("greeting", "Hi");

        placeholderHandler.setDefaultReplacements(replacements);

        assertEquals(replacements, placeholderHandler.getDefaultReplacements());
    }

    @Test
    void testSetDefaultReplacementsNull() {
        // Perform a test by setting default replacements to null
        placeholderHandler.setDefaultReplacements(null);

        // Assert that the default replacements are not null and empty
        assertEquals(new HashMap<>(), placeholderHandler.getDefaultReplacements());
    }
}
