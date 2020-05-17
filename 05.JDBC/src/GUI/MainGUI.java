package GUI;

import Frame.loginFrame;

public class MainGUI {
	public static void main(String args[]) {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/document";
		String user = "root";
		String password = "whlg201898."; 
		
		DataProcessing.connectToDatabase(driverName, url, user, password);//连接数据库
		
		new loginFrame();
		
	}		
}
