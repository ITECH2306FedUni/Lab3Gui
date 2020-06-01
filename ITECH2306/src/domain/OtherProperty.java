package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Concrete class of abstract Property. Currently a Fire Services Levy is charged
 *  together with CIV value multiplied by a CIV rate.
 */
public class OtherProperty extends Property {

	private String specialDescription;
	private static final double CIV_RATE = 0.0030;
	private static final double FIRE_SERVICES_BASE = 150;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private ServiceType fireServicesLevy;
	
	public OtherProperty() {
		super();// We explicitly call the super default constructor
		this.setSpecialDescription("None");
		setCapitalImprovedRate(CIV_RATE);
	}

	public String getSpecialDescription() {
		return specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	@Override
	public void setUpExtraServices() {
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
				FIRE_SERVICES_BASE,
				FIRE_SERVICES_PERCENT,
				getCapitalImprovedValue());
		
	}

	@Override
	public double calculateExtraServices() {
		return fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return super.toString() + "OtherProperty [\n" +
								  fireServicesLevy.toString() + "]\n ";	
	}

}
