package domain;
/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Concrete class of abstract ServiceType. Calculates a service fee
 * based on a unit number multiplied by a rate. 
 */
public class UnitAndRateService extends ServiceType {
	
	private int unit;
	private double rate;
	
	public UnitAndRateService(String description, int unit, double rate) {
		super(description);
		this.setUnit(unit);
		this.setRate(rate);
	}

	@Override
	protected double calculateChargeForServiceType() {
		return getUnit() * getRate();
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return super.toString() + "[unit=" + unit + ", rate=" + rate + "]";
	}
	
	

}
