package screens;

import enums.ContactField;
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

    // 19062024
    public EditContactScreen editField(ContactField field, String text) {
        MobileElement fieldElement = getFieldElement(field);
        waitForAnElement(fieldElement);
        fieldElement.clear();
        fieldElement.sendKeys(text);
        return this;
    }

    // 19062024
    private MobileElement getFieldElement(ContactField field) {
        switch (field) {
            case NAME:
                return inputNameField;
            case LASTNAME:
                return inputLastNameField;
            case EMAIL:
                return inputEmailField;
            case PHONE:
                return inputPhoneField;
            case ADDRESS:
                return inputAddressField;
            case DESCRIPTION:
                return inputDescriptionField;
            default:
                throw new IllegalArgumentException("Invalid field..." + field);
        }
    }
}
