package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Log.Console;

public class DriverUtils {
	
	
	private static boolean debugMode = false;
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	Dimension screen;
	
	static PropertiesFileReader obj = new PropertiesFileReader();
	static Properties properties = obj.getProperty();
	static Console console = new Console();
	static boolean isDriverClose = false;
    static String downloadFolderPath = FileHandler.downloadFolderPath+FileHandler.downloadFolderName;
	
    
    public static String browser = getBrowserName();
    public static String environment = getEnvironment();
    public static String url = getEnvironmentURL();
    
    public static String getOSName() {
    	String osName = System.getProperty("os.name");
    	System.out.println("Running on :" +osName);
    	return osName;
    }
    
	public static WebDriver getDriver() {
	    return driver;
	}
	 
	public static String getBrowserName() {
		return System.getProperty("browser");
	}
	
	public static String getEnvironment() {
		return System.getProperty("environment");
	}
	
	public static String getEnvironmentURL() {
        return properties.getProperty(getEnvironment()+".base.url");
	}

	public static void endDriver() {
		debugMode = Boolean.valueOf(System.getProperty("mode.debug"));

		if (debugMode==false) {
			if (driver != null) {
				try {
					driver.close();
					driver.quit();
				} catch(Exception e) {
					driver.quit();
				}
				isDriverClose=true;
			}
		}
	}
	
	public static void openBrowser() throws InterruptedException, IOException {
		//Create new scenario in the extent report every time new browser is opened
		Log.scenarioName();
		console.setInfo("Create Driver: " + browser);
		
		try {
			switch (browser) {
				case "chrome":
					driver=newChromeDriver();
					break;
				case "headless chrome":
					driver=newHeadlessChromeDriver();
					break;
				default:
					driver = newChromeDriver();
					break;		
			}
		} catch (Error error) {
			System.out.println("Error: " + error.getMessage());
			throw error;
		}
			
		Timeouts timeouts = driver.manage().timeouts();
		Window window = driver.manage().window();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		try {
			timeouts.pageLoadTimeout(Long.valueOf(60), TimeUnit.SECONDS);
			timeouts.implicitlyWait(Long.valueOf(2), TimeUnit.SECONDS);
			timeouts.setScriptTimeout(Long.valueOf(10), TimeUnit.SECONDS);
	
		} catch (WebDriverException e) {
		}
		
		try {
			driver.switchTo().activeElement().sendKeys(Keys.chord(Keys.CONTROL, "0"));																		
		} catch (WebDriverException e) {
		}
		
		try {
			window.maximize();
		} catch (WebDriverException e) {
			System.out.println(e);
		}
		
		console.setInfo("Window size: ["+window.getSize().width+"x"+window.getSize().height+"]");
		console.setInfo("Screen size: ["+screenSize.width+"x"+screenSize.getHeight()+"]");
	}
	
	private static WebDriver newChromeDriver() throws IOException {
		String chromeDriverPath="";
		
		try {
			//Check os first and then assign the driver path
			if (getOSName().toLowerCase().contains("windows")) {
			chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/webdriver/chromedriver.exe";
			} else if(getOSName().toLowerCase().contains("mac")) {
				chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/webdriver/chromedriver";
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			console.setInfo("Chrome Driver: " + chromeDriverPath);
		} catch (Error error) {
			throw new Error("Chrome Driver not found");
		}
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFolderPath);
//		System.out.println("downloadFolderPath"+downloadFolderPath);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
//        options.addArguments("--window-size=1382,744");
        
        
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		options.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(options);

	}
	
	private static WebDriver newHeadlessChromeDriver() throws IOException {
		String chromeDriverPath="";
		
		try {
			//Check os first and then assign the driver path
			if (getOSName().toLowerCase().contains("windows")) {
			chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/webdriver/chromedriver.exe";
			} else if(getOSName().toLowerCase().contains("mac")) {
				chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/webdriver/chromedriver";
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			console.setInfo("Chrome Driver: " + chromeDriverPath);
		} catch (Error error) {
			throw new Error("Chrome Driver not found");
		}
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFolderPath);
//		System.out.println("downloadFolderPath"+downloadFolderPath);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("no-sandbox");
		options.addArguments("headless");
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--window-size=1920,1080");
        
        
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		options.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(options);

	}	
}
