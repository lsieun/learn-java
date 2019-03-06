package lsieun.structure;

public class FileBytes {
    private String url;
    private byte[] bytes;
    private int start;
    private int stop;
    private int index;

    public FileBytes(String url, byte[] bytes) {
        this.url = url;
        this.bytes = bytes;
        this.start = 0;
        this.stop = bytes.length;
        this.index = this.start;
    }

    // region getter & setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    // endregion

    public byte readByte() {
        byte b = bytes[index];
        this.index++;
        return b;
    }

    public void reset() {
        this.index = this.start;
    }

    @Override
    public String toString() {
        return "FileBytes{" +
                "start=" + start +
                ", stop=" + stop +
                ", index=" + index +
                ", url='" + url + '\'' +
                '}';
    }
}
