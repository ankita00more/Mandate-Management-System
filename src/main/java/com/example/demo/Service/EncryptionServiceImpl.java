package com.example.demo.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class EncryptionServiceImpl 
{
	
	private final static Logger logger = Logger.getLogger(EncryptionServiceImpl.class);
	
	public static String decrypt(String cipherText) 
	{
	    String secret = "MMSPORTAL_2024";
	    byte[] decryptedData = null;
	    cipherText = cipherText.replaceAll(" ", "+");
	    byte[] cipherData = Base64.decodeBase64(cipherText.getBytes());
	    byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
	    try 
	    {
	      MessageDigest md5 = MessageDigest.getInstance("MD5");
	      byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, 
	          secret.getBytes(StandardCharsets.UTF_8), md5);
	      SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
	      IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);
	      byte[] encrypted = Arrays.copyOfRange(cipherData, 16, 
	          cipherData.length);
	      Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      aesCBC.init(2, key, iv);
	      decryptedData = aesCBC.doFinal(encrypted);
	      
	    } 
	    catch (Exception e) 
	    {
	      logger.error("Exception occurred while decrypting the text: "+e);
	    } 
	    String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
	    return decryptedText;
	  }
	
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) 
	{
	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / 
	      digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;
	    try 
	    {
	      md.reset();
	      while (generatedLength < keyLength + ivLength) {
	        if (generatedLength > 0)
	          md.update(generatedData, generatedLength - digestLength, 
	              digestLength); 
	        md.update(password);
	        if (salt != null)
	          md.update(salt, 0, 8); 
	        md.digest(generatedData, generatedLength, digestLength);
	        for (int i = 1; i < iterations; i++) {
	          md.update(generatedData, generatedLength, digestLength);
	          md.digest(generatedData, generatedLength, digestLength);
	        } 
	        generatedLength += digestLength;
	      } 
	      byte[][] result = new byte[2][];
	      result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	      if (ivLength > 0)
	        result[1] = Arrays.copyOfRange(generatedData, keyLength, 
	            keyLength + ivLength); 
	      return result;
	    } 
	    catch (Exception e) 
	    {
	      logger.error("Exception occured while generating Key and IV"+e);
	      throw new RuntimeException(e);
	    } 
	    finally 
	    {
	      Arrays.fill(generatedData, (byte)0);
	    } 
	  }
	
	
}
