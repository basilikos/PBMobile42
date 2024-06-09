package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen {

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement inputNameField;

    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement inputLastNameField;

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;

    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement inputPhoneField;

    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement inputAddressField;

    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement inputDescriptionField;

    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public AddNewContactScreen fillTheForm(Contact contact) {

        waitForAnElement(createButton);

        inputNameField.sendKeys(contact.getName());
        driver.hideKeyboard();
        inputLastNameField.sendKeys(contact.getLastName());
        driver.hideKeyboard();
        inputEmailField.sendKeys(contact.getEmail());
        driver.hideKeyboard();
        inputPhoneField.sendKeys(contact.getPhone());
        driver.hideKeyboard();
        inputAddressField.sendKeys(contact.getAddress());
        driver.hideKeyboard();
        inputDescriptionField.sendKeys(contact.getDescription());
        driver.hideKeyboard();

        return new AddNewContactScreen(driver);
    }

    public ContactListScreen submitContact() {
        createButton.click();
        return new ContactListScreen(driver);
    }
}
