package elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import utilities.DriverUtils;
import utilities.ExtentReportUtil;
import utilities.Log;
import utilities.Log.Console;
import utilities.WebControl;

public class Element extends ExtentReportUtil {

	private By by;
	private WebElement element;
	private String name;
	private WebDriver driver = DriverUtils.getDriver();
	WebControl control = new WebControl();
	WebDriverWait wait = new WebDriverWait(driver, 60);
	Console console = new Console();
	Actions action = new Actions(driver);
	static SoftAssert softAssert=new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	ArrayList<String> cN = new ArrayList<String>();
	public static Boolean bolExistFlag;
	
	public Element(String name, By by) {
		this.by = by;
		this.name = name;
	}
	
	public void click() {
		try {
			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			
			Log.setTestStepPassed("["+name+"] Clicked element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to click element:", e.getMessage());
		}
	}
	
	public void check(String bool) {
		
		boolean value=Boolean.valueOf(bool);
		try {
			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			
			boolean isSelected=element.isSelected();
			if (value==true && isSelected==false) {
				try {
					element.click();
				} catch (Exception e) {
					js.executeScript("arguments[0].click();", element);
				}
				Log.setTestStepPassed("["+name+"] Checked element.");
			} else if (value==false&& isSelected==true) {
				try {
					element.click();
				} catch (Exception e) {
					js.executeScript("arguments[0].click();", element);
				}
				Log.setTestStepPassed("["+name+"] Unchecked element.");
			} else {
				Log.setTestStepPassed("["+name+"] Element is already on the desired state.");
			}
			
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to check element:", e.getMessage());
		}
	}
	
	public void rightClick() {
		try {
			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			action.contextClick(element);
			action.build().perform();
			
			Log.setTestStepPassed("["+name+"] Right clicked element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to right click element:", e.getMessage());
		}
	}
	
	public void sendKeys(String value) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
			
			Log.setTestStepPassed("["+name+"] Entered '"+value+"' to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to enter '"+value+"' to element:", e.getMessage());
		}
	}

	
	public void clear() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			
			Log.setTestStepPassed("["+name+"] Cleared element's value.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed clear elements value:", e.getMessage());
		}
	}
	
	public void clearAndSendKeys(String value) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
			Log.setTestStepPassed("["+name+"] Cleared and entered '"+value+"' to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to clear and enter '"+value+"' to element:", e.getMessage());
		}
	}
	
	public void setPassword(String value) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
			value=value.replace(value, "*");
			
			Log.setTestStepPassed("["+name+"] Entered '**********' to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to enter '**********' to element:", e.getMessage());
		}
	}
	
	public void hover() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			
			action.moveToElement(element).build().perform();
			
			Log.setTestStepPassed("["+name+"] Hovered to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to hover element:", e.getMessage());
		}
	}
	
	public void hoverJS() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			js.executeScript(mouseOverScript, element);
			
			Log.setTestStepPassed("["+name+"] Hovered to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to hover element:", e.getMessage());
		}
	}
	
	public void clickJS() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));

			js.executeScript("arguments[0].click();", element);
			
			Log.setTestStepPassed("["+name+"] Clicked element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to click element:", e.getMessage());
		}
	}
	
	public void sendKeysJS(String value) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));

			js.executeScript("arguments[0].value='" + value + "';", element);
			
			Log.setTestStepPassed("["+name+"] Entered '"+value+"' to element.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to enter '"+value+"' to element:", e.getMessage());
		}
	}
	
	public void scrollToElementCenter() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

			js.executeScript(scrollElementIntoMiddle, element);
			
			Log.setTestStepPassed("["+name+"] Scroll to element view center.");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to scroll to element view center:", e.getMessage());
		}
	}
	
	public void doubleClick() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
			
			action.moveToElement(element);
			action.doubleClick();
			action.perform();
			
			Log.setTestStepPassed("["+name+"] Double clicked element");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to double click element:", e.getMessage());
		}
	}
	
	
	public void scrollToElement(){
		try {
			element = driver.findElement(by);
			
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			js.executeScript("arguments[0].scrollIntoView();", element);
			
			Log.setTestStepPassed("["+name+"] Scrolled to element");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to scroll click element:", e.getMessage());
		}
		
	}
	
	public void waitForExist(long timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			Log.setTestStepPassed("["+name+"] Waiting for element to be visible ("+timeOutInSeconds+")");
		} catch (Exception e) {
			
		}
	}
	
	public String getAttribute(String attribute) {	
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.getAttribute(attribute);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getCssValue(String value) {	
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.getCssValue(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public String getText() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.getText();
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to get element's text:", e.getMessage());
			return null;
		}	
	}
	
	public String getValue() {	
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.getAttribute("value");
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to get element's value:", e.getMessage());
			return null;
		}
	}
	
	public boolean isVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isDisplayed() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isEnabled() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isSelected() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			return element.isSelected();
		} catch (Exception e) {
			return false;
		}
	}
		
	public boolean isExist(long timeOutInSeconds) {
		bolExistFlag = false;
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	    	List<WebElement> oList = driver.findElements(by);
	    	if (oList.size() > 0) {
	    		wait.ignoring(StaleElementReferenceException.class)
	    		.until(ExpectedConditions.visibilityOf(oList.get(0)));
	    		bolExistFlag = true;
	    		Log.setTestStepPassed("["+name+"] Element exists");
	    	} else {
	    		bolExistFlag = false;
	    	}
	    } catch (Exception e) {
	    	Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's visibility:", e.getMessage());
	    }
	    return bolExistFlag;
	}
		
	public void verifyAttributeContains(String attribute, String expectedValue) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getAttribute(attribute);
			if (actualValue.contains(expectedValue)) {
				Log.setTestStepPassed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' contains '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' does not contains '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's attribute:", e.getMessage());
		}
	}
	
	public void verifyAttributeEquals(String attribute, String expectedValue) {
		try {			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getAttribute(attribute);
			if (actualValue.equals(expectedValue)) {
				Log.setTestStepPassed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' equals to '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' is not equals to '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's attribute:", e.getMessage());
		}
	}
	
	public void verifyTextContains(String expectedValue) {
		try {			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getText();
			if (actualValue.contains(expectedValue)) {
				Log.setTestStepPassed("["+name+"] Element \"text\": '"+actualValue+"' contains '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \"text\": '"+actualValue+"' does not contains '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's text:", e.getMessage());
		}
	}
	
	public void verifyTextEquals(String expectedValue) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getText();
			if (actualValue.equals(expectedValue)) {
				Log.setTestStepPassed("["+name+"] Element \"text\": '"+actualValue+"' equals to '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \"text\": '"+actualValue+"' is not equals to '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's text:", e.getMessage());
		}
	}
	
	
	public void verifyTextContainsAndIgnoreCase(String expectedValue) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getText();
			
			
			if (actualValue.toUpperCase().contains(expectedValue.toUpperCase())) {
				Log.setTestStepPassed("["+name+"] Element \"text\": '"+actualValue+"' contains '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \"text\": '"+actualValue+"' does not contains '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's text:", e.getMessage());
		}
	}
	
	public void verifyDisplayed() {
		try {			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			if (element.isDisplayed()) {
				Log.setTestStepPassed("["+name+"] Element is displayed");
			} else {
				Log.setTestStepFailed("["+name+"] Element is not displayed");
			}
		} catch (Exception e) {
			Log.setTestStepFailed("["+name+"] Element is not displayed");
		}
	}
	
	public void verifySelected() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			if (element.isSelected()) {
				Log.setTestStepPassed("["+name+"] Element is selected");
			} else {
				Log.setTestStepFailed("["+name+"] Element is not selected");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's selection:", e.getMessage());
		}
	}
	
	public void verifyNotSelected() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			if (element.isSelected()) {
				Log.setTestStepFailed("["+name+"] Element is selected");
			} else {
				Log.setTestStepPassed("["+name+"] Element is not selected");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's selection:", e.getMessage());
		}
	}
	
	public void verifyEnabled() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			if (element.isEnabled()) {
				Log.setTestStepPassed("["+name+"] Element is enabled");
			} else {
				Log.setTestStepFailed("["+name+"] Element is disabled");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's enablement:", e.getMessage());
		}
	}
	
	public void verifyDisabled() {
		 			
		element = driver.findElement(by);
		wait.ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.visibilityOf(element));
			
		if (element.isEnabled()) {
			Log.setTestStepFailed("["+name+"] Element is enabled");
		} else {
			Log.setTestStepPassed("["+name+"] Element is disabled");
		}
	}
	
	public void verifyNotDisplayed() {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			if (element.isDisplayed()) {
				Log.setTestStepFailed("["+name+"] Element is displayed");
			} else {
				Log.setTestStepPassed("["+name+"] Element is not displayed");
			}
		} catch (Exception e) {
			Log.setTestStepPassed("["+name+"] Element is not displayed");
		}
	}
	
	public void verifyTextNotEquals(String expectedValue) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getText();
			if (actualValue.equals(expectedValue)) {
				Log.setTestStepFailed("["+name+"] Element \"text\": '"+actualValue+"' equals to '"+expectedValue+"'");
			} else {
				Log.setTestStepPassed("["+name+"] Element \"text\": '"+actualValue+"' is not equals to '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's text", e.getMessage());
		}
	}
	
	public void verifyAttributeNotEquals(String attribute, String expectedValue) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getAttribute(attribute);
			if (actualValue.equals(expectedValue)) {
				Log.setTestStepFailed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' equals to '"+expectedValue+"'");
			} else {
				Log.setTestStepPassed("["+name+"] Element \""+attribute+"\": '"+actualValue+"' is not equals to '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's attribute:", e.getMessage());
		}
	}
	
	public void verifySortedList(String sortOrder) {
		try {
			
			List<WebElement> element = (List<WebElement>) driver.findElements(by);
			java.util.Iterator<WebElement> i = element.iterator();
			
            ArrayList<String> applicationSortedList = new ArrayList <String> ();
            ArrayList<String> codeSortedAscendingList = new ArrayList <String> ();
            ArrayList<String> codeSortedDescendingList = new ArrayList <String> ();
           
    		while(i.hasNext()) {
			    WebElement elements = i.next();
			    if (elements.isDisplayed()) {
			    	applicationSortedList.add(elements.getText());
			    }
			} 
            
    		System.out.println("sizeess: "+applicationSortedList.size());
    		System.out.println("sizeesVals: "+applicationSortedList.get(1));
    		
            if(sortOrder.contentEquals("asc")) {
    
            	codeSortedAscendingList = applicationSortedList;
            Collections.sort(codeSortedAscendingList);
            boolean match=false;
  
            for (int x=0;x<codeSortedAscendingList.size();x++) {
                if (codeSortedAscendingList.get(x).equals(applicationSortedList.get(x))) {
	                   //System.out.println(codeSortedAscendingCatalogNumberList.get(x)+" : "+applicationSortedCatalogNumberList.get(x));
	                   match=true;
	            } else {
	                   match=false;
	                   break;
	            }
            }
 
            if (match) {
               Log.setTestStepPassed("Sort functionality (Ascending) is working as expected");
            } else {
        	   Log.setTestStepFailed("Sort functionality (Ascending) is not working as expected");
            }
  
            } else {
            	
            	codeSortedDescendingList = applicationSortedList;
    			Collections.sort(codeSortedDescendingList,Collections.reverseOrder());
    			boolean match = false;
    			
                for (int x=0;x<codeSortedDescendingList.size();x++) {
                       if (codeSortedDescendingList.get(x).equals(applicationSortedList.get(x))) {
                         // System.out.println(codeSortedDescendingCatalogNumberList.get(x)+" : "+applicationSortedCatalogNumberList.get(x));
                         match=true;
                   } else {
                         match=false;
                         break;
                   }
                   
                }
                if (match) {
                    Log.setTestStepPassed("Sort functionality (Descending) is working as expected");
                 } else {
             	   Log.setTestStepFailed("Sort functionality (Descending) is not working as expected");
                 }
           }
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's attribute:", e.getMessage());
		}
	}
	
	public void verifyCssValue(String cssValue, String expectedValue) {
		try {
			
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			
			String actualValue=element.getCssValue(cssValue);
			if (actualValue.equals(expectedValue)) {
				Log.setTestStepPassed("["+name+"] Element \""+cssValue+"\": '"+actualValue+"' equals to '"+expectedValue+"'");
			} else {
				Log.setTestStepFailed("["+name+"] Element \""+cssValue+"\": '"+actualValue+"' is not equals to '"+expectedValue+"'");
			}
		} catch (Exception e) {
			Log.setTestStepFailedGetThrowable("["+name+"] Failed to verify element's css value:", e.getMessage());
		}
	}
		
	public void pressKey(Keys key) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(key);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}
	
	public void pressModKey(String value) {
		try {
			element = driver.findElement(by);
			wait.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}
	
}
