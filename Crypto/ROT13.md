# ROT13

**ROT13** ("**rotate by 13 places**", sometimes hyphenated **ROT-13**) is a simple letter substitution cipher that replaces a letter with the 13th letter after it, in the alphabet. 

| Input/Output | Value                                                  |
| ------------ | ------------------------------------------------------ |
| Input        | `ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz` |
| Output       | `NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm` |

![](images/ROT13_table_with_example.svg)

Because there are 26 letters (2×13) in the basic Latin alphabet, ROT13 is its own inverse; that is, to undo ROT13, the same algorithm is applied, so the same action can be used for encoding and decoding. 

> 在字母表中，一共有26个字母，进行13个字母的偏移，因而ROT13的逆向算法就是ROT13本身。  
> 由此，ROT13，即可以用于encoding，又可以用于decoding。


The algorithm provides virtually no cryptographic security, and is often cited as a canonical example of weak encryption.

> ROT13算法，本质上，对于加密的内容，并没有提供很大的安全性，只是作为一个弱加密的典型案例。

For example, in the following joke, the punchline has been obscured by ROT13:

```
Why did the chicken cross the road?
Gb trg gb gur bgure fvqr!
```

Transforming the entire text via ROT13 form, the answer to the joke is revealed:

```
Jul qvq gur puvpxra pebff gur ebnq?
To get to the other side!
```

A second application of ROT13 would restore the original.

Full Code:

```java
public class Rot13 {
    public static void main(String[] args) {
        String str = "Why did the chicken cross the road? Gb trg gb gur bgure fvqr!";
        String value = rot13(str);
        System.out.println(value);
    }

    public static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }
}
```

Output:

```txt
Jul qvq gur puvpxra pebff gur ebnq? To get to the other side!
```
