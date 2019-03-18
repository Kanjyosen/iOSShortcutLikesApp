package iSLA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

class ISLA extends JFrame implements ActionListener{
	JLabel selected1;
	JLabel selected2;
	JLabel testtxt1;
	String imagepath;
	String htmlpath;
	String encodedhtml;
	String redirecturl;
	String htmlfilename;
	File img;
	File html;
	JTextField url;
	JTextField imageurl;
	public static void main(String args[]) {
		ISLA father = new ISLA("iSLA");
		father.setVisible(true);
		
	}
		ISLA(String string1){
			imagepath = "NOT_SELECTED";
			htmlpath = "NOT_SELECTED";
			
			setTitle(string1);
			setSize(800, 800);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			ImageIcon icon = new ImageIcon("./src/iSLA/icon.png");
			setIconImage(icon.getImage());
			
			JPanel yama = new JPanel();
			JPanel field = new JPanel();
			JPanel openimage = new JPanel();
			JPanel openhtml = new JPanel();
			JPanel generate = new JPanel();
			
			testtxt1 = new JLabel("Welcome to iSLA. Attention: This isn't application which licensed by Apple inc.");
			yama.add(testtxt1);
			
			JLabel explain1 = new JLabel("URL:");
			url = new JTextField(50);
			field.add(explain1);
			field.add(url);
			
			/*
			JLabel explain2 = new JLabel("Image:");
			selected1 = new JLabel("(Not Selected)");
			JButton filechoose1 = new JButton("Choose Image");
			filechoose1.addActionListener(this);
			filechoose1.setActionCommand("choose_img");
			openimage.add(explain2);
			openimage.add(selected1);
			openimage.add(filechoose1);
			*/
			
			JLabel explain2 = new JLabel("Image URL:");
			imageurl = new JTextField(50);
			openimage.add(explain2);
			openimage.add(imageurl);
			
			JLabel explain3 = new JLabel("Make to:");
			selected2 = new JLabel("(Not Selected)");
			JButton filechoose2 = new JButton("Choose path");
			filechoose2.addActionListener(this);
			filechoose2.setActionCommand("choose_html");
			openhtml.add(explain3);
			openhtml.add(selected2);
			openhtml.add(filechoose2);
			
			JButton generate1 = new JButton("Generate");
			generate1.addActionListener(this);
			generate1.setActionCommand("gen");
			generate.add(generate1);
			
			Container island = getContentPane();
			JPanel parameter = new JPanel();
			island.add(yama, BorderLayout.NORTH);
			parameter.add(field);
			parameter.add(openimage);
			parameter.add(openhtml);
			parameter.add(generate);
			island.add(parameter);
			
		}
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			JFileChooser steve = new JFileChooser();
			/*if(command.equals("choose_img")) {
				FileFilter kosinuno1 = new FileNameExtensionFilter("icoファイル", "ico");
				steve.addChoosableFileFilter(kosinuno1);
				steve.setAcceptAllFileFilterUsed(false);
				int options_img = steve.showOpenDialog(this);
				if (options_img == JFileChooser.APPROVE_OPTION) {
				img = steve.getSelectedFile();
				selected1.setText(img.getName());
				imagepath = img.getAbsolutePath();
			}
				else if (options_img == JFileChooser.CANCEL_OPTION) {
					selected1.setText("Cancelled");
				}
				else if(options_img == JFileChooser.ERROR_OPTION) {
					selected1.setText("Error 01 - JFileChooser returned ERROR_OPTION");
				}
		}else */if(command.equals("choose_html")) {
			FileFilter kosinuno2 = new FileNameExtensionFilter("txtファイル", "txt");
			steve.addChoosableFileFilter(kosinuno2);
			steve.setAcceptAllFileFilterUsed(false);
			int options_html = steve.showOpenDialog(this);
			if(options_html == JFileChooser.APPROVE_OPTION) {
				html = steve.getSelectedFile();
				selected2.setText(html.getName());
				htmlpath = html.getAbsolutePath();
			}
			else if(options_html == JFileChooser.CANCEL_OPTION) {
				selected2.setText("Cancelled");
			}
			else if(options_html == JFileChooser.ERROR_OPTION) {
				selected2.setText("Error 01 - JFileChooser returned ERROR_OPTION");
			}
		}else if(command.equals("gen")) {
			if(html.exists()) {
				
			}else {
				try {
					html.createNewFile();
				} catch (IOException e) {
					testtxt1.setForeground(Color.RED);
					testtxt1.setText("Error 02b - IOException at creating file");
				}
			}
			try {
				FileWriter penandink = new FileWriter(html);
				encodetohtml();
				penandink.write(encodedhtml);
				System.out.println("a");
				penandink.close();
				testtxt1.setForeground(Color.BLACK);
				testtxt1.setText("It's all up!");
			} catch (IOException e) {
				testtxt1.setForeground(Color.RED);
				testtxt1.setText("Error 02a - IOException at writing html");
			}
		}
		
		}
		void encodetohtml(){
			encodedhtml ="<!DOCTYPE html>\n"
					+ "<html lang=\"ja\">\n"
					+ "<head>\n"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
					+ "<meta http-equiv=\"refresh\" content=\"3;URL=" + url.getText() + "\">\n"
					+ "<link rel=\"apple-touch-icon\" href=\"" + imageurl.getText() + "\">\n"
					+ "</head>";
			System.out.println("html is encoded.");
		}
}