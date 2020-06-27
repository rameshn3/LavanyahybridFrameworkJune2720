package com.qa.linkedin.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
private static Logger log = Logger.getLogger(TestBase.class);
public static WebDriver driver;
public static WebDriverWait wait;

public static String readProperty(String key) throws IOException {
	log.debug("started the getting the key value::"+key);
	log.debug("create object for the Properties class");
	Properties prop=new Properties();
	log.debug("read the properties file");
	FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));
	log.debug("load the properties");
	prop.load(fis);
	return prop.getProperty(key);

}

@BeforeSuite
public void setup() throws IOException {
	log.debug("started launching the browser and application:::");
	String browserName=readProperty("browser");
	if (browserName.equalsIgnoreCase("firefox")) {
		log.debug("launching firefox brower:::");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	} else if (browserName.equalsIgnoreCase("chrome")) {
		log.debug("launching chrome brower:::");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
	} else if (browserName.equalsIgnoreCase("edge")) {
		log.debug("launching edge brower:::");
		
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	} else if (browserName.equalsIgnoreCase("ie")) {
		log.debug("launching IE brower:::");
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	log.debug("maximize the window");
	driver.manage().window().maximize();
	log.debug("add implicit wait");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	log.debug("create Object for WebDriverWait class");
	wait = new WebDriverWait(driver, 30);
	log.debug("Open the application url:"+readProperty("url"));
	driver.get(readProperty("url"));
}
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		log.debug("closing the browser:::");
		try {
			Thread.sleep(2000);
		}finally {
			driver.close();
		}
	
	}
	
}
