package utils.test;

import java.security.NoSuchAlgorithmException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.EncryptUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncryptUtilTest {
	
	EncryptUtil encUtil = EncryptUtil.getInstance();
	
	@Test
	public void Test001() throws NoSuchAlgorithmException {
		System.out.println("Test_Start");
	    String text = "test String";
	    
	    System.out.println("MD5 Encrypt Start ");
	    System.out.println("MD5 Result : "+encUtil.encryptMD5(text));
	    
	    System.out.println("SHA256 Encrypt Start ");
	    System.out.println("SHA256 Result : "+encUtil.encryptSHA256(text));
	    
	    System.out.println("SHA512 Encrypt Start ");
	    System.out.println("SHA512 Result : "+encUtil.encryptSHA512(text));
	
	}

}
