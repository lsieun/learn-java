# Regex Constructs

<!-- TOC -->

- [1. Summary of regular-expression constructs](#1-summary-of-regular-expression-constructs)
  - [1.1. Predefined character classes](#11-predefined-character-classes)
  - [1.2. Character classes](#12-character-classes)
  - [1.3. Characters](#13-characters)
  - [1.4. Greedy quantifiers](#14-greedy-quantifiers)
  - [1.5. Logical operators](#15-logical-operators)
  - [1.6. Boundary matchers](#16-boundary-matchers)
- [2. Backslashes and escapes](#2-backslashes-and-escapes)
- [3. Groups and capturing](#3-groups-and-capturing)
  - [3.1. Group number](#31-group-number)
- [4. Reference](#4-reference)

<!-- /TOC -->

首先，说一下标题“Regex Constructs”，更完整的可以写成“regular-expression constructs”，翻译成中文就是“正则表达式的构建”。在“正则表达式的构建”中，“正则表达式”没有什么说的，而“构建(Contructs)”该怎么理解呢？我的理解是：通过不同的字符的组合来达到一种结构，而组合的过程称之为“构建(Contructs)”。

我理解的Regex中的Characters、Character classes和Predefined character classes的三者是一种不断进行高度抽象的过程。首先，三者都表示1个字符；而Characters表示一个确定的字符，Character classes在Characters的基础上加上了“范围”和“逻辑的与、或、非”，它（Character classes）加上classes应该是指一类字符的意思，一组字符范围的集合；Predefined character classes是更高一层的抽象，它能够由Character classes组合而来。

## 1. Summary of regular-expression constructs

### 1.1. Predefined character classes


|Construct|Matches|
|:--------|:------|
|`.`|Any character (may or may not match line terminators)|
|`\d`|A digit: [0-9]|
|`\D`|A non-digit: [^0-9]|
|`\s`|A whitespace character: [ \t\n\x0B\f\r]|
|`\S`|A non-whitespace character: [^\s]|
|`\w`|A word character: [a-zA-Z_0-9]|
|`\W`|A non-word character: [^\w]|


    System.out.println("a".matches("."));
    System.out.println("1".matches("\\d"));
    System.out.println("%".matches("\\D"));
    System.out.println("\r".matches("\\s"));
    System.out.println("^".matches("\\S"));
    System.out.println("a".matches("\\w"));

### 1.2. Character classes

|Construct|Matches|
|:--------|:------|
|`[abc]`|a, b, or c (simple class)|
|`[^abc]`|Any character except a, b, or c (negation)|
|`[a-zA-Z]`|a through z or A through Z, inclusive (range)|
|`[a-d[m-p]]`|a through d, or m through p: [a-dm-p] (union)|
|`[a-z&&[def]]`|d, e, or f (intersection)|
|`[a-z&&[^bc]]`|a through z, except for b and c: [ad-z] (subtraction)|
|`[a-z&&[^m-p]]`|a through z, and not m through p: [a-lq-z] (subtraction)|

    System.out.println( "a".matches("[a]") );
    System.out.println( "aa".matches("[a]+") );
    System.out.println( "abc".matches("[abc]{3,}") );
    System.out.println( "abc".matches("[abc]+") );
    System.out.println( "dshfshfu1".matches("[^abc]+") );
    System.out.println( "abcdsaA".matches("[a-z]{5,}") );
    System.out.println( "abcdsaA12".matches("[a-zA-Z]{5,}") );
    System.out.println( "abcdsaA12".matches("[a-zA-Z0-9]{5,}") );
    System.out.println( "abdxyz".matches("[a-c[x-z]]+"));
    System.out.println( "bcbcbc".matches("[a-z&&[b-c]]{5,}"));
    System.out.println( "tretrt".matches("[a-z&&[^b-c]]{5,}"));


### 1.3. Characters

|Construct|Matches|
|:--------|:------|
|`x`|The character x|
|`\\`|The backslash character|
|`\t`|The tab character ('\u0009')|
|`\n`|The newline (line feed) character ('\u000A')|
|`\r`|The carriage-return character ('\u000D')|

### 1.4. Greedy quantifiers

|Construct|Matches|
|:--------|:------|
|`X?`|X, once or not at all|
|`X*`|X, zero or more times|
|`X+`|X, one or more times|
|`X{n}`|X, exactly n times|
|`X{n,}`|X, at least n times|
|`X{n,m}`|X, at least n but not more than m times|

    System.out.println( "a".matches(".") );
    System.out.println( "a".matches("a") );
    System.out.println("a".matches("a?") );
    System.out.println( "aaa".matches("a*") );
    System.out.println( "".matches("a+") );
    System.out.println( "aaaaa".matches("a{5}") );
    System.out.println( "aaaaaaaaa".matches("a{5,8}") );
    System.out.println( "aaa".matches("a{5,}") );
    System.out.println( "aaaaab".matches("a{5,}") );

### 1.5. Logical operators

|Construct|Matches|
|:--------|:------|
|`XY`|X followed by Y|
|`X\|Y`|Either X or Y|
|`(X)`|X, as a capturing group|

### 1.6. Boundary matchers

|Construct|Matches|
|:--------|:------|
|`^`|The beginning of a line|
|`$`|The end of a line|
|`\b`|A word boundary|
|`\B`|A non-word boundary|

## 2. Backslashes and escapes

**The backslash character (`\`) serves to introduce escaped constructs** that otherwise would be interpreted as unescaped constructs. Thus the expression `\\` matches a single backslash and `\{` matches a left brace.

> backslash character的作用是引入escaped constructs

**It is an error to use a backslash prior to any alphabetic character that does not denote an escaped construct**; these are reserved for future extensions to the regular-expression language. 

> backslash character不能乱用

Backslashes within string literals in Java source code are interpreted as either Unicode escapes or other character escapes. **It is therefore necessary to double backslashes in string literals that represent regular expressions to protect them from interpretation by the Java bytecode compiler**. The string literal `\b`, for example, matches a single backspace character when interpreted as a regular expression, while `\\b` matches a word boundary. The string literal `\(hello\)` is illegal and leads to a compile-time error; in order to match the string (hello) the string literal `\\(hello\\)` must be used.

>使用Java语言时要注意：在正则表达式中，要表达backslash时，要使用double backslashs

## 3. Groups and capturing

### 3.1. Group number

Capturing groups are numbered by counting their opening parentheses from left to right. In the expression `((A)(B(C)))`, for example, there are four such groups:

> Capturing groups是通过number来进行索引的，索引的顺序从1开始，计数的方式是从左向右数opening parentheses。

|number|capturing groups|
|:----:|:--------------:|
|1     |    ((A)(B(C))) |
|2     |    (A)         |
|3     |    (B(C))      |
|4     |    (C)         |

Group zero always stands for the entire expression.

>如果group number为0的话，则表示整个regular expression。

Capturing groups are so named because, during a match, each subsequence of the input sequence that matches such a group is saved. The captured subsequence may be used later in the expression, via a back reference, and may also be retrieved from the matcher once the match operation is complete.

> 讲了一下capturing group为什么会如此命名？其实，命名倒是不重要的，重要的是在后续的表达式中可以“复用”它。

## 4. Reference

- [ ] [Java Class `Pattern`](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)
- [ ] [Predefined Character Classes](https://docs.oracle.com/javase/tutorial/essential/regex/pre_char_classes.html)
