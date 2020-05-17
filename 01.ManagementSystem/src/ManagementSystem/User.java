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
	
	public boolean changeSelfInfo(String newpassword){		//�޸ı��˵�����
		if (Dataprocessing.update(this.name, newpassword, this.role)){
			this.password=newpassword;
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu();    //��ʾ��ͬ��ɫ��Ӧ�Ĳ˵������������еĲ���
	
	public boolean downloadFile(String filename){
		System.out.println("�����ļ�... ...");
		return true;
	}
	
	public void showFileList(){
		System.out.println("�б�... ...");
	}
	
	public void exitUser() {    //�˳���¼
		System.out.println(name+"���˳���¼��");
		System.out.print("\n");
	}
	
	public void exitSystem(){    //�˳�����ϵͳ
		System.out.println("ϵͳ�˳�, ��лʹ�� ! ");
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
