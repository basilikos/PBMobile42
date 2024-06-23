import config.AppiumConfig;
import enums.ContactField;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    @Test
    public void addNewContactPositive() {
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        Contact contact = new Contact();
        contact.setName(NameAndLastNameGenerator.generateName());
        contact.setLastName(NameAndLastNameGenerator.generateLastName());
        contact.setEmail(EmailGenerator.generateEmail(5, 5, 3));
        contact.setPhone(PhoneNumberGenerator.generatePhoneNumber());
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("05062024_1");

        ContactListScreen contactListScreen = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .submitContact(true);

        Assert.assertTrue(contactListScreen.isContactAdded(contact));
    }

//    @Test
//    public void addNewContactNegative() {
//
//        new SplashScreen(driver).switchToAuthenticationScreen()
//                .fillEmailField("asd20032024@gmail.com")
//                .fillPasswordField("Ghbrjk123$")
//                .clickLoginButton();
//
//        Contact contact = ContactGenerator.createIncorrectContact(
//                ContactField.PHONE,
//                "123"
//        );
//
//        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
//                .openNewContactForm()
//                .fillTheForm(contact)
//                .submitContact();
//
//        Assert.assertTrue(addNewContactScreen.isThisTheAddNewContactScreen());
//
//    }

    //    19062024
    @Test
    public void addNewContactNegative() {
        new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();
        Contact contact = ContactGenerator.createIncorrectContact(
                ContactField.PHONE,
                "123"
        );
        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .submitContact(false);
        Assert.assertTrue(addNewContactScreen.isTheErrorScreenPresent("phone"));
    }

    @Test
    public void addNewContacts() {

        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("asd20032024@gmail.com")
                .fillPasswordField("Ghbrjk123$")
                .clickLoginButton();

        new ContactListScreen(driver).addMultipleContacts(10);

    }

}
