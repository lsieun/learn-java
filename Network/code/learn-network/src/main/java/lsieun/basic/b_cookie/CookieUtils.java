package lsieun.basic.b_cookie;

import java.io.*;
import java.net.*;
import java.util.Formatter;
import java.util.List;

public class CookieUtils {
    public static String getCookieInfo() {
        CookieManager manager = (CookieManager) CookieHandler.getDefault();
        CookieStore cookieStore = manager.getCookieStore();
        List<HttpCookie> list = cookieStore.getCookies();

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        for (HttpCookie cookie : list) {
            fm.format(">>> %s=%s, domain=%s, path=%s%n",
                    cookie.getName(),
                    cookie.getValue(),
                    cookie.getDomain(),
                    cookie.getPath());
        }
        return sb.toString();
    }

    public static String getCookieInfo2() {
        CookieManager manager = (CookieManager) CookieHandler.getDefault();
        CookieStore cookieStore = manager.getCookieStore();
        List<URI> uri_list = cookieStore.getURIs();

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        for (URI uri : uri_list) {
            fm.format(">>> %s%n", uri);
            List<HttpCookie> list = cookieStore.get(uri);

            for (HttpCookie cookie : list) {
                fm.format("    %s=%s, domain=%s, path=%s%n",
                        cookie.getName(),
                        cookie.getValue(),
                        cookie.getDomain(),
                        cookie.getPath());
            }
        }

        return sb.toString();
    }

    public static String encode(HttpCookie cookie) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            writeObject(out, cookie);
            return HexUtils.byteArrayToHexString(os.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static HttpCookie decode(String encodedCookie) {
        try {
            byte[] bytes = HexUtils.hexStringToByteArray(encodedCookie);
            ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(bai);
            return readObject(in);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static void writeObject(ObjectOutputStream out, HttpCookie cookie) throws IOException {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());

        out.writeObject(cookie.getDomain());
        out.writeObject(cookie.getPortlist());
        out.writeObject(cookie.getPath());

        out.writeLong(cookie.getMaxAge());
        out.writeBoolean(cookie.getSecure());
        out.flush();
    }

    private static HttpCookie readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();

        HttpCookie cookie = new HttpCookie(name, value);
        cookie.setDomain((String) in.readObject());
        cookie.setPortlist((String) in.readObject());
        cookie.setPath((String) in.readObject());

        cookie.setMaxAge(in.readLong());
        cookie.setSecure(in.readBoolean());

        return cookie;
    }
}
