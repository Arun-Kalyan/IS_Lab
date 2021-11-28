import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public Client(String ip, int port) throws Exception{
        Scanner inp = new Scanner(System.in);
        Socket s = new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        System.out.println("Enter your choice");
            System.out.println("1-Encryption \n2-Decryption \n3-Brute Force Attack\n4-Frequency Analysis Attack");
            int choice=inp.nextInt();
            String text= "";
            int key=0;
            if(choice==1){
                System.out.println("Enter the plain text: ");
                text = inp.next();
                System.out.println("Enter the key value: ");
                key = inp.nextInt();
            }
            if(choice == 2){
                System.out.println("Enter the cipher text to decode: ");
                text = inp.next();
                System.out.println("Enter the key value: ");
                key = inp.nextInt();
            }
	if(choice == 3){
                System.out.println("Enter the cipher text to decode : ");
                text = inp.next();
                
                key = 0;
            }
			if(choice == 4){
                System.out.println("Enter the cipher text to decode : ");
                text = inp.next();               
                key = 0;
            }
                 String join = text +":"+ String.valueOf(key)+":" + String.valueOf(choice);
        out.writeUTF(join);
        out.close();

    }
    public static void main(String[] args) throws Exception{
       Client c =  new Client("localhost", 3394);
    }
}