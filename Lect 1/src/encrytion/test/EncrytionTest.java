/* Java encryption */
package encrytion.test;

public class EncrytionTest {

    public static void main(String[] args) {
        String to_encrypt = "Hello", cipher, decrypted;
        
        //Caesae cypher encryption and decryption
        CaesarCipher caesar = new CaesarCipher();
	cipher = caesar.encrypt(to_encrypt, "5");
        System.out.println("Encrypted:" + cipher);
        decrypted = caesar.decrypt(cipher, "5");
	System.out.println("Decrypted:" + decrypted);
        
        //XOR encryption and decrytion implementation
        XORImplementation xor = new XORImplementation();
	cipher = xor.encrypt_decrypt(to_encrypt, "hcj5");
        System.out.println("Encrypted:" + cipher);
        decrypted = xor.encrypt_decrypt(cipher, "hcj5");
	System.out.println("Decrypted:" + decrypted);
        
        //XOR encryption and decrytion implementation
        MonoSubImplementation mono_sub=new MonoSubImplementation();
	cipher=mono_sub.encrypt("Hello", "ZCBMXVNSFHKADGJLWRYIPQETUO");
        System.out.println("Encrypted:" + cipher);
	decrypted = mono_sub.decrypt(cipher,"ZCBMXVNSFHKADGJLWRYIPQETUO");
        System.out.println("Decrypted:" + decrypted);
        
        //XOR encryption and decrytion implementation
        PolySubImplementation poly_sub = new PolySubImplementation();
	cipher=poly_sub.encrypt("Hello", "ZCBMX");
        System.out.println("Encrypted:" + cipher);
	decrypted = poly_sub.decrypt(cipher,"ZCBMX");
        System.out.println("Decrypted:" + decrypted);
        
    }
}
