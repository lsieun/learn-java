# When to choose checked and unchecked exceptions

<!-- TOC -->

- [1. Checked Exception VS Unchecked Exception](#1-checked-exception-vs-unchecked-exception)
- [2. Reevaluate at every level](#2-reevaluate-at-every-level)
- [3. Use the right abstraction level](#3-use-the-right-abstraction-level)
- [5. Reference](#5-reference)

<!-- /TOC -->

## 1. Checked Exception VS Unchecked Exception

**Checked Exceptions** are great, so long as you understand when they should be used. The Java core API fails to follow these rules for `SQLException` (and sometimes for `IOException`) which is why they are so terrible.

**Checked Exceptions** should be used for **predictable**, but **unpreventable** errors that are **reasonable to recover from**.

**Unchecked Exceptions** should be used for everything else.

I'll break this down for you, because most people misunderstand what this means.

- **Predictable but unpreventable**: The caller did everything within their power to validate the input parameters, but some condition outside their control has caused the operation to fail. For example, you try reading a file but someone deletes it between the time you check if it exists and the time the read operation begins. By declaring a checked exception, you are telling the caller to anticipate this failure.
- **Reasonable to recover from**: There is no point telling callers to anticipate exceptions that they cannot recover from. If a user attempts to read from an non-existing file, the caller can prompt them for a new filename. On the other hand, if the method fails due to a programming bug (invalid method arguments or buggy method implementation) there is nothing the application can do to fix the problem in mid-execution. The best it can do is log the problem and wait for the developer to fix it at a later time.

Unless the exception you are throwing meets all of the above conditions it should use an **Unchecked Exception**.

## 2. Reevaluate at every level

**Reevaluate at every level**: Sometimes the method catching the checked exception isn't the right place to handle the error. In that case, consider what is reasonable for your own callers. If the exception is predictable, unpreventable and reasonable for them to recover from then you should throw a checked exception yourself. If not, you should wrap the exception in an unchecked exception. If you follow this rule you will find yourself converting checked exceptions to unchecked exceptions and vice versa depending on what layer you are in.

My rule is

- `if` statements for business logic errors (like your code)
- cheched exceptions for environment errors where the application **can recover**
- uncheched exception for environment errors where there is **no recovery**

Example

- Example for checked exception: Network is down for an application that can work offline
- Example for uncheched exception: Database is down on a CRUD web application.

## 3. Use the right abstraction level

For both checked and unchecked exceptions, **use the right abstraction level**. For example, a code repository with two different implementations (database and filesystem) should avoid exposing implementation-specific details by throwing `SQLException` or `IOException`. Instead, it should wrap the exception in an abstraction that spans all implementations (e.g. `RepositoryException`).

Another technique I use is to create **a simple hierarchy of exceptions**. This lets me write cleaner code one layer up, since I can catch the superclass, and only deal with the individual subclasses when it really matters.

## 5. Reference

- [When to choose checked and unchecked exceptions](https://stackoverflow.com/a/19061110)
