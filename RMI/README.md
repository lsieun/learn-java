# Remote Method Invocation

RMI lets you invoke methods on objects running in another JVM. When you call a remote method with RMI, the method arguments are packaged (**marshaled**) into a byte stream and shipped over the network to the remote JVM, where they are unpacked (**unmarshaled**) and passed to the remote method.

When the RMI code calls your remote object, in what thread does that call happen? You don't know, but it's definitely not in a thread you created -- your object gets called in a thread managed by RMI. How many threads does RMI create? Could the same remote method on the same remote object be called simultaneously in multiple RMI threads?

A remote object must guard against **two thread safety hazards**: **properly coordinating access to state that may be shared with other objects**<sub>注：这里强调对象A和对象B共同访问的一个变量</sub>, and **properly coordinating access to the state of the remote object itself**<sub>注：这里强调对象A内部的状态，可能被外部对象访问</sub> (since the same object may be called in multiple threads simultaneously). Like servlets, RMI objects should be prepared for multiple simultaneous calls and must provide their own thread safety. 
