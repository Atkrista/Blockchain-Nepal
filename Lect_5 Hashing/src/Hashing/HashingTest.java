
package Hashing;

public class HashingTest {
        
	public static void main(String[] args)  {
                Hasher hasher = new Hasher();
		String[] algoType = {"SHA1", "SHA-224", "SHA-256", "SHA-512"};
                String plainText = "A quick brown fox jumps over the lazy dog";
                    System.out.println("Plain text:"+ plainText);
                    for(int i=0; i<(algoType).length;i++){
                         System.out.println(algoType[i]+ "  : "+ hasher.hash(plainText, algoType[i]));
                    }
	}

}
