package de.frauas.droneProject.group31;

import java.awt.EventQueue;
import de.frauas.droneProject.group31.BackendFunctions.*;
import de.frauas.droneProject.group31.UI.*;

public class Main {

	public static void main(String[] args) {

		WriteLogFile logger = new WriteLogFile();		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage objLogin = new LoginPage();
					createNewAccount frame = new createNewAccount(objLogin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		DataCollector dataCollector = new DataCollector();
		dataCollector.initialCollect();

	}

}
