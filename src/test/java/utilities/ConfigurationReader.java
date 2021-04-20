package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            //path to our .properties file
            //MIGHT BE WRONG PATH HERE, PAY ATTENTION
            String path = "configuration.properties";
            //we create object of input stream to access file

            FileInputStream input = new FileInputStream(path);
            //initialize configFile
            properties = new Properties();
            //load properties file
            properties.load(input);
            //close input stream
            input.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
