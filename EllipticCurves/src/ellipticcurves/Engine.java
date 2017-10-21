/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ellipticcurves;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyFactory;
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
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
//import org.apache.commons.codec.binary.Hex.*;
/**
 *
 * @author anish
 */
public class Engine{
    private static final String ALGORITHM = "ECIES";
    private static final String PROVIDER = "BC";
    private static final String CURVE = "prime256v1";
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private KeyPair keyPair, akey, bkey;
    private String FILE ="store.txt"; 
    
    public void Engine() {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public void gimmeKeyPair(){
        try {
            akey = generateKey();
            bkey = generateKey();
            keyPair = generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
            
    public byte[] encrypt(String message){
        byte[] encrypted = null;
        try{
            encrypted = encryptToByte(message, akey.getPrivate(), bkey.getPublic());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encrypted;
    }
    
    public byte[] decrypt(byte[] cipherText){
        byte[] decrypted = null;
        try{
            decrypted = decryptToByte(cipherText, bkey.getPrivate(), akey.getPublic());
        }
        catch (Exception e){
            e.printStackTrace();
        }    
        return decrypted;
    }

    private KeyPair generateKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("EC", PROVIDER);
        keyGenerator.initialize(new ECGenParameterSpec(CURVE));
        KeyPair keypair = keyGenerator.genKeyPair();
        return keypair;
    }


    public byte[] encryptToByte(String message, PrivateKey privateKey, PublicKey publicKey) throws Exception {
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        byte[] pub = publicKey.getEncoded();
        byte[] prv = privateKey.getEncoded();
        
        System.out.println(getHexString(pub));
        System.out.println(getHexString(prv));
        System.out.println(getHexString(getByteArray(getHexString(pub))));
        System.out.println(getHexString(getByteArray(getHexString(prv))));
        
        
        //KeyFactory kf = KeyFactory.getInstance("EC");
        //PrivateKey prv_recovered = kf.generatePrivate(new PKCS8EncodedKeySpec(prv));
        //PublicKey pub_recovered = kf.generatePublic(new X509EncodedKeySpec(pub));
        //System.out.println(new String(Base64.encode(pub)));
        //System.out.println(publicKey.getFormat());
        //System.out.println(new String(Base64.encode(prv)));
        //System.out.println(Base64.encode(pub));
        //System.out.println(encodeHex(pub));
        //System.out.println(pub_recovered.getEncoded());
        IESKey iesKey = (IESKey) new IEKeySpec(privateKey,publicKey);
        cipher.init(Cipher.ENCRYPT_MODE, iesKey, param);
        return cipher.doFinal(message.getBytes());
    }
    
    public class MobileData{
        byte publicKeyEncoded[];
    }
    
    public byte[] decryptToByte(byte[] encrypted, PrivateKey privateKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        byte[] pub_downloaded = (getByteArray(getHexString(publicKey.getEncoded())));
        KeyFactory kf = KeyFactory.getInstance("EC");
        PublicKey pub = kf.generatePublic(new X509EncodedKeySpec(pub_downloaded));
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        IESKey iesKey = (IESKey) new IEKeySpec(privateKey,pub);
        cipher.init(Cipher.DECRYPT_MODE, iesKey, param);
        return cipher.doFinal(encrypted);
    }
    
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    public static String getHexString(byte[] b) throws Exception {
    String result = "";
    for (int i=0; i < b.length; i++) {
        result +=
        Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }   
        return result;
    }
    
    private byte[] getByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
            }
        return data;
    }
}

