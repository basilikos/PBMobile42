package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class EditContactScreen extends BaseScreen {

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

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;

    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    MobileElement updateButton;

    public EditContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ContactListScreen submitEditContact() {
        updateButton.click();
        return new ContactListScreen(driver);
    }

    public EditContactScreen editEmailField(String text) {
        waitForAnElement(updateButton);
        inputEmailField.clear();
        inputEmailField.sendKeys(text);
        return this;
    }

    public EditContactScreen editContactFields(ContactFields contactFieldName, String text) {
        waitForAnElement(updateButton);
        switch (contactFieldName) {
            case NAMEFIELD:
                inputNameField.clear();
                inputNameField.sendKeys(text);
                break;
            case LASTNAMEFIELD:
                inputLastNameField.clear();
                inputLastNameField.sendKeys(text);
                break;
            case EMAILFIELD:
                inputEmailField.clear();
                inputEmailField.sendKeys(text);
                break;
            case PHONEFIELD:
                inputPhoneField.clear();
                inputPhoneField.sendKeys(text);
                break;
            case ADDRESSFIELD:
                inputAddressField.clear();
                inputAddressField.sendKeys(text);
                break;
            case DESCRIPTIONFIELD:
                inputDescriptionField.clear();
                inputDescriptionField.sendKeys(text);
                break;
            default:
                System.out.println("Invalid contactFieldName value. Use enum ContactFields.");
                break;
        }
        System.out.println(contactFieldName + " has been edited.");
        return this;
    }
}
