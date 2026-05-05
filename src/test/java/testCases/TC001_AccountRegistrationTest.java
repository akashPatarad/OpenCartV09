package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAcount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage ap = new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		ap.setFirstName(randomString().toUpperCase());
		ap.setLastName(randomString().toUpperCase());
		ap.setEmail(randomString() + "@gmail.com");
		ap.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		ap.setPassword(password);
		ap.setConfirmPassword(password);
		
		ap.setPrivacyPolicy();
		ap.clickContinue();
		
		logger.info("Validating Expected message");
		String cnfmmsg = ap.getConfirmationMsg();
		if (cnfmmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		} else {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(cnfmmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("Finished TC001_AccountRegistrationTest");
	}
	
}
