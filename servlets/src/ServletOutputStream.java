package javax.servlet;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Provides an output stream for sending binary data to the client. A
 * <code>ServletOutputStream</code> object is normally retrieved via the
 * {@link ServletResponse#getOutputStream} method.
 * <p>
 * This is an abstract class that the servlet container implements. Subclasses
 * of this class must implement the <code>java.io.OutputStream.write(int)</code>
 * method.
 *
 * @see ServletResponse
 */
public abstract class ServletOutputStream extends OutputStream {

    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static final ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    /**
     * Does nothing, because this is an abstract class.
     */
    protected ServletOutputStream() {
        // NOOP
    }

    /**
     * Writes a <code>String</code> to the client, without a carriage
     * return-line feed (CRLF) character at the end.
     *
     * @param s
     *            the <code>String</code> to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(String s) throws IOException {
        if (s == null)
            s = "null";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            //
            // XXX NOTE: This is clearly incorrect for many strings,
            // but is the only consistent approach within the current
            // servlet framework. It must suffice until servlet output
            // streams properly encode their output.
            //
            if ((c & 0xff00) != 0) { // high order byte must be zero
                String errMsg = lStrings.getString("err.not_iso8859_1");
                Object[] errArgs = new Object[1];
                errArgs[0] = Character.valueOf(c);
                errMsg = MessageFormat.format(errMsg, errArgs);
                throw new CharConversionException(errMsg);
            }
            write(c);
        }
    }

    /**
     * Writes a <code>boolean</code> value to the client, with no carriage
     * return-line feed (CRLF) character at the end.
     *
     * @param b
     *            the <code>boolean</code> value to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(boolean b) throws IOException {
        String msg;
        if (b) {
            msg = lStrings.getString("value.true");
        } else {
            msg = lStrings.getString("value.false");
        }
        print(msg);
    }

    /**
     * Writes a character to the client, with no carriage return-line feed
     * (CRLF) at the end.
     *
     * @param c
     *            the character to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(char c) throws IOException {
        print(String.valueOf(c));
    }

    /**
     * Writes an int to the client, with no carriage return-line feed (CRLF) at
     * the end.
     *
     * @param i
     *            the int to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(int i) throws IOException {
        print(String.valueOf(i));
    }

    /**
     * Writes a <code>long</code> value to the client, with no carriage
     * return-line feed (CRLF) at the end.
     *
     * @param l
     *            the <code>long</code> value to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(long l) throws IOException {
        print(String.valueOf(l));
    }

    /**
     * Writes a <code>float</code> value to the client, with no carriage
     * return-line feed (CRLF) at the end.
     *
     * @param f
     *            the <code>float</code> value to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(float f) throws IOException {
        print(String.valueOf(f));
    }

    /**
     * Writes a <code>double</code> value to the client, with no carriage
     * return-line feed (CRLF) at the end.
     *
     * @param d
     *            the <code>double</code> value to send to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void print(double d) throws IOException {
        print(String.valueOf(d));
    }

    /**
     * Writes a carriage return-line feed (CRLF) to the client.
     *
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println() throws IOException {
        print("\r\n");
    }

    /**
     * Writes a <code>String</code> to the client, followed by a carriage
     * return-line feed (CRLF).
     *
     * @param s
     *            the <code>String</code> to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(String s) throws IOException {
        print(s);
        println();
    }

    /**
     * Writes a <code>boolean</code> value to the client, followed by a carriage
     * return-line feed (CRLF).
     *
     * @param b
     *            the <code>boolean</code> value to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(boolean b) throws IOException {
        print(b);
        println();
    }

    /**
     * Writes a character to the client, followed by a carriage return-line feed
     * (CRLF).
     *
     * @param c
     *            the character to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(char c) throws IOException {
        print(c);
        println();
    }

    /**
     * Writes an int to the client, followed by a carriage return-line feed
     * (CRLF) character.
     *
     * @param i
     *            the int to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(int i) throws IOException {
        print(i);
        println();
    }

    /**
     * Writes a <code>long</code> value to the client, followed by a carriage
     * return-line feed (CRLF).
     *
     * @param l
     *            the <code>long</code> value to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(long l) throws IOException {
        print(l);
        println();
    }

    /**
     * Writes a <code>float</code> value to the client, followed by a carriage
     * return-line feed (CRLF).
     *
     * @param f
     *            the <code>float</code> value to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(float f) throws IOException {
        print(f);
        println();
    }

    /**
     * Writes a <code>double</code> value to the client, followed by a carriage
     * return-line feed (CRLF).
     *
     * @param d
     *            the <code>double</code> value to write to the client
     * @exception IOException
     *                if an input or output exception occurred
     */
    public void println(double d) throws IOException {
        print(d);
        println();
    }

    /**
     * Checks if a non-blocking write will succeed. If this returns
     * <code>false</code>, it will cause a callback to
     * {@link WriteListener#onWritePossible()} when the buffer has emptied. If
     * this method returns <code>false</code> no further data must be written
     * until the contain calls {@link WriteListener#onWritePossible()}.
     *
     * @return <code>true</code> if data can be written, else <code>false</code>
     *
     * @since Servlet 3.1
     */
    public abstract boolean isReady();

    /**
     * Sets the {@link WriteListener} for this {@link ServletOutputStream} and
     * thereby switches to non-blocking IO. It is only valid to switch to
     * non-blocking IO within async processing or HTTP upgrade processing.
     *
     * @param listener  The non-blocking IO write listener
     *
     * @throws IllegalStateException    If this method is called if neither
     *                                  async nor HTTP upgrade is in progress or
     *                                  if the {@link WriteListener} has already
     *                                  been set
     * @throws NullPointerException     If listener is null
     *
     * @since Servlet 3.1
     */
    public abstract void setWriteListener(javax.servlet.WriteListener listener);
}
