package GUI;

import java.io.IOException;

import javax.swing.JFrame;

import Frame.loginFrame;

public class MainGUI {
	public static void main(String args[]) throws IOException {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/document";
		String user = "root";
		String password = "whlg201898."; 
		
		DataProcessing.connectToDatabase(driverName, url, user, password);   //连接数据库
		
		Client client = new Client("127.0.0.1");
		new loginFrame(client);
		client.processConnection();
	}		
}
