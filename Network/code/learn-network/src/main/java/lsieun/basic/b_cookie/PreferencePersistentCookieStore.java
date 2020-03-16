package lsieun.basic.b_cookie;

import java.net.*;
import java.util.Formatter;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencePersistentCookieStore implements CookieStore, Runnable {
    private static final String SP_COOKIE_STORE = "cookieStore";
    private static final String SP_KEY_DELIMITER = "|"; // Unusual char in URL
    private static final String SP_KEY_DELIMITER_REGEX = "\\" + SP_KEY_DELIMITER;

    private CookieStore store;

    public PreferencePersistentCookieStore() {
        store = new CookieManager().getCookieStore();
        loadAllFromPersistence();
        // deserialize cookies into store
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    private Preferences getPreferences() {
        return Preferences.userRoot().node(SP_COOKIE_STORE);
    }

    private void loadAllFromPersistence() {
        try {
            Preferences prefs = getPreferences();
            String[] keys = prefs.keys();
            for (String key : keys) {
                String encodedCookie = prefs.get(key, null);
                if (encodedCookie == null) continue;
                String[] uriAndName = key.split(SP_KEY_DELIMITER_REGEX, 2);

                URI uri = new URI(uriAndName[0]);
                HttpCookie cookie = CookieUtils.decode(encodedCookie);
                store.add(uri, cookie);
            }
        } catch (BackingStoreException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // serialize cookies to persistent storage
        try {
            Preferences prefs = getPreferences();

            // 先清除
            prefs.clear();

            // 再存储
            List<URI> uri_list = store.getURIs();

            for (URI uri : uri_list) {
                List<HttpCookie> list = store.get(uri);
                for (HttpCookie cookie : list) {
                    if (cookie.hasExpired()) continue;

                    URI normalized_uri = cookieUri(uri, cookie);
                    saveToPersistence(normalized_uri, cookie);
                }
            }

            // 保证写入磁盘成功
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    private void saveToPersistence(URI uri, HttpCookie cookie) {
        Preferences prefs = getPreferences();
        String key = uri.toString() + SP_KEY_DELIMITER + cookie.getName();
        String value = CookieUtils.encode(cookie);
        prefs.put(key, value);
    }

    /**
     * Get the real URI from the cookie "domain" and "path" attributes, if they
     * are not set then uses the URI provided (coming from the response)
     *
     * @param uri
     * @param cookie
     * @return
     */
    private static URI cookieUri(URI uri, HttpCookie cookie) {
        URI cookieUri = uri;
        if (cookie.getDomain() != null) {
            // Remove the starting dot character of the domain, if exists (e.g: .domain.com -> domain.com)
            String domain = cookie.getDomain();
            if (domain.charAt(0) == '.') {
                domain = domain.substring(1);
            }
            try {
                cookieUri = new URI(uri.getScheme() == null ? "http"
                        : uri.getScheme(), domain,
                        cookie.getPath() == null ? "/" : cookie.getPath(), null);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return cookieUri;
    }

    // delegate all implementations to store object
    @Override
    public void add(URI uri, HttpCookie cookie) {
        store.add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return store.get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return store.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return store.getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return store.remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return store.removeAll();
    }

}
