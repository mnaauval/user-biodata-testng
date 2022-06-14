package com.nexsoft.backend;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class UserBiodataTest {

	private WebDriver driver;
	private JavascriptExecutor jse;

	private void sleep(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool/");
		System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
		driver = new ChromeDriver();
		jse = (JavascriptExecutor) driver;
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void login() {

		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("mnaauval@gmail.com");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin123");

		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-flat")).click();

	}

	@Test(priority = 2)
	public void gotoCRUDBuilder() {

		driver.findElement(By.linkText("CRUD Builder")).click();
		driver.findElement(By.cssSelector("tbody > tr:nth-child(2) > td:nth-child(5) > a:nth-child(1)")).click();

	}

	@Test(priority = 3, dataProvider = "getUserBiodata", dataProviderClass = com.nexsoft.utilities.DataProviderku.class)
	public void inputDataTest(String first, String last, String email, String gender) {

		driver.findElement(By.xpath("//a[@id='btn_add_new']")).click();

		driver.findElement(By.cssSelector("#first_name")).click();
		driver.findElement(By.cssSelector("#first_name")).sendKeys(first);

		driver.findElement(By.cssSelector("#last_name")).click();
		driver.findElement(By.cssSelector("#last_name")).sendKeys(last);

		driver.findElement(By.cssSelector("#email")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		
		driver.findElement(By.cssSelector("#gender")).click();
		driver.findElement(By.cssSelector("#gender")).sendKeys(gender);
		
		jse.executeScript("window.scrollBy(100, 500)", "");
		
		WebElement chooseFile = driver.findElement(By.cssSelector("input[title='file input"));
		
		int randomVal = 1 + (int)(Math.random() * ((3 - 1) + 1));
		if (randomVal == 1) chooseFile.sendKeys("D:\\Project\\Java\\TestNG\\UserBiodata\\src\\test\\resources\\emotsad.PNG");
		else if (randomVal == 2) chooseFile.sendKeys("D:\\Project\\Java\\TestNG\\UserBiodata\\src\\test\\resources\\juaracoding.JPG");
		else chooseFile.sendKeys("D:\\Project\\Java\\TestNG\\UserBiodata\\src\\test\\resources\\nexsoft.JPG");
		
		sleep(500);
		
		driver.findElement(By.xpath("//a[@id='btn_save']")).click();
		
		sleep(500);

	}
	
	@Test(priority = 4)
	public void resetTable() {		
		driver.findElement(By.cssSelector(".iCheck-helper")).click();
		driver.findElement(By.cssSelector("#apply")).click();
		sleep(1000);
		driver.findElement(By.xpath("//button[@class='confirm']")).click();
		sleep(3000);
	}

	@AfterClass
	public void logoutAndCloseConn() {
		driver.findElement(By.cssSelector("img.user-image")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".pull-right a.btn.btn-default.btn-flat")).click();
		sleep(3000);
		driver.close();
	}

}
