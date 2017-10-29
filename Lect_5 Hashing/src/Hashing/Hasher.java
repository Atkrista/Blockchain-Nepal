package Hashing;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class Hasher {

    public static String hash(String message, String hashingAlgorithm){
            String algorithm = hashingAlgorithm;
            String encodedHashString = null;
            try{
                    MessageDigest digest = MessageDigest.getInstance(algorithm);
                    byte[] hashValue = digest.digest(message.getBytes());
                    encodedHashString = Base64.encodeBase64String(hashValue);
                    return encodedHashString;
            }
            catch(Exception e){
                    System.out.println(e);
            }
	return encodedHashString;
	}
	
}