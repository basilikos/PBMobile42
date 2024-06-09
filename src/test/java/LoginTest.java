import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenactions.ScreenshotUtil;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {

    @Test
    public void loginPositiveTest() {
        ContactListScreen contactListScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        screenshotUtil.takeScreenShot("loginPositiveTest");
        Assert.assertTrue(contactListScreen.isContactListActivityPresent());
    }

}
