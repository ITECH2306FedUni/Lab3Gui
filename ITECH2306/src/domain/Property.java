package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.1
 * Abstract class that all types of properties inherit from
 */

public abstract class Property {
	private String description;
	private String location;
	private double area;
	private double siteValue;
	private double capitalImprovedValue;
	private double capitalImprovedRate;
	private double netAnnualValue;
	private String valuationDate;
	private RatePayer owner;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
	private static final int VALUESCALE = 2;
	protected static final String NOT_AVAILABLE = "Not Available";
	protected static final int MAX_CIV = 50000000;
	protected static final int MIN_CIV = 100;


	public Property() {
		// We are explicit about our String and date defaults but leave the numbers to be filled with Java default values
		this.setDescription(NOT_AVAILABLE);
		this.setLocation(NOT_AVAILABLE);
		this.setValuationDate(dateToString(LocalDate.now()));
		// Provide a default owner 
		this.setOwner(new RatePayer());	
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;	
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	/**
	 * @return the site value of this property. 
	 */
	public double getSiteValue() {
		return siteValue;
	}
	/*
	 * Assign the passed in site value to the 
	 * attribute on this property for that purpose
	 */
	public void setSiteValue(double siteValue) {
		this.siteValue = siteValue;
		if(siteValue > getCapitalImprovedValue())
			setCapitalImprovedValue(siteValue);
	}
	/**
	 * @return the capital improved value of this property. Should this be calculated
	 * from an addition of the siteValue and another attribute for the capital improvement?
	 * Well maybe but the valuers don't work that way; they provide the grossed up amount.
	 */
	public double getCapitalImprovedValue() {
		return capitalImprovedValue;
	}
	
	/*
	 * Assign the passed in capital improved value to the 
	 * attribute on this property for that purpose
	 */
	public void setCapitalImprovedValue(double capitalImprovedValue) {
		// Leave at default if we are beyond the bounds
		if (capitalImprovedValue >= MIN_CIV && capitalImprovedValue <= MAX_CIV)
			if(capitalImprovedValue >= getSiteValue())
				this.capitalImprovedValue = capitalImprovedValue;
	}

	public double getNetAnnualValue() {
		return netAnnualValue;
	}

	public void setNetAnnualValue(double netAnnualValue) {
		this.netAnnualValue = netAnnualValue;
	}

	public String getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(String date) {
		this.valuationDate = date;
	}
	
	private String dateToString(LocalDate date) {
		return date.format(FORMATTER);
		
	}
	
	public double getCapitalImprovedRate() {
		return capitalImprovedRate;
	}

	public void setCapitalImprovedRate(double capitalImprovedRate) {
		this.capitalImprovedRate = capitalImprovedRate;
	}

	public RatePayer getOwner() {
		return owner;
	}

	public void setOwner(RatePayer owner) {
		this.owner = owner;
	}
	

	public double calculateRates() {
		// So, for accurate rounding purposes we use a BigDecimal
		return (BigDecimal.valueOf(													  //We want the value of the calculation as a BigDecimal
				(((getCapitalImprovedValue() * getCapitalImprovedRate())  			  //We multiply the CIV multiplied by the CIV rate
									+  calculateExtraServices()) 		  			  //add the calculated extra services
									* (getOwner().isCharity() ? 					  //and this is all multiplied by either 
									   1 - getOwner().getCharityDiscountPercentage() ://1-the charity discount (if applicable)
										   1))										  //or 1.
									).setScale(VALUESCALE, BigDecimal.ROUND_HALF_UP)) //the BigDecimal we use is set to being rounded up to two decimal places
									.doubleValue();									  //and then we obtain the double value of the BigDecimal and it is returned
	}
	
	/**
	 * Method to setup the extra services on the applicable sub-class property.
	 */
	public abstract void setUpExtraServices();
	
	/**
	 * @return Return the total for all the extra services from the applicable sub-class property.
	 */
	public abstract double calculateExtraServices();

	/**
	 * @return a String of the description, CIV and CIV Rate for this property.
	 */
	@Override
	public String toString() {
		return "Property [description=" + description + ", capitalImprovedValue=" + capitalImprovedValue
				+ ", capitalImprovedRate=" + capitalImprovedRate + "] \n";
	}
	
	

}
