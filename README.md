# Infiniot Web

Inifiot Web is a server-side solution for IoT system monitoring.

## How to run

### Set up MySQL database

* Create database in MySQL Shell

        CREATE DATABASE infiniot CHARACTER SET utf8 COLLATE utf8_unicode_ci;

* Import the database structure in terminal. Our default (username, password)
  setting is `(root, root)`

        mysql -u root -p infiniot < ./database/infiniot.sql

If you want to use your own the database setting, you can modify the shell
command above and modify the configuration in application context file
`./src/main/webapp/WEB-INF/applicationContext.xml`.

## Contributions

### Import to Eclipse

* File > Import > Existing Maven Projects
* Right Click Project > Properties > Targeted Runtimes > Apache Tomcat v8.0

### Code Style

We use [Google Java Code Style][style-java] for this repo.

* Java
  * [Setup Eclipse code formatting][so-format] using
    [eclipse-java-google-style.xml][style-eclipse]
  * <kbd>⌘</kbd>+<kbd>A</kbd> select all
  * <kbd>⌘</kbd>+<kbd>SHIFT</kbd>+<kbd>F</kbd> format code
* JavaScript
  * [Formatting .js with clang format][js-format]
* Other types of file
  * No solution yet

[js-format]: https://github.com/google/closure-library/wiki/Formatting-.js-with-clang-format
[so-format]: http://stackoverflow.com/questions/1601793/how-do-i-modify-eclipse-code-formatting
[style-java]: https://google.github.io/styleguide/javaguide.html
[style-eclipse]: https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml
