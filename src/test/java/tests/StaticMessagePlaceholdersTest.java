package tests;

import com.izanagicraft.messages.StaticMessagePlaceholders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * message-format; tests:StaticMessagePlaceholdersTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 13.12.2023
 */
public class StaticMessagePlaceholdersTest {
    @BeforeEach
    void setUp() {
        // Reset default replacements before each test
        StaticMessagePlaceholders.setDefaultReplacements(new HashMap<>());
    }

    @Test
    void testFastFormatWithDefaultReplacements() {
        String result = StaticMessagePlaceholders.fastFormat("Hello ${name}");
        assertEquals("Hello ", result);
    }

    @Test
    void testFastFormatWithValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("name", "John");
        values.put("age", 25);

        String result = StaticMessagePlaceholders.fastFormat("Hello ${name}, your age is ${age}", values);
        assertEquals("Hello John, your age is 25", result);
    }

    @Test
    void testAddDefaultReplacements() {
        Map<String, Object> replacements = new HashMap<>();
        replacements.put("greeting", "Hi");

        StaticMessagePlaceholders.addDefaultReplacements(replacements);

        assertEquals(replacements, StaticMessagePlaceholders.getDefaultReplacements());
    }

    @Test
    void testSetDefaultReplacements() {
        Map<String, Object> replacements = new HashMap<>();
        replacements.put("greeting", "Hi");

        StaticMessagePlaceholders.setDefaultReplacements(replacements);

        assertEquals(replacements, StaticMessagePlaceholders.getDefaultReplacements());
    }

    @Test
    void testSetDefaultReplacementsNull() {
        StaticMessagePlaceholders.setDefaultReplacements(null);

        assertNotNull(StaticMessagePlaceholders.getDefaultReplacements());
        assertTrue(StaticMessagePlaceholders.getDefaultReplacements().isEmpty());
    }

}
