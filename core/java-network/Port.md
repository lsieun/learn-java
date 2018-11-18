# Port

Port numbers range from `0` to `65535`.

Port numbers are broken down into **three types**:

- **Well-known ports** (`0` to `1023`): These are port numbers that are used for relatively common services.
- **Registered ports** (`1024` to `49151`): These are port numbers that are assigned by IANA to a process.
- **Dynamic/private ports** (`49152` to `65535`): These are dynamically assigned to clients when a connection is initiated. These are normally temporary and cannot be assigned by IANA.


## List of TCP and UDP port numbers

URL: [List of TCP and UDP port numbers](https://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers)

### Well-known ports

| Port | TCP           | UDP      | IANA status | Description                                                  |
| ---- | ------------- | -------- | ----------- | ------------------------------------------------------------ |
| 25   | Yes           | Assigned | Official    | Simple Mail Transfer Protocol(SMTP), used for email routing between mail servers |
| 80   | Yes, and SCTP | Assigned | Official    | Hypertext Transfer Protocol (HTTP)                           |
| 443  | Yes, and SCTP | Assigned | Official    | Hypertext Transfer Protocol over TLS/SSL (HTTPS)             |
|      |               |          |             |                                                              |
|      |               |          |             |                                                              |
|      |               |          |             |                                                              |
|      |               |          |             |                                                              |

### Registered ports

| Port | TCP  | UDP      | IANA status | Description                                                  |
| ---- | ---- | -------- | ----------- | ------------------------------------------------------------ |
| 1433 | Yes  | Yes      | Official    | Microsoft SQL Server database management system (MSSQL) server |
| 3306 | Yes  | Assigned | Official    | MySQL database system                                        |
|      |      |          |             |                                                              |
|      |      |          |             |                                                              |
|      |      |          |             |                                                              |

