package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

import utilities.DriverUtils;
import utilities.ExtentReportUtil;
import utilities.Log.Console;
import utilities.WebControl;
public class ITestListenerImpl extends ExtentReportUtil  implements ITestListener{

private static ExtentReports extent;
Console console = new Console();
WebControl control = new WebControl();
	
@Override
	public void onStart(ITestContext result) {
		console.setInfo("Starting test...");
		extent=setUp();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
	
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		DriverUtils.endDriver();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	}
	@Override
	public void onFinish(ITestContext result) {
		console.setInfo("Test completed");
		extent.flush();
		
	}
	
	
	
}
