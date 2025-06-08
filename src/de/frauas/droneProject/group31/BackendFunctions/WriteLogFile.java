package de.frauas.droneProject.group31.BackendFunctions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The WriteLogFile class sets up a logging mechanism using Java's Logger and
 * FileHandler to write log messages to a file named "Log.txt". It formats log
 * messages to include the log level, timestamp, and message content. Messages
 * with a log level of WARNING or higher are marked with "ATTENTION". The log
 * file is configured to include log messages from the root logger and is
 * formatted using a custom Formatter. This class is designed to provide a
 * simple and customizable logging setup for a Java application.
 * 
 * @author Annika Schäffler
 * @version 1.0
 * @since 21.01.2024
 */

public class WriteLogFile {

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Constructor for the WriteLogFile class. It initializes a logging mechanism
	 * with a custom FileHandler and Formatter. The log messages are written to a
	 * file named "Log.txt". Errors during setup are logged using the global logger.
	 */

	public WriteLogFile() {
		Logger root = Logger.getLogger(""); // Root Logger, represents the top-level logger above loggers from different
											// levels (de, frauas, droneProject, group31)
		FileHandler txt = null;
		try { // Create a FileHandler that writes log messages to files with the name
				// "Log.txt", handle error messages.
			txt = new FileHandler("Log.txt");
			root.setLevel(Level.INFO);
			txt.setFormatter(new Formatter() { // Set a custom Formatter to format log messages with additional
												// information.
				@Override
				public String format(LogRecord record) {
					String ret = "";
					StringBuilder string = new StringBuilder();

					if (record.getLevel().intValue() >= Level.WARNING.intValue()) { // Mark messages with log level
																					// WARNING or higher with
																					// "ATTENTION".
						string.append("ATTENTION: ");
					}
					string.append(record.getLevel()); // Append log level, timestamp, and message content to the log
														// entry.
					string.append(" ");
					SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy HH:mm");
					Date date = new Date(record.getMillis());
					string.append(dateformat.format(date));
					string.append(this.formatMessage(record));
					string.append("\n");

					ret = string.toString();

					return ret;
				}

			});
			root.addHandler(txt); // Add the FileHandler to the root logger to start logging to the specified
									// file.
		} catch (SecurityException | IOException e) {
			log.log(Level.SEVERE, "Error setting up logging", e); // Log errors during setup using the global logger and
																	// print the stack trace.
			e.printStackTrace();
		}

	}

	public static void main(String[] args) { // Test

		WriteLogFile lm = new WriteLogFile(); // Instanz der WriteLogFile Klasse
		lm.doSomething();
	}

	private void doSomething() { // Test
		log.setLevel(Level.ALL);
		log.severe("ERROR SOMETHING TERRIBLE HAPPENED");
		log.warning("MAybe something happened, i´ll try sth different");
		log.info("running smooth");
		log.finest("I´m feeling great, thx");
	}
}
