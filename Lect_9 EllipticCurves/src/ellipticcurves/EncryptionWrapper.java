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
public class EncryptionWrapper {
    private byte[] encrypted;
    EllipticCurveEncryptionEngine engine;

    public EncryptionWrapper(String message) {
        engine = new EllipticCurveEncryptionEngine();
        try {
            encrypted = engine.encryptToByte(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getEncrypted () {
        return this.encrypted;
    }

    public PublicKey getPublicKey() {
        return this.engine.getPublicKey();
    }

    public PrivateKey getPrivateKey() {
        return this.engine.getPrivateKey();
    }
}
