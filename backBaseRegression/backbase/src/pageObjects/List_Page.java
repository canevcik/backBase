package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class List_Page {
	
	private static WebDriver _driver;
	
	public List_Page(WebDriver driver){
		_driver = driver;
	}
	
	public void redirect(String baseUrl){
		_driver.get(baseUrl + "/computers");
	}
	
	public WebElement btn_Add(){
		return _driver.findElement(By.id("add"));
	}
	
	public WebElement txt_Filter(){
		return _driver.findElement(By.id("searchbox"));
	}
	
	public WebElement btn_Filter(){
		return _driver.findElement(By.id("searchsubmit"));
	}
	
	public WebElement btn_Link(String text){
		return _driver.findElement(By.linkText(text));
	}
	
	public WebElement btn_Next(){
		return _driver.findElement(By.xpath("//div[@id='pagination']/ul/li[@class='next']/a"));
	}
	
	public WebElement btn_Previous(){
		return _driver.findElement(By.xpath("//div[@id='pagination']/ul/li[@class='prev']/a"));
	}
	
}
