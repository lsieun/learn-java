# RMI registry

The registry API is defined by the `java.rmi.registry.Registry` interface.

The RMI registry runs by default on port `1099`, unless another port number is specified. When the client wants to invoke methods on the remote object it obtains a reference to that object by looking up the name. The lookup returns to the client a stub of the remote object.

The method takes the object’s URL as an argument in this format:

```txt
rmi://<hostname>[:<name_service_port>]/<service_name>
```

These components are described as follows:

- `hostname` is the name of the computer on the local area network (LAN) or a DNS name on the Internet.
- `name_service_port` has to be specified only if the naming service is running on a port other than the default one (`1099`).
- `service_name` is the name of the remote object that should have been bound to the registry.
