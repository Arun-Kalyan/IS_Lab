import java.security.MessageDigest;
import java.util.*;

public class hash {
    static String[] words() {
        String[] arr = new String[456976];
        int x = 0;
        for (int g = 65; g < 91; g++) {
            for (int h = 65; h < 91; h++) {
                for (int i = 65; i < 91; i++) {
                    for (int j = 65; j < 91; j++) {
                        char a = (char) (g);
                        char b = (char) (h);
                        char c = (char) (i);
                        char d = (char) (j);
                        String pattern = "" + a + b + c + d;
                        arr[x] = pattern;
                        x++;
                    }
                }
            }
        }
        return arr;
    }

    static String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                // System.out.print(hash[i]+" ");
                final String hex = Integer.toHexString(0xff & hash[i]);
                // System.out.println("The length of hex is: "+hex.length());
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static String[] hash_combinations(String[] array) {
        String[] has_array = new String[456976];
        for (int i = 0; i < array.length; i++) {
            String hash = sha256(array[i]);
            has_array[i] = hash;
        }
        return has_array;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nGenerating the words for Dictionary attack!!\n");
        String[] combinations_array = words();
        System.out.println(
                "Generating the corresponding hash for the words!!\nNow we have the dictionary for 456976 combinations of passwords and it's hash values\n");
        String[] hash_combinations_array = hash_combinations(combinations_array);
        System.out.println("Enter the (password/hash) to crack: ");
        String has_input = scan.nextLine();
        System.out.println("Enter the option\n1.Convert password to key\n2.Dictionary Attack\n");
        int choice;
        choice = scan.nextInt();
        switch (choice) {
        case 1:
            System.out.println("The hash for this password is : " + sha256(has_input));
            break;
        case 2:
            for (int i = 0; i < hash_combinations_array.length; i++) {
                if (hash_combinations_array[i].equals(has_input)) {
                    System.out.println("The Cracked Password is : " + combinations_array[i] + "\n"
                            + "Location in the dictionary : " + i);
                    break;
                }

            }
        }
    }
}
