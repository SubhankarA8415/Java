import java.util.Scanner;
public class Affine_Chiper {
static int k1 = 0; static int k2 = 0; // key variables to hold the value of both the keys entered by user
static String plain_text = "abcdefghijklmnopqrstuvwxyz"; //characters set for plain text
static String chiper_text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//characters set for chiper text
public static String encrypt (String plaintext) { //encryption method
	 String encrypt_text = ""; //encrypted message storing variable
l1 : for (int i = 0;i<plaintext.length();i++) {
		int code = 0, space = 1; // code -> to identify entered character code from plain text or chiper text (in decrypt)
l2 :	for (int j = 0;j<26;j++) { // encryption process
			if (plain_text.charAt(j)==plaintext.charAt(i)) {//finding match of characters entered and characters available
				code = (j*k1+k2)%26; // formula for encryption
				encrypt_text = encrypt_text + chiper_text.charAt(code); // concatenation
				space = 0;
				break;
			}
			}
		if (space == 1) { // to identify spaces
			encrypt_text += " ";
		}
	}
	return encrypt_text;
} 
public static String decrypt (String chipertext) { //decryption method
	String decrypt_text = ""; int k1_inverse = 0, k2_inverse = 0;
l3 :for (int k = 0; k<=26; k++) { // for finding inverse of key k1
		if ((k*k1)%26==1) {
			k1_inverse = k;
			break;
		}
	}
	k2_inverse = 26-k2; // for finding inverse of key k2
l4 :for (int i = 0;i<chipertext.length();i++) { //
	    int code = 0 , space = 1; // space variable to detect space in between the paraphrase of chiper and plain text (in encrypt)
l5 :	for (int j = 0;j<plain_text.length();j++) {
			if (chiper_text.charAt(j)==chipertext.charAt(i)) {
				code = ((j+k2_inverse)*k1_inverse)%26; // formula for decryption
				decrypt_text = decrypt_text + plain_text.charAt(code);
				space = 0;
				break;
			}
		}
		if (space == 1) {
			decrypt_text += " ";
		}
	}
	return decrypt_text;

	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			 Scanner sc = new Scanner(System.in);
			 String plaintext = "", chipertext = "" ;
			 int k1_decrypt = 0, k2_decrypt = 0;
			 System.out.println("-----To encrypt-----");
			 System.out.println("Enter your message in lower case -: ");
			 plaintext = sc.nextLine();
			 System.out.println("Enter the value of encryption key k1 -: ");
			 k1 = sc.nextInt();
			 System.out.println("Enter the value of encryption key k2 -: ");
			 k2 = sc.nextInt();
			 System.out.println();
			 System.out.println("Encryption done !!");
			 chipertext = encrypt(plaintext);
			 System.out.println("Encrypted message -: "+chipertext);
			 System.out.println();
			 System.out.println("----- To decrypt message -----");
			 System.out.println("Enter the same value of secret key k1 -: ");
			 k1_decrypt = sc.nextInt();
			 System.out.println("Enter the same value of secret key k2 -: ");
			 k2_decrypt = sc.nextInt();
			 if (k1 == k1_decrypt && k2 == k2_decrypt) { //decrypting for correct key values entered
				 System.out.println();
				 System.out.println("Keys matched !!"); // identification
				 System.out.println("Decryption done !!");
				 System.out.println("Decrypted message -: "+decrypt(chipertext));
			 }
			 else {
				 k1 = k1_decrypt; k2 = k2_decrypt; // decrypting for incorrect key values entered
				 System.out.println(); // no identification
				 System.out.println("Decryption done !!");
				 System.out.println("Decrypted message -: "+decrypt(chipertext)); 
			 }
			 // Code by -: Subhankar Pandit
			 // Registration number -: 2241016145
			 // Section -: 2241022
			 // Branch -: BTech CSE
			 

	}

}

