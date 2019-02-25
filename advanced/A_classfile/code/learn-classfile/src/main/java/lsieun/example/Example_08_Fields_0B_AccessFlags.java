package lsieun.example;


import lsieun.domain.MemberEnum;

public class Example_08_Fields_0B_AccessFlags {
    public int publicField;
    private int privateField;
    protected int protectedField;

    static int staticField;
    final int finalField = 1;
    
    volatile int volatileField;
    transient int transientField;
    // TODO: 对于enum类型的还没有，目前不知道怎么写
}
