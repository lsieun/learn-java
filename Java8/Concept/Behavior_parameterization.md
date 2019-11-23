# Behavior parameterization

**Behavior parameterization** is a software development pattern that lets you handle frequent requirement changes. **In a nutshell, it means taking a block of code and making it available without executing it. This block of code can be called later by other parts of your programs, which means that you can defer the execution of that block of code**. For instance, you could pass the block of code as an argument to another method that will execute it later. As a result, the method’s behavior is parameterized based on that block of code. For example, if you process a collection, you may want to write a method that

- Can do “something” for every element of a list
- Can do “something else” when you finish processing the list
- Can do “yet something else” if you encounter an error



