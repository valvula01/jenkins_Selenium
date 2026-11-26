package folderSript;

import org.testng.annotations.Test;

import folderPage.classPage;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;


public class tourAutomation {
	
	private WebDriver driver;
	classPage page = new classPage(driver);
	
	
	
  @Test
  public void loginTour() {
	  
	  page.hacerLogin();
	  
  }
  @BeforeClass
  public void beforeClass() {
	  
	  page.hacerAntes();
	  
  }

  @AfterClass
  public void afterClass() {
	 
	  page.hacerDespues();
  }

}
