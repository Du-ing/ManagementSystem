package IOStream;

import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] arg) throws IOException {
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		
		while(true) {
		System.out.println("��ӭʹ�õ�������ϵͳ");
		System.out.println("�˵���\n"+"====================");
		System.out.println("1.��¼\n"+"2.�˳�\n"+"====================");
		System.out.print("�����������Ե��ò˵����ܣ�");
		int a=scanner.nextInt();
		scanner.nextLine();//ȥ����һ������ʱ������'\n'��Ӱ��
			
			switch(a){
			case 1:{
				while(true) {
					String name,password;
					System.out.print("�������û�����");
					name=scanner.nextLine();
					System.out.print("���������룺");
					password=scanner.nextLine();
					
					User u;
					try {
						u=DataProcessing.searchUser(name, password);
						if(u!=null) {
							System.out.println(name+"��½�ɹ���");
							u.showMenu();
							break;
						}
						else
							System.out.println("�û��������ڻ����벻��ȷ�����������룡");
					} catch (SQLException e) {
						System.out.println(e);
					}
				}
			};;break;
			case 2:{
				System.out.println("ϵͳ���˳�����лʹ�ã�");
				System.exit(0);
			};break;
			default:{
				System.out.println("�����������������룡\n");
			}
		    }
		}	
	}
}
