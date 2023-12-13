<div align="center">
    <img src="https://avatars.githubusercontent.com/u/153524152?s=220" alt="logo">
</div>
<div align="center">
    <h1>IzanagiCraft - Message Format Library</h1>
    <a href="https://discord.gg/ySsgfQmY">
        <img src="https://img.shields.io/discord/1183768311851388958.svg?colorB=Blue&logo=discord&label=Support+%26+Community&style=for-the-badge" alt="Support">
    </a>
    <a href="https://github.com/IzanagiCraft/message-formatter/issues">
        <img src="https://img.shields.io/github/issues/IzanagiCraft/message-formatter.svg?style=for-the-badge">
    </a>
    <a href="https://www.gnu.org/licenses/gpl-3.0">
        <img src="https://img.shields.io/badge/License-GPLv3-blue.svg?style=for-the-badge">
    </a>
    <a href="https://github.com/IzanagiCraft/IzanagiCraft/issues">
        <img src="https://img.shields.io/badge/Java-%3E%3D%2017-brightgreen?style=for-the-badge">
    </a>
</div>

---

## Overview

The Message Format Library is a Java library designed to facilitate message formatting and translation in Java applications. It provides a set of classes,
including `WrappedString`, `Translations`, and `StaticMessagePlaceholders`, to assist in handling and formatting messages efficiently.

---

## TODO: add implementation

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

## TODO: StaticMessagePlaceholders formatting description

---

## License

This library is licensed under the [GPL-3.0 License](https://www.gnu.org/licenses/gpl-3.0.txt). Feel free to use, modify, and distribute it as needed.

## Contribution

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or create a pull request.

## Versioning

We follow [Semantic Versioning](https://semver.org/). For the available versions, see the [releases](https://github.com/IzanagiCraft/message-format/releases) on this repository.

---

## Authorship

This library is actively maintained by the IzanagiCraft team. For inquiries, reach out to [contact@izanagicraft.com](mailto:contact@izanagicraft.com).

## Copyright

Copyright (c) 2023 - present | IzanagiCraft team and contributors. See the [LICENSE](./LICENSE) file for details.
