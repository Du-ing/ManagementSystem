package ExceptionSolve;

import java.sql.SQLException;
import java.io.IOException;


public abstract class User {
	private String name;
	private String password;
	private String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException{
		double ranValue=Math.random();
		if(ranValue>0.8)
			throw new SQLException("执行修改异常！");
		if (Dataprocessing.update(name, password, role)){
			this.password=password;
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String filename) throws IOException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new IOException( "加载文件异常！" );
		System.out.println("下载文件... ...");
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new SQLException( "加载文件列表错误！" );
		System.out.println("列表... ...");
	}
	
	public abstract void showMenu() throws IOException,SQLException;
	
	public void exitUser() {
		System.out.println(name+"已退出登录！");
		System.out.print("\n");
	}
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
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

