package ManagementSystem;

import java.util.*;

public class Main {
	public static void main(String[] arg) {
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
					if(Dataprocessing.search(name, password)!=null) {
						System.out.println(name+"��½�ɹ���");
						u=Dataprocessing.search(name, password);
						u.showMenu();
						break;
					}
					else
						System.out.println("�û��������ڻ����벻��ȷ�����������룡");
				}
			};;break;
			case 2:{
				System.out.println("ϵͳ���˳�����лʹ�ã�");
				System.exit(0);
			};break;
		    }
		}	
	}
}
