import config.AppiumConfig;
import enums.ContactField;
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
                .submitContact(true);

        cls.isContactAddedScroll(contact);
    }

    //    19062024
    @Test
    public void editAnyContactFieldPositive() {
        String newText = "16062024@gmail.com";
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        new ContactListScreen(driver)
                .editContact(0)
                .editField(ContactField.EMAIL, newText)
                .submitEditContact()
                .isContactContainsText(newText, 0);
    }

}
