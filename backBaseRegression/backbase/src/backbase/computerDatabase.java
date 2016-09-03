package backbase;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.*;


//to make sure the CRUD operations work synchronously(in a certain order)-easily sorted
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 		

public class computerDatabase {

	public static WebDriver driver;
	public static String testVarName;
	public static WebDriverWait wait;
	public static int polling;
	private static String baseUrl;
	public static int waits;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	public static String compName = "testComp"; 
	public static String introduced = "1995-01-01"; 
	public static String compNameUpdated = "testCompUpdated";
	public static String companyName = "Apple Inc.";
	
	public static Edit_Page editPage;
	public static List_Page listPage;

	@BeforeClass
	public static void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://computer-database.herokuapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		editPage = new Edit_Page(driver);
		listPage = new List_Page(driver);
	}

	@Test
	public void test01addComp() throws Exception {
		//add a new computer
		listPage.redirect(baseUrl);
		
		listPage.btn_Add().click();
		Thread.sleep(2000);
		
		editPage.txt_Name().clear();
		editPage.txt_Name().sendKeys(compName);
		
		editPage.txt_Introduced().clear();
		editPage.txt_Introduced().sendKeys(introduced);
		
		new Select(editPage.slc_Company()).selectByVisibleText(companyName);
		
		editPage.btn_Save().click();
	}

	@Test
	public void test02searchComp() throws Exception {
		//filter a computer by name, look it up and close it
		listPage.txt_Filter().clear();
		listPage.txt_Filter().sendKeys(compName);
		
		listPage.btn_Filter().click();
		
		listPage.btn_Link(compName).click();
		
		editPage.btn_Cancel().click();
			
	}
	
	@Test
	public void test03updateComp() throws Exception {
		//edit a computer and search with the new name, look it up and  close
		listPage.txt_Filter().clear();
		listPage.txt_Filter().sendKeys(compName);
		
		listPage.btn_Filter().click();
		
		listPage.btn_Link(compName).click();
		
		editPage.txt_Name().clear();
		editPage.txt_Name().sendKeys(compNameUpdated);
		
		editPage.btn_Save().click();
		
		listPage.txt_Filter().clear();
		listPage.txt_Filter().sendKeys(compNameUpdated);
		
		listPage.btn_Filter().click();
		
		listPage.btn_Link(compNameUpdated).click();
		
		editPage.btn_Cancel().click();
	}

	@Test
	public void test04deleteComp() throws Exception {
		//find the computer saved and updated and delete that computer
		listPage.txt_Filter().clear();
		listPage.txt_Filter().sendKeys(compNameUpdated);
		
		listPage.btn_Filter().click();
		
		listPage.btn_Link(compNameUpdated).click();
		
		editPage.btn_Remove().click();	
	}

	@Test
	public void test05moveToNextPage() throws Exception {
		//move to the next page of computers data table
		listPage.btn_Next().click();
	}
	@Test
	public void test06moveToPreviousPage() throws Exception {
		//move to the previous page of computers data table
		listPage.btn_Previous().click();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
