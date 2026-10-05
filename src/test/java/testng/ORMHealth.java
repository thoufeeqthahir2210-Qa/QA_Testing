package testng;

import com.base.Base_Class;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjectmanager.PageObjectManager;
import utility.ExcelUtility;

import java.io.FileNotFoundException;

public class ORMHealth extends Base_Class {
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws FileNotFoundException {

        return ExcelUtility.getExcelData(
                "C:\\Users\\thouf\\IdeaProjects\\QA_TESTING\\Excel\\EXCEL USERNAME&PASSWORD.xlsx",
                "Sheet1"
        );
    }

    @Test(dataProvider = "loginData")
    public static void login(String usernameValue, String passwordValue){
        launchBrowser(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("Browser"));
        launchUrl(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("url"));
        PageObjectManager.getPageObjectManager().getLoggedin().loggedin(usernameValue, passwordValue);

    }
}
