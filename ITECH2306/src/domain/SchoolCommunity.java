package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.1
 * Concrete class of abstract Property. Currently, Industrial Waste Disposal, a Fire Services Levy and
 * a Traffic Management Levy are charged together with CIV value multiplied by a CIV rate.
 */
public class SchoolCommunity extends Property{

	private String classification;
	private int category;
	private final double CIV_RATE = 0.0025;
	private static final int INDUSTRIAL_WASTE_UNITS = 2; 
	private static final double INDUSTRIAL_WASTE_MANAGEMENT_FEES = 500;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private static final double TRAFFIC_MANAGEMENT_BASE = 200;
	//This is not optimal. We should have a multi-element variable for the next three
	private static final double TRAFFIC_MANAGEMENT_SMALL = 60;
	private static final double TRAFFIC_MANAGEMENT_MEDIUM = 80;
	private static final double TRAFFIC_MANAGEMENT_LARGE = 100;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType industrialWasteManagement;
	private ServiceType fireServicesLevy;
	private ServiceType trafficManagement;
	
	public SchoolCommunity() {
		super();
		// We are explicit about our defaults for Strings
		this.setClassification(NOT_AVAILABLE);
		this.setCategory(1);
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	private double getExtraForCategory(int category) {
		switch(category)
		{
		case(1):
			return TRAFFIC_MANAGEMENT_SMALL;
		case(2):
			return TRAFFIC_MANAGEMENT_MEDIUM;
		case(3):
			return TRAFFIC_MANAGEMENT_LARGE;
		default:
			return TRAFFIC_MANAGEMENT_LARGE;
		}
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
		trafficManagement = new BaseAndExtraService("Traffic Management Levy",
				       								TRAFFIC_MANAGEMENT_BASE,
				       								getExtraForCategory(getCategory()));
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType() + 
				   trafficManagement.calculateChargeForServiceType();

	}
	@Override
	public String toString() {
		return  super.toString() + this.getClass().getSimpleName() + " [\n" + 
									industrialWasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n " +
									trafficManagement.toString() + " ]\n";
	}

}
