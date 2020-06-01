package domain;

/**
 * @author Takeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.1.
 * Concrete class of abstract Property. Currently, Industrial Waste Disposal and Fire Services Levy
 * are charged together with CIV value multiplied by a CIV rate.
 */

public class Industrial extends Property {

	private String hazardStatus;
	private boolean heavyVehicle;
	private final double CIV_RATE = 0.0059;
	private static final int INDUSTRIAL_WASTE_UNITS = 4; 
	private static final double INDUSTRIAL_WASTE_MANAGEMENT_FEES = 500;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType industrialWasteManagement;
	private ServiceType fireServicesLevy;

	
	public Industrial() {
		super();
		// We are explicit about our defaults for Strings
		this.setHazardStatus(NOT_AVAILABLE);
		// and we should default a status of false
		this.setHeavyVehicle(false);
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getHazardStatus() {
		return hazardStatus;
	}


	public void setHazardStatus(String hazardStatus) {
		this.hazardStatus = hazardStatus;
	}


	public boolean isHeavyVehicle() {
		return heavyVehicle;
	}


	public void setHeavyVehicle(boolean heavyVehicle) {
		this.heavyVehicle = heavyVehicle;
	}


	@Override
	public void setUpExtraServices() {
		// At this stage, this is perhaps more understandable but there may be better alternatives
		industrialWasteManagement = new UnitAndRateService("Industrial Waste Management",
				  INDUSTRIAL_WASTE_UNITS,
				  INDUSTRIAL_WASTE_MANAGEMENT_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();

	}

	@Override
	public String toString() {
		return  super.toString() + this.getClass().getSimpleName() + " [\n" + 
									industrialWasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}


}
