# TCP versus UDP

<!-- TOC -->

- [1. Reliability](#1-reliability)
- [2. Ordering](#2-ordering)
- [3. Header size](#3-header-size)
- [4. Speed](#4-speed)

<!-- /TOC -->

There are several differences between `TCP` and `UDP`. These differences include the following:

- **Reliability**: TCP is more reliable than UDP
- **Ordering**: TCP guarantees the order of packet transmission will be preserved
- **Header size**: The UDP header is smaller than the TCP header
- **Speed**: UDP is faster than TCP

## 1. Reliability

When a packet is sent using TCP, the packet is guaranteed to arrive. If it is lost, then it is re-sent.

UDP does not offer this guarantee. If the packet does not arrive, then it is not re-sent.

## 2. Ordering

TCP preserves the order that packets are sent in, while UDP does not.

If the TCP packets arrive at a destination in a different order than how they were sent, TCP will reassemble the packets in their original order.

With UDP, this ordering is not preserved.

## 3. Header size

When a packet is created, header information is attached to assist in the delivery of the packet.

With UDP the header consists of 8 bytes.

The usual size of a TCP header is 32 bytes.

## 4. Speed

With a smaller header size and lack of the overhead to ensure reliability, UDP is more efficient than TCP.

In addition, less effort is required to create a connection. This efficiency makes it a better choice to stream media.
