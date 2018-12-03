package lsieun.javaagent.util;

public class CCRun {
    public static void main(String args[]) throws Exception {
        String progClass = "lsieun.jvm.classloader.custom.Foo";
        String progArgs[] = new String[]{"1212", "1313"};

        CCLoader ccl = new CCLoader(CCRun.class.getClassLoader());
        System.out.println("CCLoader: " + ccl);

//        Class clazz = ccl.loadClass(progClass);
//        Class mainArgType[] = { (new String[0]).getClass() };
//        Method main = clazz.getMethod("main", mainArgType);
//        Object argsArray[] = { progArgs };
//        main.invoke(null, argsArray);
//
//        // Below method is used to check that the Foo is getting loaded
//        // by our custom class loader i.e CCLoader
//        Method printCL = clazz.getMethod("printCL", null);
//        printCL.invoke(null, new Object[0]);
    }
}
