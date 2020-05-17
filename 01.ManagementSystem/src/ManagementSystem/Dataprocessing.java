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
	
	public static User searchUser(String name){    //根据输入的名称查找储存对象并返回
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}
	
	public static User search(String name, String password){    //根据输入的名字和密码（要正确）查找储存的对象并返回
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))    //密码正确才能返回查找到的对象
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser(){    //枚举法获得哈希表内的对象

		Enumeration<User> e  = users.elements();
		return e;
	}
	
	public static boolean update(String name, String newpassword, String newrole){
		User user;
		if (users.containsKey(name)) {   //根据用户名来找到对应的用户，并更改此用户的密码和角色
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
	
	public static boolean insert(String name, String password, String role){   //增加新用户
		User user;
		if (users.containsKey(name))
			return false;      //用户名已存在，返回错误
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else if (role.equalsIgnoreCase("browser"))
				user = new Browser(name,password, role);
			else
				return false;    //角色输入错误
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean delete(String name){   //删除用户
				
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
	}
}
