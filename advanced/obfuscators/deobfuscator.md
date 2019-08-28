# deobfuscator

- [deobfuscator](https://javadeobfuscator.com/)
- [java-deobfuscator/deobfuscator](https://github.com/java-deobfuscator/deobfuscator)

## Quick Start

分两步：

- （1）第一步，就是分析出使用了哪种obfuscators的算法
- （2）第二步，针对obfuscators的算法进行还原

### 检测

Create `detect.yml` with the following contents. 

```txt
input: input.jar
detect: true
```

> Note: Replace input.jar with the name of the input

Run the following command to determine the obfuscators used

```bash
java -jar deobfuscator.jar --config detect.yml
```

### 解析

Create `config.yml` with the following contents.

```txt
input: input.jar
output: output.jar
transformers:
  - [fully-qualified-name-of-transformer]
  - [fully-qualified-name-of-transformer]
  - [fully-qualified-name-of-transformer]
  - ... etc
```

Run

```bash
java -jar deobfuscator.jar --config config.yml
```

Re-run the detection if the JAR was not fully deobfuscated - it's possible to layer obfuscations

