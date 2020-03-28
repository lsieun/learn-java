# Bitwise Math

<!-- TOC -->

- [1. Add](#1-add)
- [2. Subtraction](#2-subtraction)
- [3. Multiplication](#3-multiplication)
- [4. Division](#4-division)
- [5. Reference](#5-reference)

<!-- /TOC -->

There is a question asked on Stackoverflow : [Divide a number by 3 without using *,/,+,-,% operators](http://stackoverflow.com/questions/11694546/divide-a-number-by-3-without-using-operators). This question is an Oracle interview question. Some people give excellent answers. You can go there and take a look. Usually we need to use bitwise operators to do this kind of implementations. Here I want to show you ways to implement `+`,`-`,`*`,`/` with bitwise operators.

A bitwise operation operates on one or more bit patterns or binary numerals at the level of their individual bits. It is a fast, primitive action directly supported by the processor, and is used to manipulate values for comparisons and calculations. On simple low-cost processors, typically, bitwise operations are substantially faster than division, several times faster than multiplication, and sometimes significantly faster than addition. While modern high-performance processors usually perform addition and multiplication as fast as bitwise operations the latter may still be optimal for overall power/performance due to lower resource use. Some bitwise operators used frequently are `<<`(left shift),`>>`(right shift),`~`(complement),`^`(xor).

## 1. Add

```c
int add(int x,int y){
    int a,b;
    do{
        a=x&y;
        b=x^y;
        x=a<<1;
        y=b;
    }while(a);
    return b;
}
```

The positions that generate a carry<sub>进位</sub> and the positions that don’t generate a carry. In each run, the newly generated carrys will be added to the corresponding positions.

我的分析（一）：

- `a=x&y;`: 这里就指的是The positions that generate a carry<sub>进位</sub>
- `b=x^y;`: 这里指的是the positions that don’t generate a carry
- 抽象思考：在做加法的时候，按位进行操作时，本质是在处理这四种情况，第一种情况，就是0和0,第二种情况，就是0和1,第三种情况，就是1和0,第四种情况，就是1和1。就根据这四种情况，其实位操作`^`，就能处理前三种情况，而位操作`&`，是处理第四种情况
- `x=a<<1;`: 既然a中包含的需要“进位”的数据，那么就左移1个单位
- `y=b;`: 结合`b=x^y;`和`y=b;`这两个来看，`y`和`b`是相互“辅助、促进”的关系。最后，是以`y`的最后结果作为返回值。

我的分析（二）：

- 从bitwise的角度来说，加法使用到了`&`、`^`和`<<`这三种位操作

## 2. Subtraction

```c
int negate(int x){
    return add(~x,1);
}

int sub(int x,int y){
    return add(x,negate(y));
}
```

We can use the addition function implemented above here, first we implement how to get a negative number of a positive number. The `~x` is to get the 1's complement of `x` and then by adding 1, we get the 2's complement number. This number will be the negative number of `x`.

Ok, then substration is just a number adds another number's negative number.

我的分析（一）：

- 负数：将“求负数问题”，转化成“求加法问题”。它是借助于位操作`~`和加法来实现的
- 减法：将“求减法问题”，转化成“求负数问题”和“求加法问题”

我的分析（二）：

- 从bitwise的角度来说，“求负数问题”使用到了`~`位操作

## 3. Multiplication

```c
int mul(int x, int y){
    int m=1, z =0;
    if(x<0){
        x=negate(x);
        y=negate(y);
    }

    while(x>=m && y) {
        if (x &m) z=add(y,z);
        y <<= 1; m<<= 1;
    }
    return z;
}
```

The detail explanation for this implementation can be found in: [Adding Two Numbers With Bitwise and Shift Operators](http://geeki.wordpress.com/2007/12/12/adding-two-numbers-with-bitwise-and-shift-operators/). Here if `x<0`, we need to convert it to a positive number, otherwise, the result will be 0.

我的分析（一）：

- 乘法：将“求乘法问题”，转化成“位操作`<<`”和多个步骤的“求加法问题”。
- 符号：为了计算方便，它要求x是一个不能小于0的数，也就是正数或者是0。
- 精妙之处：`if(x & m) z=add(y,z);` 在这里体现出了将“乘法问题”转换成多个步骤的“加法问题”

我的分析（二）：

- 从bitwise的角度来说，`<<`位操作是一个数乘以2的直接转换

## 4. Division

```c
int divide(int x,int y){
    int c=0,sign=0;

    if(x<0){
        x=negate(x);
        sign^=1;
    }

    if(y<0){
        y=negate(y);
        sign^=1;
    }

    if(y!=0){
        while(x>=y){
            x=sub(x,y);
            ++c;
        }
    }
    if(sign){
        c=negate(c);
    }
    return c;
}
```

If both x and y are positive numbers, then we will going to a while loop. Basically, what the loop does is to subtract `y` from `x` if `x>=y`, the loop will end until `x` is less than `y`.

我的分析（一）：

- 除法：将“求除法问题”，转化成多个步骤的“减法问题”。
- 符号：在计算除法的过程中，先转换成正数之后，再进行处理，最后再处理符号问题

我的分析（二）：

- 我觉得，这个算法并不高效，因为如果x非常大，而y非常小，就需要计算很多次
- 如果可以借助于右移`>>`操作，或许可以增快很多

Note : The addition and multiplication implementations are from: [Adding Two Numbers With Bitwise and Shift Operators](http://geeki.wordpress.com/2007/12/12/adding-two-numbers-with-bitwise-and-shift-operators/).

## 5. Reference

- [Implementation of +,-,*,/ with bitwise operator](https://www.pixelstech.net/article/1344149505-Implementation-of-%2B---%2A-with-bitwise-operator)
