package de.frauas.droneProject.group31.DroneStructure;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import de.frauas.droneProject.group31.BackendFunctions.DroneStorageInterface;
import de.frauas.droneProject.group31.BackendFunctions.TableData;

/**
 * The Storage class represents a storage system for managing drones, drone
 * types, and drone dynamics.
 * 
 * @author Annika Schäffler
 * @version 1.0
 * @since 14.12.2023
 */
public class Storage implements DroneStorageInterface {
	private ConcurrentHashMap<Integer, Drone> drones = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Integer, DroneType> droneTypes = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Integer, DroneDynamic> droneDynamics = new ConcurrentHashMap<>();

	public static ArrayList<Storage> storages = new ArrayList<Storage>();
	
	private final static Logger LOG = Logger.getLogger(Storage.class.getName()); // Add Logger

	public Storage() {
		LOG.config("Creating a new storage instance and adding it to the list of storages.");
		storages.add(this);
	}

	/**
	 * Adds a drone to the storage.
	 *
	 * @param drone The drone to be added.
	 */
	public void addDrone(Drone drone) {
		LOG.fine("Adding drone to storage. Drone ID: {drone.getId()}");
		int key = drone.getId();
		drones.put(key, drone);
	}

	/**
	 * Adds a drone type to the storage.
	 *
	 * @param droneType The drone type to be added.
	 */
	public void addDroneType(DroneType droneType) {
		LOG.fine("Adding drone type to storage. Drone Type ID: {droneType.getId()}");
		int key = droneType.getId();
		droneTypes.put(key, droneType);
	}

	/**
	 * Adds a drone dynamics to the storage.
	 *
	 * @param droneDynamic The drone dynamics to be added.
	 */
	public void addDroneDynamic(DroneDynamic droneDynamic) {
		Drone drone = droneDynamic.getDrone();
		int key = drone.getId();
		LOG.fine("Adding drone dynamics to storage. Drone ID: {drone.getId()}, Dynamic ID: {droneDynamic.getId()}");
		droneDynamics.put(key, droneDynamic);
	}

	/**
	 * @return the drones
	 */
	public ConcurrentHashMap<Integer, Drone> getDrones() {
		LOG.finest("Retrieving drones from storage.");
		return drones;
	}

	/**
	 * @return the droneTypes
	 */
	public ConcurrentHashMap<Integer, DroneType> getDroneTypes() {
		LOG.finest("Retrieving drone types from storage.");
		return droneTypes;
	}

	/**
	 * @return the droneDynamics
	 */
	public ConcurrentHashMap<Integer, DroneDynamic> getDroneDynamics() {
		LOG.finest("Retrieving drone dynamics from storage.");
		return droneDynamics;
	}

	/**
	 * Get a single Dynamic from the Storage by the id
	 * 
	 * @param id
	 * @return the droneDynamic
	 */
	public DroneDynamic getASingelDroneDynamic(int id) {
		LOG.finest("Retrieving a single drone dynamic from storage. Dynamic ID: {id}");
		return droneDynamics.get(id);

	}

}
