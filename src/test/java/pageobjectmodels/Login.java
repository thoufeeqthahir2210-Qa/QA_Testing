package pageobjectmodels;

import com.base.Base_Class;
import interfaceelements.LoginInterfaceElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;

public class Login extends Base_Class implements LoginInterfaceElement {

    @FindBy(css = username_Css)
    public  WebElement username;
    @FindBy(css = password_Css)
    public  WebElement password;
    @FindBy(css = login_Css)
    public  WebElement login;

    public Login() {
        PageFactory.initElements(driver, this);
    }

    public void loggedin(String usernameValue, String passwordValue)
    {
        implicitWait(5);
        // passInput(username, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("Username"));
        // passInput(password, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("Password"));
        implicitWait(5);
        passInput(username, usernameValue);
        implicitWait(5);
        passInput(password, passwordValue);
        clickElement(login);
        driver.quit();
    }
}

