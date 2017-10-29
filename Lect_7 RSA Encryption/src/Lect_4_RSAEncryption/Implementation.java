
package Lect_4_RSAEncryption;

import java.security.KeyPair;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Implementation {
    
    public static void main(String[] args){
        KeyPair keyPair = null;
        String cipherText, decrypted = null;
        String thePlainText="This is for test";
        RSAEncryption rsaEncryption = new RSAEncryption();
        keyPair = rsaEncryption.giveMeKeyPair(512);
        PrivateKey prv = keyPair.getPrivate();
        PublicKey pub = keyPair.getPublic();
        cipherText = rsaEncryption.giveMeEncrypted(thePlainText, pub);
        decrypted = rsaEncryption.giveMeDecrypted(cipherText, prv);
        
        
        
        System.out.println(String.format("Plain Text:\t %s", thePlainText));
        System.out.println(String.format("cipherText:\t %s", cipherText));
        System.out.println(String.format("decryptedText:\t %s", decrypted));
        try {
           System.out.println(String.format("Private key: \t %s", rsaEncryption.getHexString(prv.getEncoded())));
        } catch (Exception ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
           System.out.println(String.format("Public key: \t %s", rsaEncryption.getHexString(pub.getEncoded())));
        } catch (Exception ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
