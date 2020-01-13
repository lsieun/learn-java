package lsieun.unsafe.d_object;

import lsieun.unsafe.utils.UnsafeUtils;

public class G_Password {
    public static void main(String[] args) {
        String password = new String("l00k@myHor$e");
        String fake = new String(password.replaceAll(".", "?"));
        System.out.println(password); // l00k@myHor$e
        System.out.println(fake); // ????????????

        UnsafeUtils.
        getUnsafe().copyMemory(
                fake, 0L, null, UnsafeUtils.toAddress(password), UnsafeUtils.sizeOf(password));

        System.out.println(password); // ????????????
        System.out.println(fake); // ????????????
    }
}
