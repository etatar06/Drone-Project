package de.frauas.droneProject.group31.BackendFunctions;

import java.util.logging.Logger;
import org.json.*;
import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * Processes various JSON data related to drones and their dynamics. This class
 * is responsible for parsing JSON strings and converting them into relevant
 * data structures for drones, drone types, and drone dynamics. It uses methods
 * to process JSON data obtained from an API, and then stores or updates this
 * data in a provided storage system.
 * 
 * The class is designed to handle complex JSON structures, ensuring that all
 * relevant data is extracted accurately and efficiently. It also manages the
 * construction of URL requests for fetching JSON objects from specific
 * endpoints.
 * 
 * <p>
 * Usage of this class involves providing JSON strings or objects to its
 * methods, which then return the processed data in an appropriate format. The
 * class works closely with the ApiConnection to fetch data as needed.
 * </p>
 * 
 * @author Tim Bornemann
 * @version 1.0
 * @since 03.12.2023
 */
public class JsonProcessor {

	ApiConnection apiConnection = new ApiConnection();
	private final static Logger LOG = Logger.getLogger(JsonProcessor.class.getName());

	/**
	 * Processes a JSON string containing an array of drone types and adds them to
	 * the provided storage. This method parses the JSON string to extract drone
	 * type information and constructs DroneType objects using the
	 * `processSingleDroneType` method. Each constructed DroneType object is then added to
	 * the provided storage. It also handles pagination by returning the next page's
	 * URL if available.
	 *
	 * @param jsonDataAsString The JSON string representing an array of drone types.
	 * @param storage      The storage instance where processed drone types are to
	 *                     be added.
	 * @return The URL of the next set of drone types to be processed, or null if
	 *         there are no more drone types.
	 */
	public String processDroneTypes(String jsonDataAsString, Storage storage) {
		LOG.fine("Starting processSingleDroneTypes with JSON input: " + jsonDataAsString);

		JSONObject droneTypeDataAsJson = new JSONObject(jsonDataAsString);
		JSONArray arrayOfDroneTypes = droneTypeDataAsJson.getJSONArray("results");

		if (arrayOfDroneTypes.length() == 0) {
			LOG.warning("No drone types found in the provided JSON.");
		}

		try {
			for (Object singleDroneTypeObject : arrayOfDroneTypes) {
				JSONObject singleDroneTypeAsJson = (JSONObject) singleDroneTypeObject;
				storage.addDroneType(processSingleDroneType(singleDroneTypeAsJson));
				LOG.info("Drone type processed: " + singleDroneTypeAsJson.toString());
			}
		} catch (Exception e) {
			LOG.severe("Error processing drone types: " + e.getMessage());

			return null;
		}
		String nextUrlLink = droneTypeDataAsJson.optString("next", null);
		if (nextUrlLink == null) {
			LOG.config("No more drone types to process.");
		} else {
			LOG.config("Next set of drone types available.");
		}

		return nextUrlLink;
	}

	/**
	 * Processes a JSONObject representing a drone type and constructs a DroneType
	 * object. This method extracts relevant drone information such as the ID,
	 * manufacturer, type, weight, maximum speed, battery capacity, control range,
	 * and maximum carriage capacity from the provided JSON object. It then
	 * constructs and returns a DroneType object with these values.
	 *
	 * @param droneTypeAsJson The JSON object containing the drone type information.
	 * @return A DroneType object populated with the data from the provided JSON
	 *         object. Returns null if there is an issue in processing the JSON
	 *         data, such as missing or invalid fields.
	 */
	public DroneType processSingleDroneType(JSONObject droneTypeAsJson) {
		LOG.fine("Processing drone type: " + droneTypeAsJson.toString());

		try {
			int id = droneTypeAsJson.getInt("id");
			String manufacturer = droneTypeAsJson.getString("manufacturer");
			String typename = droneTypeAsJson.getString("typename");
			double weight = droneTypeAsJson.getDouble("weight");
			double maxSpeed = droneTypeAsJson.getDouble("max_speed");
			double batteryCapacity = droneTypeAsJson.getDouble("battery_capacity");
			double controlRange = droneTypeAsJson.getDouble("control_range");
			double maxCarriage = droneTypeAsJson.getDouble("max_carriage");

			DroneType droneType = new DroneType(id, manufacturer, typename, weight, maxSpeed, batteryCapacity,
					controlRange, maxCarriage);

			LOG.info("Successfully processed drone type with ID: " + id);

			return droneType;
		} catch (JSONException e) {
			LOG.severe("Error processing drone type: " + e.getMessage());

			return null;
		}
	}

	/**
	 * Processes a JSON string containing an array of drones and adds them to the
	 * provided storage. This method parses the JSON string to extract drone
	 * information and constructs Drone objects using the `processDrone` method.
	 * Each constructed Drone object is then added to the provided storage. It also
	 * handles pagination by returning the next page's URL if available.
	 *
	 * @param jsonDataAsString The JSON string representing an array of drones.
	 * @param storage      The storage instance where processed drones are to be
	 *                     added.
	 * @return The URL of the next set of drones to be processed, or null if there
	 *         are no more drones.
	 */
	public String processDrones(String jsonDataAsString, Storage storage) {
		LOG.fine("Starting processDrones with JSON input: " + jsonDataAsString);

		JSONObject droneDataAsJson = new JSONObject(jsonDataAsString);
		JSONArray arrayOfDrones = droneDataAsJson.getJSONArray("results");

		if (arrayOfDrones.length() == 0) {
			LOG.warning("No drones found in the provided JSON.");
		}

		try {
			for (Object singleDroneObject : arrayOfDrones) {
				JSONObject singleDroneAsJson = (JSONObject) singleDroneObject;
				storage.addDrone(processDrone(singleDroneAsJson));
				LOG.info("Drone processed: " + singleDroneAsJson.toString());
			}
		} catch (Exception e) {
			LOG.severe("Error processing drones: " + e.getMessage());
			return null;
		}

		String nextUrlLink = droneDataAsJson.optString("next", null);
		if (nextUrlLink == null) {
			LOG.config("No more drones to process.");
		} else {
			LOG.config("Next set of drones available.");
		}

		return nextUrlLink;
	}

	/**
	 * Processes a JSONObject representing a drone and constructs a Drone object.
	 * This method extracts drone-specific information such as its ID, drone type,
	 * creation date, serial number, carriage weight, and carriage type from the
	 * provided JSON object. The drone type is processed separately by calling the
	 * `processSingleDroneType` method. A new Drone object is then constructed with these
	 * values.
	 *
	 * @param droneAsJson The JSON object containing the drone's information.
	 * @return A Drone object populated with the data from the provided JSON object.
	 *         Returns null if there is an issue in processing the JSON data, such
	 *         as missing or invalid fields.
	 */
	public Drone processDrone(JSONObject droneAsJson) {
		LOG.fine("Processing drone: " + droneAsJson.toString());

		try {
			int id = droneAsJson.getInt("id");
			DroneType dronetype = processSingleDroneType(getSingleJsonObjectFromUrl(droneAsJson.getString("dronetype")));
			String created = droneAsJson.getString("created");
			String serialnumber = droneAsJson.getString("serialnumber");
			double carriageWeight = droneAsJson.getDouble("carriage_weight");
			String carriageType = droneAsJson.getString("carriage_type");

			Drone drone = new Drone(id, dronetype, created, serialnumber, carriageWeight, carriageType);

			LOG.info("Successfully processed drone with ID: " + id);
			
			return drone;			
		} catch (JSONException e) {
			LOG.severe("Error processing drone: " + e.getMessage());
			
			return null;
		}
	}

	/**
	 * Processes a JSON string containing an array of drone dynamic data and adds
	 * them to the provided storage. This method parses the JSON string to extract
	 * dynamic information about each drone and constructs DroneDynamic objects
	 * using the `processDroneDynamic` method. Each constructed DroneDynamic object
	 * is then added to the provided storage. It also handles pagination by
	 * returning the next page's URL if available.
	 *
	 * @param jsonDataAsString The JSON string representing an array of drone dynamics.
	 * @param storage      The storage instance where processed drone dynamics are
	 *                     to be added.
	 * @return The URL of the next set of drone dynamics to be processed, or null if
	 *         there are no more drone dynamics.
	 */
	public String processDroneDynamics(String jsonDataAsString, Storage storage) {
		LOG.fine("Starting processDroneDynamics with JSON input: " + jsonDataAsString);

		JSONObject droneDynamicDataAsJson = new JSONObject(jsonDataAsString);
		JSONArray arrayOfDroneDynamics = droneDynamicDataAsJson.getJSONArray("results");

		if (arrayOfDroneDynamics.length() == 0) {
			LOG.warning("No drone dynamics found in the provided JSON.");
		}

		try {
			for (Object singleDroneDynamicObject : arrayOfDroneDynamics) {
				JSONObject singleDroneDynamicAsJson = (JSONObject) singleDroneDynamicObject;
				storage.addDroneDynamic(processSingleDroneDynamic(singleDroneDynamicAsJson));
				LOG.info("Drone dynamic processed: " + singleDroneDynamicAsJson.toString());
			}
		} catch (Exception e) {
			LOG.severe("Error processing drone dynamics: " + e.getMessage());
			
			return null;
		}
		String nextUrlLink = droneDynamicDataAsJson.optString("next", null);
		if (nextUrlLink == null) {
			LOG.config("No more drone dynamics to process.");
		} else {
			LOG.config("Next set of drone dynamics available.");
		}

		return nextUrlLink;
	}

	/**
	 * Processes a JSONObject representing a drone's dynamic data and constructs a
	 * DroneDynamic object. This method extracts dynamic information about a drone,
	 * such as speed, alignment (roll, pitch, yaw), GPS coordinates (longitude,
	 * latitude), battery status, last seen timestamp, and status. It also retrieves
	 * the associated drone object by processing a drone JSON object obtained from a
	 * URL. A new DroneDynamic object is then constructed with these values.
	 *
	 * @param droneDynamicJson The JSON object containing the dynamic data of a
	 *                         drone.
	 * @return A DroneDynamic object populated with the data from the provided JSON
	 *         object. Returns null if there is an issue in processing the JSON
	 *         data, such as missing or invalid fields.
	 */
	public DroneDynamic processSingleDroneDynamic(JSONObject droneDynamicJson) {
		LOG.fine("Processing drone dynamic: " + droneDynamicJson.toString());

		try {
			Drone drone = processDrone(getSingleJsonObjectFromUrl(droneDynamicJson.getString("drone")));
			String timestamp = droneDynamicJson.getString("timestamp");
			double speed = droneDynamicJson.getDouble("speed");
			double align_roll = droneDynamicJson.getDouble("align_roll");
			double align_pitch = droneDynamicJson.getDouble("align_pitch");
			double align_yaw = droneDynamicJson.getDouble("align_yaw");
			double longitude = droneDynamicJson.getDouble("longitude");
			double latitude = droneDynamicJson.getDouble("latitude");
			double battery_status = droneDynamicJson.getDouble("battery_status");
			String last_seen = droneDynamicJson.getString("last_seen");
			String status = droneDynamicJson.getString("status");

			DroneDynamic droneDynamic = new DroneDynamic(drone, timestamp, speed, align_roll, align_pitch, align_yaw,
					longitude, latitude, battery_status, last_seen, status);

			LOG.info("Successfully processed drone dynamic for drone ID: " + drone.getId());
			
			return droneDynamic;
		} catch (JSONException e) {
			LOG.severe("Error processing drone dynamic: " + e.getMessage());
			
			return null;
		}
	}

	/**
	 * Fetches a JSON object from a specified URL. This method ensures the URL is
	 * correctly formatted to request JSON data by appending '?format=json' if
	 * necessary. It then makes an API call to fetch the data from the URL and
	 * constructs a JSONObject from the response.
	 *
	 * @param url The URL from which to fetch the JSON data. The method modifies the
	 *            URL to ensure it requests data in JSON format.
	 * @return A JSONObject containing the data fetched from the URL. Returns null
	 *         if there is an error in fetching or parsing the data.
	 */
	private JSONObject getSingleJsonObjectFromUrl(String url) {
		// Ensure URL ends with '?format=json'
		if (!url.endsWith("?format=json")) {
			if (url.endsWith("/")) {
				url += "?format=json";
			} else {
				url += "/?format=json";
			}
		}
		
		LOG.fine("Fetching JSON object from URL: " + url);

		try {
			String data = apiConnection.fetchDataFromApi2(url);
			LOG.info("Successfully fetched data from URL: " + url);
			
			return new JSONObject(data);
		} catch (Exception e) {
			LOG.severe("Error fetching data from URL: " + url + ", Error: " + e.getMessage());
			
			return null;
		}
	}

}
