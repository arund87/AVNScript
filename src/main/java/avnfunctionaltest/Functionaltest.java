package avnfunctionaltest;

import java.io.File;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;	

public class Functionaltest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public String URL="http://ec2-54-254-182-212.ap-southeast-1.compute.amazonaws.com:8080/arun";
	public String URL1="http://ec2-54-254-182-212.ap-southeast-1.compute.amazonaws.com:8080/arun/avncreatepage.jsp";
	
	@Test(priority=0)
	public void logintest()
	{
		//driver=new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		//driver = new ChromeDriver();
		//driver = new HtmlUnitDriver();
		File file = new File("./lib/phantomjs211/bin/phantomjs.exe");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		driver = new PhantomJSDriver();
		wait = new WebDriverWait(driver, 5000);
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		//Login Page content check
		driver.findElement(By.xpath("//h1[text()='AVN COMMUNICATION']")).isDisplayed();
		driver.findElement(By.name("username")).isDisplayed();
		driver.findElement(By.name("userpassword")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Login']")).isDisplayed();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		//Login operation
		driver.findElement(By.name("username")).sendKeys("deraviyam");
		driver.findElement(By.name("userpassword")).sendKeys("deraviyam");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void viewrepairticket() throws Exception
	{
		WebElement welcome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='WELCOME TO YOUR ACCOUNT OVERVIEW']")));
		welcome.isDisplayed();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get(URL1);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		WebElement repair = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//font[text()='Create Repair Ticket']")));
		repair.isDisplayed();
		WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']")));
		cancel.click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		Thread.sleep(3000);
		WebElement repair1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Repair Ticket Page']")));
		repair1.isDisplayed();	
	}
	
	@Test(priority=2)
	public void createrepairticket()
	{
		driver.findElement(By.className("btn")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//font[text()='Create Repair Ticket']")).isDisplayed();
		Select proddd=new Select(driver.findElement(By.name("product")));
		proddd.selectByValue("BROADBAND");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.findElement(By.name("problemdescription")).click();
		driver.findElement(By.name("problemdescription")).clear();
		driver.findElement(By.name("problemdescription")).sendKeys("Increase Bandwidth");
		driver.findElement(By.name("contact")).sendKeys("Arun");
		driver.findElement(By.name("date")).sendKeys("03/03/2017");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h2[text()='Repair Ticket Page']")).isDisplayed();				
	}
	
	@Test(priority=3)
	public void pageredirection()
	{
		driver.findElement(By.partialLinkText("Home")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h2[text()='WELCOME TO YOUR ACCOUNT OVERVIEW']")).isDisplayed();
		driver.get(URL1);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//font[text()='Create Repair Ticket']")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Cancel']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h2[text()='Repair Ticket Page']")).isDisplayed();
	}
	
	@Test(priority=4)
	public void logouttest()
	{
		driver.findElement(By.partialLinkText("Logout")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h1[text()='AVN COMMUNICATION']")).isDisplayed();
		driver.findElement(By.name("username")).sendKeys("deraviyam");
		driver.findElement(By.name("userpassword")).sendKeys("deraviyam");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Logout")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h1[text()='AVN COMMUNICATION']")).isDisplayed();
		driver.quit();
	}	

	
}
