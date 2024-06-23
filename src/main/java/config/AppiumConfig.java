package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumConfig {

    public static AppiumDriver<MobileElement> driver;
    private boolean isNetworkSimulated = false;

    @BeforeSuite
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "mobqa42");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
        capabilities.setCapability("appActivity", ".SplashActivity");

        capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/zok/Documents/_TelRan/_dist/contacts-android.apk");

        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        19062024
//        isNetworkSimulated = simulateWeakNetwork();

    }

    @AfterSuite
    public void tearDown() {
        if (isNetworkSimulated) {
            resetNetwork();
        }
        driver.quit();
    }

    //    19062024
    public boolean simulateWeakNetwork() {
        try {
            String[] command = {"adb", "shell", "network", "speed", "gsm"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO().start().waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //    19062024
    public void resetNetwork() {
        try {
            String[] command = {"adb", "shell", "network", "speed", "full"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    19062024
    public void toggleInternet(boolean enable) throws IOException, InterruptedException {
        String[] commandData = {"adb", "shell", "svc", "data", enable ? "enable" : "disable"};
        String[] commandWiFi = {"adb", "shell", "svc", "data", enable ? "enable" : "disable"};

        executeCommand(commandData);
        executeCommand(commandWiFi);

        System.out.println("Internet " + (enable ? "enabled" : "disabled"));
    }

    //    19062024
    public void executeCommand(String[] command) throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        process.waitFor();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}
