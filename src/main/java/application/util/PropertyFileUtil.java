package application.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileUtil {
	public static String readPathProperty(String key) throws IOException {
		try (InputStream input = PropertyFileUtil.class.getClassLoader().getResourceAsStream("property/path.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            return prop.getProperty(key);

		}
	}
}
