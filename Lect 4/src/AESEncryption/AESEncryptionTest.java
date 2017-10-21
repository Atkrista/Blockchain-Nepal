/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AESEncryption;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESEncryptionTest {
    
public static void main(String[] args) {
        AESEncryption aesengine = new AESEncryption();
        String thePlainText = "A quick brown fox jumps over the lazy dog";
        String cipher = null;
                   
        System.out.println(String.format("Plain Text:\t %s", thePlainText));
        try {
            //cipher = aesengine.encrypt("easypassword", thePlainText);
            cipher = aesengine.encrypt("mypassword",thePlainText);
            System.out.println(String.format("Encoded:\t %s", cipher));
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String decrypted = aesengine.decrypt("mypassword", cipher );
            System.out.println(String.format("Decrypted:\t %s", decrypted));
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}   
