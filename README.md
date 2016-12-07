# Infiniot Web

Inifiot Web is a server-side solution for IoT system monitoring.

## Contributions

### Import to Eclipse

* File > Import > Existing Maven Projects
* Right Click Project > Properties > Targeted Runtimes > Apache Tomcat v8.0

### Code Style

We use [Google Java Code Style][style-java] for this repo.

If you use Eclipse, please [setup the Eclipse code format][so-format] using
Google's configuration file [eclipse-java-google-style.xml][style-eclipse].
Before any commit, do:

- open a java file
- <kbd>⌘</kbd>+<kbd>A</kbd> select all
- <kbd>⌘</kbd>+<kbd>SHIFT</kbd>+<kbd>F</kbd> format code

If you use other IDE, please respect the code style too.

[so-format]: http://stackoverflow.com/questions/1601793/how-do-i-modify-eclipse-code-formatting
[style-java]: https://google.github.io/styleguide/javaguide.html
[style-eclipse]: https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml
