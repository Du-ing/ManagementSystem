package ManagementSystem;

import java.util.*;

public class Operator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void uploadFile() {
		System.out.println("�ϴ��ļ�... ...");
	}
	
	public void showMenu() {
		int k=1;
		while(k!=5) {
			System.out.println("\n��ӭ���뵵��¼��Ա�˵���");
			System.out.println("=========================");
			System.out.println("1.�ϴ��ļ�\n"+"2.�����ļ�\n"+"3.�ļ��б�\n"+"4.�޸�����\n"+"5.�˳���¼\n"+"6.�˳�ϵͳ");
			System.out.println("=========================");
			System.out.print("�����������Ե��ò˵���");
			
			k=scanner.nextInt();
			scanner.nextLine();//����'\n'
			switch(k){
			case 1:{
				uploadFile();
			};break;
			case 2:{
				System.out.print("������Ҫ���ص��ļ�����");
				String filename=scanner.nextLine();				
				if(downloadFile(filename))
					System.out.println("���سɹ���");
			};break;
			case 3:{
				showFileList();
			};break;
			case 4:{
				System.out.print("�����������룺");
				String newpassword=scanner.nextLine();
				if(changeSelfInfo(newpassword))
					System.out.println("�޸ĳɹ���");
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
