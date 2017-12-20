package com.sample;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTest {
  WebDriver driver;
  WebElement element;
  WebDriverWait wait;
  
  @BeforeClass
  public void beforeClass() {
	  //File file = new File("C:\\Windows\\System32\\IEDriverServer3.8.0.exe");
	  File file = new File("C:\\Windows\\System32\\IEDriverServer3.8.0.exe");
	  System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//	  System.setProperty("webdriver.ie.driver", "C:\\Windows\\System32\\IEDriverServer3.8.0.exe");
	  driver = new InternetExplorerDriver();
	  wait = new WebDriverWait(driver, 5);
  }

  @BeforeMethod
  public void beforeMethod() {
	  driver.get("http://192.168.33.1");
  }

  @Test(priority = 1)
  public void invalidLogin() {
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	  element.sendKeys("admin1");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	  element.sendKeys("pass");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
	  element.click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://192.168.33.1/#/login");
  }

  @Test(priority = 2)
  public void validLogin() {
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	  element.sendKeys("admin");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	  element.sendKeys("pass");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
	  element.click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://192.168.33.1/#/home");
  }

  @Test(priority = 3)
  public void updatePassword() {
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	  element.sendKeys("admin");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	  element.sendKeys("pass");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
	  element.click();
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordUpdate")));
	  element.clear();			
	  element.sendKeys("password");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submitUpdate")));
	  element.click();
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	  element.sendKeys("admin");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	  element.sendKeys("password");
	  element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
	  element.click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://192.168.33.1/#/home");
  }


  @AfterClass
  public void afterClass() {
		 driver.quit();
  }

}
