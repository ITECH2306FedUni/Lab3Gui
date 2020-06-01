package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * Concrete class of abstract ServiceType. Calculates a service fee
 * based on a base plus an extra value.
 */
public class BaseAndExtraService extends ServiceType {

	private double base;
	private double extra;
	
	public BaseAndExtraService(String description, double base, double extra) {
		super(description);
		this.setBase(base);
		this.setExtra(extra);
	}

	@Override
	protected double calculateChargeForServiceType() {
		return getBase() + getExtra();
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return super.toString() + "[base=" + base + ", extra=" + extra + "]";
	}

}
