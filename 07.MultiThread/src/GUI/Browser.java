package GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import Frame.mainFrame;

public class Browser extends User {
	Scanner scanner=new Scanner(System.in);

	Browser(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void showMenu(Client client) throws SQLException, IOException {
		
		new mainFrame(getName(), client);
		
		/*int k=1;
		while(k!=4) {
			System.out.println("\n欢迎进入档案浏览员菜单：");
			System.out.println("=========================");
			System.out.println("1.下载文件\n"+"2.文件列表\n"+"3.修改密码\n"+"4.退出登录\n"+"5.退出系统");
			System.out.println("=========================");
			System.out.print("请输入数字以调用菜单：");
			
			k=scanner.nextInt();
			scanner.nextLine();//消除'\n'
			switch(k) {
			case 1:{
				System.out.print("请输入要下载的档案号：");
				String filename=scanner.nextLine();				
				if(downloadFile(filename))
					System.out.println("下载成功！");
			};break;
			case 2:{
				showFileList();
			};break;
			case 3:{
				System.out.print("请输入新密码：");
				String newpassword=scanner.nextLine();
				if(changeSelfInfo(newpassword))
					System.out.println("修改成功！");
			};break;
			case 4:{
				exitUser();
			};break;
			case 5:{
				exitSystem();
			};break;
			}
		}*/
	}
}
