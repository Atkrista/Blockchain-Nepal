
package encrytion.test;

public class XORImplementation{
    private String input, output;
    char[] key;

    public String encrypt_decrypt(String s, String input_key) {
	input = s;
        key=input_key.toCharArray();
	StringBuilder string_builder = new StringBuilder();
		
	for(int i = 0; i < input.length(); i++) {
            string_builder.append((char) (input.charAt(i) ^ key[i % key.length]));
	}
 		
        return output = string_builder.toString();
    }
}
