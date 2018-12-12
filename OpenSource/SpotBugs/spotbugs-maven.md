# Using the SpotBugs Maven Plugin

URL: 
- [x] https://spotbugs.readthedocs.io/en/stable/maven.html
- [ ] https://spotbugs.github.io/spotbugs-maven-plugin/index.html

# 1. Add `spotbugs-maven-plugin` to your pom.xml

```xml
<plugin>
  <groupId>com.github.spotbugs</groupId>
  <artifactId>spotbugs-maven-plugin</artifactId>
  <version>3.1.8</version>
  <dependencies>
    <!-- overwrite dependency on spotbugs if you want to specify the version of spotbugs -->
    <dependency>
      <groupId>com.github.spotbugs</groupId>
      <artifactId>spotbugs</artifactId>
      <version>3.1.9</version>
    </dependency>
  </dependencies>
</plugin>
```

# 2. Goals of spotbugs-maven-plugin

## 2.1 `spotbugs` goal

`spotbugs` goal analyses target project by SpotBugs. [maven site](https://spotbugs.github.io/spotbugs-maven-plugin/spotbugs-mojo.html)

## 2.2 `check` goal

`check` goal runs analysis like spotbugs goal, and make the build failed if it found any bugs. [maven site](https://spotbugs.github.io/spotbugs-maven-plugin/check-mojo.html)

## 2.3 `gui` goal

`gui` goal launches SpotBugs GUI to check analysis result. [maven site](https://spotbugs.github.io/spotbugs-maven-plugin/gui-mojo.html)

## 2.4 `help` goal

`help` goal displays usage of this Maven plugin.





