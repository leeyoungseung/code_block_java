package utils.test;

import org.junit.Test;

import utils.PagingUtil;

public class PagingUtilTest {
	
	PagingUtil pu;

	@Test
	public void Test001() {
		System.out.println("Test_Start Paging");
		
		pu = new PagingUtil(1);
		pu.setCalcForPaging(100);
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(2);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(3);
		pu.setCalcForPaging();
		System.out.println(pu.toString());		
		
		pu = new PagingUtil(5);
		pu.setCalcForPaging(103);
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(6);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(7);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(10);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(11);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(12);
		pu.setCalcForPaging();
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(5);
		pu.setCalcForPaging(0);
		System.out.println(pu.toString());
		
		pu.setCurrentPageNum(0);
		pu.setCalcForPaging(100);
		System.out.println(pu.toString());
		
		System.out.println("Test_End Paging");
	}
}
