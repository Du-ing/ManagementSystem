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
			setTitle("�ļ�����");
			setLayout(new BorderLayout());   //ע�⣬������ʹ�ô��Ų�������ʾ
			String[] columnNames = new String[] {"������","������","ʱ��","�ļ���","����"};
			String[][] doc = new String[10][5];
			int i = 0;
			for (Enumeration<Doc> e = DataProcessing.docs.elements(); e.hasMoreElements();) {   //ö�ٷ�����ļ�������String����
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

			
			JButton bt1 = new JButton("����");
			bt1.setBounds(80, 300, 100, 20);

			
			bt1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String upath = uploadpatn + doc[table.getSelectedRow()][3];
					System.out.println(uploadpatn + doc[table.getSelectedRow()][3]);
					
					JDialog dia = new JDialog();
					dia.setLayout(null);
					dia.setTitle("�ļ�����");
					dia.setBounds(600, 300, 300, 200);
					dia.setVisible(true);
					
					JLabel j1 = new JLabel("��ȷ��Ҫ��"+doc[table.getSelectedRow()][3]);
					JLabel j2 = new JLabel("�ļ����ص����ĵ�������");
					j1.setBounds(60, 20, 150, 30);
					j2.setBounds(60, 50, 150, 30);
					dia.add(j1);
					dia.add(j2);
					
					JButton jbt1 = new JButton("ȷ��");   // ��ȷ����ť�����¼�
					jbt1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFrame jm = new JFrame();
							FileDialog fd = new FileDialog(jm, "�ļ�����", FileDialog.SAVE);//ע��filedialog��ʹ�ã�SAVE���ļ�����ģʽ
							fd.setDirectory(uploadpatn);                       //��fd����һ��ʼҪ������ļ���·��
							fd.setFile(doc[table.getSelectedRow()][3]);        //��fd����һ��ʼҪ������ļ�����
							fd.setVisible(true);
							
							File file =new File(upath);
							
							BufferedInputStream infile;
							try {
								infile = new BufferedInputStream(new FileInputStream(uploadpatn + file.getName()));
								BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(fd.getDirectory() + file.getName()));//filedialog���ѡȡ��·��
								byte buffer[]=new byte[1024];
								while(true) {
									int byteRead=infile.read(buffer);   //�����ļ�β����-1
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
							dia2.setTitle("��ʾ");
							dia2.setBounds(600, 300, 150, 100);
							JLabel jj = new JLabel("���سɹ���");
							jj.setBounds(50, 20, 100, 30);
							dia2.add(jj);
							dia.setVisible(false);
							dia2.setVisible(true);
						}
					});
					
					
					JButton jbt2 = new JButton("ȡ��");
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
			
			JButton bt2 = new JButton("ȡ��");
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
			setTitle("�ļ��ϴ�");
			setLayout(null);
			JLabel lab1 = new JLabel("������");
			lab1.setBounds(80, 50, 100, 30);
			JLabel lab2 = new JLabel("��������");
			lab2.setBounds(80, 120, 100, 30);
			JLabel lab3 = new JLabel("�����ļ���");
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
			
			JButton jbt0 = new JButton("ѡ���ļ�");
			jbt0.setBounds(310, 255, 100, 20);
			
			jbt0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame jm = new JFrame();
					FileDialog fd = new FileDialog(jm, "ѡ���ϴ��ļ�", FileDialog.LOAD);//�ļ���ȡģʽ
					fd.setVisible(true);
					tex3.setText(fd.getDirectory() + fd.getFile());
				}
			});
			add(jbt0);
			
			JButton jbt1 = new JButton("�ϴ�");
			jbt1.setBounds(70, 330, 80, 20);
			jbt1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog dia = new JDialog();
					dia.setLayout(null);
					dia.setTitle("�ļ��ϴ�");
					dia.setBounds(600, 300, 300, 200);
					dia.setVisible(true);
					
					JLabel j1 = new JLabel("��ȷ��Ҫ�����ļ�");
					JLabel j2 = new JLabel("�ϴ���");
					j1.setBounds(60, 20, 150, 30);
					j2.setBounds(60, 50, 150, 30);
					dia.add(j1);
					dia.add(j2);
					
					JButton jbt1 = new JButton("ȷ��");   // ��ȷ����ť�����¼�
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
									int byteRead=infile.read(buffer);   //�����ļ�β����-1
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
							dia2.setTitle("��ʾ");
							dia2.setBounds(600, 300, 150, 100);
							JLabel jj = new JLabel("�ϴ��ɹ���");
							jj.setBounds(50, 20, 100, 30);
							dia2.add(jj);
							dia.setVisible(false);
							setVisible(false);
							dia2.setVisible(true);
						}
					});
					JButton jbt2 = new JButton("ȡ��");
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
			
			JButton jbt2 = new JButton("ȡ��");
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
