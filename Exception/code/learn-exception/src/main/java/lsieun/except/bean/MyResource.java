package lsieun.except.bean;

public class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new YyyException();
    }
}
