package utils;

import java.sql.*;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * DB Connection Pool을 제공해주는 클래스
 * @author leeyoungseung
 *
 */
public class DBUtil {

	
	private String dbDriverClassName;  // 드라이버 클래스 명
	private String dbUrl;              // DB URL
	private String dbName;             // DB명
	private String dbId;               // DB 접속용 유저 ID
	private String dbPw;               // DB 접속용 유저 PW
	
	/**
	 * 주어진 시간에서의 풀에 의해 할당되어 관리되는 커넥션의 최대수.
	 * 설정이 양수가 아닌경우에는 한번에 풀에서 관리될 수 있는 오브젝트의 숫자에는 제한이 없음.
	 * 기본 설정은 8
	 */
	private String dbMaxActive;
	/**
	 * 풀에서 대기상태로 관리되어 질수 있는 커넥션 최대 수.
	 * 음수인경우에는 풀에서 대기상태로 있는 오브젝트의 수에 제한이 없음 
	 * 기본설정은 8
	 */
	private String dbMaxIdle;
	
	/**
	 * 풀에서 대기상태로 관리되어 질수 있는 최소 커넥션수.
	 */
	private String dbMinIdle;
	
	/**
	 * 커넥션 할당 최대 대기 시간
	 */
	private String dbMaxWait;
	private String dbTestQuery;        // 접속 테스트용 쿼리 Oracle : 'SELECT 1 FROM DUAL' , MySQL OR MS Sql : 'SELECT 1' 

	private DBUtil() {};

	public static DBUtil getInstance() {
		return DBUtilHolder.INSTANCE;
	}

	private static class DBUtilHolder {
		private static final DBUtil INSTANCE = new DBUtil();
	}

	public void setDBParam() {
		this.dbDriverClassName = ConfigUtil.getProperties("dbDriverClassName");
		this.dbUrl = ConfigUtil.getProperties("dbUrl");
		this.dbName = ConfigUtil.getProperties("dbName");
		this.dbId = ConfigUtil.getProperties("dbId");
		this.dbPw = ConfigUtil.getProperties("dbPw");
		this.dbMaxActive = ConfigUtil.getProperties("dbMaxActive");
		this.dbMaxIdle = ConfigUtil.getProperties("dbMaxIdle");
		this.dbMinIdle = ConfigUtil.getProperties("dbMinIdle");
		this.dbMaxWait = ConfigUtil.getProperties("dbMaxWait");
		this.dbTestQuery = ConfigUtil.getProperties("dbTestQuery");

		System.out.println(toString());
	}

	public void setDBParam(String dbDriverClassName, String dbUrl, String dbName, String dbId,
			String dbPw, String dbMaxActive, String dbMaxIdle, String dbMinIdle, String dbMaxWait,
			String dbTestQuery) {
		
		this.dbDriverClassName = dbDriverClassName;
		this.dbUrl = dbUrl;
		this.dbName = dbName;
		this.dbId = dbId;
		this.dbPw = dbPw;
		this.dbMaxActive = dbMaxActive;
		this.dbMaxIdle = dbMaxIdle;
		this.dbMinIdle = dbMinIdle;
		this.dbMaxWait = dbMaxWait;
		this.dbTestQuery = dbTestQuery;
		System.out.println(toString());
	}

	public void initConnection() {
		try {

			Class.forName(dbDriverClassName);

			GenericObjectPool conPool = new GenericObjectPool(null);
			conPool.setMaxActive(Integer.parseInt(dbMaxActive));
			conPool.setMaxIdle(Integer.parseInt(dbMaxIdle));
			conPool.setMinIdle(Integer.parseInt(dbMinIdle));
			conPool.setMaxWait(Integer.parseInt(dbMaxWait));
			conPool.setTimeBetweenEvictionRunsMillis(3600000);
			conPool.setMinEvictableIdleTimeMillis(1800000);
			conPool.setTestOnBorrow(true);

			String setUrl = dbUrl + dbName ;
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(setUrl, dbId, dbPw);

			PoolableConnectionFactory pcf = new PoolableConnectionFactory(
					connectionFactory,  // ConnectionFactory
					conPool,            // GenericObjectPool
					null,               // KeyedObjectPoolFactory 
					dbTestQuery,        // 커넥션이 유효한지 테스트하는 쿼리 DB별로 다른 쿼리를 써야한다.
					false,              // read only 미설정
					false);             // auto commit 미설정

			PoolingDriver driver = new PoolingDriver();
			driver.registerPool("conn", conPool);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:apache:commons:dbcp:conn");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return con;
	}

	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close(); rs = null;
			if (pstmt != null)
				pstmt.close(); pstmt = null;
			freeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close(); rs = null;
			if (stmt != null)
				stmt.close(); stmt = null;
			freeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close(); pstmt = null;
			freeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, Statement stmt) {
		try {
			if (stmt != null)
				stmt.close(); stmt = null;
			freeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con) {
		try {
			if (con != null)
				con.close(); con = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close(); stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close(); pstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(ResultSet rs) {
		try {
			if (rs != null)
				rs.close(); rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "DBUtil [dbName=" + dbName + ", dbDriverClassName=" + dbDriverClassName + ", dbUrl=" + dbUrl + ", dbId="
				+ dbId + ", dbPw=" + dbPw + ", dbMaxActive=" + dbMaxActive + ", dbMaxIdle=" + dbMaxIdle + ", dbMinIdle="
				+ dbMinIdle + ", dbMaxWait=" + dbMaxWait + ", dbTestQuery=" + dbTestQuery + "]";
	}

	

}
