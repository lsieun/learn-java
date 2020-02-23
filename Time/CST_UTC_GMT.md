# 三个概念: CST/UTC/GMT

三个概念：

- CST
- UTC
- GMT

CST可视为美国、澳大利亚、古巴或中国的标准时间。
CST可以为如下4个不同的时区的缩写：

- 美国中部时间：Central Standard Time (USA) UT-6:00
- 古巴标准时间：Cuba Standard Time UT-4:00
- 中国标准时间：China Standard Time UT+8:00
- 澳大利亚中部时间：Central Standard Time (Australia) UT+9:30

协调世界时，又称世界统一时间、世界标准时间、国际协调时间。由于英文（CUT）和法文（TUC）的缩写不同，作为妥协，简称**UTC**。

Greenwich Mean Time (**GMT**) 世界标准时刻；格林威治时间

## The Difference Between GMT and UTC

There is no time difference between **Coordinated Universal Time** and **Greenwich Mean Time**. **2:44 a.m. Tuesday, Coordinated Universal Time (UTC)** is
**2:44 a.m. Tuesday, Greenwich Mean Time (GMT)**.

**Greenwich Mean Time (GMT)** is often interchanged or confused with **Coordinated Universal Time (UTC)**. But **GMT** is **a time zone** and **UTC** is **a time standard**.

Although **GMT** and **UTC** share the same current time in practice, there is a basic difference between the two:

- **GMT** is a **time zone** officially used in some European and African countries. The time can be displayed using both the 24-hour format (0 - 24) or the 12-hour format (1 - 12 am/pm).
- **UTC** is not a time zone, but **a time standard** that is the basis for civil time and time zones worldwide. This means that no country or territory officially uses UTC as a local time.

**GMT** defines the **Time Zones** around the world which are based on the
"Prime Meridian" (longitude zero) at Greenwich, England. **GMT** is the **local time** at the longitude of Greenwich. It is also the time anywhere within the time zone where the time is the time at Greenwich. **That time zone** is also called **GMT**. In theory the GMT zone should be 7.5 degrees either side of Greenwich, but the actual zone is far different, for convenience and political reasons.

```txt
UTC = GMT+0
```

**The UTC time** is always the **GMT** at `0` meridian, so to calculate `UTC` from `GMT` you just need to substract the timezone of the GMT for making it zero.

Examples:

- 1) For `09:00:00 GMT+2`, then `UTC = 09:00:00 - 2hs = 07:00:00`
- 2) For `09:00:00 GMT-5`, then `UTC = 09:00:00 + 5hs = 14:00:00`
