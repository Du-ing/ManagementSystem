package ExceptionSolve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Operator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void uploadFile() {
		System.out.println("上传文件... ...");
	}
	
	public void showMenu() {
		int k=1;
		while(k!=5) {
			System.out.println("\n欢迎进入档案录入员菜单：");
			System.out.println("=========================");
			System.out.println("1.上传文件\n"+"2.下载文件\n"+"3.文件列表\n"+"4.修改密码\n"+"5.退出登录\n"+"6.退出系统");
			System.out.println("=========================");
			System.out.print("请输入数字以调用菜单：");
			
			k=scanner.nextInt();
			scanner.nextLine();//消除'\n'
			switch(k){
			case 1:{
				uploadFile();
			};break;
			case 2:{
				System.out.print("请输入要下载的文件名：");
				String filename=scanner.nextLine();				
				try{
					if(downloadFile(filename))
						System.out.println("下载成功！");
				} catch(IOException e) {
					System.out.println(e);
				}				
			};break;
			case 3:{
				try {
					showFileList();
				} catch(SQLException e) {
					System.out.println(e);
				}
			};break;
			case 4:{
				System.out.print("请输入新密码：");
				String newpassword=scanner.nextLine();
				try {
					if(changeSelfInfo(newpassword))
						System.out.println("修改成功！");
				} catch(SQLException e) {
					System.out.println(e);
				}				
			};break;
			case 5:{
				exitUser();
			};break;
			case 6:{
				exitSystem();
			}
			}
		}	
	}
}
