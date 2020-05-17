package ExceptionSolve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Administrator extends User {
	Scanner scanner=new Scanner(System.in);
	
	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void showMenu() {
		int k=1;
		while(k!=8) {
			System.out.println("\n��ӭ���뵵������Ա�˵���");
			System.out.println("=========================");
			System.out.println("1.�޸��û�\n"+"2.ɾ���û�\n"+"3.�����û�\n"+"4.�г��û�\n"
			                  +"5.�����ļ�\n"+"6.�ļ��б�\n"+"7.�޸ı�������\n"+"8.�˳���¼\n"+"9.�˳�ϵͳ");
			System.out.println("=========================");
			System.out.print("�����������Ե��ò˵���");
			
			k=scanner.nextInt();
			scanner.nextLine();//����'\n'
			switch(k) {
			case 1:{
				System.out.print("�������û�����");
				String name=scanner.nextLine();
				if(!name.equalsIgnoreCase(getName())) {
					System.out.print("�����������룺");
					String newpassword=scanner.nextLine();
					System.out.print("�������½�ɫ��");
					String newrole=scanner.nextLine();
					try {
						if(Dataprocessing.update(name,newpassword,newrole))
							System.out.println("�޸ĳɹ���");
						else
							System.out.println("�޸�ʧ�ܣ�����������û��Ƿ����");
					} catch(SQLException e) {
						System.out.println(e);
					}				
				}
				else
					System.out.println("�޷��޸��Լ����˺ţ�");
				
			};break;
			case 2:{
				System.out.print("������Ҫɾ�����û�����");
				String name=scanner.nextLine();
				if(!name.equalsIgnoreCase(getName())) {
					try {					
						if(Dataprocessing.delete(name))
							System.out.println("ɾ���ɹ���");
						else
							System.out.println("ɾ��ʧ�ܣ�����������û����Ƿ��Ѵ���");					
					} catch(SQLException e) {
					System.out.println(e);
				}
				}				
				else
					System.out.println("�޷�ɾ���Լ����˺ţ�");
			};break;
			case 3:{
				System.out.print("�������������û�����");
				String newname=scanner.nextLine();
				System.out.print("���������룺");
				String newpassword=scanner.nextLine();
				System.out.print("�������ɫ��");
				String newrole=scanner.nextLine();
				try {
					if(Dataprocessing.insert(newname, newpassword, newrole))
						System.out.println("���ӳɹ���");
					else
						System.out.println("����ʧ�ܣ����û��Ѵ��ڻ��߽�ɫ���������");
				} catch(SQLException e) {
					System.out.println(e);
				}				
			};break;
			case 4:{
				//ö�ٷ��������ڵĶ���
				for (Enumeration<User> e = Dataprocessing.users.elements(); e.hasMoreElements();) {
					User u=e.nextElement();
					System.out.println("�û�����"+u.getName()+" ���룺"+u.getPassword()+" ��ɫ��"+u.getRole());
				}
			};break;
			case 5:{
				System.out.print("������Ҫ���ص��ļ�����");
				String filename=scanner.nextLine();
				try {
					if(downloadFile(filename))
						System.out.println("���سɹ���");
				} catch(IOException e) {
					System.out.println(e);
				}
			};break;
			case 6:{
				try {
					showFileList();
				} catch(SQLException e) {
					System.out.println(e);
				}
			};break;
			case 7:{
				System.out.print("�����������룺");
				String newpassword=scanner.nextLine();
				try {
					if(changeSelfInfo(newpassword))
						System.out.println("�޸ĳɹ���");
				} catch(SQLException e)	{
					System.out.println(e);
				}
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