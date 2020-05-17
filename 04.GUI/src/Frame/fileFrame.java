package Frame;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import GUI.DataProcessing;
import GUI.Doc;

public class fileFrame extends JFrame {
	
	public fileFrame(String s) {
		JFrame f = null;
		setLayout(new FlowLayout());
		setSize(450, 400);
		setLocation(500, 200);
		
		String downloadpath = "d:/dj/download/";
		String uploadpatn = "d:/dj/upload/";
		
		if(s.equalsIgnoreCase("download")) {
			setTitle("文件下载");
			setLayout(new BorderLayout());   //注意，表格必须使用此排布才能显示
			String[] columnNames = new String[] {"档案号","创建者","时间","文件名","描述"};
			String[][] doc = new String[10][5];
			int i = 0;
			for (Enumeration<Doc> e = DataProcessing.docs.elements(); e.hasMoreElements();) {   //枚举法获得文件，存入String数组
				Doc u=e.nextElement();
				doc[i][0] = u.getID();
				doc[i][1] = u.getCreator();
				doc[i][2] = u.getTimestamp().toString();
				doc[i][3] = u.getFilename();
				doc[i][4] = u.getDescription();
				i++;
			}
			JTable table = new JTable(doc, columnNames);
			JScrollPane sp = new JScrollPane(table);

			
			JButton bt1 = new JButton("下载");
			bt1.setBounds(80, 300, 100, 20);

			
			bt1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String upath = uploadpatn + doc[table.getSelectedRow()][3];
					System.out.println(uploadpatn + doc[table.getSelectedRow()][3]);
					
					JDialog dia = new JDialog();
					dia.setLayout(null);
					dia.setTitle("文件下载");
					dia.setBounds(600, 300, 300, 200);
					dia.setVisible(true);
					
					JLabel j1 = new JLabel("您确定要将"+doc[table.getSelectedRow()][3]);
					JLabel j2 = new JLabel("文件下载到您的电脑中吗？");
					j1.setBounds(60, 20, 150, 30);
					j2.setBounds(60, 50, 150, 30);
					dia.add(j1);
					dia.add(j2);
					
					JButton jbt1 = new JButton("确定");   // 给确定按钮增加事件
					jbt1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFrame jm = new JFrame();
							FileDialog fd = new FileDialog(jm, "文件下载", FileDialog.SAVE);//注意filedialog的使用（SAVE）文件保存模式
							fd.setDirectory(uploadpatn);                       //给fd设置一开始要保存的文件的路径
							fd.setFile(doc[table.getSelectedRow()][3]);        //给fd设置一开始要保存的文件名称
							fd.setVisible(true);
							
							File file =new File(upath);
							
							BufferedInputStream infile;
							try {
								infile = new BufferedInputStream(new FileInputStream(uploadpatn + file.getName()));
								BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(fd.getDirectory() + file.getName()));//filedialog最后选取的路径
								byte buffer[]=new byte[1024];
								while(true) {
									int byteRead=infile.read(buffer);   //读到文件尾返回-1
									if(byteRead==-1)
										break;
									targetfile.write(buffer,0, byteRead);
								}
								infile.close();
								targetfile.close();
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							
							JDialog dia2 = new JDialog();
							dia2.setLayout(null);
							dia2.setTitle("提示");
							dia2.setBounds(600, 300, 150, 100);
							JLabel jj = new JLabel("下载成功！");
							jj.setBounds(50, 20, 100, 30);
							dia2.add(jj);
							dia.setVisible(false);
							dia2.setVisible(true);
						}
					});
					
					
					JButton jbt2 = new JButton("取消");
					jbt2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dia.setVisible(false);
						}
					});
					jbt1.setBounds(30, 100, 80, 20);
					jbt2.setBounds(170, 100, 80, 20);
					dia.add(jbt1);
					dia.add(jbt2);
				}
			});
			
			JButton bt2 = new JButton("取消");
			bt2.setBounds(270, 300, 100, 20);
			ActionListener act2 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			};
			bt2.addActionListener(act2);
			
			add(bt1);
			add(bt2);
			add(sp,BorderLayout.CENTER);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		else {
			setTitle("文件上传");
			setLayout(null);
			JLabel lab1 = new JLabel("档案号");
			lab1.setBounds(80, 50, 100, 30);
			JLabel lab2 = new JLabel("档案描述");
			lab2.setBounds(80, 120, 100, 30);
			JLabel lab3 = new JLabel("档案文件名");
			lab3.setBounds(80, 250, 100, 30);
			add(lab1);
			add(lab2);
			add(lab3);
			
			JTextField tex1 = new JTextField();
			tex1.setBounds(150, 50, 150, 30);
			JTextField tex2 = new JTextField();
			tex2.setBounds(150, 120, 150, 100);
			JTextField tex3 = new JTextField();
			tex3.setBounds(150, 250, 150, 30);
			add(tex1);
			add(tex2);
			add(tex3);
			
			JButton jbt0 = new JButton("选择文件");
			jbt0.setBounds(310, 255, 100, 20);
			
			jbt0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame jm = new JFrame();
					FileDialog fd = new FileDialog(jm, "选择上传文件", FileDialog.LOAD);//文件读取模式
					fd.setVisible(true);
					tex3.setText(fd.getDirectory() + fd.getFile());
				}
			});
			add(jbt0);
			
			JButton jbt1 = new JButton("上传");
			jbt1.setBounds(70, 330, 80, 20);
			jbt1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog dia = new JDialog();
					dia.setLayout(null);
					dia.setTitle("文件上传");
					dia.setBounds(600, 300, 300, 200);
					dia.setVisible(true);
					
					JLabel j1 = new JLabel("您确定要将此文件");
					JLabel j2 = new JLabel("上传吗？");
					j1.setBounds(60, 20, 150, 30);
					j2.setBounds(60, 50, 150, 30);
					dia.add(j1);
					dia.add(j2);
					
					JButton jbt1 = new JButton("确定");   // 给确定按钮增加事件
					jbt1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							//========================================================================================================
							
							String filename = tex3.getText();
													
							File file =new File(filename);
							BufferedInputStream infile;
							try {
								infile = new BufferedInputStream(new FileInputStream(filename));
								BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(uploadpatn+file.getName()));
								System.out.println(filename+"======"+uploadpatn+file.getName());
								byte buffer[]=new byte[1024];
								while(true) {
									int byteRead=infile.read(buffer);   //读到文件尾返回-1
									if(byteRead==-1)
										break;
									targetfile.write(buffer,0, byteRead);
								}
								infile.close();
								targetfile.close();
								
								DataProcessing.insertDoc(tex1.getText(), "dj", new Timestamp(System.currentTimeMillis()), tex2.getText(), filename);
								
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e2) {
								e2.printStackTrace();
							} catch (SQLException e3) {
								e3.printStackTrace();
							}
							//=========================================================================================================
							
							JDialog dia2 = new JDialog();
							dia2.setLayout(null);
							dia2.setTitle("提示");
							dia2.setBounds(600, 300, 150, 100);
							JLabel jj = new JLabel("上传成功！");
							jj.setBounds(50, 20, 100, 30);
							dia2.add(jj);
							dia.setVisible(false);
							setVisible(false);
							dia2.setVisible(true);
						}
					});
					JButton jbt2 = new JButton("取消");
					jbt2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dia.setVisible(false);
						}
					});
					jbt1.setBounds(30, 100, 80, 20);
					jbt2.setBounds(170, 100, 80, 20);
					dia.add(jbt1);
					dia.add(jbt2);
				}
			});
			
			JButton jbt2 = new JButton("取消");
			jbt2.setBounds(300, 330, 80, 20);
			jbt2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			
			add(jbt1);
			add(jbt2);
			
			setVisible(true);
		}
		
	}
	
	public static void main(String[] args) {
		new fileFrame("download");
	}
}
