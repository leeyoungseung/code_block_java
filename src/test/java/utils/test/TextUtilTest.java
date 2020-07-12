package utils.test;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TextUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TextUtilTest {

	public String [] encodingSupportArray = new String [] {
			"UTF-8",
			"EUC-KR",
			"ISO-8859-1",
			"EUC-JP",
			"Shift-JIS",
			"UNICODE"
	};
	
	TextUtil tu = TextUtil.getInstance();
	
	@Before
	public void initialize() {
		
	}
	
	@Test
	public void Test001() {
		System.out.println("Test_Start isNullOrEmpty");
		
		String param1 = null;
		String param2 = "";
		String param3 = " ";
		String param4 = "　";
		String param5 = "tiger";
		
		boolean result1 = tu.isNullOrEmpty(param1);
		boolean result2 = tu.isNullOrEmpty(param2);
		boolean result3 = tu.isNullOrEmpty(param3);
		boolean result4 = tu.isNullOrEmpty(param4);
		boolean result5 = tu.isNullOrEmpty(param5);
		
		assertTrue("null check OK", result1);
		assertTrue("\"\" check OK", result2);
		assertTrue("space check OK", result3);
		assertFalse("Full char space check NG", result4);
		assertFalse("String check OK", result5);
		
		System.out.println("Test_End isNullOrEmpty");
	}
	
	@Test
	public void Test002() {
		System.out.println("Test_Start makeListFromStr");
		
		String csvFormat = "tiger1,tiger2,tiger3,tiger4,tiger5";
		
		List<String> listForCheck = new ArrayList<String>();
		listForCheck.add(0,"tiger1");
		listForCheck.add(1,"tiger2");
		listForCheck.add(2,"tiger3");
		listForCheck.add(3,"tiger4");
		listForCheck.add(4,"tiger5");
		
		List<String> resList = tu.makeListFromStr(csvFormat);
		
		assertEquals(listForCheck.size(), resList.size());
		
		System.out.println(csvFormat +" -> List" );
		for (int i = 0; i < resList.size(); i++) {
			assertTrue("Not Equal value expected : ["+i+"]{"+listForCheck.get(i)+"} , Result ["+i+"]{"+resList.get(i)+"}",
					listForCheck.get(i).equals(resList.get(i)));
			
			System.out.println("List Value ["+i+"] : "+ resList.get(i));
		}
		
		System.out.println("Test_End makeListFromStr");
	}
	
	
	@Test
	public void Test003() {
		System.out.println("Test_Start makeArrayFromStr");
		
		String csvFormat = "tiger1,tiger2,tiger3,tiger4,tiger5";
		String [] arrayForCheck = new String [] {"tiger1","tiger2","tiger3","tiger4","tiger5"};
		
		String [] resArray = tu.makeArrayFromStr(csvFormat);
		
		assertEquals(arrayForCheck.length, resArray.length);
		
		System.out.println(csvFormat +" -> Array" );
		for (int i = 0; i < resArray.length; i++) {
			assertTrue("Not Equal value expected : ["+i+"]{"+arrayForCheck[i]+"} , Result ["+i+"]{"+resArray[i]+"}",
					arrayForCheck[i].equals(resArray[i]));
			
			System.out.println("Array Value ["+i+"] : "+ resArray[i]);
		}
		
		
		System.out.println("Test_End makeArrayFromStr");
	}
	
	
	@Test
	public void Test004() {
		System.out.println("Test_Start deleteAllSpace");
		
		String param1 = " Super Build Tiger  ";
		String param2 = "  Super  Build  Tiger";
		String param3 = "　　Super　　Build　　Tiger";
		String param4 = "　S　u p　e r　　B u　　i l d     T　　i g   e　 r　";
		
		String expectedVal = "SuperBuildTiger";
		
		String res1 = tu.deleteAllSpace(param1);
		String res2 = tu.deleteAllSpace(param2);
		String res3 = tu.deleteAllSpace(param3);
		String res4 = tu.deleteAllSpace(param4);
		
		assertTrue(expectedVal.equals(res1));
		assertTrue(expectedVal.equals(res2));
		assertTrue(expectedVal.equals(res3));
		assertTrue(expectedVal.equals(res4));
		
		System.out.println("Test_End deleteAllSpace");
	}
	
	@Test
	public void Test005() {
		System.out.println("Test_Start deleteLineSeparator");
		
		String param1 = "Super\n\nBuild\n\nTiger\n";
		String expectedVal = "SuperBuildTiger";
		
		System.out.println(param1);
		System.out.println(expectedVal);
		
		String res = tu.deleteLineSeparator(param1);
		assertTrue(expectedVal.equals(res));		
		
		System.out.println("Test_End deleteLineSeparator");
	}
	
	
	@Test
	public void Test006() {
		System.out.println("Test_Start convertCsvFormatRecode");
		
		String [] array1 = new String [] {"tiger1","tiger2","tiger3","tiger4","tiger5"};
		String [] array2 = new String [] {"tiger6","tiger7","tiger8","tiger9","tiger10"};
		String param1 = "tiger11";
		String param2 = "tiger12";
		String param3 = "tiger13";
		
		String exVal1 = "tiger1,tiger2,tiger3,tiger4,tiger5";
		String exVal2 = "tiger6,tiger7,tiger8,tiger9,tiger10";
		String exVal3 = "tiger11,tiger12,tiger13";
		
		String res1 = tu.convertCsvFormatRecode(array1);
		String res2 = tu.convertCsvFormatRecode(array2);
		String res3 = tu.convertCsvFormatRecode(param1,param2,param3);
		
		assertTrue(exVal1.equals(res1));
		assertTrue(exVal2.equals(res2));
		assertTrue(exVal3.equals(res3));
		
		System.out.println("Test_End convertCsvFormatRecode");
	}
	
	@Test
	public void Test007() {
		System.out.println("Test_Start convertStrToNumSystem");
		
		String param1 = "buildtiger";
		String param2 = "빌드타이거";
		String param3 = "ビルドタイガー";
		
		// 영어는 문제없이 가능
		String res_hex_1 = tu.convertStrToHex(param1);
		System.out.println(res_hex_1);
		System.out.println(tu.convertHexToStr("UTF-8", tu.deleteAllSpace(res_hex_1)));
		
		// 한국어 일본어는 안되는듯...
		String res_hex_2 = tu.convertStrToHex(param2);
		System.out.println(res_hex_2);
		System.out.println(tu.convertHexToStr("UTF-8", tu.deleteAllSpace(res_hex_2)));
		
		String res_hex_3 = tu.convertStrToHex(param3);
		System.out.println(res_hex_3);
		System.out.println(tu.convertHexToStr("UTF-8", tu.deleteAllSpace(res_hex_3)));
		
		System.out.println("Test_End convertStrToNumSystem");
	}
	
	
	@Test
	public void Test008() throws UnsupportedEncodingException {
		System.out.println("Test_Start encodeStr / decodeStr");
		
		
		String param1 = "빌드타이거";   
		String param2 = "ビルドタイガー";
		String param3 = "buildtiger";
		
		// UTF-8이므로 위의 Param도 UTF-8
		System.out.println("file encoding : " + System.getProperty("file.encoding"));
		
		
		//1. 인코딩과 디코딩의 기본 (인코딩시에는 바이트 배열에 인코딩 형식을 지정, 디코딩 시에는 인코딩시에 지정한 인코딩 형식을 지정해서 디코딩해줘야한다.
		
		byte [] p_euc = param1.getBytes("EUC-KR");           // (1) 인코딩은 원본 문자열 데이터 -> 바이트 배열(인코딩형식지정)으로 할 수 있음
		System.out.println(new String(p_euc, "EUC-KR"));     // (2) EUC로 인코딩 되었으므로, EUC로 디코딩하면 한글이 정상적으로 출력
		System.out.println(new String(p_euc, "UTF-8"));      // (3) EUC로 인코딩 되었으므로, UTF-8로 디코딩하면 한글이 깨진다
		
		System.out.println("-------------------------------------");
		byte [] p_utf = param1.getBytes("UTF-8");
		System.out.println(new String(p_utf, "EUC-KR"));     // (4) UTF-8로 인코딩 되었으므로, EUC로 디코딩하면 한글이 깨진다
		System.out.println(new String(p_utf, "UTF-8"));      // (5) UTF-8로 인코딩 되었으므로, UTF-8로 디코딩하면 한글이 정상적으로 출력
		
		System.out.println("-------------------------------------");
		byte [] p_defualt = param1.getBytes();               // (6) 인코딩 지정이 없으면 디폴트 인코딩을 사용한다
		System.out.println(new String(p_defualt, "EUC-KR")); // (7) UTF-8로 인코딩 되었으므로, EUC로 디코딩하면 한글이 깨진다
		System.out.println(new String(p_defualt, "UTF-8"));  // (8) UTF-8로 인코딩 되었으므로, UTF-8로 디코딩하면 한글이 정상적으로 출력
		System.out.println(new String(p_defualt));           // (9) 디폴트 인코딩이 UTF-8 이므로, UTF-8로 디코딩하면 한글이 정상적으로 출력
		
		
		System.out.println("-------------------------------------");
		//2. 다른 인코딩 방식으로 인코딩된 문자열을 다른 인코딩 방식의 문자열로 변환하려면 ??
		// (Ex) EUC-KR -> UTF-8
		
		byte [] p_euc2 = param1.getBytes("EUC-KR");           // (1) "EUC-KR"로 인코딩된 문자열데이터 -> "EUC-KR"의 바이트 배열로 만듬.
		String eucStr1 = new String(p_euc2, "EUC-KR");        // (2) "EUC-KR"의 바이트 배열 -> "EUC-KR"로 디코딩한 문자열 데이터로 만듬
		byte [] eucStr1By = eucStr1.getBytes("UTF-8");        // (3) "EUC-KR"로 디코딩한 문자열에서 인코딩형식을 "UTF-8"의 바이트 배열로 만듬
		System.out.println(new String(eucStr1By, "UTF-8"));   // (4) "UTF-8"의 바이트 배열" -> UTF-8"로 디코딩한 문자열 데이터로 만듬
		
		
		// 3. StringUtil의 메소드 시험
		List<String> list1 = makeEncodedStrList(param1);
		List<String> list2 = makeEncodedStrList(param2);
		List<String> list3 = makeEncodedStrList(param3);
		int su = 0;
		for (String str : list1) {
			System.out.println("------------- "+encodingSupportArray[su] +" ------------- ");
			makeDecodedStrList(str);
			su++;
		}
		su = 0;
		for (String str : list2) {
			System.out.println("------------- "+encodingSupportArray[su] +" ------------- ");
			makeDecodedStrList(str);
			su++;
		}
		su = 0;
		for (String str : list3) {
			System.out.println("------------- "+encodingSupportArray[su] +" ------------- ");
			makeDecodedStrList(str);
			su++;
		}
		
		
		System.out.println("Test_End encodeStr / decodeStr");
	}
	
	protected List<String> makeDecodedStrList(String target) {
		List<String> list = new ArrayList<String>();
		System.out.println("------------------- makeDecodedStrList : "+target+" -------------------");
		
		String p1_res_utf8 = tu.decodeStr("UTF-8", target);
		String p1_res_euckr = tu.decodeStr("EUC-KR", target);
		String p1_res_ISO = tu.decodeStr("ISO-8859-1", target);
		String p1_res_eucjp = tu.decodeStr("EUC-JP", target);
		String p1_res_sjis = tu.decodeStr("Shift-JIS", target);
		String p1_res_uni = tu.decodeStr("UNICODE", target);
		
		list.add(0,p1_res_utf8);
		list.add(1, p1_res_euckr);
		list.add(2,p1_res_ISO);
		list.add(3, p1_res_eucjp);
		list.add(4,p1_res_sjis);
		list.add(5, p1_res_uni);
		
		System.out.println("Decode Type : UTF-8\n ["+target+"] -> ["+p1_res_utf8+"]");
		System.out.println("Decode Type : EUC-KR\n ["+target+"] -> ["+p1_res_euckr+"]");
		System.out.println("Decode Type : ISO-8859-1\n ["+target+"] -> ["+p1_res_ISO+"]");
		System.out.println("Decode Type : EUC-JP\n ["+target+"] -> ["+p1_res_eucjp+"]");
		System.out.println("Decode Type : Shift-JIS\n ["+target+"] -> ["+p1_res_sjis+"]");
		System.out.println("Decode Type : UNICODE\n ["+target+"] -> ["+p1_res_uni+"]");
		
		
		System.out.println("------------------- makeDecodedStrList : "+target+" -------------------");
		return list;
	}
	
	protected List<String> makeEncodedStrList(String target) {
		List<String> list = new ArrayList<String>();
		System.out.println("------------------- makeEncodedStrList : "+target+" -------------------");
		
		String p1_res_utf8 = tu.decodeStr("UTF-8", target);
		String p1_res_euckr = tu.decodeStr("EUC-KR", target);
		String p1_res_ISO = tu.decodeStr("ISO-8859-1", target);
		String p1_res_eucjp = tu.decodeStr("EUC-JP", target);
		String p1_res_sjis = tu.decodeStr("Shift-JIS", target);
		String p1_res_uni = tu.decodeStr("UNICODE", target);
		
		list.add(0,p1_res_utf8);
		list.add(1, p1_res_euckr);
		list.add(2,p1_res_ISO);
		list.add(3, p1_res_eucjp);
		list.add(4,p1_res_sjis);
		list.add(5, p1_res_uni);
		
		System.out.println("Encode Type : UTF-8\n ["+target+"] -> ["+p1_res_utf8+"]");
		System.out.println("Encode Type : EUC-KR\n ["+target+"] -> ["+p1_res_euckr+"]");
		System.out.println("Encode Type : ISO-8859-1\n ["+target+"] -> ["+p1_res_ISO+"]");
		System.out.println("Encode Type : EUC-JP\n ["+target+"] -> ["+p1_res_eucjp+"]");
		System.out.println("Encode Type : Shift-JIS\n ["+target+"] -> ["+p1_res_sjis+"]");
		System.out.println("Encode Type : UNICODE\n ["+target+"] -> ["+p1_res_uni+"]");
		
		
		System.out.println("------------------- makeEncodedStrList : "+target+" -------------------");
		return list;
	}
	

}
