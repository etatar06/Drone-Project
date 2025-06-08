package de.frauas.droneProject.group31.BackendFunctions;													

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ApiConnection class is responsible for interacting with a remote API
 * to retrieve information about drones and related entities.
 * @author Erdem BALCI 
 */
public class ApiConnection {
    private static final Logger logger = Logger.getLogger(ApiConnection.class.getName());
    private static final String USER_AGENT = "";
    private static final String BASE_URL = "https://dronesim.facets-labs.com/api/";
    private static final String TOKEN = "Token 7450bf00966849edc96287d7ca0894602e9b3b30";

    /**
     * Retrieves information about all drones from the API.
     *
     * @return A JSON string containing information about all drones.
     */
    public String getAllDrones() {
        logger.log(Level.INFO, "Fetching information about all drones.");
        return fetchDataFromApi("drones/?format=json&limit=25&offset=0");
    }

    /**
     * Retrieves information about a specific drone based on its ID.
     *
     * @param droneId The ID of the drone to retrieve information for.
     * @return A JSON string containing information about the specified drone.
     */
    public String getDroneById(int droneId) {
        logger.log(Level.INFO, "Fetching information about drone with ID: {0}", droneId);
        return fetchDataFromApi("drones/" + droneId + "/?format=json");
    }

    /**
     * Retrieves information about all drone types from the API.
     *
     * @return A JSON string containing information about all drone types.
     */
    public String getDroneTypes() {
        logger.log(Level.INFO, "Fetching information about all drone types.");
        return fetchDataFromApi("dronetypes/?format=json&limit=25&offset=0");
    }

    /**
     * Retrieves information about a specific drone type based on its ID.
     *
     * @param droneId The ID of the drone type to retrieve information for.
     * @return A JSON string containing information about the specified drone type.
     */
    public String getDroneTypesId(int droneId) {
        logger.log(Level.INFO, "Fetching information about drone type with ID: {0}", droneId);
        return fetchDataFromApi("dronetypes/" + droneId + "/?format=json");
    }

    /**
     * Retrieves information about all drone dynamics from the API.
     *
     * @return A JSON string containing information about all drone dynamics.
     */
    public String getDroneDynamics() {
        logger.log(Level.INFO, "Fetching information about all drone dynamics.");
        return fetchDataFromApi("dronedynamics/?format=json&limit=25&offset=0");
    }

    /**
     * Retrieves information about a specific drone dynamics based on its ID.
     *
     * @param droneId The ID of the drone dynamics to retrieve information for.
     * @return A JSON string containing information about the specified drone dynamics.
     */
    public String getDroneDynamicsId(int droneId) {
        logger.log(Level.INFO, "Fetching information about drone dynamics with ID: {0}", droneId);
        return fetchDataFromApi("dronedynamics/" + droneId + "/?format=json");
    }

    /**
     * Private method to fetch data from the API for a given endpoint.
     *
     * @param endpoint The API endpoint to fetch data from.
     * @return A JSON string containing the data retrieved from the API.
     */
    private String fetchDataFromApi(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();											   
            logger.log(Level.INFO, "API Response Code: {0}", responseCode);


            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                return response.toString();

            } else {
                throw new ApiConnectionError(responseCode, connection.getResponseMessage());
            }
        } catch (IOException | ApiConnectionError e) {
            logger.log(Level.SEVERE, "Error fetching data from API: {0}", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
	
	/**
     * Another method to fetch data from the API for a given URL.
     *
     * @param endpoint The API endpoint to fetch data from.
     * @return A JSON string containing the data retrieved from the API.
     */
    public String fetchDataFromApi2(String endpoint) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();

            logger.log(Level.INFO, "API Response Code (fetchDataFromApi2): {0}", responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                return response.toString();

            } else {
                throw new ApiConnectionError(responseCode, connection.getResponseMessage());
            }
        } catch (IOException | ApiConnectionError e) {
            logger.log(Level.SEVERE, "Error fetching data from API (fetchDataFromApi2): {0}", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
	
}