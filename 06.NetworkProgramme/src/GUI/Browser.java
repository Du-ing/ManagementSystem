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
			System.out.println("\n��ӭ���뵵�����Ա�˵���");
			System.out.println("=========================");
			System.out.println("1.�����ļ�\n"+"2.�ļ��б�\n"+"3.�޸�����\n"+"4.�˳���¼\n"+"5.�˳�ϵͳ");
			System.out.println("=========================");
			System.out.print("�����������Ե��ò˵���");
			
			k=scanner.nextInt();
			scanner.nextLine();//����'\n'
			switch(k) {
			case 1:{
				System.out.print("������Ҫ���صĵ����ţ�");
				String filename=scanner.nextLine();				
				if(downloadFile(filename))
					System.out.println("���سɹ���");
			};break;
			case 2:{
				showFileList();
			};break;
			case 3:{
				System.out.print("�����������룺");
				String newpassword=scanner.nextLine();
				if(changeSelfInfo(newpassword))
					System.out.println("�޸ĳɹ���");
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
