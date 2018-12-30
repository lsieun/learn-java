# JVM

## JVM Options

GC

- `-XX:+UseSerialGC`: to select serial collector.
- `-XX:+UseParallelGC`: to select parallel collector.
- `-XX:+UseParallelOldGC`: to enable parallel compaction.
- `-XX:+UseConcMarkSweepGC`: to select concurrent collector.

### Debug

- `-XX:+PrintGCDetails`

## GC

### Serial Collector

```bash
java -XX:+UseSerialGC -XX:+PrintGCDetails -version
java -XX:+UseParallelGC -XX:+PrintGCDetails -version
java -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -version
```

Printing information about current GC used

```bash
java -XX:+PrintGCDetails -version
```

Storing GC information in a file `gc.txt`

```bash
java -XX:+PrintGCDetails -Xloggc:gc.txt -version
```

Show all VM related settings:

```bash
java -XshowSettings:vm -version
java -Xmx128M -XshowSettings:vm -version
```
