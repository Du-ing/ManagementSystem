package GUI;

import java.sql.SQLException;
import java.util.*;
import java.io.*;

public abstract class User {
	
	Scanner scanner=new Scanner(System.in);
	
	private String name;
	private String password;
	private String role;
	
	String uploadpath="D:\\dj\\upload\\";
	String downloadpath="D:\\dj\\download\\";
	
	User(){
		this.name="";
		this.password="";
		this.role="";			
	}
	
	User(String name, String password, String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String newpassword){		//修改本人的密码
		try {
			if (DataProcessing.updateUser(this.name, newpassword, this.role)){
				this.password=newpassword;
				return true;
			}else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public abstract void showMenu() throws SQLException, IOException;    //显示不同角色对应的菜单并进行其特有的操作
	
	public boolean downloadFile(String ID) throws SQLException, IOException{
		byte buffer[]=new byte[1024];
		Doc doc=DataProcessing.searchDoc(ID);
		if(doc==null)
			return false;
		else {
			File temFile=new File(uploadpath+doc.getFilename());
			String filename=temFile.getName();
			
			BufferedInputStream infile=new BufferedInputStream(new FileInputStream(uploadpath+filename));
			BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(downloadpath+filename));
			
			while(true) {
				int byteRead=infile.read(buffer);
				if(byteRead==-1)
					break;
				
				targetfile.write(buffer, 0, byteRead);
			}
			infile.close();
			targetfile.close();
			return true;
		}	
	}
	
	public void exitUser() {    //退出登录
		System.out.println(name+"已退出登录！");
		System.out.print("\n");
	}
	
	public void exitSystem(){    //退出整个系统
		System.out.println("系统退出, 感谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}