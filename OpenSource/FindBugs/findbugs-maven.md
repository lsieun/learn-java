# FindBugs Maven Plugin Tutorial

FindBugs can be executed using Maven in **two different modes**: as **stand-alone command** or as **part of the Maven site command**.

These **two modes** require different settings in the project pom.xml but they are not incompatible to each other so we can use both of them.

URL: 
- [x] https://www.petrikainulainen.net/programming/maven/findbugs-maven-plugin-tutorial/
- [x] https://gualtierotesta.wordpress.com/2015/06/14/tutorial-using-findbugs-with-maven/

## Stand-alone mode

Let’s start with **the stand-alone mode** which requires the following configuration in the pom.xml:

```xml
<build>
    <plugins>       
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>findbugs-maven-plugin</artifactId>
            <version>3.0.5</version>
        </plugin>
         ... other build plugins ....
    </plugins>
</build>
```

Giving the following command:

```bash
mvn findbugs:findbugs
```

FindBugs is run against our project with the following log in the console output:

```txt
[INFO] --- findbugs-maven-plugin:3.0.1:findbugs (default-cli) @ CodeQualityGallery ---
[INFO] Fork Value is true
     [java] Warnings generated: 1
[INFO] Done FindBugs Analysis....
```

> FindBugs analyze the compiled files (`*.class`). If the project is not compiled or just cleaned, FindBugs will report nothing, without error messages. So take care to build your project before running FindBugs.

A **warnings number** is reported on the console. Warnings are possible bugs found by the FindBugs detectors.

The warnings details are included in a report created in the file `target/findbugsXml.xml` which is in XML format and so not really readable.

A better way to examine the warnings is to use the FindBugs native GUI with the command:

```bash
mvn findbugs:gui
```

## Use Case 2: Fail the Build if Problems Are Found

If we want to be sure that our code doesn’t contain even **a minor problem**, it might be good idea to **run static code analysis every time when our project is compiled**. Of course, this makes sense only if the build is failed when a problem is found.

In other words, we have to configure the FindBugs Maven plugin to fail the build if problems are found. We can do this by following these steps:

- **Add** the plugin declaration to the plugins section of the `pom.xml` file.
- **Configure** the FindBugs Maven plugin by following these steps:
  - Ensure that most accurate analysis is performed.
  - Ensure that all bugs are reported.
  - Ensure that XML report is generated.
  - Configure the plugin to create the XML report to the directory `${project.build.directory}/findbugs`.
  - Add an execution which runs the plugin’s `check` goal during the `compile` Maven lifecycle phase.

The relevant part of the pom.xml file looks as follows:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>findbugs-maven-plugin</artifactId>
            <version>3.0.5</version>
            <configuration>
                <!--
                    Enables analysis which takes more memory but finds more bugs.
                    If you run out of memory, changes the value of the effort element
                    to 'Low'.
                -->
                <effort>Max</effort>
                <!-- Reports all bugs (other values are medium and max) -->
                <threshold>Low</threshold>
                <!-- Produces XML report -->
                <xmlOutput>true</xmlOutput>
                <!-- Configures the directory in which the XML report is created -->
                <findbugsXmlOutputDirectory>${project.build.directory}/findbugs</findbugsXmlOutputDirectory>
            </configuration>
            <executions>
                <!--
                    Ensures that FindBugs inspects source code when project is compiled.
                -->
                <execution>
                    <id>analyze-compile</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

This configuration ensures that the `check` goal of the Maven FindBugs plugin is invoked during the `compile` Maven lifecycle phase. If FindBugs finds problems from the source code, it fails the build.

```bash
$ mvn clean compile
$ mvn findbugs:gui
```

## Use Case 3: Create XML Report Without Failing the Build

If we want to integrate Jenkins with FindBugs, we need to find a way to create XML reports without failing the build.

We can configure the FindBugs Maven plugin to do this by following these steps:

- Configure the FindBugs Maven plugin as described in the previous section (Use case 2).
- Ensure that the build doesn’t fail if problems are found by setting the value of the `failOnError` configuration property to `false`.

The relevant part of the pom.xml file looks as follows:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>findbugs-maven-plugin</artifactId>
            <version>3.0.5</version>
            <configuration>
                <!--
                    Enables analysis which takes more memory but finds more bugs.
                    If you run out of memory, changes the value of the effort element
                    to 'Low'.
                -->
                <effort>Max</effort>
                <!-- Build doesn't fail if problems are found -->
                <failOnError>false</failOnError>
                <!-- Reports all bugs (other values are medium and max) -->
                <threshold>Low</threshold>
                <!-- Produces XML report -->
                <xmlOutput>true</xmlOutput>
                <!-- Configures the directory in which the XML report is created -->
                <findbugsXmlOutputDirectory>${project.build.directory}/findbugs</findbugsXmlOutputDirectory>
            </configuration>
            <executions>
                <!--
                    Ensures that FindBugs inspects source code when project is compiled.
                -->
                <execution>
                    <id>analyze-compile</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

We can now create the XML report by compiling the project.

```bash
$ mvn clean compile
$ mvn findbugs:gui
```

## Use Case 4: Create Both XML and HTML Reports Without Creating the Site


If we want to create both **XML** and **HTML** reports without creating the project site or failing the build, we have to follow these steps:

- Configure the FindBugs Maven plugin as described in the previous section (use case 3).
- Add the declaration of the [XML Maven Plugin](http://www.mojohaus.org/xml-maven-plugin/) to the plugins section of the `pom.xml` file.
- Configure the plugin by following these steps:
  - Create a transformation set which transforms all XML files found from the `${project.build.directory}/findbugs` directory and writes the results of the XSLT transformation to the same directory.
  - Configure stylesheet which specifies the output of the XSLT transformation. The FindBugs library provides five stylesheets which can be used for this purpose. The available stylesheets are described in the sample configuration.
  - Ensure that all output files of the XSLT transformation have the file extension `.html`.
- Add an execution which invokes the `transform` goal of the XML Maven plugin during the `compile` Maven lifecycle phase.
- Add FindBugs (version 3.0.1) as the dependency of the plugin.

The relevant part of the pom.xml file looks as follows:

```xml
<build>
    <plugins>

        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>findbugs-maven-plugin</artifactId>
            <version>3.0.5</version>
            <configuration>
                <!--
                    Enables analysis which takes more memory but finds more bugs.
                    If you run out of memory, changes the value of the effort element
                    to 'Low'.
                -->
                <effort>Max</effort>
                <!-- Build doesn't fail if problems are found -->
                <failOnError>false</failOnError>
                <!-- Reports all bugs (other values are medium and max) -->
                <threshold>Low</threshold>
                <!-- Produces XML report -->
                <xmlOutput>true</xmlOutput>
                <!-- Configures the directory in which the XML report is created -->
                <findbugsXmlOutputDirectory>${project.build.directory}/findbugs</findbugsXmlOutputDirectory>
            </configuration>
            <executions>
                <!--
                    Ensures that FindBugs inspects source code when project is compiled.
                -->
                <execution>
                    <id>analyze-compile</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>xml-maven-plugin</artifactId>
            <version>1.0</version>
            <configuration>
                <transformationSets>
                    <transformationSet>
                        <!-- Configures the source directory of XML files. -->
                        <dir>${project.build.directory}/findbugs</dir>
                        <!-- Configures the directory in which the FindBugs report is written.-->
                        <outputDir>${project.build.directory}/findbugs</outputDir>
                        <!-- Selects the used stylesheet. -->
                        <!-- <stylesheet>fancy-hist.xsl</stylesheet> -->
                        <stylesheet>default.xsl</stylesheet>
                        <!--<stylesheet>plain.xsl</stylesheet>-->
                        <!--<stylesheet>fancy.xsl</stylesheet>-->
                        <!--<stylesheet>summary.xsl</stylesheet>-->
                        <fileMappers>
                            <!-- Configures the file extension of the output files. -->
                            <fileMapper implementation="org.codehaus.plexus.components.io.filemappers.FileExtensionMapper">
                                <targetExtension>.html</targetExtension>
                            </fileMapper>
                        </fileMappers>
                    </transformationSet>
                </transformationSets>
            </configuration>
            <executions>
                <!-- Ensures that the XSLT transformation is run when the project is compiled. -->
                <execution>
                    <phase>compile</phase>
                    <goals>
                        <goal>transform</goal>
                    </goals>
                </execution>
            </executions>
            <dependencies>
                <dependency>
                    <groupId>com.google.code.findbugs</groupId>
                    <artifactId>findbugs</artifactId>
                    <version>3.0.1</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```

```bash
$ mvn clean compile
```

在`${project.build.directory}/findbugs`可以找到生成的`html`文件。

