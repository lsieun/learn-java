# SpotBugs

Github: 
- [ ] https://github.com/spotbugs/spotbugs
- [ ] https://spotbugs.github.io/


SpotBugs is FindBugs' successor. A tool for static analysis to look for bugs in Java code.

SpotBugs requires JRE (or JDK) 1.8.0 or later to run. However, it can analyze programs compiled for any version of Java, from 1.0 to 1.9.

> 基于JDK 1.8.0以上


## Using SpotBugs

### Maven

URL: https://spotbugs.readthedocs.io/en/latest/maven.html

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







