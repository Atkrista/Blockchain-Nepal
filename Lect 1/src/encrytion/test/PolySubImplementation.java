
package encrytion.test;


public class PolySubImplementation {
	private String cipher, to_encrypt, decrypted;
        static char [][] char_matrix;
	String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
	
	public PolySubImplementation(){
            char_matrix=new char[26][26];
            char c='A';
            for(int i=0;i<26;i++){
		char v=c;
		for(int j=0;j<26;j++){
                    char value=(char) (v+j);
                    if(value>'Z'){
			value-=26;				
                    }
                    char_matrix[i][j]=value;		
			}
                    c+=1;
		}
	}

	public String encrypt(String to_encrypt, String key){
		cipher="";
		for(int i=0;i<to_encrypt.length();i++){
			int temp1=alphabets.indexOf(Character.toUpperCase(to_encrypt.charAt(i)));
			int temp2=alphabets.indexOf(Character.toUpperCase(key.charAt(i)));
                        System.out.println(temp1);
                        System.out.println(temp2);
                        System.out.println(key.charAt(i));
			char c=char_matrix[temp1][temp2];
			cipher+=c;	
		}
		return cipher;
	}
	
	public String decrypt(String cipher,String key){
		decrypted="";
		for(int i=0;i<cipher.length();i++){
			int temp1=alphabets.indexOf(key.charAt(i));
			char temp2=cipher.charAt(i);
			for(int j=0;j<26;j++){
				if(char_matrix[temp1][j]==temp2){
					decrypted+=alphabets.charAt(j);
					break;
				}
			}
			
		}
		return decrypted;
	}

}
