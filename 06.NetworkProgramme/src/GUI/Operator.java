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
	
	public void uploadFile() throws SQLException, IOException {
		System.out.print("请输入源文件名：");
		String fileName=scanner.nextLine();
		System.out.print("请输入档案号：");
		String ID=scanner.nextLine();
		System.out.print("请输入档案描述：");
		String description=scanner.nextLine();
		System.out.print("请输入制作者：");
		String creator=scanner.nextLine();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		if(DataProcessing.insertDoc(ID, creator, timestamp, description, fileName)) {
			File f=new File(fileName);
			BufferedInputStream infile=new BufferedInputStream(new FileInputStream(fileName));
			BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(uploadpath+f.getName()));
			byte buffer[]=new byte[1024];
			while(true) {
				int byteRead=infile.read(buffer);   //读到文件尾返回-1
				if(byteRead==-1)
					break;
				targetfile.write(buffer,0, byteRead);
			}
			infile.close();
			targetfile.close();
			
			System.out.println("上传成功！");
		}
		else
			System.out.println("上传失败！");	
	}
	
	public void showMenu(Client client) throws SQLException, IOException {
		new mainFrame(getName(), client);
		
		/*int k=1;
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
				System.out.print("请输入要下载的档案号：");
				String ID=scanner.nextLine();				
				if(downloadFile(ID))
					System.out.println("下载成功！");
			};break;
			case 3:{
				showFileList();
			};break;
			case 4:{
				System.out.print("请输入新密码：");
				String newpassword=scanner.nextLine();
				if(changeSelfInfo(newpassword))
					System.out.println("修改成功！");
			};break;
			case 5:{
				exitUser();
			};break;
			case 6:{
				exitSystem();
			}
			}
		}*/	
	}
}