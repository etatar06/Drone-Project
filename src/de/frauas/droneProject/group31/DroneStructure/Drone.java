package de.frauas.droneProject.group31.DroneStructure;

/**
 * The Drone class represents a drone with specific attributes such as ID, type,
 * creation date, serial number, carriage weight, and carriage type.
 * 
 * @author Annika Schäffler
 * @version 1.0
 * @since 14.12.2023
 */
public class Drone {
	private int id;
	private DroneType droneType;
	private String created;
	private String serialnummer;
	private double carriageWeight;
	private String carriageType;

	/**
	 * empty Constructor
	 */
	public Drone() {

	}

	/**
	 * complete Constructor
	 */
	public Drone(int id, DroneType dronetype, String created, String serialnummer, double carriageWeight,
			String carriageType) {
		this.id = id;
		this.droneType = dronetype;
		this.created = created;
		this.serialnummer = serialnummer;
		this.carriageWeight = carriageWeight;
		this.carriageType = carriageType;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dronetype
	 */
	public DroneType getDronetype() {
		return droneType;
	}

	/**
	 * @param dronetype the dronetype to set
	 */
	public void setDronetype(DroneType dronetype) {
		this.droneType = dronetype;
	}

	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the serialnummer
	 */
	public String getSerialnummer() {
		return serialnummer;
	}

	/**
	 * @param serialnummer the serialnummer to set
	 */
	public void setSerialnummer(String serialnummer) {
		this.serialnummer = serialnummer;
	}

	/**
	 * @return the carriageWeight
	 */
	public double getCarriageWeight() {
		return carriageWeight;
	}

	/**
	 * @param carriageWeight the carriageWeight to set
	 */
	public void setCarriageWeight(double carriageWeight) {
		this.carriageWeight = carriageWeight;
	}

	/**
	 * @return the carriageType
	 */
	public String getCarriageType() {
		return carriageType;
	}

	/**
	 * @param carriageType the carriageType to set
	 */
	public void setCarriageType(String carriageType) {
		this.carriageType = carriageType;
	}

}
