package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    public InputStream in;
    public int lastinp;
    public int repeat;

    public MyDecompressorInputStream(InputStream in ){
        this.in = in;
    }
    public MyDecompressorInputStream(){

    }

    @Override
    public int read() throws IOException{
        int returnVal = 0;
        returnVal = in.read();
        return returnVal;
    }

    public byte[] read( ArrayList<Byte> mazeCondenceInfo ){
        ArrayList<Byte> mazeBytesInfo = new ArrayList<>();
        int pos=0;
        for(int i=0; i<6; i++){
            while( mazeCondenceInfo.get(pos)!=-1 ){
                mazeBytesInfo.add( mazeCondenceInfo.get(pos) );
                pos++;
            }
            mazeBytesInfo.add( mazeCondenceInfo.get(pos) );
            pos++;
        }
        int curnum= mazeCondenceInfo.get(pos);
        int infoType=0;
        for(int j=pos; j<mazeCondenceInfo.size(); j++){
            curnum= mazeCondenceInfo.get(pos);
            for(int i=0; i<curnum; i++){
                if(infoType%2==0){
                    mazeBytesInfo.add( (byte)0 );
                }
                else{
                    mazeBytesInfo.add( (byte)1 );
                }
            }
            infoType++;
            pos++;
        }
        byte[] mazeinfo = new byte[mazeBytesInfo.size()];
        for(int i=0; i<mazeBytesInfo.size(); i++){
            mazeinfo[i]= mazeBytesInfo.get(i);
        }

        return mazeinfo;
    }



}//class

