# Exception Processing Mechanism

The term **exception** is shorthand for the phrase "exceptional event."

**Definition**: **An exception is an event**, which occurs during the execution of a program, that disrupts the normal flow of the program's instructions.

When an error occurs within a method, the method creates an object and hands it off to the runtime system. The object, called **an exception object**, contains information about the error, including its type and the state of the program when the error occurred. Creating an exception object and handing it to the runtime system is called **throwing an exception**.<sub>这段主要讲述：method会创建exception object，然后交给runtime system</sub>

After a method throws an exception, the runtime system attempts to find "something" to handle it. The set of possible "somethings" to handle the exception is the ordered list of methods that had been called to get to the method where the error occurred. The list of methods is known as the **call stack**.<sub>这段主要讲：runtime system接收到exception object之后，会在call stack中寻找处理问题的方法</sub>

![](images/exceptions-callstack.gif)

The runtime system searches the **call stack** for a method that contains a block of code that can handle the exception. This block of code is called **an exception handler**. The search begins with the method in which the error occurred and proceeds through the call stack in the reverse order in which the methods were called. When an appropriate handler is found, the **runtime system** passes **the exception** to **the handler**. An exception handler is considered appropriate if the type of the exception object thrown matches the type that can be handled by the handler. The exception handler chosen is said to **catch the exception**.<sub>这段主要讲：更详细的来说，就是runtime将exception object交由exception handler处理</sub>

![](images/exceptions-errorOccurs.gif)

If the runtime system exhaustively searches all the methods on the call stack without finding an appropriate exception handler, as shown in the next figure, the runtime system (and, consequently, the program) terminates.<sub>这段主要讲：如果找不到合适的exception handler，runtime system就会terminate</sub>


