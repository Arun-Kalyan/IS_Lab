import java.util.*;
import java.math.BigInteger;

class RSA {
    static BigInteger one = new BigInteger("1");
    static BigInteger zero = new BigInteger("0");

    public static void main(String args[]) {
        RSA rsa = new RSA();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of P: ");
        BigInteger p = sc.nextBigInteger();
        System.out.println("Enter the value of Q: ");
        BigInteger q = sc.nextBigInteger();
        BigInteger a = new BigInteger("1");
        BigInteger n = p.multiply(q);
        System.out.println("The value of n is: " + n);
        BigInteger phi = (p.subtract(a)).multiply(q.subtract(a));
        BigInteger pubkey = new BigInteger("0");
        System.out.println("The value of phi is : " + phi);
        BigInteger i = new BigInteger("2");
        for (BigInteger x = i; x.compareTo(phi) < 0; x = x.add(a)) {
            if ((x.gcd(phi)).equals(a)) {
                pubkey = x;
                break;
            } else {
                continue;
            }
        }

        System.out.println("Public key is: " + pubkey);
        BigInteger privkey = multInv(pubkey, phi);
        System.out.println("Private Key is: " + privkey);
        System.out.println("Enter from the following choices:");
        System.out.println("1.Encryption\n2.Decryption");
        int choice = sc.nextInt();
        if (choice == 1) {
            sc.nextLine();
            System.out.println("Enter message value to be encrypted:");
            String msg = sc.nextLine();
            System.out.println("Message data = " + msg);
            encrypt(msg, pubkey, n);
        } else if (choice == 2) {
            sc.nextLine();
            System.out.println("Enter the size: ");
            int s = sc.nextInt();
            int[] iput = new int[s];
            for (int j = 0; j < s; j++) {
                System.out.println("Enter the encrypted value of " + j + ": ");
                iput[j] = sc.nextInt();
            }
            char[] ct = new char[s];
            for (int j = 0; j < s; j++) {
                ct[j] = rsa.decrypt(iput[j], privkey, n);
            }
            System.out.println();
            System.out.println("The plain text is: " + String.valueOf(ct));

        }

        else

        {
            System.out.println("Invalid choice");
        }

    }

    static void encrypt(String m, BigInteger e, BigInteger n) {
        String final_ans = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < m.length(); i++) {
            int char_position = alphabet.indexOf(m.charAt(i));
            BigInteger cp = BigInteger.valueOf(char_position);
            BigInteger c = cp.modPow(e, n);
            System.out.println("Encrypted ans : " + c);
            int val = c.intValue();
            val = val % 26;
            char res = alphabet.charAt(val);
            final_ans += res;

        }
        System.out.println("\nEncrypted data = " + final_ans);

    }

    char decrypt(int m, BigInteger d, BigInteger n) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        BigInteger twosix = new BigInteger("26");
        BigInteger btext = new BigInteger(String.valueOf(m));
        BigInteger answer = btext.modPow(d, n);
        answer = answer.mod(twosix);
        System.out.println("Num to be char encoded: " + answer);
        int ans = answer.intValue();
        char res = alphabet.charAt(ans);
        System.out.println("Char encoded value: " + res);
        return res;
    }

    static BigInteger multInv(BigInteger d, BigInteger m) {

        BigInteger z = new BigInteger("1");
        for (BigInteger x = z; x.compareTo(m) < 0; x = x.add(one)) {
            if ((x.multiply(d)).mod(m).equals(one)) {
                return x;
            } else {
                continue;
            }

        }

        return z;
    }
}
