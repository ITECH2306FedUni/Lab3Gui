package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.1
 * Concrete class of abstract Property. Currently, Waste Management and
 * a Fire Services Levy are charged together with CIV value multiplied by a CIV rate.
 */

public class Commercial extends Property{
	private String businessName;
	private String aBN;
	private static final double CIV_RATE = 0.0059;
	private static final int WASTE_MANAGEMENT_UNITS = 2;
	private static final double WASTE_MANAGEMENT_FEES = 350.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType wasteManagement;
	private ServiceType fireServicesLevy;
	
	public Commercial() {
		super(); // We explicitly call the super constructor
		// We are explicit about our defaults for Strings
		this.setBusinessName(NOT_AVAILABLE);
		this.setABN("00 000 000 000");
		setCapitalImprovedRate(CIV_RATE);
		
	}

	
	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getABN() {
		return aBN;
	}


	public void setABN(String aBN) {
		this.aBN = aBN;
	}


	@Override
	public void setUpExtraServices() {
		// At this stage, this is perhaps more understandable but there may be better alternatives
		wasteManagement = new UnitAndRateService("Waste Management",
				  WASTE_MANAGEMENT_UNITS,
				  WASTE_MANAGEMENT_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}
	
	@Override
	public double calculateExtraServices() {
		return wasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return  super.toString() + this.getClass().getSimpleName() + " [\n" + 
									wasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}

}
