import java.io.DataInputStream;
import java.net.*;
import java.lang.Math;
import java.util.*;

class functions {
    static final int ASCII_SIZE = 256;

    public String encryption(int key, String text) {
        String final_answer = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < text.length(); i++) {
            int char_position = alphabet.indexOf(text.charAt(i));
            char res = alphabet.charAt((char_position + key) % 26);
            final_answer += res;
        }
        return final_answer;
    }

    public String decryption(String text, int key) {
        String final_answer = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < text.length(); i++) {
            int char_position = alphabet.indexOf(text.charAt(i));
            char res = alphabet.charAt(Math.floorMod((char_position - key), 26));
            final_answer += res;
        }
        return final_answer;

    }

    public void Brute_force_attack(String text) {
        for (int j = 0; j <= 25; j++) {
            String final_answer = "";
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < text.length(); i++) {
                int char_position = alphabet.indexOf(text.charAt(i));
                char res = alphabet.charAt(Math.floorMod((char_position - j), 26));
                final_answer += res;

            }
            System.out.println("For Key = " + j + " the obtained plain text is " + final_answer);

        }

    }

    public void Frequency_analysis_attack(String str) {
        Scanner sc = new Scanner(System.in);
        String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        int i, j, count = 0;
        String st;
        int[] freq = new int[str.length()];
        char string[] = str.toCharArray();
        for (i = 0; i < str.length(); i++) {
            freq[i] = 1;
            for (j = i + 1; j < str.length(); j++) {
                if (string[i] == string[j]) {
                    freq[i]++;
                    string[j] = '0';
                }
            }
        }
        System.out.println("\nCharacters and their corresponding frequencies");
        for (i = 0; i < freq.length; i++) {
            if (string[i] != ' ' && string[i] != '0')
                System.out.println(string[i] + "-" + freq[i]);
        }
        do {
            System.out.println("\nEnter the letter in Cipher Text :");
            String ct = sc.nextLine();
            System.out.println("Enter the most occuring letter :");
            String pt = sc.nextLine();
            int ctp = ALPHABET.indexOf(ct);
            int ptp = ALPHABET.indexOf(pt);
            int key = (ctp - ptp) % 26;
            String ccPlainText = decryption(str, key);
            System.out.println("Obtained Plain Text :" + ccPlainText);
            System.out.print("Do you think the above displayed plain text is meaningful : ");
            st = sc.nextLine();
        } while (st.equals("no"));

    }
}

public class Server {
    public Server(int port) throws Exception {
        ServerSocket ss = new ServerSocket(port);
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());

        String result = (String) in.readUTF();
        String arr[] = result.split(":");
        String text = arr[0].toLowerCase();
        int key = Integer.parseInt(arr[1]);
        int choice = Integer.parseInt(arr[2]);

        functions f = new functions();
        switch (choice) {
        case 1:
            String answer = f.encryption(key, text);
            System.out.println(answer);
            break;
        case 2:
            String d_answer = f.decryption(text, key);
            System.out.println(d_answer);
            break;
        case 3:
            f.Brute_force_attack(text);
            break;
        case 4:
            f.Frequency_analysis_attack(text);
            break;

        }

    }

    public static void main(String[] args) throws Exception {
        Server s = new Server(3394);

    }
}
