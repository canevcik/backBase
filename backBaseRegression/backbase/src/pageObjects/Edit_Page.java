package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Edit_Page {
	
	private static WebDriver _driver;
	
	public Edit_Page(WebDriver driver){
		_driver = driver;
	}
	
	public WebElement txt_Name(){
		return _driver.findElement(By.id("name"));
	}
	
	public WebElement txt_Introduced(){
		return _driver.findElement(By.id("introduced"));
	}
	
	public WebElement slc_Company(){
		return _driver.findElement(By.id("company"));
	}
	
	public WebElement btn_Save(){
		return _driver.findElement(By.cssSelector("input.btn.primary"));
	}
	
	public WebElement btn_Cancel(){
		return _driver.findElement(By.linkText("Cancel"));
	}
	
	public WebElement btn_Remove(){
		return _driver.findElement(By.cssSelector("#main > form.topRight > input"));
	}
	
}
