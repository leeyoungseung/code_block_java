package utils.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.FileIOUtil;

public class FileIOUtilTest {
	
	FileIOUtil fo = new FileIOUtil();
	
	@Test
	public void Test001() {
		System.out.println("Test_Start createDir");
		String testDir = "C:\\Users\\leeyoungseung\\fileTest\\test001";
		fo.createDir(testDir);
		
		assertTrue(fo.existDir(testDir));
		System.out.println("Test_End createDir");
	}
	
	@Test
	public void Test002() {
		System.out.println("Test_Start existDir");
		String testDir = "C:\\Users\\leeyoungseung\\fileTest\\test002";
		
		assertFalse(fo.existDir(testDir));
		System.out.println("Test_End existDir");
	}

	@Test
	public void Test003() {
		System.out.println("Test_Start createFile");
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test003.txt";
		fo.createFile(testFile);
		
		assertTrue(fo.existFile(testFile));
		System.out.println("Test_End createFile");
	}
	
	@Test
	public void Test004() {
		System.out.println("Test_Start existFile");
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test004.txt";

		assertFalse(fo.existFile(testFile));
		System.out.println("Test_End existFile");
	}
	
	@Test
	public void Test005() {
		System.out.println("Test_Start setFilePermission");
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test003.txt";
		
		assertTrue(fo.setFilePermission(testFile, 5));
		
		System.out.println("Test_End setFilePermission");
	}
	
	
	@Test
	public void Test006() {
		System.out.println("Test_Start checkPermission");
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test003.txt";
		
		System.out.println(fo.checkPermission(testFile));
		
		assertTrue((5 == fo.checkPermission(testFile)));
		
		System.out.println("Test_End checkPermission");
	}
	
	
	@Test
	public void Test007() {
		System.out.println("Test_Start writeCsvFile");
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test007.csv";
		if(fo.existFile(testFile)) {
			fo.delete(testFile);
		}
		
		String [] array1 = new String [] {"tiger1","tiger2","tiger3","tiger4","tiger5"};
		String [] array2 = new String [] {"tiger6","tiger7","tiger8","tiger9","tiger10"};
		List<String[]> list = new ArrayList<String[]>();
		list.add(array1);
		list.add(array2);
		
		assertTrue(fo.writeCsvFile(testFile, list));
		
		System.out.println("Test_End writeCsvFile");
	}
	
	
	@Test
	public void Test008() {
		System.out.println("Test_Start readCsvFile");
		
		String testFile = "C:\\Users\\leeyoungseung\\fileTest\\test007.csv";
		List<String[]> list = fo.readCsvFile(testFile);
		
		for (String [] ary : list) {
			
			for (int su=0; su < ary.length; su++) {
				System.out.println(ary[su]);
			}
			
		}
		
		System.out.println("Test_End readCsvFile");
	}
	
	
	@Test
	public void Test009() {
		System.out.println("Test_Start delete");
		String testTxtFile = "C:\\Users\\leeyoungseung\\fileTest\\test003.txt";
		String testCsvFile = "C:\\Users\\leeyoungseung\\fileTest\\test007.csv";
		String testDir = "C:\\Users\\leeyoungseung\\fileTest\\test001";
		
		assertTrue(fo.delete(testTxtFile));
		assertFalse(fo.delete(testTxtFile));
		
		assertTrue(fo.delete(testCsvFile));
		assertFalse(fo.delete(testCsvFile));
		
		assertTrue(fo.delete(testDir));
		assertFalse(fo.delete(testDir));
		
		
		System.out.println("Test_End delete");
	}
	
}
