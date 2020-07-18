package utils.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import utils.HttpUtil;

public class HttpUtilTest {

	
	HttpUtil hu = new HttpUtil();
	
	@Test
	public void Test001() {
		System.out.println("Test_Start requestGET");
		
		String testUrl = "http://localhost/httpTest/test001.txt";
		hu.requestGET(testUrl);
		
		System.out.println("Test_End requestGET");
	}
	
	@Test
	public void Test002() {
		System.out.println("Test_Start requestPOST");
		
		String testUrl = "http://localhost/httpTest/postTest.php";
		Map<String,String> contents = new HashMap<String, String>();
		contents.put("id", "David");
		contents.put("nickname", "Build Tiger");
		
		hu.requestPOST(testUrl, contents);
		
		System.out.println("Test_End requestPOST");
	}
}
