
package encrytion.test;

public class MonoSubImplementation {
    private String to_encrypt, cipher, decrypted;
    String u_alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String l_alphabets=u_alphabets.toLowerCase();
	
	
    public String encrypt(String s,String key){
        cipher="";
        to_encrypt = s;
	int len=to_encrypt.length();
	String key_lower=key.toLowerCase();
        for(int i=0;i<len;i++){
            char temp=to_encrypt.charAt(i);
            char per_key=0;
            if(Character.isAlphabetic(temp)){
		if(temp>=97 && temp<=122){
                    per_key=key_lower.charAt(l_alphabets.indexOf(temp));	
                }else if(temp>=65 && temp<=90){
                    per_key=key_lower.charAt(u_alphabets.indexOf(temp));	
                }
                    cipher+=per_key;
            }else{
                    cipher+=temp;
            }
	}
            return cipher;
	}
	
	public String decrypt(String encrypted,String key){
	
            decrypted="";
            int len=encrypted.length();
            String key_lower=key.toLowerCase();
		
            for(int i=0;i<len;i++){
		char temp=encrypted.charAt(i);
		char per_key=0;
		if(Character.isAlphabetic(temp)){
                    if(temp>=97 && temp<=122){
			per_key=l_alphabets.charAt(key_lower.indexOf(temp));
                    }else if(temp>=65 && temp<=90){
			per_key=u_alphabets.charAt(key.indexOf(temp));	
                    }
			decrypted+=per_key;
                    }else{
			decrypted+=temp;
		}
			
	}
	return decrypted;	
    }
}
