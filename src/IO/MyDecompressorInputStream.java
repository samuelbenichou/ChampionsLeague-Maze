package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    /**
     * Constructor that gets an InputStream
     * and initialize new Decompressor
     */
    public MyDecompressorInputStream(InputStream stream){
        if (stream != null)
            in = stream;
    }

    /**
     * This function reads the whole file
     * @return
     */
    @Override
    public int read() {
        try {
            return in.read();
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This function reads the file decompress it to the given byte array
     * @param b
     * @return
     */
    @Override
    public int read(byte[] b) throws ArrayIndexOutOfBoundsException{
        try {
            int to_return = in.read(b);
            decompress(b);
            return to_return;
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This function decompress the given byte array
     * @param in
     */
    public static void decompress(byte[] in) throws ArrayIndexOutOfBoundsException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out /*, new Inflater(true)*/);
            infl.write(in);
            infl.flush();
            infl.close();

            byte[] decompress = out.toByteArray();
            for (int i = 0 ; i < decompress.length ; i++)
                in[i] = decompress[i];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}