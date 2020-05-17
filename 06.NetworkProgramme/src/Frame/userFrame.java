package Frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import GUI.Client;
import GUI.DataProcessing;
import GUI.User;

public class userFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public userFrame(int flag, String fname, Client client) throws SQLException {   //设置flag参数来打开不同的选项卡
	
		setTitle("用户管理界面");
		setSize(400, 400);
		setLocation(500, 200);
		setLayout(null);
		
		
		JPanel jp1 = new JPanel();	
		jp1.setLayout(null);         //注意，要设置布局为空才能人工给JPanel布局
		JLabel lab1 = new JLabel("用户名：");
		lab1.setBounds(80, 30, 50, 30);
		JLabel lab2 = new JLabel("新密码：");
		lab2.setBounds(80, 100, 50, 30);
		JLabel lab3 = new JLabel("新角色：");
		lab3.setBounds(80, 170, 50, 30);	
		
		String[] name = DataProcessing.getAllUser(fname);
		JComboBox<String> cbox1 = new JComboBox<String>(name);
		cbox1.setBounds(200, 30, 100, 30);
		
		JTextField text = new JTextField();
		text.setBounds(200, 100, 100, 30);
		
		String[] role = new String[] {"administrator", "browser", "operator"};
		JComboBox<String> cbox2 = new JComboBox<String>(role);
		cbox2.setBounds(200, 170, 120, 30);
		
		JButton bt1 = new JButton("修改");
		bt1.setBounds(80, 280, 80, 20);
		MouseListener m1 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JDialog dia = new JDialog();
				dia.setLayout(null);
				dia.setTitle("修改");
				dia.setBounds(550, 300, 300, 200);
				JLabel lab = new JLabel("确认要修改吗？");
				lab.setBounds(100, 20, 100, 30);
				dia.add(lab);
				JButton bt1 = new JButton("确认");
				bt1.setBounds(50, 100, 80, 20);
				MouseListener m11 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						String name = cbox1.getSelectedItem().toString();  //获取下拉框中的当前对象
						String newpassword = text.getText();               //获取文字框中的字符串
						String newrole = cbox2.getSelectedItem().toString();
						try {
							DataProcessing.updateUser(name,newpassword,newrole);//调用修改方法
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JDialog dia1 = new JDialog();
						dia1.setLayout(null);
						dia1.setTitle("提示");
						dia1.setBounds(600, 350, 200, 100);
						JLabel lab11 = new JLabel("信息修改成功！");
						lab11.setBounds(50, 20, 100, 30);
						dia1.add(lab11);
						dia1.setVisible(true);
						dia.setVisible(false);
						
						client.sendData("CLIENT_INFO_CHANGE_" + name);
						client.displayMessage("\nCLIENT_INFO_CHANGE_" + name);
					}
				};
				bt1.addMouseListener(m11);
				dia.add(bt1);
				JButton bt2 = new JButton("取消");
				bt2.setBounds(170, 100, 80, 20);
				MouseListener m2 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						dia.setVisible(false);
					}
				};
				bt2.addMouseListener(m2);
				dia.add(bt2);
				dia.setVisible(true);
			
			}
		};
		bt1.addMouseListener(m1);
		
		JButton bt2 = new JButton("取消");
		bt2.setBounds(220, 280, 80, 20);
		MouseListener m2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
			}
		};
		bt2.addMouseListener(m2);
		
		jp1.add(lab1);
		jp1.add(lab2);
		jp1.add(lab3);
		jp1.add(cbox1);
		jp1.add(text);
		jp1.add(cbox2);
		jp1.add(bt1);
		jp1.add(bt2);
		
//========================================================================================================================
//========================================================================================================================
		
		JPanel jp2 = new JPanel();	
		jp2.setLayout(null);
		
		JLabel lab111 = new JLabel("用户名：");
		lab111.setBounds(80, 30, 50, 30);
		JLabel lab222 = new JLabel("密码：");
		lab222.setBounds(80, 100, 50, 30);
		JLabel lab333 = new JLabel("角色：");
		lab333.setBounds(80, 170, 50, 30);
				
		JComboBox<String> cbox11 = new JComboBox<String>(name);
		cbox11.setBounds(200, 30, 100, 30);
		JTextField text22 = new JTextField();		
		
		User u = DataProcessing.searchUser(cbox11.getSelectedItem().toString());//获取下拉菜单中用户名获取相应对象		
		text22.setBounds(200, 100, 100, 30);
		text22.setText(u.getPassword());
		text22.setEditable(false);
		JTextField text33 = new JTextField();
		text33.setBounds(200, 170, 100, 30);
		text33.setText(u.getRole());
		text33.setEditable(false);
				
		jp2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("11");
            	User u;
				try {
					u = DataProcessing.searchUser(cbox11.getSelectedItem().toString());
					text22.setText(u.getPassword());
	        		text33.setText(u.getRole());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
        });
		
		JButton jbt1 = new JButton("删除");
		jbt1.setBounds(80, 280, 80, 20);
		MouseListener m111 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {JDialog dia = new JDialog();
			dia.setLayout(null);
			dia.setTitle("删除");
			dia.setBounds(550, 300, 300, 200);
			JLabel lab = new JLabel("确认要删除吗？");
			lab.setBounds(100, 20, 100, 30);
			dia.add(lab);
			JButton bt1 = new JButton("确认");
			bt1.setBounds(50, 100, 80, 20);
			MouseListener m11 = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					String name = cbox11.getSelectedItem().toString();  //获取下拉框中的当前对象
					try {
						DataProcessing.deleteUser(name);//调用删除修改方法
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					cbox1.removeAllItems(); //删除后要刷新下拉列表
					cbox11.removeAllItems();           
					int i = 0;
					String name1[];
					try {
						name1 = DataProcessing.getAllUser(fname);
						while(name1[i] != null) {
							if(name1[i].equalsIgnoreCase(fname)) {
								continue;
							}
							cbox1.addItem(name1[i]);
							cbox11.addItem(name1[i]);
							i++;
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					JDialog dia1 = new JDialog();
					dia1.setLayout(null);
					dia1.setTitle("提示");
					dia1.setBounds(600, 350, 200, 100);
					JLabel lab11 = new JLabel("用户删除成功！");
					lab11.setBounds(50, 20, 100, 30);
					dia1.add(lab11);
					dia1.setVisible(true);
					dia.setVisible(false);
					
					client.sendData("CLIENT_INFO_DELETE_" + name);
					client.displayMessage("\nCLIENT_INFO_DELETE_" + name);
				}
			};
			bt1.addMouseListener(m11);
			
			dia.add(bt1);
			JButton bt2 = new JButton("取消");
			bt2.setBounds(170, 100, 80, 20);
			MouseListener m2 = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					dia.setVisible(false);
				}
			};
			bt2.addMouseListener(m2);
			dia.add(bt2);
			dia.setVisible(true);}
		};
		jbt1.addMouseListener(m111);
		
		
		JButton jbt2 = new JButton("取消");
		jbt2.setBounds(220, 280, 80, 20);
		jbt2.addMouseListener(m2);
		
		jp2.add(lab111);
		jp2.add(lab222);
		jp2.add(lab333);
		jp2.add(cbox11);
		jp2.add(text22);
		jp2.add(text33);
		jp2.add(jbt1);
		jp2.add(jbt2);
		
//========================================================================================================================
//========================================================================================================================
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(null);
		
		JLabel lab11 = new JLabel("用户名：");
		lab11.setBounds(80, 30, 50, 30);
		JLabel lab22 = new JLabel("密码：");
		lab22.setBounds(80, 100, 50, 30);
		JLabel lab33 = new JLabel("角色：");
		lab33.setBounds(80, 170, 50, 30);
		
		JTextField text1 = new JTextField();
		text1.setBounds(200, 30, 100, 30);
		JTextField text2 = new JTextField();
		text2.setBounds(200, 100, 100, 30);
		
		String[] role1 = new String[] {"administrator", "browser", "operator"};
		JComboBox<String> cbox3 = new JComboBox<String>(role1);
		cbox3.setBounds(200, 170, 120, 30);
		
		JButton button1 = new JButton("增加");
		button1.setBounds(80, 280, 80, 20);
		MouseListener m11 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JDialog dia1 = new JDialog();
				dia1.setLayout(null);
				dia1.setTitle("增加");
				dia1.setBounds(550, 300, 300, 200);
				JLabel lab = new JLabel("确认要增加吗？");
				lab.setBounds(100, 20, 100, 30);
				dia1.add(lab);
				JButton bt1 = new JButton("确认");
				bt1.setBounds(50, 100, 80, 20);
				MouseListener m11 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						String newname = text1.getText();  //获取下拉框中的当前对象
						String newpassword = text2.getText();               //获取文字框中的字符串
						String newrole = cbox3.getSelectedItem().toString();
						try {
							DataProcessing.insertUser(newname,newpassword,newrole);//调用增加修改方法
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						cbox1.removeAllItems();//增加后要刷新下拉列表
						cbox11.removeAllItems();       
						int i = 0;
						String name1[];
						try {
							name1 = DataProcessing.getAllUser(fname);
							while(name1[i] != null) {
								if(name1[i].equalsIgnoreCase(fname)) {
									continue;
								}
								cbox1.addItem(name1[i]);
								cbox11.addItem(name1[i]);
								i++;
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
										
						
						JDialog dia11 = new JDialog();
						dia11.setLayout(null);
						dia11.setTitle("提示");
						dia11.setBounds(600, 350, 200, 100);
						JLabel lab11 = new JLabel("用户增加成功！");
						lab11.setBounds(50, 20, 100, 30);
						dia11.add(lab11);
						dia11.setVisible(true);
						dia1.setVisible(false);
						
						client.sendData("CLIENT_INFO_ADD_" + newname);
						client.displayMessage("\nCLIENT_INFO_ADD_" + newname);
					}
				};
				bt1.addMouseListener(m11);
				dia1.add(bt1);
				JButton bt2 = new JButton("取消");
				bt2.setBounds(170, 100, 80, 20);
				MouseListener m2 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						dia1.setVisible(false);
					}
				};
				bt2.addMouseListener(m2);
				dia1.add(bt2);
				dia1.setVisible(true);
			}
		};
		button1.addMouseListener(m11);
		
		JButton button2 = new JButton("取消");
		button2.setBounds(220, 280, 80, 20);
		button2.addMouseListener(m2);		
		
		jp3.add(lab11);
		jp3.add(lab22);
		jp3.add(lab33);
		jp3.add(text1);
		jp3.add(text2);
		jp3.add(cbox3);
		jp3.add(button1);
		jp3.add(button2);		
		
		JTabbedPane tab = new JTabbedPane();
		tab.add(jp1,"修改用户");
		tab.add(jp2,"删除用户");
		tab.add(jp3,"新增用户");
		
		tab.setSelectedIndex(flag);  //注意：动态设置默认打开的选项卡
		
		setContentPane(tab);
		setVisible(true);
	}
}