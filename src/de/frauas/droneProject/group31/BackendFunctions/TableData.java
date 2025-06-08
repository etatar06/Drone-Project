package de.frauas.droneProject.group31.BackendFunctions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * The TableData class provides methods to generate table data arrays from the
 * data stored in a Storage object. It includes methods for creating table data
 * arrays for drones, drone types, and dynamic drone information.
 * 
 * @author Annika Schäffler, Emre Tatar, Erdem BALCI 
 * @version 1.0
 * @since 21.01.2024
 */
public class TableData {

	private final static Logger LOG = Logger.getLogger(TableData.class.getName()); // Add Logger

	/**
	 * Retrieves and formats data for drones stored in the given Storage object.
	 *
	 * @param storage The Storage object containing drone data.
	 * @return An array of objects representing drone data suitable for display in a
	 *         table.
	 */
	public Object[][] getDroneTableData(Storage storage) {

		LOG.fine("Generating drone table data.");
		return createDroneTableData(storage.getDrones());
	}

	/**
	 * Retrieves and formats data for drone types stored in the given Storage
	 * object.
	 *
	 * @param storage The Storage object containing drone type data.
	 * @return An array of objects representing drone type data suitable for display
	 *         in a table.
	 */
	public Object[][] getDroneTypeTableData(Storage storage) {

		LOG.fine("Generating drone type table data.");
		return createDroneTypeTableData(storage.getDroneTypes());
	}

	/**
	 * Retrieves and formats dynamic drone information stored in the given Storage
	 * object.
	 *
	 * @param storage The Storage object containing dynamic drone information.
	 * @return An array of objects representing dynamic drone information suitable
	 *         for display in a table.
	 */
	public Object[][] getDroneDynamicTableData(Storage storage) {

		LOG.fine("Generating drone dynamic table data.");
		return createDroneDynamicTableData(storage.getDroneDynamics());
	}

	// Private helper method to create table data array for drones
	private Object[][] createDroneTableData(ConcurrentHashMap<Integer, Drone> hashmap) {
		Object[][] array = new Object[hashmap.size()][6];
		int i = 0;

		for (Map.Entry<Integer, Drone> drone : hashmap.entrySet()) {
			Drone tempDrone = drone.getValue();

			LOG.finest("Creating row for drone table data.");
			array[i][0] = tempDrone.getId();
			array[i][1] = tempDrone.getDronetype().getManufacturer();
			array[i][2] = tempDrone.getCreated();
			array[i][3] = tempDrone.getSerialnummer();
			array[i][4] = tempDrone.getCarriageWeight();
			array[i][5] = tempDrone.getCarriageType();

			i++;
		}

		return array;
	}

	// Private helper method to create table data array for drone types
	private Object[][] createDroneTypeTableData(ConcurrentHashMap<Integer, DroneType> hashmap) {
		Object[][] array = new Object[hashmap.size()][8];
		int i = 0;

		for (Map.Entry<Integer, DroneType> entry : hashmap.entrySet()) {

			DroneType droneType = entry.getValue();

			LOG.finest("Creating row for drone type table data.");
			array[i][0] = droneType.getId();
			array[i][1] = droneType.getManufacturer();
			array[i][2] = droneType.getTypename();
			array[i][3] = droneType.getWeight();
			array[i][4] = droneType.getMaximumSpeed();
			array[i][5] = droneType.getBatteryCapacity();
			array[i][6] = droneType.getControlRange();
			array[i][7] = droneType.getMaximumCarriage();

			i++;
		}

		return array;
	}

	// Private helper method to create table data array for drone dynamic
	// information
	private Object[][] createDroneDynamicTableData(ConcurrentHashMap<Integer, DroneDynamic> map) {
		Object[][] data = new Object[map.size()][12];
		int i = 0;

		for (Map.Entry<Integer, DroneDynamic> entry : map.entrySet()) {
			DroneDynamic tempDroneDynamic = entry.getValue();

			StringBuilder droneInfo = new StringBuilder("Drone: " + tempDroneDynamic.getDrone().getSerialnummer()
					+ "(created:" + tempDroneDynamic.getDrone().getCreated() + ")");
			// Drone: AlAA-2023-2BD9DD (created: 2023-12-01 14:43:27.917854+00:00)

			LOG.finest("Creating row for drone dynamic table data.");
			data[i][0] = tempDroneDynamic.getDrone().getId();
			data[i][1] = tempDroneDynamic.getTimestamp();
			data[i][2] = droneInfo;
			data[i][3] = tempDroneDynamic.getSpeed();
			data[i][4] = tempDroneDynamic.getAlignRoll();
			data[i][5] = tempDroneDynamic.getDrone().getDronetype().getControlRange();
			data[i][6] = tempDroneDynamic.getAlignYaw();
			data[i][7] = tempDroneDynamic.getLongitude();
			data[i][8] = tempDroneDynamic.getLatitude();
			data[i][9] = tempDroneDynamic.getBatteryStatus();
			data[i][10] = tempDroneDynamic.getLastSeen();
			data[i][11] = tempDroneDynamic.getStatus();

			i++;
		}

		return data;
	}

}
