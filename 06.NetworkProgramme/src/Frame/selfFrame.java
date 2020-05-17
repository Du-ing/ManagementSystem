package Frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUI.Client;
import GUI.DataProcessing;

public class selfFrame extends JFrame {
	
	public selfFrame(String name, Client client) throws SQLException {
		
		setTitle("个人信息管理");
		setSize(350, 400);
		setLocation(500, 200);
		setLayout(null);
		
		JLabel lab1 = new JLabel("用户名：");
		lab1.setBounds(50, 30, 50, 30);
		JLabel lab2 = new JLabel("原口令：");
		lab2.setBounds(50, 80, 50, 30);
		JLabel lab3 = new JLabel("新口令：");
		lab3.setBounds(50, 130, 50, 30);
		JLabel lab4 = new JLabel("确认新口令：");
		lab4.setBounds(50, 180, 80, 30);
		JLabel lab5 = new JLabel("角色：");
		lab5.setBounds(50, 230, 50, 30);
		
		JTextField jtf1 = new JTextField(name);
		jtf1.setBounds(200, 30, 80, 30);
		jtf1.setEditable(false);   //不可编辑
		JPasswordField jtf2 = new JPasswordField();
		jtf2.setBounds(200, 80, 80, 30);
		JPasswordField jtf3 = new JPasswordField();
		jtf3.setBounds(200, 130, 80, 30);
		JPasswordField jtf4 = new JPasswordField();
		jtf4.setBounds(200, 180, 80, 30);
		JTextField jtf5 = new JTextField(DataProcessing.searchUser(name).getRole());
		jtf5.setBounds(200, 230, 80, 30);
		jtf5.setEditable(false);
		
		JButton jb1 = new JButton("修改");
		jb1.setBounds(50, 300, 80, 20);		
		MouseListener m1 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					if(DataProcessing.searchUser(name, jtf2.getText()) == null||jtf3.getText().isEmpty()||jtf4.getText().isEmpty()) {
						if(DataProcessing.searchUser(name, jtf2.getText()) == null) {
							JDialog j1 = new JDialog();
							j1.setTitle("提示");
							j1.setBounds(600, 200, 300, 150);						
							JLabel l2 = new JLabel("     原密码不正确，请重新输入！");
							j1.add(l2);
							j1.setVisible(true);
						}
						if(jtf3.getText().isEmpty()||jtf4.getText().isEmpty()) {
							JDialog j1 = new JDialog();
							j1.setTitle("提示");
							j1.setBounds(600, 400, 300, 150);						
							JLabel l2 = new JLabel("     新密码输入无效，请重新输入！");
							j1.add(l2);
							j1.setVisible(true);
						}						
					}
					else if(!jtf3.getText().equalsIgnoreCase(jtf4.getText())) {  //确认密码不一致
						JDialog j1 = new JDialog();
						j1.setTitle("提示");
						j1.setBounds(600, 400, 300, 150);						
						JLabel l2 = new JLabel("           两次输入不一致，请重新输入！");
						j1.add(l2);
						j1.setVisible(true);
					}
					else {
						JDialog j1 = new JDialog();
						j1.setLayout(null);
						j1.setTitle("修改");
						j1.setBounds(600, 400, 300, 150);						
						JLabel l2 = new JLabel("确认要修改吗？");
						l2.setBounds(100, 20, 100, 30);
						
						JButton jb1 = new JButton("确认");
						jb1.setBounds(30, 70, 80, 20);
						MouseListener m1 = new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								try {
									DataProcessing.updateUser(name, jtf3.getText(), DataProcessing.searchUser(name).getRole());  //调用修改方法
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JDialog j11 = new JDialog();
								j11.setLayout(null);
								j11.setTitle("提示");
								j11.setBounds(600, 400, 300, 150);						
								JLabel l2 = new JLabel("修改成功！");
								l2.setBounds(100,50, 100, 30);
								j11.add(l2);
								j11.setVisible(true);
								j1.setVisible(false);
								setVisible(false);
								
								client.sendData("CLIENT_SELF_MOD");
								client.displayMessage("\nCLIENT_SELF_MOD");
							}
						};
						jb1.addMouseListener(m1);
						
						JButton jb2 = new JButton("取消");
						jb2.setBounds(170, 70, 80, 20);
						MouseListener m2 = new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								j1.setVisible(false);
							}
						};
						jb2.addMouseListener(m2);
						j1.add(l2);
						j1.add(jb1);
						j1.add(jb2);
						j1.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		jb1.addMouseListener(m1);
		
		JButton jb2 = new JButton("取消");
		jb2.setBounds(200, 300, 80, 20);
		MouseListener m2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
			}
		};
		jb2.addMouseListener(m2);
		
		add(lab1);
		add(lab2);
		add(lab3);
		add(lab4);
		add(lab5);
		add(jtf1);
		add(jtf2);
		add(jtf3);
		add(jtf4);
		add(jtf5);
		add(jb1);
		add(jb2);
		setVisible(true);
	}
}
