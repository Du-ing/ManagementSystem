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
		setTitle(name+"的界面");
		setSize(400, 300);
		setLocation(500, 200);
		setLayout(null);	
		
		JMenuBar menu = new JMenuBar();
		JMenu jm1 = new JMenu("用户管理");
		if(!role.equalsIgnoreCase("administrator")) {
			jm1.setEnabled(false);
		}
		JMenuItem mit1 = new JMenuItem("修改用户");
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
		JMenuItem mit2 = new JMenuItem("删除用户");
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
		JMenuItem mit3 = new JMenuItem("新增用户");
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

		
		JMenu jm2 = new JMenu("档案管理");
		JMenuItem mi1 = new JMenuItem("文件上传");	
		if(!role.equalsIgnoreCase("operator")) {
			mi1.setEnabled(false);
		}
		MouseListener m4 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new fileFrame("upload", client);//调同一方法，传不同参数
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mi1.addMouseListener(m4);
		
		JMenuItem mi2 = new JMenuItem("文件下载");
		MouseListener m5 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					new fileFrame("download", client);//调同一方法，传不同参数
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		mi2.addMouseListener(m5);		
		
		jm2.add(mi1);
		jm2.add(mi2);
			
		JMenu jm3 = new JMenu("个人信息管理");
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //主窗口退出即系统结束
		
		setVisible(true);
	}
}
