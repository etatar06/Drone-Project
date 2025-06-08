package de.frauas.droneProject.group31.BackendFunctions;

import java.util.logging.Logger;

import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * Manages the collection and updating of drone data from an external API. This
 * class encapsulates the functionality required to retrieve different types of
 * drone data (drone data, drone types, and drone dynamics) from a specified API
 * and store them in a given storage system. It is designed to handle both the
 * initial data collection and periodic updates to maintain current data.
 * 
 * The class uses an ApiConnection to fetch data and a JsonProcessor to process
 * the raw JSON data into usable formats. It also manages the URL and offset for
 * fetching drone dynamics data, ensuring the most recent data is always
 * retrieved and stored.
 * 
 * <p>
 * Usage involves initializing a Storage (storage) instance and passing it to
 * the appropriate methods for initial data collection or updating existing
 * data.
 * </p>
 * 
 * @author Tim Bornemann
 */
public class DataCollector {

	private final static Logger LOG = Logger.getLogger(JsonProcessor.class.getName());

	private ApiConnection apiConnection = new ApiConnection();
	private JsonProcessor jsonProcessor = new JsonProcessor();
	
	private String urlForDronedynamics = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json&limit=25&offset=";
	private static int offsetForUpdatingDronedynamics = 25;

	/**
	 * Performs the initial collection of all drone-related data using
	 * multithreading and stores it in the provided storage. This method creates and
	 * starts three separate threads to concurrently collect different sets of
	 * drone-related information: drone data, drone type data, and drone dynamics
	 * data. Each thread is responsible for fetching and processing data from an API
	 * and storing it in the provided storage instance.
	 *
	 * The storage instance given as a parameter should be initialized and prepared
	 * to store the respective types of drone data. This method efficiently sets up
	 * the necessary data foundation for subsequent operations by utilizing
	 * concurrent processing.
	 *
	 * Note: It is assumed that the functions invoked within the threads are
	 * thread-safe and can manage concurrent execution without compromising data
	 * integrity.
	 *
	 * @param storage The storage instance where all collected drone data are to be
	 *                stored.
	 */
	public void initialCollect() {
		LOG.info("Beginning initial collection of all drone-related data.");

		Storage storage = new Storage();

		Thread droneDataThread = new Thread(() -> {
			LOG.fine("Collecting basic drone data.");
			collectDrones(storage);
		});

		Thread droneTypesThread = new Thread(() -> {
			LOG.fine("Collecting drone types data.");
			collectDroneTypes(storage);
		});

		Thread droneDynamicsThread = new Thread(() -> {
			LOG.fine("Collecting drone dynamics data.");
			collectDroneDynamics(storage);
		});

		// Start all threads
		droneDataThread.start();
		droneTypesThread.start();
		droneDynamicsThread.start();

		try {
			// Wait for all threads to complete
			droneDataThread.join();
			droneTypesThread.join();
			droneDynamicsThread.join();
		} catch (InterruptedException e) {
			LOG.severe("Thread execution was interrupted: " + e.getMessage());
			Thread.currentThread().interrupt(); // Preserve interruption status
		}

		LOG.info("Initial data collection for drones completed.");
	}

	/**
	 * Collects drone data from an API and processes it for storage. This method
	 * first fetches drone data as a JSON string using the API connection's
	 * `getAllDrones` method. It then processes this data by calling `processDrones`
	 * of the jsonProcessor, adding the processed drones to the provided storage.
	 *
	 * @param storage The storage instance where processed drones are to be stored.
	 */
	private void collectDrones(Storage storage) {
		LOG.fine("Starting collection of drones data.");

		String data = apiConnection.getAllDrones();
		if (data == null || data.isEmpty()) {
			LOG.warning("No data received for drones.");
		} else {
			LOG.info("Received data for drones, beginning processing.");
			jsonProcessor.processDrones(data, storage);
		}
	}

	/**
	 * Collects drone type data from an API and processes it for storage. This
	 * method first retrieves drone type data as a JSON string using the API
	 * connection's `getDroneTypes` method. It then processes this data by calling
	 * `processDroneTypes` of the jsonProcessor, adding the processed drone types to
	 * the provided storage.
	 *
	 * @param storage The storage instance where processed drone types are to be
	 *                stored.
	 */
	private void collectDroneTypes(Storage storage) {
		LOG.fine("Starting to collect drone types.");

		String data = apiConnection.getDroneTypes();
		if (data == null || data.isEmpty()) {
			LOG.warning("No drone types data received.");
		} else {
			LOG.info("Received data for drone types, beginning processing.");
			jsonProcessor.processDroneTypes(data, storage);
		}
	}

	/**
	 * Collects drone dynamics data from an API and processes it for storage. This
	 * method retrieves drone dynamics data as a JSON string using the API
	 * connection's `getDroneDynamics` method. It then processes this data by
	 * calling `processDroneDynamics` of the jsonProcessor, adding the processed
	 * drone dynamics to the provided storage.
	 *
	 * @param storage The storage instance where processed drone dynamics are to be
	 *                stored.
	 */
	private void collectDroneDynamics(Storage storage) {
		LOG.fine("Starting to collect drone dynamics.");

		String data = apiConnection.getDroneDynamics();
		if (data == null || data.isEmpty()) {
			LOG.warning("No drone dynamics data received.");
		} else {
			LOG.info("Received data for drone dynamics, beginning processing.");
			jsonProcessor.processDroneDynamics(data, storage);
		}
	}

	/**
	 * Updates the drone dynamics data in the provided storage by fetching new data
	 * from an API. This method constructs a URL using predefined values for the
	 * base URL and the current offset, then fetches drone dynamics data as a JSON
	 * string from this URL. It processes the fetched data by calling
	 * `processDroneDynamics` of the jsonProcessor, and then updates the offset for
	 * the next data fetch.
	 *
	 * @param storage The storage instance where the updated drone dynamics data are
	 *                to be stored. The method assumes that the storage is already
	 *                initialized and ready to store or update data.
	 */
	private void updateDroneDynamics(Storage storage) {
		LOG.fine("Preparing to update drone dynamics.");

		String url = urlForDronedynamics + offsetForUpdatingDronedynamics;
		LOG.finer("Fetching data from URL: " + url);

		String data = apiConnection.fetchDataFromApi2(url);
		if (data == null || data.isEmpty()) {
			LOG.warning("No data received from API for URL: " + url);
		} else {
			LOG.info("Received data for drone dynamics, beginning processing.");
			jsonProcessor.processDroneDynamics(data, storage);
		}

		offsetForUpdatingDronedynamics += 25;
		LOG.fine("Updated offset for drone dynamics: " + offsetForUpdatingDronedynamics);
	}

	/**
	 * Updates the drone-related data in the provided storage using multithreading.
	 * This method creates and starts three separate threads to concurrently collect
	 * and update different sets of drone-related information. One thread collects
	 * general drone data, another collects drone type data, and the third updates
	 * drone dynamics data in the storage.
	 *
	 * Each thread is responsible for fetching data from an API and processing it
	 * for storage. The storage instance provided should be initialized and capable
	 * of storing the respective drone data types. The method ensures that all
	 * threads complete their execution before concluding the update process.
	 *
	 * Note: The method assumes that the functions called within the threads are
	 * thread-safe and can handle concurrent execution without data integrity
	 * issues.
	 *
	 * @param storage The storage instance where all collected and updated drone
	 *                data are to be stored.
	 */
	public void updateData() {
		LOG.info("Starting the update of drone data.");

		Storage storage = new Storage();

		Thread droneDataThread = new Thread(() -> {
			LOG.fine("Updating basic drone data.");
			collectDrones(storage);
		});

		Thread droneTypesThread = new Thread(() -> {
			LOG.fine("Updating drone types data.");
			collectDroneTypes(storage);
		});

		Thread droneDynamicsThread = new Thread(() -> {
			LOG.fine("Updating drone dynamics data.");
			updateDroneDynamics(storage);
		});

		// Start all threads
		droneDataThread.start();
		droneTypesThread.start();
		droneDynamicsThread.start();

		try {
			// Wait for all threads to complete
			droneDataThread.join();
			droneTypesThread.join();
			droneDynamicsThread.join();
		} catch (InterruptedException e) {
			LOG.severe("Thread execution was interrupted: " + e.getMessage());
			Thread.currentThread().interrupt(); // Preserve interruption status
		}

		LOG.info("Data update process completed.");
	}

}
