public class LoadClassError {

    public static void main(String[] args) {
        try{
            Class.forName("lsieun.NotExistClass");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
