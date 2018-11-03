package lsieun.javaagent;

import java.lang.instrument.Instrumentation;


public class SimpleAgent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        SimpleClassFileTransformer transformer = new SimpleClassFileTransformer();
        instrumentation.addTransformer(transformer);
    }
}
