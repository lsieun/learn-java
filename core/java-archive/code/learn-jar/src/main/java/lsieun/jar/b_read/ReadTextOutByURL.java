package lsieun.jar.b_read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadTextOutByURL {
    public static void main(String[] args) {
        try {

            URL url = new URL("jar:file:/home/liusen/Workspace/git-repo/learn-java/core/java-util/code/learn-jar/target/hello.jar!/text/readme.txt");
            InputStream in = url.openStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            reader.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
