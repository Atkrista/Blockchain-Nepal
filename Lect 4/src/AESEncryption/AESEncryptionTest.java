/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AESEncryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AESEncryptionTest {
    
    
    public static void SimpleAESTest(){
        AESEncryption aesengine = new AESEncryption();
        //String thePlainText = "A quick brown fox jumps over the lazy dog";
        String cipher = null;
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the password:");
        String password = scanner.nextLine();
        System.out.println("Enter the plain text:");
        String thePlainText = scanner.nextLine();
        System.out.println("#####################################");
        
        System.out.println(String.format("Plain Text:\t %s", thePlainText));
        try {
            //cipher = aesengine.encrypt("easypassword", thePlainText);
            cipher = aesengine.encrypt(password,thePlainText);
            System.out.println(String.format("Encoded:\t %s", cipher));
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String decrypted = aesengine.decrypt(password, cipher );
            System.out.println(String.format("Decrypted:\t %s", decrypted));
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    //Get filenames in a list
    public static List<String> listFilesForFolder(File folder) {
        System.out.println("Reading file names");
        List<String> names = new ArrayList<String>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //listFilesForFolder(fileEntry);
                //skip
            } else {
                System.out.println(fileEntry.getName());
                names.add(fileEntry.getName());
            }
        }
        return names;
    }
   
    public static void filesAESTest(String password, String path){
        final File folder = new File(path);
        List<String> names = listFilesForFolder(folder);
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
            try {
                encryptForFile(password, path + names.get(i), path + "Encrypted/enc" +names.get(i));
            } catch (IOException ex) {
                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < names.size(); i++) {
            try {
                decryptForFile(password, path + "Encrypted/" + "enc" + names.get(i), path + "Decrypted/enc" +names.get(i));
            } catch (IOException ex) {
                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
    public static void encryptForFile(String password, String readName, String writeName) throws IOException {   
        System.out.println(String.format ("Starting Encryption for %s", readName));
        
        RandomAccessFile aFile = new RandomAccessFile(readName, "r");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        AESEncryption aesengine = new AESEncryption();
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeName));
        String chunk = null;
        while(inChannel.read(byteBuffer) > 0)
        {
            
            ArrayList<Character> charList = new ArrayList<Character>();
//            Charset charset = Charset.forName("UTF-8");
//            String chunk = charset.decode(byteBuffer).toString();
//            System.out.println(chunk);
            byteBuffer.flip();
            for (int i = 0; i < byteBuffer.limit(); i++) {
                    //System.out.print((char) byteBuffer.get());
                    charList.add((char) byteBuffer.get());
                    
            }
            chunk = getStringRepresentation(charList);
            System.out.println(chunk);
            try {
                String cipher = aesengine.encrypt(password, chunk);
                bw.write(cipher);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            byteBuffer.clear(); 
        }
        bw.close();
//        BufferedReader br = new BufferedReader(new FileReader(readName));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(writeName));     
//        StringBuffer sb = new StringBuffer();
//        AESEncryption aesengine = new AESEncryption();
//        while (true) {
//            String line = br.readLine();
//            line.replace("\n", "");
//            String chunk = toString((br.read(myBuffer,0,512))); 
//            //System.out.println(line);
//            try {
//                String cipher = aesengine.encrypt(password, line);
//                bw.write(cipher);
//            } catch (GeneralSecurityException ex) {
//                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (line == null) break;
//        }
//        br.close();
//        bw.close();
    }    
    
    public static void decryptForFile(String password, String readName, String writeName) throws IOException {   
        System.out.println(String.format ("Starting Encryption for %s", readName));
        
        RandomAccessFile aFile = new RandomAccessFile(readName, "r");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        AESEncryption aesengine = new AESEncryption();
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeName));
        String chunk = null;
        while(inChannel.read(byteBuffer) > 0)
        {
            
            ArrayList<Character> charList = new ArrayList<Character>();
//            Charset charset = Charset.forName("UTF-8");
//            String chunk = charset.decode(byteBuffer).toString();
//            System.out.println(chunk);
            byteBuffer.flip();
            for (int i = 0; i < byteBuffer.limit(); i++) {
                    //System.out.print((char) byteBuffer.get());
                    charList.add((char) byteBuffer.get());
                    
            }
            chunk = getStringRepresentation(charList);
            System.out.println(chunk);
            try {
                String cipher = aesengine.decrypt(password, chunk);
                bw.write(cipher);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            byteBuffer.clear(); 
        }
        bw.close();
//        BufferedReader br = new BufferedReader(new FileReader(readName));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(writeName));     
//        StringBuffer sb = new StringBuffer();
//        AESEncryption aesengine = new AESEncryption();
//        while (true) {
//            String line = br.readLine();
//            line.replace("\n", "");
//            String chunk = toString((br.read(myBuffer,0,512))); 
//            //System.out.println(line);
//            try {
//                String cipher = aesengine.encrypt(password, line);
//                bw.write(cipher);
//            } catch (GeneralSecurityException ex) {
//                Logger.getLogger(AESEncryptionTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (line == null) break;
//        }
//        br.close();
//        bw.close();
    }    

    
    public static String getStringRepresentation(ArrayList<Character> list)
    {    
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
    public static void main(String[] args) {
        //SimpleAESTest();
        filesAESTest("hello", "F:/Encryptions/Tests/");
    }
}
    

