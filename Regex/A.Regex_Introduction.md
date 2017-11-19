# 正则表达式 #

## What Are Regular Expressions? ##

Regular expressions are a way to describe a set of strings based on common characteristics shared by each string in the set. They can be used to search, edit, or manipulate text and data. You must learn a specific syntax to create regular expressions — one that goes beyond the normal syntax of the Java programming language. 

>上面这段话一共三句，表明3个意思
>第1句：Regular expression是什么？
>第2句：Regular expression可以用来做什么？
>第3句：Regular expression需要特定的语法支持（a specific syntax）
>Regular expression可以表示一组字符串的集合（Regular expressions are a way to describe a set of strings），集合内的这些字符串具有共同的特征（based on common characteristics shared by each string in the set），这些共同特征是用来构建regular expression的基础。

Regular expressions vary in complexity, but once you understand the basics of how they're constructed, you'll be able to decipher (or create) any regular expression.

正则表达式：

	（1）正则表达式其实就是用来表达一组字符串共同特征的一个规则
	（2）正则表达式的规则使用了特殊的符号表示

示例：

	public class A_Introduction {
	
	    @Test
	    public void testIsNumer(){
	        //只能输入数字
	        String str = "124354232";
	        char[] arr = str.toCharArray();
	        boolean flag = true;
	        for(int i = 0 ;  i< arr.length ; i++){
	            if(!(arr[i]>=48&&arr[i]<=57)){
	                flag = false;
	            }
	        }
	        System.out.println(flag?"输入正确":"输出只能是数字");
	    }
	
	    @Test
	    public void testIsNumerWithRegex(){
	        //只能输入数字
	        String str = "12435423a2";
	        boolean flag = str.matches("[0-9]+");
	        System.out.println(flag?"输入正确":"只能输入数字");
	    }
	}