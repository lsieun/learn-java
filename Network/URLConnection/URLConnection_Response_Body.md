# Reading Response Data from a Server

The following is the minimal set of steps needed to retrieve data from a `URL` using a `URLConnection` object:

1. Construct a `URL` object.
2. Invoke the `URL` object’s `openConnection()` method to retrieve a `URLConnection` object for that `URL`.
3. Invoke the `URLConnection`’s `getInputStream()` method.
4. Read from the input stream using the usual stream API.

The `getInputStream()` method returns a generic `InputStream` that lets you read and parse the data that the server sends.

## Read Text Data

```java
String url = "http://www.example.com";
try {
    // Open the URLConnection for reading
    URL u = new URL(url);
    URLConnection uc = u.openConnection();
    try (InputStream raw = uc.getInputStream()) { // autoclose
        InputStream buffer = new BufferedInputStream(raw);
        // chain the InputStream to a Reader
        Reader reader = new InputStreamReader(buffer);
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
} catch (MalformedURLException ex) {
    System.err.println(url + " is not a parseable URL");
} catch (IOException ex) {
    System.err.println(ex);
}
```

## Read Binary Data

```java
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
```
