package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Log extends ExtentReportUtil{
	
	static Console console = new Console();
	static SoftAssert softAssert = new SoftAssert(); 
//	static Assert hardAssert = new Assert(); 
	static WebControl control = new WebControl();
	
	public static void featureName() {
		// get Feature file name
		ExtentReportUtil.feature=ExtentReportUtil.extent.createTest(Feature.class, Hooks.featureName);
		console.setFeatureName(Hooks.featureName);
	}
	
	public static void scenarioName()  {
		//Get Scenario Name
		scenario=feature.createNode(Scenario.class, Hooks.scenarioName);
		console.setScenarioName(Hooks.scenarioName);
	}
	
	public static void stepDefinitionName() {
		//Get invoking Method name as step definition
		String methodName=getMethodName().replace("_", " ").toUpperCase();
		try {
			stepDefinition=scenario.createNode(new GherkinKeyword("Given"), methodName);
			console.setStepDefinitionName(methodName);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void setTestStepPassed(String description) {
		stepDefinition.pass(getTimeStamp()+" | "+description);
		console.setPassed(description);
	}
	
	public static void setTestStepFailed(String description) {
//		stepDefinition.fail(getTimeStamp()+" | "+description);
		stepDefinition.fail(MarkupHelper.createLabel(getTimeStamp()+" | "+description, ExtentColor.RED));
		console.setFailed(description);
		control.takeScreenshot();
//		softAssert.assertTrue(false);
		Assert.assertTrue(false);
	}
	
	public static void setTestStepFailedGetThrowable(String description, String exception) {
//		Log.setTestStepFailed(description);
		stepDefinition.fail(MarkupHelper.createLabel(getTimeStamp()+" | "+description, ExtentColor.RED));
		stepDefinition.fail(exception);
		console.setInfo(exception);
		control.takeScreenshot();
		Assert.assertTrue(false);
	}
	
	//This method returns the the name of the invoking method
    private static String getMethodName() {
    	
    	String methodName=null;
    	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    	StackTraceElement e = stacktrace[3];
    	methodName = e.getMethodName();
      	return methodName;

    }
    
    public static String getTimeStamp() {
    	
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
		Date date = new Date();
		return dateformat.format(date);
    }
    
	public static class Console {
		
		public void setFeatureName(String description) {
			System.out.println("[Feature] "+description);
		}
		
		public void setScenarioName(String description) {
			System.out.println("\t[Scenario] "+description);
		}
		public void setStepDefinitionName(String description) {
			System.out.println("\t\t[Step Definition] "+description);
		}
		
		public void setTestStep(String description) {
			System.out.println("\t\t\t[Test Step] "+description);
		}
		
		public void setPassed(String description) {
			System.out.println("\t\t\t"+getTimeStamp()+" | PASSED | "+description);
		}
		public void setFailed(String description) {
			System.err.println("\t\t\t"+getTimeStamp()+" | FAILED | "+description);
		}
		
		public void setInfo(String description) {
			System.out.println("[INFO] "+description);
			
		}
	}
	
	

}
