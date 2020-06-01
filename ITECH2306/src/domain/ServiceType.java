package domain;
/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0.
 * Abstract class for all serviceTypes. Description of serviceType is held here
 * and the actual charges for each sub-class is determined by what is implemented
 * in calculateChargeForServiceType()
 */

public abstract class ServiceType {
	private String description;
	public ServiceType(String description) {
		this.setDescription(description);
	}
	
	protected abstract double calculateChargeForServiceType();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ServiceType [description=" + description + "]";
	}

}
