package utils.test;

import org.junit.Test;

import utils.XmlUtil;
import utils.XmlUtilForExXmlModel;
import utils.model.ExXmlModel;

public class XmlParsingTest {
	
	String testXml = "<test01Request>\n" + 
			"  <mainId>m1234</mainId>\n" + 
			"  <mainControlId>c1234</mainControlId>\n" + 
			"  <mainNo>1</mainNo>\n" + 
			"  <openId>12345678abcd</openId>\n" + 
			"  <totalFee>900</totalFee>\n" + 
			"  <createDate>2020-09-11</createDate>\n" + 
			"  <items>\n" + 
			"    <item>\n" + 
			"      <itemId>1</itemId>\n" + 
			"      <itemName>item-a</itemName>\n" + 
			"      <itemFee>100</itemFee>\n" + 
			"      <itemCount>3</itemCount>\n" + 
			"    </item>\n" + 
			"    <item>\n" + 
			"      <itemId>2</itemId>\n" + 
			"      <itemName>item-b</itemName>\n" + 
			"      <itemFee>300</itemFee>\n" + 
			"      <itemCount>2</itemCount>\n" + 
			"    </item>\n" + 
			"  </items>\n" + 
			"  <members>\n" + 
			"    <member>\n" + 
			"      <memberId>a0001</memberId>\n" + 
			"      <memberName>david</memberName>\n" + 
			"    </member>\n" + 
			"    <member>\n" + 
			"      <memberId>a0002</memberId>\n" + 
			"      <memberName>alice</memberName>\n" + 
			"    </member>\n" + 
			"  </members>\n" + 
			"</test01Request>";

	@Test
	public void Test001() {
		System.out.println("Test_Start");
	
		XmlUtil util = new XmlUtilForExXmlModel();
		
		ExXmlModel res = (ExXmlModel) util.parser(testXml, new ExXmlModel());
		res.setItems();
		res.setMembers();
		
		System.out.println("Test_End");
		
		System.out.println("---------------------------------------------------\n");
		System.out.println("---------------------------------------------------\n");
		System.out.println("---------------------------------------------------\n");
		
		System.out.println(res.toString());
		
		System.out.println("---------------------------------------------------\n");
		System.out.println("---------------------------------------------------\n");
		System.out.println("---------------------------------------------------\n");
		
		
		String resXml = util.makeXml(res);
		System.out.println(resXml);
		
		
	}
}
