package utilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHandler {
	
	public static String downloadFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\downloads\\";
	public static String downloadFolderName="FOLDER-"+getCurrentTimeAndDate()+"\\";
	public static String translationFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	public static String translationFolderPathAll = System.getProperty("user.dir") + "\\src\\test\\resources\\translation\\";
	public static String translationFolderName="translation\\";
	public static String PATH=System.getProperty("user.dir") + "\\target\\sikuli-logs\\";
	
	public static String getCurrentTimeAndDate() {
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		Date date = new Date();
		
		String currentTimeAndDate =dateformat.format(date); 
		currentTimeAndDate=currentTimeAndDate.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
	
		return currentTimeAndDate;
	}
	
	
	public static void createDirectory(String folderPath, String folderName) {
		Path dir = Paths.get(folderPath+folderName);
		
		try {
			Files.createDirectory(dir);
			Log.setTestStepPassed("'"+downloadFolderName+"' created at :"+downloadFolderPath);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
}
