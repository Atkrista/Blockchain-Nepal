
package MAC;

import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author anish
 */
public class Test {
    public static void main(String[] args){

    String algoMAC="HmacSha1";

    AESEncryption aesengine = new AESEncryption();
    MAC mac = new MAC();

    //String thePlainText = "A quick brown fox jumps over the lazy dog";
    String cipher = null, decrypted = null;
    Scanner scanner = new Scanner( System.in );
    System.out.println("Enter the password for AESEncryption");
    String passwordAES = scanner.nextLine();
    System.out.println("Enter the plain text:");
    String thePlainText = scanner.nextLine();
    System.out.println("Enter the MAC password:");
    String passwordMAC = scanner.nextLine();
    System.out.println("#####################################");

    System.out.println(String.format("Plain Text:\t %s", thePlainText));	
    String sendingMAC = Base64.encodeBase64String(mac.giveMeMAC(passwordMAC, thePlainText, algoMAC));
    
    try {
        //cipher = aesengine.encrypt("easypassword", thePlainText);
        cipher = aesengine.encrypt(passwordAES,thePlainText);
        System.out.println(String.format("Encrypted:\t %s", cipher));
    } catch (GeneralSecurityException ex) {
        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        decrypted = aesengine.decrypt(passwordAES, cipher );
        System.out.println(String.format("Decrypted:\t %s", decrypted));
    } catch (GeneralSecurityException ex) {
        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }	
    System.out.println(String.format("SendingMAC that has been appended:\t %s", sendingMAC));
    
    String receivedMAC = Base64.encodeBase64String(mac.giveMeMAC(passwordMAC, decrypted, algoMAC));
    System.out.println(String.format("ReceivedMAC that has been appended:\t %s", receivedMAC));

    }
}
