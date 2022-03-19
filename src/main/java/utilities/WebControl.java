package utilities;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebControl extends DriverUtils {
	
	public void takeScreenshot() {
		try {
			ExtentReportUtil.stepDefinition.addScreenCaptureFromPath(ExtentReportUtil.captureScreenShot());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void takeFullScreenshot() {
		try {
			ExtentReportUtil.stepDefinition.addScreenCaptureFromPath(ExtentReportUtil.captureFullPageScreenShot());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void navigateTo(String url)  {
		try {
			getDriver().get(url);
			waitForPageToLoad(60);
			Log.setTestStepPassed("Navigate to url: '"+url+"'");
		}catch(Exception e){
			Log.setTestStepFailedGetThrowable("Failed to navigate to: '"+url+"'", e.getMessage());
		}
	}
	
	
	public void navigate(String direction)	{
		try {
			if (direction.equals("back")) {
				getDriver().navigate().back();
				Log.setTestStepPassed("Navigate back");
			}else {
				getDriver().navigate().forward();
				Log.setTestStepPassed("Navigate forward");
			}
		}catch(Exception e){
			Log.setTestStepFailedGetThrowable("Failed to navigate to: '"+direction+"'", e.getMessage());
		}
	}
	
	
	public void closeBrowser(){
		try {
			getDriver().close();
			Log.setTestStepPassed("Close browser");
		}catch(Exception e){
			Log.setTestStepFailedGetThrowable("Failed to close the browser", e.getMessage());
		}
	}
	
	public void refresh()  {
		try {
			getDriver().navigate().refresh();
			waitForPageToLoad(60);
			Log.setTestStepPassed("Refresh page");
		}catch(Exception e){
			Log.setTestStepFailedGetThrowable("Failed to refresh page", e.getMessage());
		}
	}
	
	public void clearData()  {
		try {
			getDriver().manage().deleteAllCookies();
			waitForPageToLoad(60);
			Log.setTestStepPassed("Cookies are deleted");
		}catch(Exception e){
			Log.setTestStepFailedGetThrowable("Failed to delete cookies", e.getMessage());
		}
	}
	
	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}
	
	public void wait(int sec){
    	try {
    		sec = sec * 1000;
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void waitForPageToLoad(long timeout) {
		WebDriver driver = DriverUtils.getDriver();
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(pageLoadCondition);
		
	}
}

