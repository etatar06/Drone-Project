package de.frauas.droneProject.group31.UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.logging.Logger;
/**
 * 
 * @author Devansh Jaggi
 *
 */
public class createNewAccount extends JFrame {
	 private static final Logger LOGGER = Logger.getLogger(createNewAccount.class.getName());
	
	//Dashboard objDash = new Dashboard();
	
	 // Reference to the LoginPage
	private LoginPage objLogin;
	
	 // User account information
	String name; 
	String email;
	String pass; 
	boolean flag = false;
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	protected JTextField txtEmail;
	protected JTextField txtPass;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage objLogin = new LoginPage();
					createNewAccount frame = new createNewAccount(objLogin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public createNewAccount(LoginPage objlogin) {
		LOGGER.info("Account creation started for user:" + getEmail());
		this.objLogin = objLogin;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1545, 865);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(450, 80, 730, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSignUp = new JButton("Create Account");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkTxtVal()) {
					
					setEmail(txtEmail.getText());
					setPass(txtPass.getText());
					setName(txtName.getText());
				

	                 JOptionPane.showMessageDialog(btnSignUp, "Account Created Successfully");
	                 //objDash.setVisible(true);
	                 LOGGER.info("Account Created Successfully for user:"+ getEmail());
			

	                 objlogin.updateCredentials(getEmail(), getPass());
	                 objlogin.setVisible(true);
				}
				
				else {
					LOGGER.warning("Invalid input during account creation");
					
					 
					JOptionPane.showMessageDialog(btnSignUp, "Please fill all the fields");
				}	
				
			}
		});
		
		JLabel lblDrone = new JLabel("");
		lblDrone.setIcon(new ImageIcon(createNewAccount.class.getResource("drone (5).png")));
		lblDrone.setBounds(420, 230, 250, 250);
		panel.add(lblDrone);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("add-user.png")));
		lblIcon.setBounds(140, 118, 80, 80);
		panel.add(lblIcon);
		
		
		
		btnSignUp.setForeground(Color.BLACK);
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignUp.setBounds(75, 470, 220, 30);
		panel.add(btnSignUp);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(74, 263, 220, 30);
		panel.add(txtName);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(75, 244, 45, 20);
		panel.add(lblName);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(74, 336, 220, 30);
		panel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(75, 317, 45, 20);
		panel.add(lblEmail);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPass.setBounds(75, 384, 90, 20);
		panel.add(lblPass);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(75, 403, 220, 30);
		panel.add(txtPass);
		
		
		
		
		JLabel lblPanelBG = new JLabel("");
		lblPanelBG.setIcon(new ImageIcon(createNewAccount.class.getResource("desktop-wallpaper-color-gradient-neon-gradient-thumbnail (1).jpg")));
		lblPanelBG.setBounds(0, 0, 365, 700);
		panel.add(lblPanelBG);
		
		JLabel lblPanelBG1 = new JLabel("");
		lblPanelBG1.setIcon(new ImageIcon(createNewAccount.class.getResource("inline_image_preview (1).jpg")));
		lblPanelBG1.setBounds(365, 0, 365, 700);
		panel.add(lblPanelBG1);
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(createNewAccount.class.getResource("peakpx (9).jpg")));
		lblBG.setBounds(0, 0, 1545, 875);
		contentPane.add(lblBG);
	}
	
	public String getEmail() {
		
		return email;
		
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setEmail(String email) {
		this.email = email; 
	}
	
	public void setPass(String pass) {
		this.pass = pass; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public boolean checkTxtVal() {
		name = txtName.getText();
		email = txtEmail.getText();
		pass = txtPass.getText();
		
		
		
		if((name.length()==0 || email.length()==0 || pass.length()==0)) {
			flag=false;
		}
		else {
			flag = true;

		}
		
		return flag;
	}
}
