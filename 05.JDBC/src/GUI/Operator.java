package GUI;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import Frame.mainFrame;
import java.io.*;

public class Operator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void showMenu() throws SQLException, IOException {
		new mainFrame(getName());
		
	}
}