import config.AppiumConfig;
import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.io.IOException;

public class RemoveContactTest extends AppiumConfig {

    @Test
    public void removeContactPositive() throws InterruptedException, IOException {
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        Contact contact = new Contact();
        contact.setName(NameAndLastNameGenerator.generateName());
        contact.setLastName(NameAndLastNameGenerator.generateLastName());
        contact.setEmail(EmailGenerator.generateEmail(5, 5, 3));
        contact.setPhone(PhoneNumberGenerator.generatePhoneNumber());
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("05062024_2");

//        19062024
        toggleInternet(false);
        Thread.sleep(7000);

        contactListScreen.openNewContactForm()
                .fillTheForm(contact)
                .submitContact(true);
//        contactListScreen.removeAContact(); // 16062024
//        Assert.assertTrue(contactListScreen.removeAContact().isContactRemoved()); // 16062024

        toggleInternet(true); // 19062024

//        16062024
        Assert.assertTrue(contactListScreen
                .removeAContact()
                .isContactRemovedList(contact.getName(), contact.getLastName(), contact.getPhone())
        );
    }

    //    16062024
    @Test
    public void removeAllContactsTest() {

        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        Assert.assertTrue(contactListScreen.removeAllContacts().isContactRemoved());

    }

}
