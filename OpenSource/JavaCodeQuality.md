# Best Practices and Tools to Improve the Java Code Quality

URL: https://www.romexsoft.com/blog/improve-java-code-quality/

Find out **how to improve the Java code quality** within your application by applying **best practices** and using **the right code quality analysis tools**.


## What Is a Software Development Methodology?

**Software development methodology** is **a set of patterns and rules** that are used to structure, plan, and control the process of software solutions developing. In order to manage the development process efficiently, the project manager must select the **development methodologies** that suit the appropriate project the most.

### Pair Programming

Pair programming is a proven methodology that decreases bugs in your code. It is done by two developers who share one workspace (a keyboard and a screen) and it looks like a flashback from school or university – one developer is coding while another reads the written code and contemplates about potential issues. Developers coach each other in coding and can change roles during a session.

> 这里讲Pair programming究竟是什么样子的

**The main benefit of pair programming** is **knowledge sharing** and **code quality improvement**. It also improves communication inside of the team.

> 这里讲Pair programming的好处

Pair programming is not only about **the code** but it can be used for **OS command lines**, work with **DB server**, **file management**, work with cloud and many other cases.

> 这里是讲Pair programming的使用场景：不仅可以用于写代码（code），也可以用于命令行（OS command lines）、数据库（DB server）和文件管理（file management）等。

So if you work on the more complex or critical items feel free to team up with your co-workers and use this methodology to prevent bugs/issues in your code. Given the higher costs of bugs/issues that will be found later during development or after the release pair programming saves your time and money.

Considering the common knowledge that ‘two heads are better than one’, the discussion about pair programming seems to have died down and people actually do practice it.


### Coding Conventions

**Coding conventions** are **the set of guidelines** that are developed by dev teams and include the recommendations for the programming style, practices, and methods for each aspect of a code that is written within the company or certain project. 

These conventions are usually specific for every programming language and cover file organization, indentation, comments, declarations, statements, white spaces, naming conventions, programming practices and principles, programming rules, architectural best practices, etc. 

> 具体包含的内容

**The main advantage of defined standards** is that every piece of code looks and feels familiar. It makes it more readable and helps programmers understand code written by another programmer.

> 好处

If the coding standards are followed by and applied consistently throughout the development process, in future, it will be easier to maintain and extend the code, refactor it, and resolve integration conflicts. The standard itself matters much less than adherence to it.

Code conventions are important to programmers for a number of reasons:

- 40% – 80% of the lifetime cost of software goes to maintenance.
- Hardly any software is maintained for its whole life by its author.
- Code conventions improve the readability of the software, allowing programmers to understand the new code more quickly.

Frankly speaking, I am a big fan of coding standards. For me, **it makes sense to spend time on debating and arguing over them**, since it is a valuable contribution to save your time and efforts in the future.

Start with arranging a meeting on what should be in the coding standard. It should be only a face to face meeting, but not over email or via the chat systems. Discuss the rules that you decided to include in your code convention. The rules that are agreed by the majority of the team should be accepted as “mandatory” and other rules can be considered as “optional”.

**The code convention guidelines should be reviewed frequently**. Rules can be moved from “mandatory” to “optional” and vice versa. If some of them do not work as expected, then they need to be reworked or removed from the guidelines.

Programmers are highly recommended to follow these guidelines during the coding and it should be used as part of the code review which is the next one in our list.

### Code Review

The next best thing to **pair programming** is **code review**. If you do not practice pair programming then it is recommended to consider at least code review. **It is a lightweight process** that should be applied as soon as possible after the code is written.

Some of the other subjects listed below are not so obvious but are worth to be considered. Let’s continue.

### Unit Tests

The next methodology we will talk about is **unit testing**. Depending on your experience you may or may not have heard something about **unit tests**, **test-driven development** or **some other type of testing methodology**. Usually, these methodologies are applied in the context of large software systems and sometimes in the context of simple web sites.

In computer programming, **unit testing** is a software development process in which **the smallest testable part of a source code**, called **unit**, is tested individually and independently to examine whether they are working as expected. This helps you to identify failures in the algorithms and/or logic before the code is released.

**One of the benefits** that you acquire from creating unit tests is that **your team will be motivated to write testable code**. Since unit testing requires the appropriate structure of your code it means that code must be divided into smaller more focused functions. Each of which is responsible for a single operation on a set of data rather than on large functions performing a number of different operations.

**The second benefit** of writing a well-tested code is that you can prevent the future failure when the small changes to existing code break functionality. When a failure happens you will be informed that you have written something wrong.

At first glance, spending time on writing the unit tests during development looks like extra expenses. However, it will save time you might spend on debugging. This should be a step by step process.

Of course, you **can not** write unit tests for every part of your project but you need to **make sure** the core components behave as expected. When the project grows you can simply run the tests you have developed to ensure that existing functionality is not broken when new functionality is introduced.


### Test-Driven Development

**Test-driven development (TDD)**, also called test-driven design, is a methodology of developing software that combines unit testing, programming, and refactoring of source code. Usually, developers tend to skip the architectural aspect of software development and jump straight into coding. They try to get a rough implementation and only after having a working prototype they think about writing the tests. While this approach can succeed it takes a lot of efforts to just make it work.

TDD is one of those practices which contribute to better code quality and decreases bugs. It was introduced as part of a larger software design paradigm known as Scrum and Extreme Programming (XP), which are types of the Agile software development methodology. TDD helps to produce applications of high quality in less time. Proper implementation of TDD requires the developers and testers to accurately anticipate how the application and its features will be used in the real world.

When it is run as part of a continuous integration cycle with frequent automated builds and tests, the practice is Unit Testing on steroids. In order to apply this methodology, developers need specific training and coaching.

In my understanding of TDD, before coding you need to know (at least) the starting point. First of all, you need to think of the objects and their behavior to solve the given problem. When doing TDD you start from a test and from your dream vision of how the test would look like.


### Continuous Integration

**Continuous Integration (CI)** is a development practice that requires developers to integrate code into a shared repository several times a day (SVN, Subversion, or Git). Each check-in is verified by the automated tests. Although **automated tests** are not strictly part of CI, they are usually anticipated. Such approach allows developers to detect problems earlier and, as a result, solve them faster.

This is a valuable practice by itself. You should focus on setting up a simple Continuous Integration process as early as possible. There are many tools that can help you to set up this process and the most known are `Jenkins`, `Bamboo`, and `Teamcity`. They allow you to automate your software deployment and let you focus on building your product.

### Demo Session

The demo review meeting usually takes place close to the end of the Sprint(冲刺；短跑比赛). The purpose of this meeting is to show the other team members, customers, and stakeholders the results of work team has accomplished over the Sprint.

It may not be immediately visible why it leads to a better code but it will. By regularly showing the source code developers need to keep it close to the release state. With demo meetings on regular basis, you will have a well-organized process of receiving feedbacks. And this will give you a better understanding of what was done right and will indicate when something went in the wrong direction.

Now, let’s move on and check what are some recommended code quality tools to polish the process.

## Tools to Improve Java Code Quality

There is no developer who never made a mistake.

Usually, **the compiler** catches the syntactic and arithmetic issues and lists out a stack trace. But there still might be some issues that compiler does not catch. These could be inappropriately implemented requirements, incorrect algorithm, bad code structure or some sort of potential issues that community knows from experience.

The only way to catch such mistakes is to have some senior developer to review your code. Such approach is not a panacea(万灵药；万能之计) and does not change much. With each new developer in the team, you should have an extra pair of eyes which will look at his/her code. But luckily there are many tools which can help you control the code quality including `Checkstyle`, `PMD`, `FindBugs`, `SonarQube` etc. All of them are usually used to analyze the quality and build some useful reports. Very often those reports are published by **continuous integration servers**, like **Jenkins**.

Here is a checklist of Java static code analysis tools. Let’s review each of them.

### Checkstyle

Code reviews are essential to code quality, but usually, no one in the team wants to review tens of thousands lines of code. But the challenges associated with manually code reviews can be automated by source code analyzers tool like [Checkstyle](http://checkstyle.sourceforge.net/).

Checkstyle is a free and open source **static code analysis tool** used in software development for **checking whether Java code conforms to the coding conventions** you have established. It automates the crucial but boring task of checking Java code. It is one of the most popular tools used to automate the code review process.

Checkstyle comes with **predefined rules** that help in maintaining the code standards. These rules are a good starting point but they do not account for project-specific requirements. The trick to gain a successful automated code review is to combine the built-in rules with custom ones as there is a variety of tutorials with how-tos.

Checkstyle can be used as an **Eclipse plugin** or as the part of a built systems such as **Ant**, **Maven** or **Gradle** to validate code and create reports coding-standard violations.


### PMD

[PMD](https://pmd.github.io/) is a **static code analysis tool** that is capable to automatically detect a wide range of potential bugs and unsafe or non-optimized code. 

It examines **Java source code** and looks for potential problems such as possible bugs, dead code, suboptimal code, overcomplicated expressions, and duplicated code.

> PMD分析是source code吗？我印象中，FindBugs分析的是class文件。

Whereas other tools, such as Checkstyle, can verify whether coding conventions and standards are respected, PMD focuses more on preemptive(先发制人的；优先权的) defect detection. It comes with a rich and highly configurable set of rules that you can easily configure and choose which particular rules should be used for a given project.

The same as Checkstyle, PMD can be used with **Eclipse**, **IntelliJ IDEA**, **Maven**, **Gradle** or **Jenkins**.

Here are a few cases of bad practices that PMD deals with:

- Empty try/catch/finally/switch blocks.
- Empty if/while statements.
- Dead code.
- Cases with direct implementation instead of an interface.
- Too complicated methods.
- Classes with high Cyclomatic Complexity measurements.
- Unnecessary ‘if’ statements for loops that could be ‘while’ loops.
- Unused local variables, parameters, and private methods.
- Override hashcode() method without the equals() method.
- Wasteful String/StringBuffer usage.
- Duplicated code – copy/paste code can mean copy/paste bugs, and, thus, bring the decrease in maintainability.


### FindBugs

FindBugs is an open source **Java code quality tool** similar in some ways to Checkstyle and PMD, but with **a quite different focus**. FindBugs **doesn’t** concern **the formatting** or **coding standards** but is only marginally interested in best practices.

In fact, it concentrates on detecting potential bugs and performance issues and does a very good job of detecting a variety of many types of common hard-to-find coding mistakes, including thread synchronization problems, null pointer dereferences, infinite recursive loops, misuse of API methods etc. 

FindBugs operates on **Java bytecode**, rather than source code. Indeed, it is capable of detecting quite a different set of issues with a relatively high degree of precision in comparison to **PMD** or **Checkstyle**. As such, it can be a useful addition to your static analysis toolbox.

FindBugs is mainly used for identifying hundreds of serious defects in large applications that are classified in four ranks:

- scariest
- scary
- troubling
- of concern


### SonarQube

SonarQube is an open source platform which was originally launched in 2007 and is used by developers to manage source code quality. Sonar was designed to support global continuous improvement strategy on code quality within a company and therefore can be used as a shared central system for quality management. It makes management of code quality possible for any developer in the team. As a result, in recent years it has become a world’s leader in Continuous Inspection of code quality management systems.

Sonar currently supports **a wide variety of languages** including Java, C/C++, C#, PHP, Flex, Groovy, JavaScript, Python, and PL/SQL (some of them via additional plugins). And Sonar is very useful as it offers fully automated analyses tools and integrates well with **Maven**, **Ant**, **Gradle**, and **continuous integration tools**.

Sonar uses FindBugs, Checkstyle and PMD to collect and analyze source code for bugs, bad code, and possible violation of code style policies. It examines and evaluates different aspects of your source code from minor styling details, potential bugs, and code defects to the critical design errors, lack of test coverage, and excess complexity. At the end, Sonar produces metric values and statistics, revealing problematic areas in the source that require inspection or improvement.

Here is a list of some of SonarQube‘s features:

- It doesn’t show only what’s wrong. It also offers quality management tools to help you put it right.
- SonarQube addresses not only bugs but also coding rules, test coverage, code duplications, complexity, and architecture providing all the details in a dashboard.
- It gives you a snapshot of your code quality at the certain moment of time as well as trends of lagging and leading quality indicators.
- It provides you with code quality metrics to help you take the right decision.
- There are code quality metrics that show your progress and whether you’re getting better or worse.

SonarQube is a web application that can be installed standalone or inside the existing Java web application. The code quality metrics can be captured by running `mvn sonar:sonar` on your project.

Your `pom.xml` file will need a reference to this plugin because it is not a default maven plugin.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>3.3.0.603</version>
        </plugin>
    </plugins>
</build>
```

Also, Sonar provides an enhanced reporting via multiple views that show certain metrics (you can configure which ones you want to see) for all projects. And what’s most important, it does not only provide metrics and statistics about your code but translates these nondescript values to real business values such as risk and technical debt.

