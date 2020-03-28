package lsieun.hash.md5;

public class Test2 {
    public static void main(String[] args) {
        byte[] bytes = MD5Example.collision_str_2.getBytes();

        A_MD5 md5 = new A_MD5();
        md5.Update(bytes, bytes.length);
        System.out.println("Hash            : " + md5.asHex());

        // edde4181249fea68547c2fd0edd2e22f
        // e234dbc6aa0932d9dd5facd53ba0372a
    }
}
