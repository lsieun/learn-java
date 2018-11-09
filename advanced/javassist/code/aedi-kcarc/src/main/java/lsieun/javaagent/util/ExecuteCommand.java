package lsieun.javaagent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExecuteCommand {
    public static String run(String dir, String[] commands) {
        System.out.println("Run Command: " + Arrays.toString(commands));
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder = builder.directory(new File(dir));
            p = builder.start();
            //p = Runtime.getRuntime().exec(command);
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
