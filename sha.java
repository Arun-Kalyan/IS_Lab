import java.math.BigInteger;
import java.util.Scanner;
public class sha{
    public static int boolfunc(int b,int c,int d){
        return ((b&c)|((~b)&d));
    }
    public static int circularleftshift(int x,int n){
        return (x<<n)|(x>>32-n);
    }
    public static int toHex(String str){
        try{
            String string=String.format("%x", new BigInteger(1, str.getBytes("UTF-8")));
            return Integer.parseInt(string,16);
        }catch(Exception exc){
            System.out.println(exc);
        }
        return -1;
    }
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        String text=input.nextLine();
        int[] vector={0x67452301,0xefcdab89,0x98badcfe,0x10325476,0xc3d2e1f0};
        
        int fi=boolfunc(vector[1],vector[2],vector[3]);
        int kt=0x5a827999;
        int wt=toHex(text);

        int buffer=wt^vector[4];
        buffer=buffer^circularleftshift(vector[0],5);
        buffer=buffer^wt;
        buffer=buffer^kt;

        int[] finalvector=new int[5];
        finalvector[0]=buffer;
        finalvector[1]=0x67452301;
        finalvector[2]=circularleftshift(vector[1],30);
        finalvector[3]=0x98badcfe;        
        finalvector[4]=0x10325476;

        for(int i=0;i<finalvector.length;i++){
            System.out.println(Integer.toHexString(finalvector[i]));
        }
    }
}