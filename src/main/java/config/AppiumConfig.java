package config;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumConfig implements TestHelper {

    public static AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "mobqa42");
//        capabilities.setCapability("platformVersion", "8.0");
//        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
//        capabilities.setCapability("appActivity", ".SplashActivity");
//        capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "Appium");
//        capabilities.setCapability(MobileCapabilityType.APP, "/Users/zok/Documents/_TelRan/QA-42_Testing_Auto_43_29052024/contacts-android.apk");
        capabilities.setCapability(PLATFORMNAME, PropertiesReaderXML.getProperty("platform", XML_FILE_PATH));
        capabilities.setCapability(DEVICENAME, PropertiesReaderXML.getProperty("device", XML_FILE_PATH));
        capabilities.setCapability(PLATFORMVERSION, PropertiesReaderXML.getProperty("version", XML_FILE_PATH));
        capabilities.setCapability(PACKAGE, PropertiesReaderXML.getProperty("package", XML_FILE_PATH));
        capabilities.setCapability(ACTIVITY, PropertiesReaderXML.getProperty("activity", XML_FILE_PATH));

        capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, PropertiesReaderXML.getProperty("appium", XML_FILE_PATH));
        capabilities.setCapability(MobileCapabilityType.APP, PropertiesReaderXML.getProperty("appiumapp", XML_FILE_PATH));

        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
