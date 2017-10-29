/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ellipticcurves;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author anish
 */
public class DecryptionWrapper {
     private byte[] decrypted;
    EllipticCurveEncryptionEngine engine;

    public DecryptionWrapper() {
        engine = new EllipticCurveEncryptionEngine();

    }

    public byte[] getDecrypted(byte[] cypherText, PrivateKey privateKey, PublicKey publicKey) {
        try {
            decrypted = engine.decryptToByte(cypherText, privateKey, publicKey);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return decrypted;
    }
}
