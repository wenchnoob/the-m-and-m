package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private static Database forWenchy, forAll;
	
	private DBType type;
	private String path, username, password;
	
	private Database(String path, String username, String password, DBType type) {
		this.path = path; 
		this.username = username;
		this.password = password;
	}
	
	public static Database getInstance(DBType type) {
		if (type == DBType.WENCHY) {
			if (forWenchy == null) forWenchy = new Database("jdbc:mysql://localhost:3306/megatherium", "cmc", "pleasejustwork!", DBType.WENCHY);
			return forWenchy;
		}
		if (forAll == null) forAll = new Database("jdbc:mysql://devsrv.cs.csbsju.edu/megatherium", "megatherium", "csci230", DBType.ALL);
		return forAll;
	}
	
	private Connection openConnection() {
		try {
			if (type == DBType.WENCHY) {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} else {
				Class.forName("com.mysql.jdbc.Driver");
			}
			return DriverManager.getConnection(path, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet execute(String cmd) {
		try {
			return openConnection().createStatement().executeQuery(cmd);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(String cmd) {
		try {
			openConnection().createStatement().executeUpdate(cmd);
			return true;
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	
	public enum DBType {
		WENCHY, ALL;
	}

}
