package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class MyCompressorOutputStream extends OutputStream {
    public OutputStream out;
    //private byte theLastByte;
    //private int counterByte;



    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
        //theLastByte = 0;
        //counterByte = 0;
    }
    public MyCompressorOutputStream() {
        //theLastByte = 0;
        //counterByte = 0;
    }


    public void write(int b) throws IOException {

    }
    public void write(byte[] mazeinfo) throws IOException {
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
    }//func

}//class