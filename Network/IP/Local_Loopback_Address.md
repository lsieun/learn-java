# Local Loopback Address

**Local Loopback Address** is used to let a system send a message to itself to make sure that TCP/IP stack is installed correctly on the machine.

In IPv4, IP addresses that start with decimal `127` or that has `01111111` in the first octet are loopback addresses(`127.X.X.X`). Typically `127.0.0.1` is used as the local loopback address. This leads to the wastage of many potential IP addresses.

But in IPv6 `::1` is used as local loopback address and therefore there isn’t any wastage of addresses.
