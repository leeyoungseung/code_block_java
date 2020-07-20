package utils.test;

import org.junit.Test;

import utils.DBUtil;

public class DBUtilTest {

	DBUtil du = DBUtil.getInstance();

	@Test
	public void Test001() {
		System.out.println("Test_Start Oracle");
		du.setDBParam();
		du.initConnection();

		System.out.println();
		System.out.println(du.getConnection());

		System.out.println("Test_End Oracle");
	}

	@Test
	public void Test002() {
		System.out.println("Test_Start MySQL");
		du.setDBParam(
				"com.mysql.jdbc.Driver",        // Driver Class
				"jdbc:mysql://localhost:3306/", // MySQL URL
				"board_test?serverTimezone=UTC",// DB Name + Option
				"root",                         // User Name
				"1234",                         // Password
				"10",                           // MaxActive                    
				"10",                           // MaxIdle
				"5",                            // MinIdle
				"5",                            // MaxWait
				"SELECT 1"                      // TestQuery
				);
		du.initConnection();

		System.out.println();
		System.out.println(du.getConnection());

		System.out.println("Test_End MySQL");
	}

}
