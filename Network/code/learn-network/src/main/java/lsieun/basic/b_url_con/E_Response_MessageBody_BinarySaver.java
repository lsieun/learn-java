package lsieun.basic.b_url_con;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class E_Response_MessageBody_BinarySaver {
    public static void main (String[] args) {
        String url = "http://ui.sina.com/assets/img/www/worldmap.jpg";
        try {
            URL root = new URL(url);
            saveBinaryFile(root);
        } catch (MalformedURLException ex) {
            System.err.println(url + " is not URL I understand.");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1 ) {
            throw new IOException("This is not a binary file.");
        }
        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) break;
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset
                        + " bytes; Expected " + contentLength + " bytes");
            }
            String filename = u.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            String filepath = System.getProperty("user.dir") + "/target/" + filename;
            new File(filepath).getParentFile().mkdirs();
            System.out.println("file://" + filepath);
            try (FileOutputStream fout = new FileOutputStream(filepath)) {
                fout.write(data);
                fout.flush();
            }
        }
    }
}
