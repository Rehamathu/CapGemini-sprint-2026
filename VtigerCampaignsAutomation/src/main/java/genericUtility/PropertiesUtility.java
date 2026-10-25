
package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    public String readDatafromPropertyFile(String key)
            throws IOException {

        FileInputStream fis = new FileInputStream(
                "src/main/resources/CommonData.properties");

        Properties prop = new Properties();
        prop.load(fis);
        fis.close();

        return prop.getProperty(key);
    }
}
