package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0
 * The owner of one or more Properties
 */
public class RatePayer {

	private String name;
	private String address;
	private String postcode;
	private String phone;
	private String type;
	private boolean charity;
	//Discount wouldn't necessarily be on RatePayer but for convenience at the moment we place it here.
	private double charityDiscountPercentage = 0.20;
	private static final String DUMMY_VALUE = "Dummy Value";
	
	public RatePayer() {
		this.setName(DUMMY_VALUE);
		this.setAddress(DUMMY_VALUE);
		this.setPostcode(DUMMY_VALUE);
		this.setPhone(DUMMY_VALUE);
		this.setType(DUMMY_VALUE);
		this.setCharity(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCharity() {
		return charity;
	}

	public void setCharity(boolean charity) {
		this.charity = charity;
	}

	
	public double getCharityDiscountPercentage() {
		return charityDiscountPercentage;
	}

	public void setCharityDiscountPercentage(double charityDiscountPercentage) {
		this.charityDiscountPercentage = charityDiscountPercentage;
	}

	@Override
	public String toString() {
		return "RatePayer [name=" + name + ", address=" + address + ", postcode=" + postcode + ", phone=" + phone
				+ ", type=" + type + ", charity=" + charity + ", charityDiscountPercentage=" + charityDiscountPercentage
				+ "]";
	}
	
	

}
