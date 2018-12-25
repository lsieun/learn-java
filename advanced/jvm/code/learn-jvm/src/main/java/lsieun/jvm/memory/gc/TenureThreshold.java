package lsieun.jvm.memory.gc;

public class TenureThreshold {
    private static final int _1BM = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio -XX:MaxTenuringThreshold=1
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1BM / 4];
        // 什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1BM];
        allocation3 = new byte[4 * _1BM];
        allocation3 = null;
        allocation3 = new byte[4 * _1BM];
    }
}
