package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {

	public Properties getProperty() {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties"));
		}catch(Exception e) {
			System.out.println("Exception: "+ e);
		}
		return properties;
	}
}
