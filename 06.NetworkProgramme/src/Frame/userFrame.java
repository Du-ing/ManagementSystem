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


	public userFrame(int flag, String fname, Client client) throws SQLException {   //����flag�������򿪲�ͬ��ѡ�
	
		setTitle("�û��������");
		setSize(400, 400);
		setLocation(500, 200);
		setLayout(null);
		
		
		JPanel jp1 = new JPanel();	
		jp1.setLayout(null);         //ע�⣬Ҫ���ò���Ϊ�ղ����˹���JPanel����
		JLabel lab1 = new JLabel("�û�����");
		lab1.setBounds(80, 30, 50, 30);
		JLabel lab2 = new JLabel("�����룺");
		lab2.setBounds(80, 100, 50, 30);
		JLabel lab3 = new JLabel("�½�ɫ��");
		lab3.setBounds(80, 170, 50, 30);	
		
		String[] name = DataProcessing.getAllUser(fname);
		JComboBox<String> cbox1 = new JComboBox<String>(name);
		cbox1.setBounds(200, 30, 100, 30);
		
		JTextField text = new JTextField();
		text.setBounds(200, 100, 100, 30);
		
		String[] role = new String[] {"administrator", "browser", "operator"};
		JComboBox<String> cbox2 = new JComboBox<String>(role);
		cbox2.setBounds(200, 170, 120, 30);
		
		JButton bt1 = new JButton("�޸�");
		bt1.setBounds(80, 280, 80, 20);
		MouseListener m1 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JDialog dia = new JDialog();
				dia.setLayout(null);
				dia.setTitle("�޸�");
				dia.setBounds(550, 300, 300, 200);
				JLabel lab = new JLabel("ȷ��Ҫ�޸���");
				lab.setBounds(100, 20, 100, 30);
				dia.add(lab);
				JButton bt1 = new JButton("ȷ��");
				bt1.setBounds(50, 100, 80, 20);
				MouseListener m11 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						String name = cbox1.getSelectedItem().toString();  //��ȡ�������еĵ�ǰ����
						String newpassword = text.getText();               //��ȡ���ֿ��е��ַ���
						String newrole = cbox2.getSelectedItem().toString();
						try {
							DataProcessing.updateUser(name,newpassword,newrole);//�����޸ķ���
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JDialog dia1 = new JDialog();
						dia1.setLayout(null);
						dia1.setTitle("��ʾ");
						dia1.setBounds(600, 350, 200, 100);
						JLabel lab11 = new JLabel("��Ϣ�޸ĳɹ���");
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
				JButton bt2 = new JButton("ȡ��");
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
		
		JButton bt2 = new JButton("ȡ��");
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
		
		JLabel lab111 = new JLabel("�û�����");
		lab111.setBounds(80, 30, 50, 30);
		JLabel lab222 = new JLabel("���룺");
		lab222.setBounds(80, 100, 50, 30);
		JLabel lab333 = new JLabel("��ɫ��");
		lab333.setBounds(80, 170, 50, 30);
				
		JComboBox<String> cbox11 = new JComboBox<String>(name);
		cbox11.setBounds(200, 30, 100, 30);
		JTextField text22 = new JTextField();		
		
		User u = DataProcessing.searchUser(cbox11.getSelectedItem().toString());//��ȡ�����˵����û�����ȡ��Ӧ����		
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
		
		JButton jbt1 = new JButton("ɾ��");
		jbt1.setBounds(80, 280, 80, 20);
		MouseListener m111 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {JDialog dia = new JDialog();
			dia.setLayout(null);
			dia.setTitle("ɾ��");
			dia.setBounds(550, 300, 300, 200);
			JLabel lab = new JLabel("ȷ��Ҫɾ����");
			lab.setBounds(100, 20, 100, 30);
			dia.add(lab);
			JButton bt1 = new JButton("ȷ��");
			bt1.setBounds(50, 100, 80, 20);
			MouseListener m11 = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					String name = cbox11.getSelectedItem().toString();  //��ȡ�������еĵ�ǰ����
					try {
						DataProcessing.deleteUser(name);//����ɾ���޸ķ���
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					cbox1.removeAllItems(); //ɾ����Ҫˢ�������б�
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
					dia1.setTitle("��ʾ");
					dia1.setBounds(600, 350, 200, 100);
					JLabel lab11 = new JLabel("�û�ɾ���ɹ���");
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
			JButton bt2 = new JButton("ȡ��");
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
		
		
		JButton jbt2 = new JButton("ȡ��");
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
		
		JLabel lab11 = new JLabel("�û�����");
		lab11.setBounds(80, 30, 50, 30);
		JLabel lab22 = new JLabel("���룺");
		lab22.setBounds(80, 100, 50, 30);
		JLabel lab33 = new JLabel("��ɫ��");
		lab33.setBounds(80, 170, 50, 30);
		
		JTextField text1 = new JTextField();
		text1.setBounds(200, 30, 100, 30);
		JTextField text2 = new JTextField();
		text2.setBounds(200, 100, 100, 30);
		
		String[] role1 = new String[] {"administrator", "browser", "operator"};
		JComboBox<String> cbox3 = new JComboBox<String>(role1);
		cbox3.setBounds(200, 170, 120, 30);
		
		JButton button1 = new JButton("����");
		button1.setBounds(80, 280, 80, 20);
		MouseListener m11 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JDialog dia1 = new JDialog();
				dia1.setLayout(null);
				dia1.setTitle("����");
				dia1.setBounds(550, 300, 300, 200);
				JLabel lab = new JLabel("ȷ��Ҫ������");
				lab.setBounds(100, 20, 100, 30);
				dia1.add(lab);
				JButton bt1 = new JButton("ȷ��");
				bt1.setBounds(50, 100, 80, 20);
				MouseListener m11 = new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						String newname = text1.getText();  //��ȡ�������еĵ�ǰ����
						String newpassword = text2.getText();               //��ȡ���ֿ��е��ַ���
						String newrole = cbox3.getSelectedItem().toString();
						try {
							DataProcessing.insertUser(newname,newpassword,newrole);//���������޸ķ���
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						cbox1.removeAllItems();//���Ӻ�Ҫˢ�������б�
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
						dia11.setTitle("��ʾ");
						dia11.setBounds(600, 350, 200, 100);
						JLabel lab11 = new JLabel("�û����ӳɹ���");
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
				JButton bt2 = new JButton("ȡ��");
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
		
		JButton button2 = new JButton("ȡ��");
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
		tab.add(jp1,"�޸��û�");
		tab.add(jp2,"ɾ���û�");
		tab.add(jp3,"�����û�");
		
		tab.setSelectedIndex(flag);  //ע�⣺��̬����Ĭ�ϴ򿪵�ѡ�
		
		setContentPane(tab);
		setVisible(true);
	}
}