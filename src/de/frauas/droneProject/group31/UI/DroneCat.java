package de.frauas.droneProject.group31.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import de.frauas.droneProject.group31.BackendFunctions.TableData;
import de.frauas.droneProject.group31.DroneStructure.Storage;
import java.awt.Font;
import javax.swing.JScrollPane;

/**
 * 
 * @author Devansh Jaggi
 *
 */
public class DroneCat extends JFrame {

    TableData tableData = new TableData();

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    DefaultTableModel Tmodel;
    private JTable table;
    private JLabel lblBg;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DroneCat frame = new DroneCat();
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
    public DroneCat() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBounds(800, 900, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Creating a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(45, 120, 900, 439);
        setLocationRelativeTo(null);
        contentPane.add(scrollPane);

        // Fetching data for the drone table
        Object[][] data = tableData.getDroneTableData(Storage.storages.get(Storage.storages.size() - 1));

        // Setting column names for the drone table
        String[] columnNames = { "ID", "Drone Type", "Created", "Serial number", "Carriage Weight", "Carriage Type" };

        // Creating a default table model with data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Creating the drone table with the model
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));

        // Setting the table inside the scroll pane
        scrollPane.setViewportView(table);

        // Adding a background image label to the frame
        lblBg = new JLabel("");
        lblBg.setIcon(new ImageIcon(DroneCat.class.getResource("peakpx (9).jpg")));
        lblBg.setBounds(0, 0, 1560, 880);
        contentPane.add(lblBg);
    }
}
