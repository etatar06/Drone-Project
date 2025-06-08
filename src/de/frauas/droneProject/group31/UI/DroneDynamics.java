package de.frauas.droneProject.group31.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import de.frauas.droneProject.group31.BackendFunctions.JsonProcessor;
import de.frauas.droneProject.group31.BackendFunctions.TableData;
import de.frauas.droneProject.group31.DroneStructure.Storage;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

/**
 * 
 * @author Devansh Jaggi
 *
 */
public class DroneDynamics extends JFrame {

	private final static Logger logger = Logger.getLogger(JsonProcessor.class.getName());
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DroneDynamics frame = new DroneDynamics();
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
	public DroneDynamics() {
	    // Log INFO message indicating the creation of DroneDynamics frame
	    logger.info("Creating DroneDynamics frame.");

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(50, 50, 1325, 700);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(-5, 5, 5, 5));

	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    Object[][] data = {};

	    String[] columnNames = {"ID", "Timestamp", "Drone", "Speed", "Alignment Roll", "Control Range", "Alignment Yaw",
	            "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"};

	    // Log CONFIG message indicating the setup of table column names
	    logger.config("Setting up table columns with names: " + Arrays.toString(columnNames));

	    DefaultTableModel model = new DefaultTableModel(data, columnNames);

	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(5, 120, 1300, 439);
	    setLocationRelativeTo(null);
	    contentPane.add(scrollPane);

	    TableData d = new TableData();
	    Object[][] list = d.getDroneDynamicTableData(Storage.storages.get(Storage.storages.size()-1));

	    // Log FINE message indicating the retrieval of drone dynamic table data
	    logger.fine("Retrieved drone dynamic table data from storage.");

	    table = new JTable(new DefaultTableModel(
	        list,
	        new String[] {
	                "ID", "Timestamp", "Drone","Speed","Alignment Roll", "Control Range","Alignment Yaw", "Longitude", "Latitude", "Battery Status", "Last Seen", "Status"
	        }
	    ));

	    // Log FINE message indicating the setup of JTable with model and data
	    logger.fine("Setting up JTable with model and data.");

	    scrollPane.setViewportView(table);
	    table.setFont(new Font("Verdana", Font.PLAIN, 14));
	    table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

	    JLabel lblBg = new JLabel("");
	    lblBg.setIcon(new ImageIcon(DroneDynamics.class.getResource("peakpx (9).jpg")));
	    lblBg.setBounds(0, 0, 1545, 875);
	    contentPane.add(lblBg);

	    // Log INFO message indicating the completion of DroneDynamics frame creation
	    logger.info("DroneDynamics frame created successfully.");
	}
}
