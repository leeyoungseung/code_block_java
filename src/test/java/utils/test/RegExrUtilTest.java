package utils.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.RegExrUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegExrUtilTest {

	RegExrUtil ru = RegExrUtil.getInstance();
	
	
	@Before
	public void initialize() {
		
	}
	
	@Test
	public void Test001() {
		System.out.println("Test_Start isMatch");
		String param1 = "BuildTiger";
		String param2 = "buildtiger";
		String param3 = "12345tiger";
		String param4 = "tig12345";
		String param5 = "tiger12345";
		String param6 = "12345tige";
		
		// 조건 일치인지
		assertFalse(ru.isMatch(ru.P_ENG_LOWER, param1));
		assertTrue(ru.isMatch(ru.P_ENG_LOWER, param2));
		
		// 영문자 5개로 시작하는지 확인
		assertFalse(ru.isMatch("^[a-zA-Z]{5,5}.*", param3));  
		assertFalse(ru.isMatch("^[a-zA-Z]{5,5}.*", param4));
		assertTrue(ru.isMatch("^[a-zA-Z]{5,5}.*", param5));
		
		// 영문자 5개로 끝나는지  확인 
		assertTrue(ru.isMatch(".*[a-zA-Z]{5,5}$", param3));  
		assertFalse(ru.isMatch(".*[a-zA-Z]{5,5}$", param4));
		assertFalse(ru.isMatch("^.*[a-zA-Z]{5,5}$", param5));
		assertFalse(ru.isMatch("^.*[a-zA-Z]{5,5}$", param6));
		
		
		
		
		System.out.println("Test_End isMatch");
	}
	
	
	@Test
	public void Test002() {
		System.out.println("Test_Start replaceAllMatchStr");
		String param1 = "Super,,Build,,Tiger";
		
		String res = ru.replaceAllMatchStr("[,]{2,2}", param1, "-");
		System.out.println(res);
		
		assertTrue(res.equals("Super-Build-Tiger"));  
		
		
		System.out.println("Test_End replaceAllMatchStr");
	}
	
	
	@Test
	public void Test003() {
		System.out.println("Test_Start replacePrefixMatchStr");
		String param1 = "Super,,Build,,Tiger";
		
		String res = ru.replacePrefixMatchStr("[,]{2,2}", param1, "-");
		System.out.println(res);
		
		assertTrue(res.equals("Super-Build,,Tiger"));  
		
		
		System.out.println("Test_End replacePrefixMatchStr");
	}
	
	
	@Test
	public void Test004() {
		System.out.println("Test_Start getMatchStrLength");
		String param1 = "슈퍼-Builds-타이거";
		
		int res = (Integer) ru.getMatchStrLength("[a-zA-Z]{1,}",param1);
		System.out.println(res);
	
		assertTrue(res == 6);
		System.out.println("Test_End getMatchStrLength");
	}
	
	
	@Test
	public void Test005() {
		System.out.println("Test_Start getMatchCount");
		
		String param1 = "test12test34test56test78test90";
		
		int res = (Integer) ru.getMatchCount("[0-9]{2,2}", param1);
		System.out.println(res);
		
		assertTrue(res == 5);
		System.out.println("Test_End getMatchCount");
	}
	
	
	@Test
	public void Test006() {
		System.out.println("Test_Start getMatchStrList");
		
		String param1 = "test12test34test56test78test90";
		String [] exRes = new String [] {"12","34","56","78","90"};
		
		List<String> list = ru.getMatchStrList("[0-9]{2,2}", param1);
		int su = 0;
		for (String var : list ) {
			System.out.println(var);
			assertTrue("var : "+var +" exRes["+su+"] : "+exRes[su], var.equals(exRes[su]));
			su++;
		}
		
		System.out.println("Test_End getMatchStrList");
	}
	
	
}
