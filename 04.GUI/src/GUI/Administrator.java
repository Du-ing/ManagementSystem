package GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import Frame.mainFrame;

public class Administrator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void showMenu() throws SQLException, IOException {
		
		new mainFrame(getName());																																																																																																
		
	}
}