# Regex In Action #

正则表达式，主要是用于描述一组字符串的规则（字符串的共同特殊）。

正则表达式对字符串的操作主要有以下几种应用：

1. 匹配:String.matches()将字符串作为一个整体进行匹配
2. 切割：String.split()
3. 替换：String.replaceAll()
4. 查找：Pattern和Matcher匹配字符串中的一部分


使用regular expression过程中，比较难的地方在于：如果你写错了正则表达式，很可能你不知道是哪里写错了，因为它不像程序一样可以打断点。不过，还是有一个工具RegexBuddy可以帮助进行分析regular expression。

## 1、匹配 ##

匹配，是将字符串作为一个整体，用reguar expression去匹配；可以直接调用`String.matches`方法，但本质上是调用的`Pattern.matches`方法。

java.lang.String#matches定义如下：

    public boolean matches(String regex) {
        return Pattern.matches(regex, this);
    }

匹配手机号：

    /**
     * 需求：匹配是否为一个合法的手机号码。
     * 第1位：只能是1开头
     * 第2位：3 4 5 7 8
     * 长度：11位
     */
    public void matchesPhone(String phone){
        String reg = "1[34578]\\d{9}";
        System.out.println(phone.matches(reg)?"合法手机号":"非法手机号");
    }

匹配固话：

    /**
     * 需求2：匹配固定电话
     * 区号-主机号
     * 区号首位是0， 长度3~4位
     * 主机号首位不能是0，长度7~8位
     */
    public void matchesTel(String tel){
        String reg = "0\\d{2,3}-[1-9]\\d{6,7}";
        System.out.println(tel.matches(reg)?"合法固话":"非法固话");
    }

## 2、切割 ##

进行字符串切换，可以使用String的split方法。

java.lang.String#split(java.lang.String)

    public String[] split(String regex) {
        return split(regex, 0);
    }

空格切割：

    /**
     * 按照空格进行切割
     */
    public void splitSpace() {
        String str = "我    的  名  字   叫   永 不 放   弃";
        String reg = "[ ]+";
        String[] array = str.split(reg);
        System.out.println(Arrays.toString(array));
    }

输出：

	[我, 的, 名, 字, 叫, 永, 不, 放, 弃]


如果正则的内容需要被复用，那么需要对正则的内容进行分组。分组的目的就是为了提高正则的复用性。组号不能人为指定组号，默认组号从1开始，表示第1个遇到的左括号。

    /**
     * 根据重叠词进行分隔
     */
    public void splitDuplicateWord(){
        String str = "祝大大家明明明天玩玩玩玩的开开开开开心";
        String reg = "(.)\\1+";
        String[] array = str.split(reg);
        System.out.println(Arrays.toString(array));
    }

输出：

	[祝, 家, 天, 的, 心]


## 3、替换 ##

可以调用String的replaceAll方法。

java.lang.String#replaceAll

    public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }


示例：将手机号替换为“****”

    public void replaceAllPhone(){
        StringBuilder sb = new StringBuilder();
        sb.append("北京移动15810103829北京联通13520983362");
        sb.append("北京移动13661287209北京联通13683268910");
        sb.append("北京移动15101682015北京联通13520238157");
        sb.append("北京移动13521658027北京联通15010729506");
        sb.append("北京移动15201172732北京联通15810060872");
        sb.append("北京移动13552915937北京联通13260070970");
        String str = sb.toString();
        String reg = "1[34578]\\d{9}";
        String newStr = str.replaceAll(reg, "****");
        System.out.println(newStr);
    }

部分输出：

	北京移动****北京联通****北京移动****北京联通****

如果需要在replaceAll方法正则的外部引用组的内容，那么使用“$组号”。

示例：去除多余的重复字，只留下一个字

    /**
     * 去除多余的重复字，只留下一个字
     */
    public void replaceAllDuplicateWord(){
        String str = "天天道道道酬酬酬酬勤勤勤勤勤勤";
        String newString = str.replaceAll("(.)\\1+","$1");
        System.out.println(newString);
    }

输出：

	天道酬勤

## 4、查找 ##

查找需要使用的对象：

1. Pattern对象 
2. Matcher对象

Matcher要使用的方法有

1. find() ：通知Matcher去匹配字符串，查找符合规则的字符串。如果查找到符合规则的字符串，则返回True，否则返回False。
2. group()：获取符合规则的字符串。

**注意**：使用group()方法的时候一定要先调用find()方法让匹配器去查找符合规则的字符串，否则报错。

> 注意：Matcher对象的matches()方法和find()方法的区别，一个着眼于整体，一个着眼于局部。
> Matcher对象的matches()方法是对字符串的整体进行匹配，即Attempts to match the entire region against the pattern.
> 而Matcher对象的find()方法对字符串的局部进行匹配，即Attempts to find the next subsequence of the input sequence that matches the pattern.


----------


Class `Pattern` is a compiled representation of a regular expression.

> Pattern类可以理解为一个已经编译过(compiled)的正则表达式。

A regular expression, specified as a string, must first be compiled into an instance of this class. The resulting pattern can then be used to create a Matcher object that can match arbitrary character sequences against the regular expression. All of the state involved in performing a match resides in the matcher, so many matchers can share the same pattern.

>顺序：regular expression(String) --> Pattern --> Matcher
>reguar expression和Pattern应该是一对一的关系，而Pattern和Matcher可以是一对多的关系。
>用regular expression去匹配字符串的所有状态信息都存储在Matcher对象当中。

A typical invocation sequence is thus

	Pattern p = Pattern.compile("a*b");
	Matcher m = p.matcher("aaaaab");
	boolean b = m.matches();

A `matches` method is defined by this class as a convenience for when a regular expression is used just once. This method compiles an expression and matches an input sequence against it in a single invocation. The statement

	boolean b = Pattern.matches("a*b", "aaaaab");

is equivalent to the three statements above, though for repeated matches it is less efficient since it does not allow the compiled pattern to be reused.

> 这种方法和上面的三行Java代码达到的效果是一致的，但是谈到复用时，效率就会低下来
> 两者的区别：三行的代码中调用的是Matcher的matches方法，而一行的代码中调用的是Pattern的matches方法。

Instances of this class are immutable and are safe for use by multiple concurrent threads. Instances of the Matcher class are not safe for such use.

> Pattern是线程安全的，而Matcher是线程不安全的。


----------


示例：在一段话中查找出由3个字母组成的单词（有正确写法和错误写法两种情况哟~~~）
	
    public void patternFind(){
        String content = "You are one of the most beautiful girl I have ever seen.";
        //错误的写法：
        String reg = "[a-zA-Z]{3}b";
        //正确的写法：
        String reg = "\\b[a-zA-Z]{3}\\b";

        //先把regular expression的字符串编译成Pattern对象
        Pattern p = Pattern.compile(reg);
        //使用Pattern对象匹配内容字符串(content)，从而产生一个Matcher对象
        Matcher matcher = p.matcher(content);

        while(matcher.find()){
            String value = matcher.group();
            System.out.println(value);
        }
    }

正确写法的输出：

	You
	are
	one
	the

错误写法的输出：

	You
	are
	one
	the
	mos
	bea
	uti
	ful
	gir
	hav
	eve
	see

由上面这个例子，我们了解到了“单词边界匹配器”：

	\b 单词边界匹配器，只是代表了单词的开始和结束部分，不匹配任何的字符。

为了加深对“单词边界匹配器”的理解，请看下面的例子：

	System.out.println("Hello World".matches("Hello\\bWorld"));//false
	System.out.println("Hello World".matches("Hello\\b World"));//true

之所以有这样的结棍，是因为单词边界匹配器不匹配任何的字符，只是作为边界的标识。

> 至此结束














