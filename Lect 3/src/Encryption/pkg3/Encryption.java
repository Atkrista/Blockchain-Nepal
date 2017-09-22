
package Encryption.pkg3;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class Encryption {

    public static void main(String[] args)throws Exception{
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DesImplementation encrypter = new DesImplementation(key);
        String encrypted = encrypter.encrypt("Hello");
        String decrypted = encrypter.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        
    }
    
}
