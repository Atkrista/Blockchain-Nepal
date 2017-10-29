/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrytion.test;

/**
 *
 * @author anish
 */
public class CaesarCipher {
    private String cipher="";
    private int key;
    private String decrypted;
    public String encrypt(String to_encrypt, String input_key) {
        cipher="";
        int len=to_encrypt.length();
	key=Integer.parseInt(input_key);
	for(int i=0;i<len;i++){
			char temp=to_encrypt.charAt(i);
			char test=temp;
			if(Character.isAlphabetic(temp)){
				temp+=key;
				if(temp>'z'){
					temp-=26;
				}
				if(temp>'Z' && test<='Z'){
					temp-=26;
				}
			}
			cipher+=temp;
		}
		return cipher;
	}

	public String decrypt(String cipher, String skey) {
		decrypted="";
		int len=cipher.length();
		key=Integer.parseInt(skey);
		for(int i=0;i<len;i++){
			char temp=cipher.charAt(i);
			char test=temp;
			if(Character.isAlphabetic(temp)){
				temp-=key;
				if(temp<'A'){
					temp+=26;
				}
				if(temp<'a' && test >='a'){
					temp+=26;
				}
			}
			
			decrypted+=temp;
		}
		
		return decrypted;
	}
}
