package ManagementSystem;

//import java.sql.SQLException;
import java.util.*;

public class Dataprocessing {
	static Hashtable<String, User> users;

	static {
		users = new Hashtable<String, User>();
		users.put("ope", new Operator("ope","123","operator"));
		users.put("bro", new Browser("bro","123","browser"));
		users.put("adm", new Administrator("adm","123","administrator"));		
	}
	
	public static User searchUser(String name){    //������������Ʋ��Ҵ�����󲢷���
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}
	
	public static User search(String name, String password){    //������������ֺ����루Ҫ��ȷ�����Ҵ���Ķ��󲢷���
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))    //������ȷ���ܷ��ز��ҵ��Ķ���
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser(){    //ö�ٷ���ù�ϣ���ڵĶ���

		Enumeration<User> e  = users.elements();
		return e;
	}
	
	public static boolean update(String name, String newpassword, String newrole){
		User user;
		if (users.containsKey(name)) {   //�����û������ҵ���Ӧ���û��������Ĵ��û�������ͽ�ɫ
			if (newrole.equalsIgnoreCase("administrator"))
				user = new Administrator(name,newpassword, newrole);
			else if (newrole.equalsIgnoreCase("operator"))
				user = new Operator(name,newpassword, newrole);
			else
				user = new Browser(name,newpassword, newrole);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	public static boolean insert(String name, String password, String role){   //�������û�
		User user;
		if (users.containsKey(name))
			return false;      //�û����Ѵ��ڣ����ش���
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else if (role.equalsIgnoreCase("browser"))
				user = new Browser(name,password, role);
			else
				return false;    //��ɫ�������
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean delete(String name){   //ɾ���û�
				
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
	}
}
