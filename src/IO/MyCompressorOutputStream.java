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
            out.write(compress(b));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /*public void write(byte[] mazeinfo) throws IOException {
        ArrayList<Byte> mazeBytesInfo = new ArrayList<>();
        int pos=0;
        int num=0;
        byte curentByte;
        byte theLastByte;
        int counterByte=0;
        for(int i=0; i<6; i++){
            while( mazeinfo[pos]!=-1 ){
                mazeBytesInfo.add( mazeinfo[pos] ) ;
                pos++;
            }
            mazeBytesInfo.add( mazeinfo[pos] ) ;
            pos++;
        }

        curentByte = mazeinfo[pos];
        theLastByte = mazeinfo[pos];
        if(curentByte==1){/////////////////////////////////////the first num in the info is always 0
            mazeBytesInfo.add( (byte)0 ) ;
        }

        num=0;
        for(int i=pos; i<mazeinfo.length; i++){
            curentByte = mazeinfo[pos];
            if (curentByte == theLastByte) {
                counterByte++;
                pos++;
            }
            else{
                if(counterByte>127){
                    num=counterByte;
                    while(num>127){
                        mazeBytesInfo.add( (byte)127 ) ;
                        if ( theLastByte==1) {
                            mazeBytesInfo.add( (byte)0 ) ;
                        }
                        else{
                            mazeBytesInfo.add( (byte)1 ) ;
                        }
                        num = num-127;
                    }
                    mazeBytesInfo.add( (byte)num ) ;
                    counterByte=1;
                }
                else{
                    mazeBytesInfo.add( (byte)counterByte ) ;
                    counterByte=1;
                }
                theLastByte=curentByte;
                pos++;
            }
        }

        for(int i=0; i<mazeBytesInfo.size(); i++){
            try{
                //out.write( mazeBytesInfo.get(i) );
                System.out.print( mazeBytesInfo.get(i)+" " );
            }
            catch (Exception e){
                throw e;
            }

        }
    }//func*/

}//class