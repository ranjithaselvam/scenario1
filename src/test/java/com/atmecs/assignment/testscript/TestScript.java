package com.atmecs.assignment.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.assignment.base.Base;
import com.atmecs.assignment.config.Constant;
import com.atmecs.assignment.helper.Helper;
import com.atmecs.assignment.helper.Waits;
import com.atmecs.assignment.report.Reports;

/**
 * Verify and validate the product
 * 
 * @author ranjitha.selvam
 *
 */

public class TestScript extends Base {

	public Helper helper = new Helper();
	public Waits waits = new Waits();
	public Reports report = new Reports();

	@BeforeClass
	public void browserLaunch() {
		getBrowser();
		report.logInfo("browser opened");
		getUrl();
		report.logInfo("entered url");

	}

	@DataProvider
	public Object[][] productSearch() throws Exception {
		Object data[][] = utils.getExcel(Constant.productData_file, "Sheet1");
		return data;
	}

	@Test(dataProvider = "productSearch")
	public void productValidation(String searchcategories, String price, String extax, String description,String quantity)
			throws Exception {
		waits.implicitlyWait(driver);
		helper.elementIsDisplayed(driver, utils.propertyRead(Constant.homePage_file, "loc_home"));
		report.logInfo("validated home page");
		// search product
		helper.inputValuesToTheWebelement(driver, utils.propertyRead(Constant.homePage_file, "loc_searchBox"),
				searchcategories);
	
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.homePage_file, "loc_searchbutton"));
		report.logInfo("product entered");
		helper.scrollToBottomOfThePage(driver, utils.propertyRead(Constant.homePage_file, "loc_product"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.homePage_file, "loc_product"));
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_availability"));
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_price"));
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_extax"));
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_description"));
		report.logInfo("validated product availability,price and extax");
		helper.inputValuesToTheWebelement(driver,utils.propertyRead(Constant.searchProduct_file, "loc_quantity"),quantity);
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_add"));
		report.logInfo("products are added into cart");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_cart"));
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_item"));
		report.logInfo("added products");
		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_total"));
		report.logInfo("products grant total");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_remove"));
		report.logInfo("removed one product");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_cart"));

		helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_total"));
		report.logInfo("verified the grand total");

	}

	@AfterClass
	public void browserClose() {

		driverClose();
		report.logInfo("driver closed");
	}

}
