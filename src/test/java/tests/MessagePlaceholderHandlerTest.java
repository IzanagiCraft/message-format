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
