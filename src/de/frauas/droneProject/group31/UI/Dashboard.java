package de.frauas.droneProject.group31.UI;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import de.frauas.droneProject.group31.BackendFunctions.DataCollector;
import de.frauas.droneProject.group31.BackendFunctions.JsonProcessor;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Devansh Jaggi
 *
 */
public class Dashboard extends JFrame {

	 private static final long serialVersionUID = 1L;
	 Toolkit toolkit = Toolkit.getDefaultToolkit();
	 Dimension screenSize = toolkit.getScreenSize();
	 int screenWidth = screenSize.width;
	 int screenHeight = screenSize.height;
	 private final static Logger logger = Logger.getLogger(JsonProcessor.class.getName());
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		createNewAccount objAcc = new createNewAccount(null);  
		
		 setTitle("Java Project");
	     setSize(screenSize);
	     setExtendedState(JFrame.MAXIMIZED_BOTH);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

	     JPanel panel = new JPanel();
	     getContentPane().add(panel);
	     panel.setLayout(null);
	     
	     JButton btnRefresh = new JButton("Refresh");
			btnRefresh.addMouseListener(new MouseAdapter() {
				@Override
			 public void mouseClicked(MouseEvent e) {
                // Log a FINE message indicating the start of the mouseClicked method
                logger.fine("Refresh button clicked");

                DataCollector collector = new DataCollector();
                collector.updateData();
                JOptionPane.showMessageDialog(btnRefresh, "Data has been refreshed. Please open small windows again!");	 
                // Log a FINER message indicating the end of the mouseClicked method
                logger.finer("Refresh button click handling completed");
				}
			});
			
			btnRefresh.setOpaque(false);
			btnRefresh.setForeground(new Color(102, 255, 255));
			btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnRefresh.setContentAreaFilled(false);
			btnRefresh.setBorderPainted(false);
			btnRefresh.setBackground(Color.WHITE);
			btnRefresh.setBounds(47, 619, 200, 30);
			panel.add(btnRefresh);
			
			JLabel lblRefreshIcon = new JLabel("");
			lblRefreshIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("refresh.png")));
			lblRefreshIcon.setBounds(57, 619, 32, 32);
			panel.add(lblRefreshIcon);
		
	     
			JLabel lblGrp = new JLabel("Java Project: Group 31");
			lblGrp.setFont(new Font("Tahoma", Font.PLAIN, 99));
			lblGrp.setForeground(new Color(0, 255, 255));
			lblGrp.setBounds(400, 250, 1200, 130);
			panel.add(lblGrp);
			
			JLabel lblNames = new JLabel("- Annika, Erdem, Emre, Tim, Devansh");
			lblNames.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblNames.setForeground(new Color(0, 255, 255));
			lblNames.setBounds(624, 431, 500, 40);
			panel.add(lblNames);
			
	     
		JLabel lblFlightIcon = new JLabel("");
		lblFlightIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("dynamic.png")));
		lblFlightIcon.setBounds(55, 570, 32, 32);
		panel.add(lblFlightIcon);
		
		JLabel lblHistIcon = new JLabel("");
		lblHistIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("catalogue (2).png")));
		lblHistIcon.setBounds(55, 520, 32, 32);
		panel.add(lblHistIcon);
		
		JLabel lblCatIcon = new JLabel("");
		lblCatIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("catalogue (1).png")));
		lblCatIcon.setBounds(55, 420, 32, 32);
		panel.add(lblCatIcon);
		
		JLabel lblDroneIcon = new JLabel("");
		lblDroneIcon.setIcon(new ImageIcon(createNewAccount.class.getResource("1830867 (1).png")));
		lblDroneIcon.setBounds(55, 470, 34, 25);
		panel.add(lblDroneIcon);
		
		JButton btnCat = new JButton("Catalog");			
		btnCat.setForeground(new Color(102, 255, 255));
		btnCat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DroneCat drone = new DroneCat();
				drone.setVisible(true);
				drone.setDefaultCloseOperation(drone.DISPOSE_ON_CLOSE);			}
		});
		btnCat.setBackground(new Color(148, 0, 211));
		btnCat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCat.setBounds(47, 420, 200, 30);
		btnCat.setBorderPainted(false);
		btnCat.setOpaque(false);
		
		panel.add(btnCat);
		
		JButton btnDrone =  new JButton("Drone Types");
		btnDrone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DroneTypes droneTypes = new DroneTypes();
				droneTypes.setVisible(true);
				droneTypes.setDefaultCloseOperation(droneTypes.DISPOSE_ON_CLOSE);
			}
		});
		btnDrone.setBackground(new Color(224, 255, 255));
		btnDrone.setForeground(new Color(102, 255, 255));
		btnDrone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDrone.setBounds(47, 470, 200, 30);
		btnDrone.setOpaque(false);
		btnDrone.setBorderPainted(false);
	
		panel.add(btnDrone);
		
		JButton btnHist = new JButton("    Historical Analysis");
		btnHist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> {
					HistoricalAnalysis frame = new HistoricalAnalysis();
					frame.setVisible(true);

				});
				
				
			}
		});
		btnHist.setBackground(UIManager.getColor("Button.highlight"));
		btnHist.setForeground(new Color(102, 255, 255));
		btnHist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHist.setBounds(48, 520, 200, 30);
		btnHist.setOpaque(false);
		btnHist.setBorderPainted(false);
		btnHist.setContentAreaFilled(false);
		panel.add(btnHist);
		
		
		
		JButton btnFlight = new JButton("   Flight Dynamics");
		btnFlight.setOpaque(false);
		btnFlight.setForeground(new Color(102, 255, 255));
		btnFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFlight.setContentAreaFilled(false);
		btnFlight.setBorderPainted(false);
		btnFlight.setBackground(Color.WHITE);
		btnFlight.setBounds(47, 612, 200, 30);
		panel.add(btnFlight);	
		btnFlight.addMouseListener(new MouseAdapter() {
			@Override
		  public void mouseClicked(MouseEvent e) {
                // Log a FINE message indicating the start of the mouseClicked method
                logger.fine("Flight Dynamics button clicked");

                DroneDynamics droneD = new DroneDynamics();
                droneD.setVisible(true);
                droneD.setDefaultCloseOperation(droneD.DISPOSE_ON_CLOSE);

                // Log a FINER message indicating the end of the mouseClicked method
                logger.finer("Flight Dynamics button click handling completed");
            }
        });
		
		btnFlight.setBackground(UIManager.getColor("Button.highlight"));
		btnFlight.setForeground(new Color(102, 255, 255));
		btnFlight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFlight.setBounds(47, 570, 200, 30);			
		btnFlight.setBorderPainted(false);
		btnFlight.setOpaque(false);
		
		btnFlight.setContentAreaFilled(false);
		panel.add(btnFlight);
		
		JButton btnCatalogue = new JButton("Welcome");
		btnCatalogue.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCatalogue.setForeground(new Color(102, 255, 255));
		btnCatalogue.setOpaque(false);
		btnCatalogue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCatalogue.setContentAreaFilled(false);
		btnCatalogue.setBorderPainted(false);
		btnCatalogue.setBackground(Color.WHITE);
		
		btnCatalogue.setBounds(47, 300, 194, 30);
		panel.add(btnCatalogue);
		
		JButton btnUser = new JButton("");
		
		btnUser.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUser.setForeground(new Color(102, 255, 255));
		btnUser.setOpaque(false);
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUser.setContentAreaFilled(false);
		btnUser.setBorderPainted(false);
		btnUser.setBackground(Color.WHITE);
		btnUser.setBounds(48, 330, 194, 30);
		panel.add(btnUser);
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon(Dashboard.class.getResource("add-user.png")));
		lblUserIcon.setBounds(115, 240, 60, 60);
		panel.add(lblUserIcon);
		
		JLabel lblRectangle = new JLabel("");
		lblRectangle.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 204, 255)));
		lblRectangle.setBounds(45, 180, 200, 500);
		panel.add(lblRectangle);
		
		JLabel lblRectangleBackground = new JLabel("");
		lblRectangleBackground.setBounds(880, 100, 300, 300);
		panel.add(lblRectangleBackground);
		
		
		
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(createNewAccount.class.getResource("peakpx (9).jpg")));
		lblBG.setBounds(0, 0, 1545, 875);
		panel.add(lblBG);
		
		
		
		

		
		
		
	}
}
