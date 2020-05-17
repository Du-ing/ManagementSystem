package ManagementSystem;

public abstract class User {
	private String name;
	private String password;
	private String role;
	
	User(String name, String password, String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String newpassword){		//修改本人的密码
		if (Dataprocessing.update(this.name, newpassword, this.role)){
			this.password=newpassword;
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu();    //显示不同角色对应的菜单并进行其特有的操作
	
	public boolean downloadFile(String filename){
		System.out.println("下载文件... ...");
		return true;
	}
	
	public void showFileList(){
		System.out.println("列表... ...");
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
