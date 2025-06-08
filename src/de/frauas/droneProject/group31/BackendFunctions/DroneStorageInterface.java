package de.frauas.droneProject.group31.BackendFunctions;

import java.util.concurrent.ConcurrentHashMap;
import de.frauas.droneProject.group31.DroneStructure.*;

/**
 * 
 * @author Emre Tatar
 *
 */
public interface DroneStorageInterface {

    void addDrone(Drone drone);

    void addDroneType(DroneType droneType);

    void addDroneDynamic(DroneDynamic droneDynamic);

    ConcurrentHashMap<Integer, Drone> getDrones();

    ConcurrentHashMap<Integer, DroneType> getDroneTypes();

    ConcurrentHashMap<Integer, DroneDynamic> getDroneDynamics();
}