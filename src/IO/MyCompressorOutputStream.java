package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;


public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;


    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }


    public void write(int b) throws IOException {
        try {
            out.write(b);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) {
        try{
            out.write(compressInArray(b));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static byte[] compressInArray(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream deflater = new DeflaterOutputStream(out);
            deflater.write(in);
            deflater.flush();
            deflater.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}