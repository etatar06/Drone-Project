package de.frauas.droneProject.group31.UI;

import java.awt.EventQueue;

import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import BusinessLayer.userDetails;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


/**
 * 
 * @author Devansh Jaggi
 *
 */
public class LoginPage extends JFrame {
	// Logger for LoginPage class
    	private static final Logger logger = Logger.getLogger(LoginPage.class.getName());

	String loginEmail;
	String loginPass;
	public void updateCredentials(String email, String pass) {
        loginEmail = email;
        loginPass = pass;
    }
	createNewAccount objAcc;
	boolean flag;

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
                    LoginPage frame = new LoginPage();
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
	
	
	public LoginPage() {
		logger.info("LoginPage initialized.");
		objAcc = new createNewAccount(null);
		Dashboard objDash = new Dashboard();
				

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1545, 875);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(450, 80, 730, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblKeyIcon = new JLabel("");
		lblKeyIcon.setIcon(new ImageIcon(LoginPage.class.getResource("key.png")));
		lblKeyIcon.setBounds(470, 270, 200, 200);
		panel.add(lblKeyIcon);
		
		JLabel lblUser = new JLabel("");
		lblUser.setIcon(new ImageIcon(LoginPage.class.getResource("add-user.png")));
		lblUser.setBounds(140, 160, 80, 80);
		panel.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPass.setBounds(63, 354, 90, 20);
		panel.add(lblPass);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(63, 287, 45, 20);
		panel.add(lblEmail);
		
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(63, 373, 220, 30);
		panel.add(txtPass);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(62, 306, 220, 30);
		panel.add(txtEmail);
		
			
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		       	 // Retrieving entered email and password
			  String enteredEmail = txtEmail.getText();
	           	  String enteredPass = txtPass.getText();
	             
	            
	             // Validating credentials
	             if (enteredEmail.equals(loginEmail) && enteredPass.equals(loginPass)) {
	                    JOptionPane.showMessageDialog(btnSignIn, "Successful");
	                    objDash.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(btnSignIn, "Invalid credentials");
                }
			}

			
		});
		btnSignIn.setForeground(Color.BLACK);
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignIn.setBounds(93, 448, 150, 30);
		panel.add(btnSignIn);
		
		JLabel lblPanelBG = new JLabel("");
		lblPanelBG.setIcon(new ImageIcon(LoginPage.class.getResource("desktop-wallpaper-color-gradient-neon-gradient-thumbnail (1).jpg")));
		lblPanelBG.setBounds(0, 0, 365, 700);
		panel.add(lblPanelBG);
		
		 // Background images for login panel
		JLabel lblPanelBG1 = new JLabel("");
		lblPanelBG1.setIcon(new ImageIcon(LoginPage.class.getResource("inline_image_preview (1).jpg")));
		lblPanelBG1.setBounds(365, 0, 365, 700);
		panel.add(lblPanelBG1);
		
		 // Background image for the whole frame
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(LoginPage.class.getResource("peakpx (9).jpg")));
		lblBG.setBounds(0, 0, 1545, 875);
		contentPane.add(lblBG);
	}
	
	
	
	


}
