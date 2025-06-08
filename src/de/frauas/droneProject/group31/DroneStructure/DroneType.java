package de.frauas.droneProject.group31.DroneStructure;

   /**
 * The DroneType class represents a specific type of drone with various attributes.
 * It includes information such as ID, manufacturer, typename, weight, maximum speed,
 * battery capacity, control range, and maximum carriage capacity.
 * 
 * @author Emre Tatar
 */
public class DroneType {
	private int id;
	private String manufacturer;
	private String typename;
	private double weight;
	private double maximumSpeed;
	private double batteryCapacity;
	private double controlRange;
	private double maximumCarriage;
/**
     * Constructs a new DroneType with the specified parameters.
     *
     * @param id               The unique identifier for the drone type.
     * @param manufacturer     The manufacturer of the drone type.
     * @param typename         The name or type designation of the drone.
     * @param weight           The weight of the drone type.
     * @param maximumSpeed     The maximum speed the drone type can achieve.
     * @param batteryCapacity  The battery capacity of the drone type.
     * @param controlRange     The control range of the drone type.
     * @param maximumCarriage  The maximum weight the drone type can carry.
     */  
	public DroneType(int id, String manufacturer, String typename, double weight, double maximumSpeed,
			double batteryCapacity, double controlRange, double maximumCarriage) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.typename = typename;
		this.weight = weight;
		this.maximumSpeed = maximumSpeed;
		this.batteryCapacity = batteryCapacity;
		this.controlRange = controlRange;
		this.maximumCarriage = maximumCarriage;

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
 * @return the manufacturer
 */
public String getManufacturer() {
	return manufacturer;
}
/**
 * @param manufacturer the manufacturer to set
 */
public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
}
/**
 * @return the typename
 */
public String getTypename() {
	return typename;
}
/**
 * @param typename the typename to set
 */
public void setTypename(String typename) {
	this.typename = typename;
}
/**
 * @return the weight
 */
public double getWeight() {
	return weight;
}
/**
 * @param weight the weight to set
 */
public void setWeight(double weight) {
	this.weight = weight;
}
/**
 * @return the maximumSpeed
 */
public double getMaximumSpeed() {
	return maximumSpeed;
}
/**
 * @param maximumSpeed the maximumSpeed to set
 */
public void setMaximumSpeed(double maximumSpeed) {
	this.maximumSpeed = maximumSpeed;
}
/**
 * @return the batteryCapacity
 */
public double getBatteryCapacity() {
	return batteryCapacity;
}
/**
 * @param batteryCapacity the batteryCapacity to set
 */
public void setBatteryCapacity(double batteryCapacity) {
	this.batteryCapacity = batteryCapacity;
}
/**
 * @return the controlRange
 */
public double getControlRange() {
	return controlRange;
}
/**
 * @param controlRange the controlRange to set
 */
public void setControlRange(double controlRange) {
	this.controlRange = controlRange;
}
/**
 * @return the maximumCarriage
 */
public double getMaximumCarriage() {
	return maximumCarriage;
}
/**
 * @param maximumCarriage the maximumCarriage to set
 */
public void setMaximumCarriage(double maximumCarriage) {
	this.maximumCarriage = maximumCarriage;
}

  

}
