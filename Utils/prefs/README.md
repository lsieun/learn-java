# Java Preferences API

<!-- TOC -->

- [1. Intro](#1-intro)
- [Mind Map](#mind-map)
- [2. Reference](#2-reference)

<!-- /TOC -->

## 1. Intro

The **Java Preferences API** provides a systematic way to **handle program preference configurations**, e.g. to save user settings, remember the last value of a field etc.

`Preferences` are **key/values** pairs where the **key** is an arbitrary name for the preference. The **value** can be a `boolean`, `string`, `int` of another primitive type. `Preferences` are received and saved by `get` and `put` methods while the `get` methods also supply **a default value** in case the `preferences` is not yet set.

This Java Preferences API is not indented to save application data, i.e. larger structured data.  

> 笔记：为什么要说这一段话呢？Java Preferences API主要是用来存储用户对于程序的某种“偏好数据”（用户的设置／上一次编辑的值），而并不适合于存储程序的业务数据。

The **Java Preference API** removes the burden from the individual programmer to write code to save configuration values on the different platforms his program may be running.

> 笔记：Java Preference API是与操作系统无关的，但是（接下段）

The actual storage of the data is dependent on the platform.

> 笔记：真实的数据存储是依赖于操作系统的，Java Preference API对此做了抽象。

## Mind Map

第一呢，Preferences给的印象是一个“树”，因此第一个文件命名为A_Preferences_Tree。

第二呢，“树”中是有“节点”的，第二个总结就是关于“节点”的操作

第三呢，是一个比较特殊（但确常用）的使用

第四呢，是关于“树”是如何保存信息的，究竟保存到什么地方

第五呢，就是一些示例

## 2. Reference

- [Core Java Preferences API](https://docs.oracle.com/javase/8/docs/technotes/guides/preferences/index.html) 这是Oracle的文档
- [Java Preferences API - Tutorial](https://www.vogella.com/tutorials/JavaPreferences/article.html)
- [Learning Java: The Preferences API](https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch11s06.html)
