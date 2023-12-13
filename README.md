# Message Format Library

![Java Version](https://img.shields.io/badge/Java-%3E%3D%2017-brightgreen?style=for-the-badge) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg?style=for-the-badge)](https://www.gnu.org/licenses/gpl-3.0)

---

## Overview

The Message Format Library is a Java library designed to facilitate message formatting and translation in Java applications. It provides a set of classes,
including `WrappedString`, `Translations`, and `Formatter`, to assist in handling and formatting messages efficiently.

---

## WrappedString

The `WrappedString` class is a straightforward utility designed for wrapping `String` values. It provides methods for creating, retrieving, and modifying the wrapped string.

### Example Usage:

```java
WrappedString wrappedString = WrappedString.of("Hello, World!");
System.out.println(wrappedString.toString()); // Output: Hello, World!
```

---

## Translations

The `Translations` class manages translations for different locales using a map of `Properties` objects. It supports the initialization of translations, default replacements, and
provides methods for translating messages.

### Example Initialization:

```java
// greeting='Hello, ${0}!'
File englishFile = new File("en.properties");
File spanishFile = new File("es.properties");

Translations.init(Map.of("0", "MyApp"), englishFile, spanishFile);
```

### Example Translation:

```java
String translatedMessage = Translations.translate(Locale.EN, "greeting");
System.out.println(translatedMessage); // Output: Hello, MyApp!

// with added object placeholers
String translatedMessage = Translations.translate(Locale.EN, "greeting", "John");
System.out.println(translatedMessage); // Output: Hello, John!
```

---

## Formatter

The `Formatter` class offers fast and efficient string formatting with placeholder replacement. It supports default replacements and allows users to provide additional values for
specific placeholders.

### Example Usage:

```java
String formatString = "Hello, ${name}! Today is ${day}.";
Map<String, Object> values = Map.of("name", "John", "day", "Monday");

String formattedMessage = Formatter.fastFormat(formatString, values);
System.out.println(formattedMessage); // Output: Hello, John! Today is Monday.
```

---

## License

This library is licensed under the [GPL-3.0 License](https://www.gnu.org/licenses/gpl-3.0.txt). Feel free to use, modify, and distribute it as needed.

## Contribution

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or create a pull request.

## Versioning

We follow [Semantic Versioning](https://semver.org/). For the available versions, see the [releases](https://github.com/IzanagiCraft/message-format/releases) on this repository.

---

## Testing

The library includes a comprehensive set of JUnit tests to ensure robust functionality. Here's an example test class demonstrating various scenarios:

```java
// TODO: test class content here...
```

Feel free to explore and expand on these tests as needed for your specific use cases.

---

## Authorship

This library is actively maintained by the IzanagiCraft team. For inquiries, reach out to [contact@izanagicraft.com](mailto:contact@izanagicraft.com).

## Copyright

(c) 2023 - present | IzanagiCraft team and contributors. See the [LICENSE](./LICENSE) file for details.