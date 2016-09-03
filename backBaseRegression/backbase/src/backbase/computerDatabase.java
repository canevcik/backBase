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

	@BeforeClass
	public static void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://computer-database.herokuapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test01addComp() throws Exception {
		//add a new computer
		driver.get(baseUrl + "/computers");
		driver.findElement(By.id("add")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(compName);
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys(introduced);
		new Select(driver.findElement(By.id("company"))).selectByVisibleText("Apple Inc.");
		driver.findElement(By.cssSelector("input.btn.primary")).click();
	}

	@Test
	public void test02searchComp() throws Exception {
		//filter a computer by name, look it up and close it
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys(compName);
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.linkText(compName)).click();
		driver.findElement(By.linkText("Cancel")).click();		
	}
	
	@Test
	public void test03updateComp() throws Exception {
		//edit a computer and search with the new name, look it up and  close
	    driver.findElement(By.id("searchbox")).clear();
	    driver.findElement(By.id("searchbox")).sendKeys(compName);
	    driver.findElement(By.id("searchsubmit")).click();
	    driver.findElement(By.linkText(compName)).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(compNameUpdated);
	    driver.findElement(By.cssSelector("input.btn.primary")).click();
	    driver.findElement(By.id("searchbox")).clear();
	    driver.findElement(By.id("searchbox")).sendKeys(compNameUpdated);
	    driver.findElement(By.id("searchsubmit")).click();
	    driver.findElement(By.linkText(compNameUpdated)).click();
	    driver.findElement(By.linkText("Cancel")).click();
	}

	@Test
	public void test04deleteComp() throws Exception {
		//find the computer saved and updated and delete that computer
	    driver.findElement(By.id("searchbox")).clear();
	    driver.findElement(By.id("searchbox")).sendKeys(compNameUpdated);
	    driver.findElement(By.id("searchsubmit")).click();
	    driver.findElement(By.linkText(compNameUpdated)).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(compNameUpdated);
	    driver.findElement(By.cssSelector("input.btn.primary")).click();
	    driver.findElement(By.id("searchbox")).clear();
	    driver.findElement(By.id("searchbox")).sendKeys(compNameUpdated);
	    driver.findElement(By.id("searchsubmit")).click();
	    driver.findElement(By.linkText(compNameUpdated)).click();
	    driver.findElement(By.linkText("Cancel")).click();		
	}

	@Test
	public void test05moveToNextPage() throws Exception {
		//move to the next page of computers data table
		driver.findElement(By.xpath("//div[@id='pagination']/ul/li[@class='next']/a")).click();
	}
	@Test
	public void test06moveToPreviousPage() throws Exception {
		//move to the previous page of computers data table
		driver.findElement(By.xpath("//div[@id='pagination']/ul/li[@class='prev']/a")).click();
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
