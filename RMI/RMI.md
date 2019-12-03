# Remote Method Invocation (RMI)

URL:

- [Getting Started with Java RMI](https://www.baeldung.com/java-rmi)

## Providing an Overview of RMI

**RMI** is the action of invoking a method of a remote interface on a remote object. It enables Java clients to invoke methods on Java objects living on the remote computer’s Java Virtual Machine (JVM). Both JVMs can be running on the same or different computers, but the most important thing is that the application has to be distributed, which in the case of RMI means that **it should use at least two JVMs**. The client program will “have a feeling” that it calls local methods, but actually these calls will be redirected to a remote server.

> RMI并一定是在两台机器上，重点是在不同的两个JVM之间的方法调用。

Since RMI is a part of J2SE, it’s available on all platforms that support Java, and does not require any additional software.

> RMI是属于JavaSE的技术。

Any RMI application consists of the following **components**:

- Client
- Server
- Registry

The **registry** is the naming service. **The RMI components** usually run on separate networked computers(三种不同的components可以分布在不同的机器上). The **server** creates some Java objects, registers them with the naming service, and waits for remote clients to invoke methods on these objects. A **client** application gets a reference to a remote-server object from the registry and then invokes methods on this remote object. **The main concept of RMI** is that even though the methods are **being called** in the **client’s JVM**, they are **executed** on the **server’s JVM**.

> 描述三个component之间的协作关系。

The best part of RMI applications is that a developer does not have to program the network communications — the programming is done automatically by a special tool called an **RMI compiler**(`rmic`). This tool generates **two additional classes**, **stub** and **skeleton**, that will take care of data exchange over the network by using data marshalling — presentation of Java objects as a set of bytes — and serialization.

## Developing Applications with RMI

Writing distributed RMI applications usually consists of the following steps:

- (1) Declaring a remote interface for the client
- (2) Implementing a remote interface on the server
- (3) Writing a client program that uses this remote interface to connect to a server and call its methods
- (4) Generating stubs (client proxies) and skeletons (server entities)
- (5) Starting the registry on the server and registering remote objects with it
- (6) Starting the server application on the remote machine
- (7) Starting the Java application that is either located on the client machine

## Important Observations

- (1) RMI is a pure java solution to Remote Procedure Calls (RPC) and is used to create distributed application in java.
- (2) Stub and Skeleton objects are used for communication between client and server side.
