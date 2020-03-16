package lsieun.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteCommand {
    public static void main(String[] args) {
        String domainName = "www.baidu.com";

        // in mac oxs
        String command = "ping -c 3 " + domainName;

        // in windows
        // String command = "ping -n 3" + domainName;

        //command = "ls /home/liusen";
        command = "java -jar /home/liusen/workdir/dummy/tmp/tank.jar";
        String output = executeCommand(command);
        System.out.println(output);
    }

    private static String executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = null;
            while ((line = br.readLine()) != null) {
                output.append(line + "\n");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return output.toString();
    }
}
