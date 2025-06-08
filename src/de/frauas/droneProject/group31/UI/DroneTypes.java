package de.frauas.droneProject.group31.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import de.frauas.droneProject.group31.BackendFunctions.TableData;
import de.frauas.droneProject.group31.DroneStructure.Storage;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.MatteBorder;
public class DroneTypes extends JFrame {
	private static final Logger logger = Logger.getLogger(DroneTypes.class.getName());
	
	// Instances of TableData and other components
	TableData tableData = new TableData();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel lblDrone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DroneTypes frame = new DroneTypes();
					frame.setVisible(true);
				} catch (Exception e) {
					 logger.log(Level.SEVERE, "An error occurred during application launch", e);
					e.printStackTrace();

				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DroneTypes() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-3, 10, 1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		logger.info("DroneTypes frame created successfully.");
	    logger.warning("This is a warning message.");

		
		Object[][] data = {
				
               
            };
		
		String[] columnNames = {"ID", "Manufacturer", "Typename","Weight","Maximum Speed","Battery Capacity", "Control Range","Maximum Carriage"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		lblDrone = new JLabel("");
		lblDrone.setIcon(new ImageIcon(createNewAccount.class.getResource("drone (3).png")));
		lblDrone.setBounds(410, -40, 161, 211);
		contentPane.add(lblDrone);
		
		// Set up the JScrollPane and JTable for drone types
		scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 120, 900, 439);
		contentPane.add(scrollPane);
		table_1 = new JTable(new DefaultTableModel(
			tableData.getDroneTypeTableData(Storage.storages.get(Storage.storages.size()-1)) ,
			new String[] {
				"ID", "Manufacturer", "Typename", "Weight", "Maximum Speed", "Battery Capacity", "Control Range", "Maximum Carriage"
			}
		));
		table_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		table_1.setFont(new Font("Verdiga", Font.PLAIN, 13));
		
		scrollPane.setViewportView(table_1);
	
		
		
		JLabel lblBg = new JLabel("");
		lblBg.setIcon(new ImageIcon(createNewAccount.class.getResource("peakpx (9).jpg")));
		lblBg.setBounds(0, 0, 1545, 875);
		
		contentPane.add(lblBg);
		
	}
}
