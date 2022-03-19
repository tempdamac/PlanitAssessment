package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class ExtentReportUtil {

	public static ExtentReports extent = null;
	public static ExtentHtmlReporter report = null;
	public static String folderName = null;
	public static ExtentTest test,feature,scenario,stepDefinition = null;
	
	public static PropertiesFileReader property = new PropertiesFileReader();
	
	final static String OS = System.getProperty("os.name");
	final static String USERNAME = System.getProperty("user.name");
	final static String APPLICATION_NAME = property.getProperty().getProperty("application.name");
	final static String BROWSER_NAME = System.getProperty("browser");
	final static String ENVIRONMENT = System.getProperty("environment");
	
	
	public static ExtentReports setUp() {
		
		//Setting up the report properties
		SimpleDateFormat screenshotFormat = new SimpleDateFormat("MM-dd-HH-mm");
		Date date = new Date();

		folderName = "results_" + screenshotFormat.format(date);
		String reportLocation=System.getProperty("user.dir") + "/extent-reports/" + folderName + "/extentreports.html";
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportLocation);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle("Selenium Cucumber BDD Framework");
		reporter.config().setEncoding("UTF-8");
		reporter.config().setReportName("Selenium Cucumber BDD Framework - Report");
		reporter.start();
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System: ",OS );
		extent.setSystemInfo("User: ", USERNAME);
		extent.setSystemInfo("Application Name: ", APPLICATION_NAME);
		extent.setSystemInfo("Browser Name: ", BROWSER_NAME);
		extent.setSystemInfo("Environment: ", ENVIRONMENT);
		
		return extent;
	}
	
	public void flushReport() {
		extent.flush();
	}
	
	
	public static String captureScreenShot() {
		TakesScreenshot screenshot = (TakesScreenshot) DriverUtils.getDriver();
		File src = screenshot.getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir") + "/extent-reports/screenshots/"+fileHandler.getCurrentTimeAndDate()+".png";
		String parentFolderName = "extent-reports/" + folderName + "/";
		String path = "screenshots/" + FileHandler.getCurrentTimeAndDate() + ".png";
		File target = new File (parentFolderName+path);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public static String captureFullPageScreenShot() {
		String parentFolderName = "extent-reports/" + folderName + "/";
		String path = "screenshots/" + FileHandler.getCurrentTimeAndDate() + ".png";

		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(DriverUtils.getDriver());
		try {
			ImageIO.write(fpScreenshot.getImage(),"PNG",new File(parentFolderName+path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return path;
	}
	
	public static void testStepHandle(String testStatus, ExtentTest test, Throwable throwable) {
		switch(testStatus) {
			case "FAIL":
				test.fail(MarkupHelper.createLabel("Test Case Failed: ", ExtentColor.RED));
				test.error(throwable.fillInStackTrace());
				DriverUtils.endDriver();
				break;
			case "PASS":
				test.pass(MarkupHelper.createLabel("TestCase is Passed: ", ExtentColor.GREEN));
				break;
			default:
				break;
		}
	}
	
}
