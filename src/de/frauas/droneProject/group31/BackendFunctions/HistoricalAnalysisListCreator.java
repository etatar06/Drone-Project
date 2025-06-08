package de.frauas.droneProject.group31.BackendFunctions;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * The {@code HistoricalAnalysisListCreator} class provides functionality for
 * generating lists of data from a collection of drones and their associated
 * dynamics. It includes methods for extracting drone IDs and timestamps from
 * stored drone data. The class is designed to work with {@link DroneDynamic}
 * and {@link Storage} objects, utilizing a static collection of storages and a
 * ConcurrentHashMap for drone dynamics.
 * 
 * <p>
 * Key functionalities include:
 * </p>
 * <ul>
 * <li>Generating an ArrayList of drone IDs, formatted as strings, from a
 * ConcurrentHashMap of drone dynamics.</li>
 * <li>Creating an ArrayList of timestamps corresponding to the first
 * {@link DroneDynamic} object in each {@link Storage}.</li>
 * </ul>
 *
 * 
 * @author Annika Schäffler, Tim Bornemann
 */
public class HistoricalAnalysisListCreator {

	private static final Logger logger = Logger.getLogger(HistoricalAnalysisListCreator.class.getName());
	
	// Test
	public static void main(String[] args) {

		ConcurrentHashMap<Integer, DroneDynamic> droneDynamicMap = new ConcurrentHashMap<>();

		Drone drone1 = new Drone(1, new DroneType(1, "Manufacturer1", "ABCDE", 100.0, 100.1, 100.2, 100.3, 100.4),
				"2023-01-01", "SN123", 10, "TypeA");
		Drone drone2 = new Drone(2, new DroneType(2, "Manufacturer2", "FGHIJ", 200.0, 200.1, 200.2, 200.3, 200.4),
				"2023-01-02", "SN456", 15, "TypeB");

		droneDynamicMap.put(1, new DroneDynamic(drone1, "2023-01-01", 100.5, 100.6, 100.7, 100.8, 100.9, 100.11, 100.12,
				"23-01-02", "ON"));
		droneDynamicMap.put(2, new DroneDynamic(drone2, "2023-02-01", 200.5, 200.6, 200.7, 200.8, 200.9, 200.11, 100.12,
				"23-02-02", "OFF"));

		ArrayList<String> droneIds = createArrayOfDroneIds(droneDynamicMap);

		for (Object cell : droneIds) {

			System.out.println(cell);
		}
	}

	/**
	 * Retrieves an ArrayList of drone IDs from a specified {@link Storage} object.
	 * This is achieved by extracting the ConcurrentHashMap of Drone Dynamics from
	 * the {@link Storage} and then using the {@code CreateArrayOfDroneIds} method
	 * to generate the list of formatted drone IDs.
	 *
	 * @param storage The {@link Storage} object from which the drone IDs are to be
	 *                retrieved. It is expected to contain a ConcurrentHashMap of
	 *                Integer to {@link DroneDynamic} mappings.
	 * @return An ArrayList of Strings, each representing a drone ID formatted as
	 *         "Drone [id]". The list will mirror the number and order of drone
	 *         dynamics in the provided {@link Storage}.
	 */
	public ArrayList<String> getArrayOfDroneIds(Storage storage) {
		return createArrayOfDroneIds(storage.getDroneDynamics());
	}

	/**
	 * Constructs an ArrayList containing the IDs of drones from a given
	 * ConcurrentHashMap, where each entry maps an integer to a {@link DroneDynamic}
	 * object. The method iterates over the entries of the map, extracts the drone's
	 * ID from each {@link DroneDynamic} object, and formats it into a string
	 * prefixed with "Drone " before adding it to the ArrayList.
	 *
	 * @param map The ConcurrentHashMap of Integer and {@link DroneDynamic} pairs
	 *            from which the drone IDs are extracted. The Integer key is not
	 *            used in the ID extraction process.
	 * @return An ArrayList of Strings, where each string represents the ID of a
	 *         drone, formatted as "Drone [id]". The list will be empty if the input
	 *         map is empty.
	 */
	private static ArrayList<String> createArrayOfDroneIds(ConcurrentHashMap<Integer, DroneDynamic> map) {
		ArrayList<String> array = new ArrayList<>();

		for (Map.Entry<Integer, DroneDynamic> droneDynamic : map.entrySet()) {
			DroneDynamic tempDroneDynamic = droneDynamic.getValue();

			StringBuilder droneId = new StringBuilder();
			droneId.append("Drone ");
			droneId.append(tempDroneDynamic.getDrone().getId());

			array.add(droneId.toString());

            logger.log(Level.INFO, "Added drone ID to String: " + droneId.toString());
		}

		return array;
	}

	/**
	 * Retrieves an ArrayList of timestamps corresponding to the first
	 * {@link DroneDynamic} object in each {@link Storage} object from the static
	 * collection {@code Storage.storages}. This is achieved by delegating the task
	 * to the {@code CreateArrayOfStorageDates} method.
	 *
	 * @return An ArrayList of String objects, each representing the timestamp of
	 *         the first {@link DroneDynamic} object in each {@link Storage}. The
	 *         list may contain {@code null} values if some {@link Storage} objects
	 *         do not contain any {@link DroneDynamic} objects.
	 */
	public ArrayList<String> getArrayOfStorageDates() {
		return createArrayOfStorageDates();
	}

	/**
	 * Generates an ArrayList of timestamps, each corresponding to the first
	 * {@link DroneDynamic} object in each {@link Storage} object from the static
	 * collection {@code Storage.storages}. This method iterates over each
	 * {@link Storage} object in the collection, retrieving the timestamp using the
	 * {@code GetDateOutOfStorage} method.
	 *
	 * @return An ArrayList of String objects, where each string is the timestamp
	 *         from the first {@link DroneDynamic} object in each {@link Storage}
	 *         object. If a {@link Storage} object contains no {@link DroneDynamic}
	 *         objects, {@code null} is added to the list for that storage.
	 */
	private ArrayList<String> createArrayOfStorageDates() {

		ArrayList<String> list = new ArrayList<>();
		for (Storage storage : Storage.storages) {
			list.add(getDateOutOfStorage(storage));
		}

		return list;
	}

	/**
	 * Retrieves the timestamp from the first {@link DroneDynamic} object found in
	 * the provided {@link Storage}. This method assumes that the {@link Storage}
	 * object contains at least one {@link DroneDynamic} object. It prints the size
	 * of the drone dynamics collection and the timestamp of the retrieved
	 * {@link DroneDynamic} object to the console.
	 *
	 * @param storage The {@link Storage} object from which the timestamp is to be
	 *                retrieved. It should contain at least one {@link DroneDynamic}
	 *                object.
	 * @return The timestamp of the first {@link DroneDynamic} object in the
	 *         {@link Storage}, or {@code null} if no {@link DroneDynamic} object is
	 *         found.
	 */
	private String getDateOutOfStorage(Storage storage) {
		DroneDynamic d = (DroneDynamic) storage.getDroneDynamics().values().iterator().next();

		String storageDate = d.getTimestamp();
        logger.log(Level.FINE, "Retrieved storage date: " + storageDate);
		
		return d.getTimestamp();
	}

}
