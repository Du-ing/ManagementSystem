package Frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GUI.Client;
import GUI.DataProcessing;

public class mainFrame extends JFrame {
	
	public mainFrame(String name, Client client) throws SQLException {
		
		String role = DataProcessing.searchUser(name).getRole();
	
		getContentPane().setFocusCycleRoot(true);
		setTitle(name+"�Ľ���");
		setSize(400, 300);
		setLocation(500, 200);
		setLayout(null);	
		
		JMenuBar menu = new JMenuBar();
		JMenu jm1 = new JMenu("�û�����");
		if(!role.equalsIgnoreCase("administrator")) {
			jm1.setEnabled(false);
		}
		JMenuItem mit1 = new JMenuItem("�޸��û�");
		MouseListener m1 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new userFrame(0, name, client);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mit1.addMouseListener(m1);
		jm1.add(mit1);
		JMenuItem mit2 = new JMenuItem("ɾ���û�");
		MouseListener m2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new userFrame(1, name, client);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mit2.addMouseListener(m2);
		jm1.add(mit2);
		JMenuItem mit3 = new JMenuItem("�����û�");
		MouseListener m3 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new userFrame(2, name, client);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mit3.addMouseListener(m3);
		jm1.add(mit3);

		
		JMenu jm2 = new JMenu("��������");
		JMenuItem mi1 = new JMenuItem("�ļ��ϴ�");	
		if(!role.equalsIgnoreCase("operator")) {
			mi1.setEnabled(false);
		}
		MouseListener m4 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new fileFrame("upload", client);//��ͬһ����������ͬ����
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mi1.addMouseListener(m4);
		
		JMenuItem mi2 = new JMenuItem("�ļ�����");
		MouseListener m5 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new fileFrame("download", client);//��ͬһ����������ͬ����
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mi2.addMouseListener(m5);		
		
		jm2.add(mi1);
		jm2.add(mi2);
			
		JMenu jm3 = new JMenu("������Ϣ����");
		MouseListener m6 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new selfFrame(name, client);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		jm3.addMouseListener(m6);		
		
		menu.add(jm1);
		menu.add(jm2);
		menu.add(jm3);		
		
		setJMenuBar(menu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //�������˳���ϵͳ����
		
		setVisible(true);
	}
}
