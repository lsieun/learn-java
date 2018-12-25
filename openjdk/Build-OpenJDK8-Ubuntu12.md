# Build OpenJDK8

The minimum build environment that should produce the most compatible **OpenJDK 8** binaries on Linux is **Oracle Linux 6.4 amd64** with **GCC Version 4.7**. We will configure **Ubuntu 12.04** to use **GCC 4.7** to be close to the minimum build environment.

The following procedures will help us to build OpenJDK 8:

## Install the prepackaged binaries of OpenJDK 7

```bash
sudo apt-get install openjdk-7-jdk
```

## Add an additional packages repository:

```bash
sudo add-apt-repository ppa:ubuntu-toolchain-r/test
sudo apt-get update
```

## Install the GCC toolchain and build dependencies:

```bash
sudo apt-get build-dep openjdk-7
```

## Install the C and C++ compilers, Version 4.7:

```bash
sudo apt-get install gcc-4.7
sudo apt-get install g++-4.7
```

## Go to the `/usr/bin` directory and set up default compiler-symbolic links:

```bash
sudo rm gcc
sudo ln -s gcc-4.7 gcc
sudo rm g++
sudo ln -s g++-4.7 g++
```

## Download and decompress the official OpenJDK 8 source code archive

## Run the autotools configuration script:

```bash
bash ./configure
```

## Start the build:

```bash
make all 2>&1 | tee make_all.log
```

## Run the build binaries:

```bash
cd build/linux-x86_64-normal-server-release/images/
./bin/java -version
```

Output:

```txt
openjdk version "1.8.0-internal"
OpenJDK Runtime Environment (build 1.8.0-internal-
ubuntu_2014_02_22_08_51-b00)
OpenJDK 64-Bit Server VM (build 25.0-b69, mixed mode)
```

## Go back to the source's root and build the compact profile's images:

```bash
make profiles 2>&1 | tee make_profiles.log
```

Check the profiles images in the `build/linux-x86_64-normal-server-release/images` directory:

```txt
j2re-compact1-image
j2re-compact2-image
j2re-compact3-image
```


