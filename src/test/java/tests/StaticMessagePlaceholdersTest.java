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
        assertEquals("Hello null", result);
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
