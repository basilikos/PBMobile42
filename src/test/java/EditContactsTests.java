import config.AppiumConfig;
import helpers.ContactGenerator;
import models.Contact;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class EditContactsTests extends AppiumConfig {

    @Test
    public void editAnyContactPositive() {

        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        Contact contact = ContactGenerator.createCorrectContact();

        ContactListScreen cls = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .fillTheForm(contact)
                .submitContact();

        cls.isContactAddedScroll(contact);

    }

}
