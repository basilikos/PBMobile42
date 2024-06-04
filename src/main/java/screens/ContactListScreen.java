package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen {

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleViewTest;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactButton;

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isContactListActivityPresent() {
        return isElementPresent(titleViewTest, "Contact list", 5);
    }
}
