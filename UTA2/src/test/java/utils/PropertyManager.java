package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyManager
{
	static Logger logger = LoggerFactory.getLogger(PropertyManager.class);
	
	static String projectDir = System.getProperty("user.dir") + "\\src\\test\\java\\";
	static String globalConfigFilePath = projectDir + "config\\global.config";
	static Properties properties = new Properties();

	public static String getGlobalConfigProperty(String key)
	{
		try
		{
			properties.load(new FileInputStream(globalConfigFilePath));
		}
		catch (IOException e)
		{
			logger.error("global.config not found");
		}
		return properties.getProperty(key);
	}
}
