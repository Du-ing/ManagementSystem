package GUI;

import java.sql.*;

import javax.swing.JDialog;
import javax.swing.JLabel;

public  class DataProcessing {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static boolean connectedToDatabase = false;
	
	public static void connectToDatabase(String driverName, String url, String user, String password) {
		String s ="";
		try {
			Class.forName(driverName);    //加载数据库驱动类
			connection = DriverManager.getConnection(url, user, password);
			connectedToDatabase = true;
			s = "       连接数据库成功！";
		} catch (ClassNotFoundException e) {
			s = "       加载驱动失败！";
			e.printStackTrace();
		} catch (SQLException e) {
			s = "       数据库访问错误！";
			e.printStackTrace();
		} finally {
			JDialog dia = new JDialog();
			dia.setTitle("提示");
			dia.setBounds(100, 100, 100, 100);
			dia.add(new JLabel(s));
			dia.setVisible(true);
		}
	}
		
	
	public static void disconnectFromDatabase() {
		if(connectedToDatabase) {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connectedToDatabase = false;
			}
		}
	}
	
	public static User searchUser(String name) throws SQLException {
		User temp = null;
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "select * from user_info where username = '" + name + "'";
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			String username = resultSet.getString("username");
			String password1 = resultSet.getString("password");
			String role = resultSet.getString("role");
			//对象不会转化为相应角色，要手动判断
			if(resultSet.getString("role").equalsIgnoreCase("browser")) {
				temp = new Browser(username, password1, role);
				return temp;
			}
			else if(resultSet.getString("role").equalsIgnoreCase("operator")) {
				temp = new Operator(username, password1, role);
				return temp;
			}
			else if(resultSet.getString("role").equalsIgnoreCase("administrator")) {
				temp = new Administrator(username, password1, role);
				return temp;
			}
		}
		return temp;
	}
	
	public static User searchUser(String name, String password) throws SQLException {
		
		User temp = null;
		
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "select * from user_info where username = '" + name + "'";
		resultSet = statement.executeQuery(sql);
		resultSet.next();
		
		if(password.equalsIgnoreCase(resultSet.getString("password"))) {
			System.out.println(resultSet.getString("username"));
				String username = resultSet.getString("username");
				String password1 = resultSet.getString("password");
				String role = resultSet.getString("role");
				//对象不会转化为相应角色，要手动判断
				if(resultSet.getString("role").equalsIgnoreCase("browser")) {
					temp = new Browser(username, password1, role);
					return temp;
				}
				else if(resultSet.getString("role").equalsIgnoreCase("operator")) {
					temp = new Operator(username, password1, role);
					return temp;
				}
				else if(resultSet.getString("role").equalsIgnoreCase("administrator")) {
					temp = new Administrator(username, password1, role);
					return temp;
				}
		}
		return temp;
	}
	
	public static boolean updateUser(String name, String password, String role) throws SQLException {
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "update user_info set password = '" + password + "', role = role where username = '" + name + "'";
		statement.execute(sql);
		
		return true;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException {
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "insert into user_info values('" + name + "','" + password + "','"+ role + "')";
		statement.execute(sql);
		
		return true;
	}
	
	public static boolean deleteUser(String name) throws SQLException {
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "delete from user_info where username='" + name + "'";
		statement.execute(sql);
		
		return true;
	}
	
	public static String[] getAllUser(String fname) throws SQLException {
		String name[] = new String[10];
		
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "select * from user_info";
		resultSet = statement.executeQuery(sql);
		
		int i = 0;
		while(resultSet.next()) {
			if(resultSet.getString("username").equalsIgnoreCase(fname)) {
				continue;
			}
			name[i++] = resultSet.getString("username");
		}
		return name;
	}
	
	public static Doc searchDoc(String DocID) throws SQLException {
		Doc temp = null;
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "select * from doc_info where Id='" + DocID + "'";
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			String ID = resultSet.getString("Id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			temp = new Doc(ID, creator, timestamp, description, filename);
		}
		return temp;
	}
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException {
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "insert into doc_info values('" + ID + "','"+ creator +"','" + timestamp +"','"+ description +"','"+ filename +"')";
		statement.execute(sql);
		
		return true;
	}
	
	public static String[][] getAllDoc() throws SQLException {
		String doc[][] = new String[10][5];
		
		if(!connectedToDatabase) {
			throw new SQLException("连接数据库失败！");
		}
		
		statement = connection.createStatement();
		String sql = "select * from doc_info";
		resultSet = statement.executeQuery(sql);
		
		int i = 0;
		while(resultSet.next()) {
			doc[i][0] = resultSet.getString("ID");
			doc[i][1] = resultSet.getString("creator");
			doc[i][2] = resultSet.getString("timestamp");
			doc[i][3] = resultSet.getString("filename");
			doc[i][4] = resultSet.getString("description");
			i++;
		}
		return doc;
	}
	
}
