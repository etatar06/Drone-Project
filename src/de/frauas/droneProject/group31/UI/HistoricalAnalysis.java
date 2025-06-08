package de.frauas.droneProject.group31.UI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.frauas.droneProject.group31.BackendFunctions.HistoricalAnalysisListCreator;
import de.frauas.droneProject.group31.BackendFunctions.JsonProcessor;
import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * 
 * @author Tim Bornemann
 *
 */
public class HistoricalAnalysis extends JFrame {

	private final static Logger LOG = Logger.getLogger(JsonProcessor.class.getName());
	private DefaultListModel<String> listModel;
	private JComboBox<String> comboBox;
	private JTextField[][] textFields;
	private Storage activeStorage;

	public static void main(String[] args) {
		HistoricalAnalysis analysis = new HistoricalAnalysis();
		analysis.setVisible(true);
	}
	
	
	/**
	 * Constructs an instance of the HistoricalAnalysis class. This constructor initializes the active storage based on
	 * the available storage data and builds the user interface for the historical analysis application.
	 *
	 * <p> Key Steps: </p>
	 * <ul>
	 *   <li>Validating the storage list to ensure it is neither null nor empty.</li>
	 *   <li>Setting the active storage to the last item in the storage list if available, otherwise to null.</li>
	 *   <li>Building the user interface by calling {@code BuildUI()}.</li>
	 *   <li>Setting the default close operation for the JFrame.</li>
	 * </ul>
	 */
	public HistoricalAnalysis() {
		LOG.fine("Initializing HistoricalAnalysis.");
		if (Storage.storages == null || Storage.storages.isEmpty()) {
			LOG.warning("Storage list is null or empty. Setting activeStorage to null.");
			activeStorage = null;
		} else {
			this.activeStorage = Storage.storages.get(Storage.storages.size() - 1);
			LOG.info("Set activeStorage to the last storage in the list.");
		}
		BuildUI();
		LOG.info("UI built for HistoricalAnalysis.");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	/**
	 * Builds the User Interface (UI) components for the application. This method sets up various UI elements like JList, JComboBox,
	 * and a panel of text fields, configuring their layouts and adding them to the JFrame. It also initializes event listeners
	 * for the list and combo box to handle user interactions.
	 *
	 * <p> Key UI Components: </p>
	 * <ul>
	 *   <li>A JList for displaying a list of items (e.g., drone names).</li>
	 *   <li>A JComboBox for selecting from a set of options (e.g., dates).</li>
	 *   <li>A panel of JTextField objects for displaying data.</li>
	 * </ul>
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Setting up the JFrame with title, size, layout, and default close operation.</li>
	 *   <li>Initializing and adding a JList with a ListSelectionListener for user selection handling.</li>
	 *   <li>Initializing and adding a JComboBox with an ItemListener for user selection handling.</li>
	 *   <li>Creating a panel with JLabels and JTextFields in a grid layout to display data.</li>
	 *   <li>Adding the panel with text fields to the JFrame.</li>
	 *	 <li>Populating the JComboBox with data from storages using the {@code AddStorageDatesToCombobox} method.</li>
	 *	</ul>
	 */
	public void BuildUI() {
	    LOG.fine("Building UI components.");

	    setTitle("Custom JFrame");
	    setSize(900, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new GridBagLayout());
	    setLocationRelativeTo(null);

	    // Dark Mode Farben
	    Color darkBackground = Color.BLACK; // Dunkelgrauer Hintergrund
	    Color lightForeground = Color.white; // Hellweißer Text

	    getContentPane().setBackground(darkBackground); // Hintergrundfarbe setzen

	    GridBagConstraints gbc = new GridBagConstraints();

	    // Initialisierung der JList auf der linken Seite
	    listModel = new DefaultListModel<>();
	    JList<String> jList = new JList<>(listModel);
	    JScrollPane scrolpane = new JScrollPane(jList);

	    jList.setBackground(darkBackground); // Hintergrundfarbe der JList setzen
	    jList.setForeground(lightForeground); // Textfarbe der JList setzen

	    gbc.gridx = 0; // Erste Spalte
	    gbc.gridy = 0; // Erste Zeile
	    gbc.gridheight = GridBagConstraints.REMAINDER; // Rest der Höhe
	    gbc.fill = GridBagConstraints.BOTH; // Vertikale und horizontale Ausdehnung
	    gbc.weightx = 0.3; // Gewichtung für die Breitenverteilung
	    gbc.weighty = 1.0; // Gewichtung für die Höhenverteilung
	    gbc.insets = new Insets(10, 10, 10, 5); // Abstand
	    add(scrolpane, gbc);
	    LOG.info("JList initialized and added to UI.");

	    jList.addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()) {
	                try {
	                    LOG.fine("List selection changed: " + jList.getSelectedIndex());
	                    RefreshDataInTextboxes(jList.getSelectedIndex());
	                } catch (Exception exception) {
	                    LOG.severe("Exception in ListSelectionListener: " + exception.getMessage());
	                }
	            }
	        }
	    });

	    // Initialisierung der JComboBox oben
	    comboBox = new JComboBox<>();
	    comboBox.setBackground(darkBackground); // Hintergrundfarbe der JComboBox setzen
	    comboBox.setForeground(lightForeground); // Textfarbe der JComboBox setzen

	    gbc.gridx = 1; // Zweite Spalte
	    gbc.gridy = 0; // Erste Zeile
	    gbc.gridwidth = GridBagConstraints.REMAINDER; // Rest der Breite
	    gbc.gridheight = 1; // Eine Zeile hoch
	    gbc.weightx = 0.7; // Gewichtung für die Breitenverteilung
	    gbc.weighty = 0; // Keine zusätzliche Höhengewichtung
	    gbc.fill = GridBagConstraints.HORIZONTAL; // Nur horizontale Ausdehnung
	    gbc.insets = new Insets(10, 5, 10, 10); // Abstand
	    add(comboBox, gbc);
	    LOG.info("JComboBox initialized and added to UI.");

	    comboBox.addItemListener(new ItemListener() {
	        @Override
	        public void itemStateChanged(ItemEvent e) {
	            if (e.getStateChange() == ItemEvent.SELECTED) {
	                LOG.fine("ComboBox item selected: " + comboBox.getSelectedItem());
	                ChangeDate(comboBox.getItemCount() - comboBox.getSelectedIndex() - 1);
	            }
	        }
	    });

	    // Initialisierung der Textfelder in der Mitte
	    JPanel textFieldsPanel = new JPanel(new GridLayout(10, 2, 10, 10));
	    // Initialisierung der Labels und Textfelder in der Mitte
	    int rows = 5;
	    int cols = 4; // Da Labels und Textfelder abwechselnd angeordnet werden sollen
	    textFieldsPanel = new JPanel(new GridLayout(rows, cols, 10, 10));
	    textFields = new JTextField[rows][2]; // 3 Zeilen und 2 Textfelder pro Zeile
	    JLabel[][] labels = new JLabel[rows][2]; // 3 Zeilen und 2 Labels pro Zeile

	    String[] labelTexts = { "Timestamp", "Speed:", "Align Roll: ", "Align Pitch:", "Align Yaw", "Longitude",
	            "Latitude", "batteryStatus", "LastSeen", "Status" };
	    int counter = 0;

	    for (int i = 0; i < textFields.length; i++) {
	        for (int j = 0; j < textFields[i].length; j++) {
	            labels[i][j] = new JLabel(labelTexts[counter++]);
	            labels[i][j].setForeground(lightForeground);
	            textFields[i][j] = new JTextField();
	            textFields[i][j].setBackground(darkBackground); // Hintergrundfarbe der Textfelder setzen
	            textFields[i][j].setForeground(lightForeground); // Textfarbe der Textfelder setzen
	            textFieldsPanel.add(labels[i][j]);
	            textFieldsPanel.add(textFields[i][j]);
	            LOG.finest("Added label and text field for " + labelTexts[counter - 1]);
	        }
	    }

	    gbc.gridx = 1; // Zweite Spalte
	    gbc.gridy = 1; // Zweite Zeile
	    gbc.gridwidth = 1; // Eine Spalte breit
	    gbc.gridheight = GridBagConstraints.REMAINDER; // Rest der Höhe
	    gbc.weightx = 0.7; // Gewichtung für die Breitenverteilung
	    gbc.weighty = 1.0; // Gewichtung für die Höhenverteilung
	    gbc.fill = GridBagConstraints.BOTH; // Vertikale und horizontale Ausdehnung
	    gbc.insets = new Insets(0, 5, 10, 10); // Abstand
	    textFieldsPanel.setBackground(darkBackground); // Hintergrundfarbe des Textfeldpanels setzen
	    add(textFieldsPanel, gbc);
	    LOG.info("Text fields panel initialized and added to UI.");

	    AddStorageDatesToCombobox();
	    LOG.info("UI construction completed.");
	}

	
	
	/**
	 * Adds a new item to a list, ensuring that the item is neither null nor empty. This method performs validation checks
	 * on the item before adding it to the list to maintain data integrity and prevent adding invalid entries.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Validating the item to ensure it is not null or empty.</li>
	 *   <li>Adding the validated item to the list.</li>
	 * </ul>
	 *
	 * @param item The string item to be added to the list.
	 */
	public void addToList(String item) {
		LOG.fine("Adding item to List: " + item);

		if (item == null || item.isEmpty()) {
			LOG.warning("Attempted to add null or empty item to List.");
			return;
		}

		listModel.addElement(item);
		LOG.info("Item '" + item + "' added to the List.");
	}

	/**
	 * Adds a new item to the top of a ComboBox, ensuring that the item is neither null nor empty.
	 * This method performs a check before adding the item to the ComboBox to maintain data integrity.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Validating the item to ensure it is not null or empty.</li>
	 *   <li>Adding the item to the top of the ComboBox if it passes validation.</li>
	 * </ul>
	 *
	 * @param item The string item to be added to the ComboBox.
	 */
	public void addToComboBox(String item) {
		LOG.fine("Adding item to ComboBox: " + item);

		if (item == null || item.isEmpty()) {
			LOG.warning("Attempted to add null or empty item to ComboBox.");
			return;
		}

		comboBox.insertItemAt(item, 0);
		LOG.info("Item '" + item + "' added to the top of the ComboBox.");
	}

	
	/**
	 * Sets the content of a specified text field in a two-dimensional array of text fields. This method ensures the
	 * specified indexes are within the bounds of the text field array before setting the text.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Validating the row and column indices to ensure they fall within the bounds of the text field array.</li>
	 *   <li>Setting the specified text field's content if the indices are valid.</li>
	 * </ul>
	 *
	 * @param row     The row index of the text field in the array.
	 * @param col     The column index of the text field in the array.
	 * @param content The content to be set in the text field.
	 */
	public void setTextFieldContent(int row, int col, String content) {
		LOG.finer("Setting text field content at row " + row + ", column " + col + " with content: " + content);

		if (row >= 0 && row < textFields.length && col >= 0 && col < textFields[row].length) {
			textFields[row][col].setText(content);
			LOG.finest("Text set in text field at row " + row + ", column " + col);
		} else {
			LOG.warning("Attempted to set text field content at invalid index: row " + row + ", column " + col);
			System.out.println("Index außerhalb des Bereichs");
		}
	}

	
	/**
	 * Changes the active storage to a new storage based on the specified index and updates the user interface accordingly.
	 * This method sets the active storage, clears the current list model and textboxes, and then populates the list with
	 * drone names from the new active storage.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Validates the storage list and the provided index.</li>
	 *   <li>Sets the active storage to the storage at the specified index.</li>
	 *   <li>Clears the existing data from the list model and textboxes.</li>
	 *   <li>Populates the list with drone names from the new active storage.</li>
	 * </ul>
	 *
	 * @param storageIndex The index of the storage to be set as active.
	 */
	private void ChangeDate(int storageIndex) {
		LOG.fine("Changing date with storage index: " + storageIndex);

		if (Storage.storages == null || Storage.storages.isEmpty()) {
			LOG.warning("Storage list is null or empty, cannot change date.");
			return;
		}

		if (storageIndex < 0 || storageIndex >= Storage.storages.size()) {
			LOG.severe("Storage index out of bounds: " + storageIndex);
			return;
		}

		activeStorage = Storage.storages.get(storageIndex);
		LOG.info("Active storage set to storage at index: " + storageIndex);

		listModel.clear();
		LOG.finer("Cleared list model.");

		ClearTextInTextboxes();
		LOG.finer("Cleared text in textboxes.");

		AddDroneNamesToList();
		LOG.info("Added drone names to list for the new storage date.");
	}

	
	/**
	 * Clears the text content in all textboxes organized in a two-dimensional array. This method iterates through each textbox
	 * and sets its content to null, effectively clearing any existing text.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Iterating through each row and column of the textbox array.</li>
	 *   <li>Clearing the text content of each textbox.</li>
	 * </ul>
	 */
	private void ClearTextInTextboxes() {
		LOG.fine("Clearing text in all textboxes.");

		for (int i = 0; i < textFields.length; i++) {
			for (int j = 0; j < textFields[i].length; j++) {
				setTextFieldContent(i, j, null);
				LOG.finest("Cleared textbox at row " + i + " and column " + j + ".");
			}
		}

		LOG.info("All textboxes cleared.");
	}

	/**
	 * Refreshes the data displayed in textboxes with the information of a specific drone, identified by its index in a list.
	 * This method retrieves the dynamic data of the drone from the active storage and updates various textboxes with this information.
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Checks if the active storage is not null.</li>
	 *   <li>Retrieves the {@link DroneDynamic} object for the drone at the specified index.</li>
	 *   <li>Updates textboxes with various pieces of drone data, such as timestamp, speed, alignment parameters, geographical coordinates, battery status, and more.</li>
	 * </ul>
	 *
	 * @param droneIndex The index of the drone in the list, which is used to identify the drone in the active storage.
	 */
	private void RefreshDataInTextboxes(int droneIndex) {
		if (activeStorage == null) {
			LOG.severe("Active storage is null, cannot refresh data in textboxes.");
			return;
		}

		LOG.fine("Refreshing data in textboxes for drone at index: " + droneIndex);

		DroneDynamic tempDroneDynamic = activeStorage.getASingelDroneDynamic(ExtractIDFromListElement(droneIndex));
		if (tempDroneDynamic == null) {
			LOG.warning("No DroneDynamic data found for drone at index: " + droneIndex);
			return;
		}
		LOG.info("Retrieved DroneDynamic data for drone at index: " + droneIndex);

		setTextFieldContent(0, 0, formatTime(tempDroneDynamic.getTimestamp())); // "Timestamp"
		LOG.finest("Updated Timestamp textbox.");

		setTextFieldContent(0, 1, String.valueOf(tempDroneDynamic.getSpeed())); // "Speed"
		LOG.finest("Updated Speed textbox.");

		setTextFieldContent(1, 0, String.valueOf(tempDroneDynamic.getAlignRoll()));// "Align Roll"
		LOG.finest("Updated Align Roll textbox.");

		setTextFieldContent(1, 1, String.valueOf(tempDroneDynamic.getAlignPitch()));// "Align Pitch"
		LOG.finest("Updated Align Pitch textbox.");

		setTextFieldContent(2, 0, String.valueOf(tempDroneDynamic.getAlignYaw())); // "Align Yaw"
		LOG.finest("Updated Align Yaw textbox.");

		setTextFieldContent(2, 1, String.valueOf(tempDroneDynamic.getLongitude())); // "Longitude"
		LOG.finest("Updated Longitude textbox.");

		setTextFieldContent(3, 0, String.valueOf(tempDroneDynamic.getLatitude())); // "Latitude"
		LOG.finest("Updated Latitude textbox.");

		setTextFieldContent(3, 1, String.valueOf(tempDroneDynamic.getBatteryStatus()));// "batteryStatus"
		LOG.finest("Updated Battery Status textbox.");

		setTextFieldContent(4, 0, formatTime(tempDroneDynamic.getLastSeen())); // "LastSeen"
		LOG.finest("Updated Last Seen textbox.");

		setTextFieldContent(4, 1, tempDroneDynamic.getStatus()); // "Status"
		LOG.finest("Updated Status textbox.");

		LOG.info("Completed refreshing data in textboxes for drone at index: " + droneIndex);
	}

	/**
	 * Extracts an ID from a list element at the specified index. This method assumes that the ID is a part of the string
	 * in the list element and is formatted in a specific way (e.g., "Drone 123" where 123 is the ID to extract).
	 *
	 * <p> Steps involved: </p>
	 * <ul>
	 *   <li>Accesses the element at the given index in the list model.</li>
	 *   <li>Attempts to parse the ID portion of the string (assumed to be after a specific prefix, e.g., "Drone ").</li>
	 * </ul>
	 *
	 * @param index The index of the element in the list from which the ID is to be extracted.
	 * @return The extracted ID as an integer. If the extraction fails (e.g., due to parsing errors or invalid format), returns -1.
	 */
	private int ExtractIDFromListElement(int index) {
		LOG.fine("Extracting ID from list element at index: " + index);

		try {
			int id = Integer.parseInt(listModel.get(index).substring(6));
			LOG.info("Extracted ID: " + id + " from index: " + index);

			return id;
		} catch (Exception e) {
			LOG.severe("Exception encountered while extracting ID at index " + index + ": " + e.getMessage());
		} finally {
			LOG.finest("ExtractIDFromListElement method completed.");
		}
		return -1;
	}

	/**
	 * Formats a given time string from ISO offset date-time format to a custom format ("dd.MM.yyyy HH:mm:ss").
	 * This static method attempts to parse the provided time string, format it, and then return the formatted string.
	 *
	 * <p> Process: </p>
	 * <ul>
	 *   <li>Attempts to parse the input time string as a {@link LocalDateTime} using the ISO offset date-time format.</li>
	 *   <li>If successful, formats the {@link LocalDateTime} to the "dd.MM.yyyy HH:mm:ss" format.</li>
	 * </ul>
	 *
	 * @param time The time string to be formatted, expected in ISO offset date-time format.
	 * @return A string representing the formatted time in "dd.MM.yyyy HH:mm:ss" format.
	 *         If parsing fails, returns "Invalid date format".
	 */
	public static String formatTime(String time) {
		LOG.fine("Attempting to format time: " + time);

		try {
			LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			LOG.finer("Parsed LocalDateTime: " + dateTime);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
			String formattedTime = dateTime.format(formatter);
			LOG.info("Formatted time: " + formattedTime);

			return formattedTime;
		} catch (DateTimeParseException e) {
			LOG.warning("DateTimeParseException encountered with input: " + time + ". Error: " + e.getMessage());
			return "Invalid date format";
		} finally {
			LOG.finest("formatTime method completed.");
		}
	}

	/**
	 * Populates a list with drone names extracted from an active storage instance. This method uses the {@link HistoricalAnalysisListCreator}
	 * to create an ArrayList of drone names, formatted as "Drone [id]", which are then added to a designated list.
	 *
	 * <p> Steps in the process: </p>
	 * <ul>
	 *   <li>Instantiating {@link HistoricalAnalysisListCreator}.</li>
	 *   <li>Retrieving drone names from the active storage using {@code GetArrayOfDroneIds}.</li>
	 *   <li>Adding each drone name to the list.</li>
	 * </ul>
	 */

	private void AddDroneNamesToList() {
		LOG.fine("Starting to add drone names to list.");

		HistoricalAnalysisListCreator tempArrayOfDroneIds = new HistoricalAnalysisListCreator();
		LOG.finer("Created HistoricalAnalysisListCreator instance.");

		for (String droneName : tempArrayOfDroneIds.getArrayOfDroneIds(activeStorage)) {
			LOG.fine("Adding drone name to list: " + droneName);
			addToList(droneName);
		}

		LOG.info("Completed adding drone names to list.");
	}

	
	
	/**
	 * Adds formatted timestamps from storage data to a ComboBox. This method utilizes the {@link HistoricalAnalysisListCreator}
	 * class to generate an ArrayList of timestamps, each representing the first {@link DroneDynamic} object's timestamp in a 
	 * {@link Storage} instance. These timestamps are then formatted and added to the ComboBox.
	 *
	 * <p> The process involves:</p>
	 * <ul>
	 *   <li>Creating a temporary instance of {@link HistoricalAnalysisListCreator}.</li>
	 *   <li>Retrieving an ArrayList of timestamps using the {@code GetArrayOfStorageDates} method.</li>
	 *   <li>Formatting each timestamp and adding it to the ComboBox.</li>
	 * </ul>
	 */
	private void AddStorageDatesToCombobox() {
		LOG.fine("Starting to add storage dates to ComboBox.");

		HistoricalAnalysisListCreator tempArrayOfDroneIds = new HistoricalAnalysisListCreator();
		LOG.finer("Created HistoricalAnalysisListCreator instance for storage dates.");

		for (String date : tempArrayOfDroneIds.getArrayOfStorageDates()) {
			LOG.finest("Formatting and adding date to ComboBox: " + date);
			addToComboBox(formatTime(date));
		}

		int itemCount = comboBox.getItemCount();
		LOG.info(itemCount + " dates added to ComboBox.");
	}

}
