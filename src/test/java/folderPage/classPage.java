package folderPage;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class classPage {
	
	
	
	private WebDriver driver;
	
	public classPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	String path ="C:\\Users\\Administrador\\Desktop\\credenciales.xlsx";
	String sheet = "hoja1";
	
	String url ="https://demo.guru99.com/test/newtours/index.php";
	By userLacator = By.xpath("//input[@name='userName']");
	By passLocator = By.xpath("//input[@name='password']");
	By signinLocator = By.xpath("//input[@name='submit']");
	By validSigninLacator = By.xpath("//b[starts-with(text(),' Thank ')]");   ////b[contains(text(),' Thank ')]
	By signOutLocator = By.xpath("//a[contains(text(),'SIGN-OFF')]");
	
	
	public void hacerAntes() {
		
		 //System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe"); en las versiones actuales de selenioum(4.6 en adelante) no es necesario esto.
		  ChromeOptions option = new ChromeOptions();
		  option.addArguments("--guest");
		  driver = new ChromeDriver(option);
		  driver.manage().window().maximize();
		  driver.get(url);
		  
		 /*driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(url); */
	}
	public void hacerDespues() {
		
		driver.close();
	}
	public void hacerLogin() {
		
		try {
			
			int numRow = rowCount(path, sheet);
			
			  if (driver.findElement(userLacator).isDisplayed()) {
				  
				  for (int i = 1; i < numRow; i++) {
					
					  String user = readExcel(path, sheet, i, 0);
					  String pass = readExcel(path, sheet, i, 1);
				
				
				  driver.findElement(userLacator).sendKeys(user);
				  driver.findElement(passLocator).sendKeys(pass);
					
					/* driver.findElement(passLocator).sendKeys(Keys.CLEAR);
					 driver.findElement(passLocator).sendKeys(Keys.TAB);*/
					
				  
				  
				  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				  wait.until(ExpectedConditions.elementToBeClickable(signinLocator));
				  
				  driver.findElement(signinLocator).click();
				  
				  WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(1));
				  wait2.until(ExpectedConditions.presenceOfElementLocated(validSigninLacator));
				  
				  System.out.println(driver.findElement(validSigninLacator).getText().toString());
				  assertEquals(driver.findElement(validSigninLacator).getText(), "Thank you for Loggin.");
				  
				  driver.findElement(signOutLocator).click();
				  Thread.sleep(1);
				  
				  }
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public int rowCount(String path, String sheetName) throws IOException {
		
		File file = new File(path);
		FileInputStream input = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int row = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		return row;
	}
	
	public String readExcel(String path, String sheetName, int rowNumber, int cellNumber) throws IOException {
		
		File file = new File(path);
		FileInputStream input = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		XSSFRow row = sheet.getRow(rowNumber);
		XSSFCell cell = row.getCell(cellNumber);
		
		return cell.getStringCellValue().toString();
	}
	
     public void escribirExcel(String path, String sheetName, int rowNumber, int cellNumber, String dataToWrite) throws IOException {
		
		File file = new File(path);
		FileInputStream input = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		XSSFRow row = sheet.getRow(rowNumber);
		XSSFCell cell = row.createCell(cellNumber);
		
		cell.setCellValue(dataToWrite);
		input.close();
		
		//abro File output
		FileOutputStream output = new FileOutputStream(file);
		workbook.write(output);
		output.close();
		
	}
}
