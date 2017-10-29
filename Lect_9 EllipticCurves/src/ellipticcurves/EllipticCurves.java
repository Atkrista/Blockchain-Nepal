/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ellipticcurves;

import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author anish
 */

public class EllipticCurves {

    /**
     * @param args the command line arguments
     */
    
    public static final String ALGORITHM = "ECIES";
    public static final String PROVIDER = "BC";
    public static final String CURVE = "prime256v1";
    private static KeyPair akey;
    private static KeyPair bkey;

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        /*
        KeyPair keyPair = null;
        try {
            akey = generateKeyPair();
            bkey = generateKeyPair();
            keyPair = generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        // generate public and private keys
        
        
        //KeyPair keyPair = generateKeyPair();
        
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // Original message
        */
        //String plainText = "how do u do jire bbiiiitch hello";
        String plainText = "HELLO WORLD";
        System.out.println(plainText);
//        

        
        Engine engine = new Engine();
        engine.gimmeKeyPair();
        byte[] cipherText = engine.encrypt(plainText);
        String ss = new sun.misc.BASE64Encoder().encode(cipherText);
        System.out.println(ss);
        System.out.println(new String(engine.decrypt(cipherText)));        
//        EncryptionWrapper encryptionWrapper = new EncryptionWrapper(plainText);
//        byte[] cipherText = encryptionWrapper.getEncrypted();
//        System.out.println(encryptionWrapper.getPrivateKey());
//        System.out.println(encryptionWrapper.getPublicKey());
//        String ss = new sun.misc.BASE64Encoder().encode(cipherText);
//        System.out.println(ss);
//        EllipticCurveEncryptionEngine et= new EllipticCurveEncryptionEngine();
//        byte[] cipherText=et.encryptToByte(plainText);
//        System.out.println(et.getPrivateKey());
//        System.out.println(et.getPublicKey());
//        String ss = new sun.misc.BASE64Encoder().encode(cipherText);
//        System.out.println(ss);
//        // encrypt the message
        //byte[] cipherText = encrypt(pubKey,privateKey, plainText);
        //System.out.println(Base64.encode(cipherText));

        // decrypt the message
//        DecryptionWrapper decryptionWrapper = new DecryptionWrapper();
//        byte[] decryptedText = decryptionWrapper.getDecrypted(cipherText, encryptionWrapper.getPrivateKey(), encryptionWrapper.getPublicKey());
//        System.out.println(new String(decryptedText));
//        
    }
    
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("EC", PROVIDER );
        generator.initialize(new ECGenParameterSpec(CURVE));
        return generator.genKeyPair();
    }   
    
    public static byte[] encrypt(PublicKey publicKey, PrivateKey privatekey, String message) throws Exception {
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        IESKey iesKey = (IESKey) new IEKeySpec(privatekey,publicKey);
        cipher.init(Cipher.ENCRYPT_MODE, iesKey, param);
        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PrivateKey privateKey, PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        IESKey iesKey = (IESKey) new IEKeySpec(privateKey,publicKey);
        cipher.init(Cipher.DECRYPT_MODE, iesKey, param);
        return cipher.doFinal(encrypted);
    }
 
    
}
