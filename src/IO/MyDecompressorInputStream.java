package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterOutputStream;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    public MyDecompressorInputStream(InputStream stream){
        this.in = stream;
    }


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
            decompressFromArray(b);
            return to_return;
        }
        catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void decompressFromArray(byte[] in)  {
        int i = 0;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream inflater = new InflaterOutputStream(out);
            inflater.write(in);
            inflater.flush();
            inflater.close();
            byte[] decompress = out.toByteArray();
            for (i = 0 ; i < decompress.length ; i++) {
                in[i] = decompress[i];
            }
        } catch (Exception e) {
            System.out.println("index : " + i);
            System.out.println("Length : " + in.length);
            e.printStackTrace();
        }
    }
}