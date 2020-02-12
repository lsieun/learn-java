# Port

Each computer with an IP address has several thousand logical ports (`65,535` per transport layer protocol, to be precise). These are purely abstractions in the computer’s memory and do not represent anything physical, like a USB port. Each port is identified by a number between `1` and `65535`. Each port can be allocated to a particular service.

For example, HTTP, the underlying protocol of the Web, commonly uses port 80. We say that a web server listens on port 80 for incoming connections. When data is sent to a web server on a particular machine at a particular IP address, it is also sent to a particular port (usually port 80) on that machine. The receiver checks each packet it sees for the port and sends the data to any program that is listening to that port. This is how different types of traffic are sorted out.
