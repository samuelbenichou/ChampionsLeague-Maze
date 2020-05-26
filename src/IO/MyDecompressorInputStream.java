package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterOutputStream;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    public MyDecompressorInputStream(InputStream stream){
        in = stream;
    }

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

    public static void decompress(byte[] in)  {
        int i = 0;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out /*, new Inflater(true)*/);
            infl.write(in);
            infl.flush();
            infl.close();

            byte[] decompress = out.toByteArray();

            for (i = 0 ; i < decompress.length ; i++)
                in[i] = decompress[i];

        } catch (Exception e) {
            System.out.println("index : " + i);
            e.printStackTrace();
        }
    }
}