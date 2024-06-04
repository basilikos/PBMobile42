import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {

    @Test
    public void loginPositiveTest() {
        ContactListScreen contactListScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        Assert.assertTrue(contactListScreen.isContactListActivityPresent());
    }

}
