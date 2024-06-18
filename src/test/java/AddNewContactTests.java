import config.AppiumConfig;
import enums.ContactField;
import helpers.*;
import jdk.jfr.Description;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    String phoneNumberErrorTextExpected = "{phone=Phone number must contain only digits! And length min 10, max 15!}";

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
                .submitContact();

        Assert.assertTrue(contactListScreen.isContactAdded(contact));
    }

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
                .submitContact();

        Assert.assertTrue(addNewContactScreen.isThisTheAddNewContactScreen());

    }

    @Test
    @Description("Phone number must contain only digits! And length min 10, max 15!")
    public void addNewContactNegativePhoneNumber() {

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
                .submitContact();

        String phoneNumberErrorTextActual = addNewContactScreen.getPhoneNumberErrorText();
        Assert.assertEquals(phoneNumberErrorTextActual, phoneNumberErrorTextExpected);

    }
}
