public class CustomerClassLoader extends ClassLoader {
    public CustomerClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class getClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFTP(name);
        return defineClass(name, b, 0, b.length);
    }

    @Override
    public class loadClass(String name) throws ClassNotFoundException {
        if (name.startswith("com.lsieun")) {
            System.out.println("Loading Class from customer Class Loader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

    private byte[] loadClassFromFTP(String fileName) {
        // Return a byte array from specified file
        return null;
    }
}
