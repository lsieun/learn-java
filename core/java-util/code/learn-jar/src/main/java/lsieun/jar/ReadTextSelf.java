package lsieun.jar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadTextSelf {
    public static void main(String[] args) {
        try {
            ReadTextSelf instance = new ReadTextSelf();
            instance.readText();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readText() throws IOException {
        // 第一种方法
        //InputStream in = getClass().getResourceAsStream("/text/readme.txt");

        // 第二种方法。注意：路径开头没有“/”
        ClassLoader CLDR = this.getClass().getClassLoader();
        InputStream in = CLDR.getResourceAsStream("text/readme.txt");


        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        reader.close();
        in.close();
    }
}
