package Frame;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUI.Client;
import GUI.DataProcessing;
import GUI.User;

public class loginFrame extends JFrame {
	
	
	public loginFrame(Client client) throws IOException {
		
		setTitle("ϵͳ��¼");
		setSize(400, 300);
		setLocation(500, 200);
		setLayout(null);
		
		JLabel lname = new JLabel("�û�����");
		lname.setBounds(80, 50, 100, 30);
		JLabel lpass = new JLabel("��    �룺");
		lpass.setBounds(80, 120, 100, 30);
		
		JTextField tfname = new JTextField();
		tfname.setBounds(150, 50, 150, 30);
		
		JPasswordField jpass = new JPasswordField();
		jpass.setBounds(150, 120, 150, 30);
		
		KeyListener key_Listener = new KeyListener(){
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER ){
					if(tfname.getText().isEmpty()||jpass.getText().isEmpty()) {   //����Ϊ��ʱ
						JDialog j1 = new JDialog();
						j1.setTitle("��ʾ");
						j1.setBounds(600, 300, 100, 100);
						
						JLabel l1 = new JLabel("                ��¼��Ч��");
						j1.add(l1);
						j1.setVisible(true);
					}
					else {                                                       
						String name = tfname.getText();
						String password = jpass.getText();
						User u;
						try {
							u=DataProcessing.searchUser(name, password);
							if(u!=null) {                           //ƥ����ȷ						
								try {
									client.connectToServer();   //�������ݿ�
									client.getStreams();
									
									client.sendData("CLIENT_"+name+"_Login\n"+"Thread "+name+" Beging\n");
							        client.displayMessage("\n"+"CLIENT_"+name+"_login");
									setVisible(false);
									u.showMenu(client);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							else {                                  //ƥ�����
								JDialog j2 = new JDialog();
								j2.setLayout(null);
								j2.setTitle("��ʾ");
								j2.setBounds(600, 300, 300, 150);
								
								JLabel l2 = new JLabel("     �û��������ڻ����벻��ȷ�����������룡");
								j2.add(l2);
								j2.setVisible(true);
							}
						} catch (SQLException e1) {
							System.out.println(e1);
							JDialog j2 = new JDialog();
							j2.setTitle("��ʾ");
							j2.setBounds(600, 300, 200, 150);
							
							JLabel l2 = new JLabel("            ���û��������ڣ�");
							j2.add(l2);
							j2.setVisible(true);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		};
		jpass.addKeyListener(key_Listener);
		
		Button b1 = new Button("��¼");
		b1.setBounds(110, 200, 50, 20);
		
		Button b2 = new Button("ȡ��");
		b2.setBounds(220, 200, 50, 20);
		
		ActionListener act = new ActionListener() {   //����¼��ť�����¼�
			public void actionPerformed(ActionEvent e) {
				if(tfname.getText().isEmpty()||jpass.getText().isEmpty()) {   //����Ϊ��ʱ
					JDialog j1 = new JDialog();
					j1.setTitle("��ʾ");
					j1.setBounds(600, 300, 100, 100);
					
					JLabel l1 = new JLabel("                ��¼��Ч��");
					j1.add(l1);
					j1.setVisible(true);
				}
				else {                                                       
					String name = tfname.getText();
					String password = jpass.getText();
					User u;
					try {
						u=DataProcessing.searchUser(name, password);
						if(u!=null) {                           //ƥ����ȷ						
							try {
								client.connectToServer();   //�������ݿ�
								client.getStreams();
								
								client.sendData("CLIENT_"+name+"_Login\n"+"Thread "+name+"Beging\n");
						        client.displayMessage("\n"+"CLIENT_"+name+"_login");
								setVisible(false);
								u.showMenu(client);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else {                                  //ƥ�����
							JDialog j2 = new JDialog();
							j2.setTitle("��ʾ");
							j2.setBounds(600, 300, 300, 150);
							
							JLabel l2 = new JLabel("     �û��������ڻ����벻��ȷ�����������룡");
							j2.add(l2);
							j2.setVisible(true);
						}
					} catch (SQLException e1) {
						System.out.println(e1);
					}
				}
			}
		};
		b1.addActionListener(act);
		
		ActionListener act1 =new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		b2.addActionListener(act1);
		
		add(b1);
		add(b2);
		add(lname);
		add(lpass);
		add(jpass);
		add(tfname);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
/*	
	public static void main(String args[]) {
		new loginFrame(cilent);
	}*/
}
