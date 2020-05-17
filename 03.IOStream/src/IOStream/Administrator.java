package IOStream;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void showMenu() throws SQLException, IOException {
		int k=1;
		while(k!=8) {
			System.out.println("\n欢迎进入档案管理员菜单：");
			System.out.println("=========================");
			System.out.println("1.修改用户\n"+"2.删除用户\n"+"3.新增用户\n"+"4.列出用户\n"
			                  +"5.下载文件\n"+"6.文件列表\n"+"7.修改本人密码\n"+"8.退出登录\n"+"9.退出系统");
			System.out.println("=========================");
			System.out.print("请输入数字以调用菜单：");
			
			k=scanner.nextInt();
			scanner.nextLine();//消除'\n'
			switch(k) {
			case 1:{
				System.out.print("请输入用户名：");
				String name=scanner.nextLine();
				if(!name.equalsIgnoreCase(getName())) {
					System.out.print("请输入新密码：");
					String newpassword=scanner.nextLine();
					System.out.print("请输入新角色：");
					String newrole=scanner.nextLine();
					try {
						if(DataProcessing.updateUser(name,newpassword,newrole))
							System.out.println("修改成功！");
						else
							System.out.println("修改失败！请检查输入的用户是否存在");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
					System.out.println("无法修改自己的账号！");
			};break;
			case 2:{
				System.out.print("请输入要删除的用户名：");
				String name=scanner.nextLine();
				if(!name.equalsIgnoreCase(getName())) {
					try {
						if(DataProcessing.deleteUser(name))
							System.out.println("删除成功！");
						else
							System.out.println("删除失败！请检查输入的用户名是否已存在");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
					System.out.println("无法删除自己的账号！");
			};break;
			case 3:{
				System.out.print("请输入新增的用户名：");
				String newname=scanner.nextLine();
				System.out.print("请输入密码：");
				String newpassword=scanner.nextLine();
				System.out.print("请输入角色：");
				String newrole=scanner.nextLine();
				try {
					if(DataProcessing.insertUser(newname, newpassword, newrole))
						System.out.println("增加成功！");
					else
						System.out.println("增加失败！该用户已存在或者角色名输入错误");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			};break;
			case 4:{
				//枚举法遍历表内的对象
				for (Enumeration<User> e = DataProcessing.users.elements(); e.hasMoreElements();) {
					User u=e.nextElement();
					System.out.println("用户名："+u.getName()+" 密码："+u.getPassword()+" 角色："+u.getRole());
				}
			};break;
			case 5:{
				System.out.print("请输入要下载的档案号：");
				String filename=scanner.nextLine();				
				if(downloadFile(filename))
				System.out.println("下载成功！");
			};break;
			case 6:{
				showFileList();
			};break;
			case 7:{
				System.out.print("请输入新密码：");
				String newpassword=scanner.nextLine();
				if(changeSelfInfo(newpassword))
					System.out.println("修改成功！");
			};break;
			case 8:{
				exitUser();
			};break;
			case 9:{
				exitSystem();
			}
			}
		}
	}
}