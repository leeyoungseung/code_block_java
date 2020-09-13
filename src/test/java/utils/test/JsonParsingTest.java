package utils.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonProcessingException;

import utils.JsonUtil;
import utils.model.JsonValBeanDat;
import utils.model.ValueTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class JsonParsingTest {
	
	String testJson1 = "{\"mainId\":\"a1001\","
			+ "\"mainNo\":\"10\"}";
	
	String testJson2 = "{\"message\":\"Success List Sentence\","
			+ "\"res\":true,"
			+ "\"data\":"
			+ "["
			+ "{\"user_id\":1,\"user_mail\":\"admin@gmail.com\",\"user_passwd\":\"admin\",\"user_name\":\"admin\",\"user_gender\":2,\"user_join_day\":\"2019-05-18T19:44:45\",\"user_update_day\":\"2019-05-18T09:00:00\",\"user_sort\":1,\"user_country\":\"JP\"},"
			+ "{\"user_id\":3,\"user_mail\":\"user2@gmail.com\",\"user_passwd\":\"user2\",\"user_name\":\"user2\",\"user_gender\":1,\"user_join_day\":\"2019-05-27T07:44:25\",\"user_update_day\":\"2019-05-27T07:44:25\",\"user_sort\":1,\"user_country\":\"JP\"},"
			+ "{\"user_id\":4,\"user_mail\":\"user3@gmail.com\",\"user_passwd\":\"user3\",\"user_name\":\"user3\",\"user_gender\":2,\"user_join_day\":\"2019-05-27T07:44:25\",\"user_update_day\":\"2019-05-27T07:44:25\",\"user_sort\":1,\"user_country\":\"KR\"}"
			+ "]}";
	
	@Test
	public void Test001() {
		System.out.println("Test_Start [Test001]");
		
		JsonUtil util = JsonUtil.getInstance();
		ValueTest res = null;
		res = (ValueTest)util.makeObjFromJson(testJson1, ValueTest.class);
		System.out.println("Parsing Result : ");
		System.out.println(res.toString());
		
		System.out.println("Test_End [Test001]");
		
	}
	
	
	@Test
	public void Test002() {
		System.out.println("Test_Start [Test002]");
		
		JsonUtil util = JsonUtil.getInstance();
		JsonValBeanDat res = null;
		res = (JsonValBeanDat) util.makeObjFromJson(testJson2, JsonValBeanDat.class);
		System.out.println("Parsing Result : \n"+res.toString());
		
		
		System.out.println("---------------------");
		System.out.println("--------Convert from java object to json ---------");
		String convertedJson = null;
				
		convertedJson = util.makeJsonFromObj(res);
		System.out.println("Convert Result : \n"+convertedJson);
		
		System.out.println("Test_End [Test002]");
		
	}
	

}


