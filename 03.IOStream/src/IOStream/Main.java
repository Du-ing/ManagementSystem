package IOStream;

import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] arg) throws IOException {
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		
		while(true) {
		System.out.println("欢迎使用档案管理系统");
		System.out.println("菜单：\n"+"====================");
		System.out.println("1.登录\n"+"2.退出\n"+"====================");
		System.out.print("请输入数字以调用菜单功能：");
		int a=scanner.nextInt();
		scanner.nextLine();//去掉上一次输入时滞留的'\n'的影响
			
			switch(a){
			case 1:{
				while(true) {
					String name,password;
					System.out.print("请输入用户名：");
					name=scanner.nextLine();
					System.out.print("请输入密码：");
					password=scanner.nextLine();
					
					User u;
					try {
						u=DataProcessing.searchUser(name, password);
						if(u!=null) {
							System.out.println(name+"登陆成功！");
							u.showMenu();
							break;
						}
						else
							System.out.println("用户名不存在或密码不正确，请重新输入！");
					} catch (SQLException e) {
						System.out.println(e);
					}
				}
			};;break;
			case 2:{
				System.out.println("系统已退出，感谢使用！");
				System.exit(0);
			};break;
			default:{
				System.out.println("输入有误，请重新输入！\n");
			}
		    }
		}	
	}
}
