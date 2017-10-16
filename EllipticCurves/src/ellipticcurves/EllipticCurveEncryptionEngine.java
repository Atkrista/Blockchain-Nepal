/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ellipticcurves;

import static ellipticcurves.EllipticCurves.ALGORITHM;
import static ellipticcurves.EllipticCurves.PROVIDER;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import javax.crypto.Cipher;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.spec.IEKeySpec;
import org.bouncycastle.jce.spec.IESParameterSpec;
/**
 *
 * @author anish
 */
public class EllipticCurveEncryptionEngine {
    private static final String ALGORITHM = "ECIES";
    private static final String PROVIDER = "BC";
    private static final String CURVE = "prime256v1";
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public void EllipticCurveEncryptionEngine() {
        //idk what this is for
        //manually made bcprov-jdk15-130.jar a library
        //did not find gradle dependency
        //has a different tested version named SpongyCastle
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private void init_encryptor() {

        //Generate keypair with public and private key
        KeyPair keyPair = null;
        try {
            keyPair = generateKey();
            //setting the public and private key
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        System.out.println(publicKey);
        System.out.println(privateKey);
    }


    private KeyPair generateKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(" EC", PROVIDER);
        keyGenerator.initialize(new ECGenParameterSpec(CURVE));
        KeyPair keypair = keyGenerator.genKeyPair();
        return keypair;
    }


    public byte[] encryptToByte(String message) throws Exception {
        init_encryptor();
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        System.out.println(publicKey);
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        IESKey iesKey = (IESKey) new IEKeySpec(privateKey,publicKey);
        cipher.init(Cipher.ENCRYPT_MODE, iesKey, param);
        return cipher.doFinal(message.getBytes());
    }
    
    public byte[] decryptToByte(byte[] encrypted, PrivateKey privateKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        byte[]  d = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[]  e = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };
        IESParameterSpec param = new IESParameterSpec(d, e, 256);
        IESKey iesKey = (IESKey) new IEKeySpec(privateKey,publicKey);
        cipher.init(Cipher.DECRYPT_MODE, iesKey, param);
        return cipher.doFinal(encrypted);
    }
    
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
