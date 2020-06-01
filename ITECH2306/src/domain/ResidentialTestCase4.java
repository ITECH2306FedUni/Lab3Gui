package domain;

/**
 * @author TAKeogh
 * created 02-Apr-2020 8:30:00am.
 * @version 1.0.
 * Test class for Residential class. 
 * Although we identified this class as a JUnit5 class and use JUnit 5 in this project,
 * we use the classes of Junit4 as this is what is demonstrated in the lecture notes.
 * We show only classic-style assertions (not Hamcrest ones).
 * We don't use exception tests here as we haven't got to them yet in ITECH2306.
 * There are also more sophisticated ways of providing parameterized tests to avoid
 * separate tests for example testing for null or empty strings but students weren't
 * shown this in their lecture slides.
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResidentialTestCase4 {

	private Residential residentialProperty;
	
	@Before
	public void setUp() {
		residentialProperty = new Residential();
	}
	@Test
	public void testSetUpExtraServices() {
		ServiceType wasteManagement = new UnitAndRateService("Waste Management",1,350.00);
		ServiceType greenWasteManagement = new UnitAndRateService("Green Waste Management",1,75.00);
		ServiceType fireServicesLevy = new BaseAndPercentageOfValueService("Fire Services Levy",110.00, 0.00006, 350000);
		double expectedServicesValue = wasteManagement.calculateChargeForServiceType() + 
									   greenWasteManagement.calculateChargeForServiceType() +
									   fireServicesLevy.calculateChargeForServiceType();
		residentialProperty.setCapitalImprovedValue(350000);
		residentialProperty.setUpExtraServices();
		assertEquals(residentialProperty.calculateExtraServices(), expectedServicesValue, 0.0);
	}

	@Test
	public void testCalculateExtraServicesValidCalculation() {
		residentialProperty.setCapitalImprovedValue(350000);
		residentialProperty.setUpExtraServices();
		assertEquals(residentialProperty.calculateExtraServices(),556.00, 0.0) ; 
	}
	@Test
	public void testCalculateExtraServicesNegativeNumber() {
		residentialProperty.setCapitalImprovedValue(-350000);
		residentialProperty.setUpExtraServices();		
		assertEquals(residentialProperty.calculateExtraServices(),535.00, 0.0) ; 
	}
	
	@Test
	public void testCalculateExtraServicesBoundaryLowCIV() {
		residentialProperty.setCapitalImprovedValue(100);
		residentialProperty.setUpExtraServices();
		assertEquals(residentialProperty.calculateExtraServices(),535.01, 0.1) ; 
	}
	
	@Test
	public void testCalculateExtraServicesBoundaryHighCIV() {
		residentialProperty.setCapitalImprovedValue(50000000);
		residentialProperty.setUpExtraServices();
		assertEquals(residentialProperty.calculateExtraServices(),3535.00, 0.1) ; 
	}
	
	@Test
	public void testCalculateExtraServicesBoundaryOverCIV() {
		residentialProperty.setCapitalImprovedValue(50000001);
		residentialProperty.setUpExtraServices();
		assertEquals(residentialProperty.calculateExtraServices(),535.00, 0.1) ; 
	}

	@Test
	public void testResidentialNoParmsConstructorCIVRatePositive() {
		//also for setCIVRate
		assertTrue("Test the CIV stores positive number", residentialProperty.getCapitalImprovedRate() > 0); 
	}
	
	@Test
	public void testResidentialNoParmsConstructorCIVRateZero() {
		assertFalse("Test the CIV does not store zero", residentialProperty.getCapitalImprovedRate() == 0); 
	}
	
	public void testResidentialNoParmsConstructorCIVRateNegative() {
		assertFalse("Test the CIV does not store negative number", residentialProperty.getCapitalImprovedRate() < 0); 
	}

	@Test
	public void testsetCapitalImprovedValueValid() {
		residentialProperty.setCapitalImprovedValue(300000);
		assertEquals("Test the CIV stores a valid number in the range 100.00 to 50000000",300000, residentialProperty.getCapitalImprovedValue(),0.0); 
	}
	@Test
	public void testsetCapitalImprovedValueUnderValue() {
		residentialProperty.setCapitalImprovedValue(50.0);
		assertFalse("Test that you can't store a value of 99.99 as it is below 100.00",residentialProperty.getCapitalImprovedValue() == 99.99); 
	}

	@Test
	public void testsetCapitalImprovedValueOverValue() {
		residentialProperty.setCapitalImprovedValue(50000000.01);
		assertFalse("Test that you can't store a value greater than 50 million",residentialProperty.getCapitalImprovedValue() == 50000000.01); 
	}

	@Test
	public void testsetCapitalImproveValueSiteValueHigherThanCIV() {
		residentialProperty.setCapitalImprovedValue(200000);
		residentialProperty.setSiteValue(300000);
		assertEquals("If site value is higher than CIV, set CIV to site value", residentialProperty.getSiteValue(), residentialProperty.getCapitalImprovedValue(), 0.0);
	}
	
	@Test
	public void testsetCapitalImproveValueSiteValueLowerThanCIV() {
		residentialProperty.setCapitalImprovedValue(300000);
		residentialProperty.setSiteValue(200000);
		assertEquals("If site value is lower than CIV, site value remains at site value", 200000,residentialProperty.getSiteValue(), 0.0);
		assertEquals("If site value is lower than CIV, CIV receives site value", 300000,residentialProperty.getCapitalImprovedValue(), 0.0);
	}
	
	@Test
	public void testsetCapitalImproveValueCIVEqualToSiteValue() {
		residentialProperty.setCapitalImprovedValue(300000);
		residentialProperty.setSiteValue(300000);
		assertEquals("If site value and CIV are equal", residentialProperty.getSiteValue(), residentialProperty.getCapitalImprovedValue(), 0.0);
	}
	@Test
	public void testCalculateRatesCIVPortion() {
		residentialProperty.setCapitalImprovedValue(350000);
		residentialProperty.setCapitalImprovedRate(0.0039);
		assertEquals("Correct Calculation of CIV Rates portion",1365.00,residentialProperty.getCapitalImprovedValue() * 
																		residentialProperty.getCapitalImprovedRate() ,0.0);
	}
	@Test
	public void testCalculateRatesCharityTestDiscount() {
		residentialProperty.getOwner().setCharity(true);
		assertEquals("We get 20% discount for a charity", (residentialProperty.getOwner().isCharity() ?  1 - residentialProperty.getOwner().getCharityDiscountPercentage(): 1),0.8,0.0);
	}

	@Test
	public void testCalculateRatesCharityTestNoDiscount() {
		residentialProperty.getOwner().setCharity(false);
		assertEquals("We get no discount for a charity", (residentialProperty.getOwner().isCharity() ?  1 - residentialProperty.getOwner().getCharityDiscountPercentage(): 1),1,0.0);
	}
	
	@Test
	public void testCalculateRatesNoDiscount() {
		residentialProperty.setCapitalImprovedValue(350000);
		residentialProperty.setUpExtraServices();
		residentialProperty.getOwner().setCharity(false);
		assertEquals("Correct Calculation of Rates without discount",1921.00, residentialProperty.calculateRates(), 0.0);
	}

	@Test
	public void testCalculateRatesWithDiscount() {
		residentialProperty.setCapitalImprovedValue(350000);
		residentialProperty.setUpExtraServices();
		residentialProperty.getOwner().setCharity(true);
		assertEquals("Correct Calculation of Rates with discount",1536.80, residentialProperty.calculateRates(), 0.0);
	}	

}
