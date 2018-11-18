# TCP versus UDP

There are several differences between `TCP` and `UDP`. These differences include the following:

- **Reliability**: TCP is more reliable than UDP
- **Ordering**: TCP guarantees the order of packet transmission will be preserved
- **Header size**: The UDP header is smaller than the TCP header
- **Speed**: UDP is faster than TCP

## Reliability

When a packet is sent using TCP, the packet is guaranteed to arrive. If it is lost, then it is re-sent. 

UDP does not offer this guarantee. If the packet does not arrive, then it is not re-sent.

## Ordering

TCP preserves the order that packets are sent in, while UDP does not. 

If the TCP packets arrive at a destination in a different order than how they were sent, TCP will reassemble the packets in their original order. 

With UDP, this ordering is not preserved.

## Header size

When a packet is created, header information is attached to assist in the delivery of the packet. 

With UDP the header consists of 8 bytes. 

The usual size of a TCP header is 32 bytes.

## Speed

With a smaller header size and lack of the overhead to ensure reliability, UDP is more efficient than TCP. 

In addition, less effort is required to create a connection. This efficiency makes it a better choice to stream media.


