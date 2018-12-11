package lsieun.javadoc.geometry;

/**
 * Javadoc link test.
 * Square is subclass of {@link Rectangle}
 * @see Rectangle
 */
public class Square extends Rectangle {
    /**
     * Sets size.
     * Below two lines refers same method.
     * @param edge length of square
     * @see Rectangle#setSize
     * @see #setSize(int width, int height)
     */
    public void setSize(int edge) {
        super.setSize(edge, edge);
    }
}
