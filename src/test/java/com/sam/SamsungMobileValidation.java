package com.sam;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SamsungMobileValidation {
  static long StartTime;
  static WebDriver driver;
  String galaxy;
  @DataProvider(name = "mobile")
  public  Object[][] getmobile() {
	  	return new Object[][] {{"samsung mobile"}};
  }
  
	@BeforeClass
	public static void validateStart() {
		System.out.println("To Start Validation");
	}
	
	@BeforeMethod
	public static void runTime() {
  long StartTime = System.currentTimeMillis();
   System.out.println(StartTime);
   
	}
	
	@AfterMethod
	public void endTime() {
		long endtime = System.currentTimeMillis();
		System.out.println("taken time :"+ (endtime - StartTime));
	}
	
	@Test
	public void driverLaunch() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	}
	
		@Test(priority = 1)
		public void loggin (){
			try {
				driver.findElement(By.xpath("//button[text()='✕']")).click();
			}catch(Exception e){
				System.out.println(e);
			}
			
		}
		
	@Test(priority = 2,dataProvider = "mobile")
	public void search(String m) {
		WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
		search.sendKeys(m);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority = 3)
	public void mobileGalaxyf23() {
	WebElement f23 =driver.findElement(By.xpath("(//div[text()='SAMSUNG Galaxy F23 5G (Aqua Blue, 128 GB)'])[1]"));
		f23.click();
		 galaxy = f23.getText();
		System.out.println(galaxy);
	}
	@Test(priority = 4)
	public void Windows() {
		String window = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for( String x : windows) {
			if(!window.equals(x)) {
				driver.switchTo().window(x);
			}
		}
	}

	 @Test(priority = 5)
	 public void cheackMobile() {
		 WebElement ch = driver.findElement(By.xpath("//span[text()='SAMSUNG Galaxy F23 5G (Aqua Blue, 128 GB)']"));
		 String cm = ch.getText();
		 System.out.println(cm);
		 boolean resultcheack = cm.equals(galaxy);
		 System.out.println(resultcheack);
		 
	 }
	 @Test(priority = 6)
	 public void screenshotResult() throws Exception {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File ssf = ss.getScreenshotAs(OutputType.FILE);
		File fssf = new File("C:\\Users\\NK\\eclipse-workspace\\FlipkartMobileValidate\\screenshort result\\flipsam.png");
		FileUtils.copyFile(ssf, fssf);
	 }

	
}