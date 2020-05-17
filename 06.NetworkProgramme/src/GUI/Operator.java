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
		System.out.print("������Դ�ļ�����");
		String fileName=scanner.nextLine();
		System.out.print("�����뵵���ţ�");
		String ID=scanner.nextLine();
		System.out.print("�����뵵��������");
		String description=scanner.nextLine();
		System.out.print("�����������ߣ�");
		String creator=scanner.nextLine();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		if(DataProcessing.insertDoc(ID, creator, timestamp, description, fileName)) {
			File f=new File(fileName);
			BufferedInputStream infile=new BufferedInputStream(new FileInputStream(fileName));
			BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(uploadpath+f.getName()));
			byte buffer[]=new byte[1024];
			while(true) {
				int byteRead=infile.read(buffer);   //�����ļ�β����-1
				if(byteRead==-1)
					break;
				targetfile.write(buffer,0, byteRead);
			}
			infile.close();
			targetfile.close();
			
			System.out.println("�ϴ��ɹ���");
		}
		else
			System.out.println("�ϴ�ʧ�ܣ�");	
	}
	
	public void showMenu(Client client) throws SQLException, IOException {
		new mainFrame(getName(), client);
		
		/*int k=1;
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
				System.out.print("������Ҫ���صĵ����ţ�");
				String ID=scanner.nextLine();				
				if(downloadFile(ID))
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
		}*/	
	}
}