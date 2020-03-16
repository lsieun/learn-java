# Integer factorization

In number theory, **integer factorization**(因式分解；分解为因子；因数分解法) is the decomposition of a composite number into a product of smaller integers. 

> 先介绍一个大的概念： integer factorization

If these integers are further restricted to prime numbers, the process is called **prime factorization**.

> 再介绍一个相对小的概念： prime factorization

When the numbers are sufficiently large, no efficient, non-quantum integer factorization algorithm is known. 

> quantum 量子  
> 当numbers足够大的的时候，目前还没有一个有效的方法进行integer factorization。

An effort by several researchers, concluded in 2009, to factor a 232-digit number (RSA-768) utilizing hundreds of machines took two years and the researchers estimated that a 1024-bit RSA modulus would take about a thousand times as long. However, it has not been proven that no efficient algorithm exists. 

> 这里理解3个意思：  
> （1）举一个例子，在2009年，数名研究者，为了分解一个232位的数字（可能是768个bit表示），使用了数百台计算机，耗时2年的时间。  
> （2）进一步预测，研究者预测说，要分解一个1024个bit的数（RSA modulus），要花费1000倍的时间；换句话说，就是2000年。 
> （3）为了严谨性，又再进一步说明，即使如此，这并不代表“没有有效的算法存在”。 

**The presumed difficulty of this problem** is at the **heart** of widely used algorithms in cryptography such as **RSA**. 

> RSA算法，正是基于这样一个“假设的难题”（The presumed difficulty of this problem）

Many areas of mathematics and computer science have been brought to bear on the problem, including **elliptic curves**, **algebraic number theory**, and **quantum computing**.

> bearn on 对…产生影响；涉及  
> 

Not all numbers of a given length are equally hard to factor. The hardest instances of these problems (for currently known techniques) are **semiprimes**, the product of two prime numbers.

