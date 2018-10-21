# 轻松理解java的File，InputStream，InputStreamReader，BufferedReader之间的关系

URL: https://blog.csdn.net/learner_lps/article/details/52463173

1.前言
    在开始学习java文件读写的时候，仅仅为了从文件中读取一个字符而已，就需要一下子和InputStream、InputStreamReader、File几个类打交道。实话说，笔者刚开始接触的时候，花了很长时间在理解他们三者的关系，不知道其他初学者是否也和笔者有同样的感触，写这篇博文是为了把自己理解的分享给大家，有不对之处敬请指正。
2.轻松理解InputStream、InputStreamReader、BufferedReader和File
    我们都知道计算机能识别的只有0和1，数据是以字节为单位存储的。
    a.故在java中，我们若想要从文件中读取一个字符，首先我们需要有一个文件(数据源)，于是我们就使用到了File类来制定一个文件;
    b. 因为数据在计算机中都是0和1代码，以字节为单位，所以我们所指定的file对象就需要先转化成字节流，所以需要用到InputStream类；
    c. 这时候，如果在java中只是以字节来读取文件的话，就足够了。但是如果想要以字符来读取文件的话，还需要对字节流进行以某种方式编码，因为不同的字符采用不同的编码方式，如果仍然使用字节流的话，可能会达不到我们想要的效果。我们可以指定字符编码方式，这个通过在InputStreamReader相关类的构造器中指定并实例化，默认下是Unicode编码方式。到了这里，我们已经用到了InputStreamReader。
    d. 经过了上面三个步骤，我们已经可以从文件中读取到一个字符了，但是每次只读取一个字符又不能满足我们读取大数据的需求，这时候BufferedReader就派上用场了。从名字中的Buffer我们可以推测出它具有缓冲的作用，事实也是如此，它可以一次读取多个字符，一次性读取的字符数目还可以自由设置。
    写到这里，不知道读者是否能够简单地知道FIle、InputStream、InputStreamReader 和 BufferedReader的关系了

---------------------

本文来自 Nancyse 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/learner_lps/article/details/52463173?utm_source=copy 
