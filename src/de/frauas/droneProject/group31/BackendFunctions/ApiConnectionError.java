package de.frauas.droneProject.group31.BackendFunctions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Custom exception class for API connection errors.
 * This exception is thrown when there is an issue connecting to the API.
 * @author Erdem BALCI 
 */
public class ApiConnectionError extends Exception {

    private static final Logger logger = Logger.getLogger(ApiConnectionError.class.getName());

    private final int statusCode;

    /**
     * Constructs an API connection error with the specified status code and message.
     *
     * @param statusCode The HTTP status code indicating the error.
     * @param message    The error message describing the issue.
     */
    public ApiConnectionError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        logger.log(Level.SEVERE, "API Connection Error - Status Code: {0}, Message: {1}", new Object[]{statusCode, message});
    }

    /**
     * Gets the HTTP status code associated with the API connection error.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
}