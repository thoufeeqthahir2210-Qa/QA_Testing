package pageobjectmanager;

import pageobjectmodels.Login;
import utility.FileReaderManager;

public class PageObjectManager {
    private FileReaderManager fileReaderManager;
    private static PageObjectManager pageObjectManager;
    private static Login loggedin;

    public static Login getLoggedin() {
        if (loggedin == null){
            loggedin = new Login();
        }
        return loggedin;
    }

    public FileReaderManager getFileReaderManager() {
        if (fileReaderManager == null) {
            fileReaderManager = new FileReaderManager();
        }
        return fileReaderManager;
    }

    public static PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager();
        }
        return pageObjectManager;
    }
}
