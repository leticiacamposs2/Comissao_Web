package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

	private Connection con;
	private static ConnectionSingleton instancy;
	private static final String JDBC_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433/comissoes";
	private static final String JDBC_USER = "admin";
	private static final String JDBC_PASS = "";
	
	
	/*	private Connection con;
	private static ConnectionSingleton instancy;
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String JDBC_USER = "SYSTEM";
	private static final String JDBC_PASS = "CATDOG";
	
		private ConnectionSingleton() throws GenericDAOException {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			} catch (SQLException | ClassNotFoundException e) {
				throw new GenericDAOException( e );
			}
		} */
	
	
	
	private ConnectionSingleton() throws GenericDAOException {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (SQLException | ClassNotFoundException e) {
			throw new GenericDAOException( e );
		}
	} 
	
	public static ConnectionSingleton getInstancy() {
		if (instancy == null) {
			try {
				instancy = new ConnectionSingleton();
			} catch (GenericDAOException e) {
				e.printStackTrace();
			}
		}
		return instancy;	
	}
	 
	public Connection getcon() {
		return this.con;
		
	}
}