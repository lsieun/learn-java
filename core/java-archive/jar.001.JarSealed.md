# Java jar seal packages

Java JAR provides option to seal the package. Java jar seal package means that if any program is using that jar then all the classes should be loaded from that jar itself. Any attempt to load the classes from other jar files will throw `java.lang.SecurityException`.

Jar package sealing helps us in making sure that all the classes are loaded from same version of jar file.

Tip: If you want **all the packages of a jar file** to be sealed, you can just add entry for `Sealed` as `true`. To **exclude any package** from sealing, you can add an extra entry with `Sealed` as `false`.

File: `MANIFEST.MF`

```txt

Sealed: true

Name: com.jd.util
Sealed: false
```

## Reference

- [Java jar seal packages](https://www.journaldev.com/1347/java-jar-seal-packages)
