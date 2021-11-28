import java.util.Scanner;
import java.lang.Math;
public class hillCipher {
    int[][] MultiMod(int a[][], int b[][], int r1, int r2, int c1, int c2, int m){
        int[][] c = new int[r1][c2];
        for(int i=0;i<r1;i++){
            for(int j=0;j<c2;j++){
                for(int k=0;k<r2;k++){
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] = c[i][j]%m;
                }
            }
        }
        return c;
    }

    int[][] numEncod(String s, int rp, int cp){
       
        int res[][] = new int[rp][cp];
        int x = 0;
        for(int j=0;j<cp;j++){
            for (int i=0;i<rp;i++){
                if(x < s.length()){
                    res[i][j] = s.charAt(x)-97;
                }
                else{
                    res[i][j] = 23; 
                }
                x++;
            }
        }
		System.out.println("Num Encoded \n");
		 for(int i = 0; i < cp; i++){
            for (int j = 0; j < rp; j++){
                System.out.print(res[i][j]+" ");
				
            }
			System.out.println("\n");

        }
        return res;
    }

    String charEncod(int[][] ct, int rk, int cp){
        String ctxt = "";
        for(int j=0;j<cp;j++){
            for (int i=0;i<rk;i++){
                char c = (char) (ct[i][j] + 97);
                ctxt += c;
            }
        }
        return ctxt;
    }

    int deter(int[][] k, int m){
        int det = ((k[0][0]*k[1][1]) - (k[1][0]*k[0][1]));
		int mo = Math.floorMod(det,m);
		System.out.println("");
		System.out.println("Determinant "+mo );
		System.out.println("");
		
        return mo;
    }

    int multInv(int d, int m){
		for (int x = 1; x < m; x++)
            if (((d%m) * (x%m)) % m == 1){
				System.out.println("Inverse "+x);
				System.out.println("");
                return x;
			}
			
        return 1;
		
    }

    int[][] adj(int[][] k, int m){
        int temp;
        temp = k[0][0];
        k[0][0] = k[1][1]%m;
        k[1][1] = temp%m;
        k[0][1] = -1*k[0][1]%m;
        k[1][0] = -1*k[1][0]%m;
		System.out.println("Adjoint of Key\n");
		 for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                System.out.print(k[i][j]+" ");
				
            }
			System.out.println("\n");

        }
		
        return k;
    }

    int[][] scalarMulti(int detIn, int adjo[][], int rk, int ck, int m){
        int[][] mult = new int[rk][ck];
        for(int i=0;i<rk;i++){
            for (int j=0;j<ck;j++){
				int valuem =detIn * adjo[i][j];
                mult[i][j] = Math.floorMod(valuem,m);
			
            }
        }

        return mult;
    }

    int[][] keyInv(int[][] k, int rk, int ck, int m){
        int det = deter(k, m);
        int detInv = multInv(det, m);
        int adjo[][] = adj(k, m);
		
        int[][] sMulti = scalarMulti(detInv, adjo, rk, ck, m);
		
		
        return sMulti;
    }

    void print(int arr[][], int r, int c){
        for(int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                System.out.print(arr[i][j]+" ");
				
				
            }
			System.out.println("\n");

        }
    }
	

    public static void main(String[] args){
        Scanner ip = new Scanner(System.in);
        int[][] k = new int[2][2];
		
        int rk = 2;
        int ck = 2;
        int m = 26;
        hillCipher obj = new hillCipher();

        System.out.println("1. Encryption \n2. Decryption \n3. Find Key\nEnter your choice: ");
        int ch = ip.nextInt();
		ip.nextLine();
        if(ch==1){
            System.out.println("Enter Plain Text: ");
            String plain = ip.nextLine();
			for(int i=0;i<2;i++){
			for(int j=0;j<2;j++)	{
				System.out.println("Enter Key matrix "+i+" "+j);
				k[i][j]= ip.nextInt();
				
			}
			}

            int rp = ck;
            int cp = (int) Math.ceil((double) plain.length()/rp); // 
            int[][] plainencoded = obj.numEncod(plain, rp, cp);
        

            int[][] ctn = obj.MultiMod(k, plainencoded, rk, rp, ck, cp, m);
            
            System.out.println("Cipher Text "+obj.charEncod(ctn, rk, cp));

        }

        if(ch==2){
            System.out.println("Enter Cipher Text: ");
            String cipher = ip.nextLine();
			for(int i=0;i<2;i++){
			for(int j=0;j<2;j++)	{
				System.out.println("Enter Key: "+i+" "+j);
				k[i][j]= ip.nextInt();
				
			}
			}
			
		
            int[][] kinv = obj.keyInv(k, rk, ck, m);
            int rc = rk;
				System.out.println("Key Inverse");
			obj.print(kinv, 2, 2);
			
            int cc = (int) Math.ceil((double) cipher.length()/rc);
            int[][] ctt = obj.numEncod(cipher, rc, cc);
			
            
            int[][] ptn = obj.MultiMod(kinv, ctt, rk, rc, ck, cc, m);
			System.out.println("Cipher Matrix");
            obj.print(ptn, rc, cc);
			System.out.print("Plain Text: ");
            System.out.println(obj.charEncod(ptn, rk, cc));
        }
		if(ch==3){
			System.out.println("Enter Plain Text: ");
            String plain = ip.nextLine();
			System.out.println("Enter Cipher Text: ");
            String cipher = ip.nextLine();
			int rc = rk;
		
			int cc = (int) Math.ceil((double) cipher.length()/rc);
			int cp = (int) Math.ceil((double) plain.length()/rc);
			
			
			
			int[][] ptt = obj.numEncod(plain, rc, cp);
			int[][] ctt = obj.numEncod(cipher, rc, cc);
			 int[][] cinv = obj.keyInv(ctt, rc, cc, m);
			 System.out.println("C-1	");
			 obj.print(cinv, 2, cc);
			 int[][] kinverse = obj.MultiMod(ptt,cinv, 2, 2, cp, cc, m);
			 
			 
			 int[][] keymatrix = obj.keyInv(kinverse, rc, cc, m);
			 System.out.println("Key value	");
			  obj.print(keymatrix, 2, cc);
		}
    }
}