package de.frauas.droneProject.group31.DroneStructure;

/**
 * The DroneDynamic class represents dynamic information about a drone,
 * including its current state and attributes. It contains methods to access and
 * modify various properties such as drone details, timestamp, speed, alignment,
 * location, battery status, last seen and overall status.
 * 
 * @author Annika Schäffler
 * @version 1.0
 * @since 21.01.2024
 */
public class DroneDynamic {
	private Drone drone;
	private String timestamp;
	private double speed;
	private double alignRoll;
	private double alignPitch;
	private double alignYaw;
	private double longitude;
	private double latitude;
	private double batteryStatus;
	private String lastSeen;
	private String status;

	/*
	 * empty Constructor
	 */
	public DroneDynamic() {

	}

	/*
	 * complete Constructor
	 */
	public DroneDynamic(Drone drone, String timestamp, double speed, double alignRoll, double alignPitch,
			double alignYaw, double longitude, double latitude, double batteryStatus, String lastSeen, String status) {
		this.drone = drone;
		this.timestamp = timestamp;
		this.speed = speed;
		this.alignRoll = alignRoll;
		this.alignPitch = alignPitch;
		this.alignYaw = alignYaw;
		this.longitude = longitude;
		this.latitude = latitude;
		this.batteryStatus = batteryStatus;
		this.lastSeen = lastSeen;
		this.status = status;
	}

	/**
	 * @return the drone
	 */
	public Drone getDrone() {
		return drone;
	}

	/**
	 * @param drone the drone to set
	 */
	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the alignRoll
	 */
	public double getAlignRoll() {
		return alignRoll;
	}

	/**
	 * @param alignRoll the alignRoll to set
	 */
	public void setAlignRoll(double alignRoll) {
		this.alignRoll = alignRoll;
	}

	/**
	 * @return the alignPitch
	 */
	public double getAlignPitch() {
		return alignPitch;
	}

	/**
	 * @param alignPitch the alignPitch to set
	 */
	public void setAlignPitch(double alignPitch) {
		this.alignPitch = alignPitch;
	}

	/**
	 * @return the alignYaw
	 */
	public double getAlignYaw() {
		return alignYaw;
	}

	/**
	 * @param alignYaw the alignYaw to set
	 */
	public void setAlignYaw(double alignYaw) {
		this.alignYaw = alignYaw;
	}

	/**
	 * @return the batteryStatus
	 */
	public double getBatteryStatus() {
		return batteryStatus;
	}

	/**
	 * @param batteryStatus the batteryStatus to set
	 */
	public void setBatteryStatus(double batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	/**
	 * @return the lastSeen
	 */
	public String getLastSeen() {
		return lastSeen;
	}

	/**
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
