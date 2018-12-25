# Build OpenJDK 7

## Download

URL: [Java Platform, Standard Edition 7 Reference Implementations](http://jdk.java.net/java-se-ri/7)

## modify code


## Install the prepackaged binaries of OpenJDK 6

The prepackaged binaries of OpenJDK 6 are required because some of the build steps are run using the external Java runtime.

```bash
sudo apt-get install openjdk-6-jdk
```

## Install the GCC toolchain and build dependencies

```bash
sudo apt-get build-dep openjdk-6
```

The `build-dep` command is used to install all the dependencies that are required to build
the specified package. As Ubuntu packaged OpenJDK 6 is quite close to the official OpenJDK
6, this command will install almost all the required dependencies.

## Create a new text file `buildenv.sh` with the following environment settings:

```txt
export LD_LIBRARY_PATH=
export CLASSPATH=
export JAVA_HOME=
export LANG=C
export ALT_BOOTDIR=/usr/lib/jvm/java-6-openjdk-amd64
```

## Import the environment into the current shell session 

Note: **a dot**(`.`) and **a space** before `buildenv.sh`:

```bash
. buildenv.sh
```

## Start the build process from the `openjdk` directory:

```bash
cd openjdk
make 2>&1 | tee make.log
```

## Wait for the build to finish, and try to run the newly built binaries

```bash
cd build/linux-amd64/j2sdk-image/
./bin/java –version
```

Output:

```txt
openjdk version "1.7.0-internal"
OpenJDK Runtime Environment (build 1.7.0-internal-
ubuntu_2014_02_08_08_56-b00)
OpenJDK 64-Bit Server VM (build 24.0-b56, mixed mode)
```

After a successful build on the amd64 platform, **JDK** files will be placed into `build/linux-amd64/j2sdk-image` and **JRE** files will be placed into `build/linux-amd64/j2re-image`. On the i586 platform, the `build/linux-i586` path will be used instead. An additional package of Server JRE that contains JDK without demos and samples will be placed into the `j2sdk-server-image` directory.

编译失败后清理：make clean

time is more than 10 years from present
Error: time is more than 10 years from present: 1136059200000
java.lang.RuntimeException: time is more than 10 years from present: 1136059200000
  at build.tools.generatecurrencydata.GenerateCurrencyData.makeSpecialCaseEntry(GenerateCurrencyData.java:285)
  at build.tools.generatecurrencydata.GenerateCurrencyData.buildMainAndSpecialCaseTables(GenerateCurrencyData.java:225)
  at build.tools.generatecurrencydata.GenerateCurrencyData.main(GenerateCurrencyData.java:154)


# 解决办法
# 修改CurrencyData.properties（路径：jdk/src/share/classes/java/util/CurrencyData.properties）
修改108行
AZ=AZM;2009-12-31-20-00-00;AZN
修改381行
MZ=MZM;2009-06-30-22-00-00;MZN
修改443行
RO=ROL;2009-06-30-21-00-00;RON
修改535行
TR=TRL;2009-12-31-22-00-00;TRY
修改561行
VE=VEB;2009-01-01-04-00-00;VEF
--------------------- 