package lsieun.sys;

/**
 * By convention in Unix, a status of 0 means a normal exit,
 * while non-zero means some error occurred
 */
public class AppExit {
    public static void main(String[] args) {
        boolean error = true;
        if (error) {
            System.exit(1);
        }
        else {
            System.exit(0);
        }
    }
}
