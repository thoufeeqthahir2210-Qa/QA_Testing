package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Base_Class {

    public static WebDriver driver;
    public static ExtentReports extentReports;
    public static File file;

    protected static WebDriver launchBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING LAUNCHING BROWSER");
        }
        driver.manage().window().maximize();
        return driver;
    }

    protected static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING LAUNCHING URL");
        }
    }

    protected static void passInput(WebElement element, String value) {
        try {
            element.sendKeys(value);


        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING PASSING INPUT");
        }
    }

       protected static void clickElement(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            element.click();

        } catch (Exception e) {

            System.out.println("Click failed : " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());

            e.printStackTrace();

            try {
                jsClickElement(element);
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Click failed : " + ex.getMessage());
            }
        }
    }

    protected static void clearInput(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING CLEARING INPUT");
        }
    }

    protected static void submitElement(WebElement element) {
        try {
            element.submit();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING SUBMITTING ELEMENT");
        }
    }

    protected static void selectOptions(WebElement element, String type, String value) {
        Select select = new Select(element);
        try {
            if (type.equalsIgnoreCase("value")) {
                select.selectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                select.selectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.selectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING SELECTING OPTION");
        }
    }

    protected static void deselectOptions(WebElement element, String type, String value) {
        Select select = new Select(element);
        try {
            if (type.equalsIgnoreCase("value")) {
                select.deselectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                select.deselectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.deselectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING DESELECTING OPTION");
        }
    }

    protected static void getOptions(WebElement element, String type) {
        Select select = new Select(element);
        try {
            if (type.equalsIgnoreCase("alloptions")) {
                List<WebElement> allOptions = select.getOptions();
                for (WebElement option : allOptions) {
                    System.out.println(option.getText());
                }
            } else if (type.equalsIgnoreCase("allselectedoptions")) {
                List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
                for (WebElement option : allSelectedOptions) {
                    System.out.println(option.getText());
                }
            } else if (type.equalsIgnoreCase("firstselectedoption")) {
                WebElement firstSelectedOption = select.getFirstSelectedOption();
                System.out.println(firstSelectedOption.getText());
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING GET OPTION");
        }
    }

    protected static void isMultipleMethod(WebElement element, String type) {
        Select select = new Select(element);
        try {
            select.isMultiple();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING PERFORM ISMULTIPLE METHOD");
        }
    }

    protected static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING NAVIGATE TO");
        }
    }

    protected static void navigateRefresh() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING NAVIGATE REFRESH");
        }
    }

    protected static void navigateForward() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING NAVIGATE FORWARD");
        }
    }

    protected static void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING NAVIGATE BACK");
        }
    }

    protected static void windowHandles(int num) {
        try {
            List<String> allWindow = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allWindow.get(num));
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING WINDOW HANDLES");
        }
    }

    protected static void alertWindow(String value) {
        try {
            if (value.equalsIgnoreCase("accept")) {
                driver.switchTo().alert().accept();
            } else if (value.equalsIgnoreCase("dismiss")) {
                driver.switchTo().alert().dismiss();
            } else if (value.equalsIgnoreCase("gettext")) {
                String alertText = driver.switchTo().alert().getText();
                System.out.println(alertText);
            } else if (value.equalsIgnoreCase("sendkeys")) {
                driver.switchTo().alert().sendKeys(value);
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING ALERT WINDOWS");
        }
    }

    protected static void dragAndDrop(WebElement source, WebElement target) {
        try {
            Actions action = new Actions(driver);
            action.dragAndDrop(source, target).perform();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING DRAG AND DROP");
        }
    }

    protected static void robotMethod(int keycode) throws AWTException {
        Robot robot = new Robot();
        try {
            robot.keyPress(keycode);
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING ROBOT METHOD");
        }
    }

    protected static void actionMethod(WebElement element, String type) {
        Actions action = new Actions(driver);
        try {
            if (type.equalsIgnoreCase("click")) {
                action.click(element).build().perform();
            } else if (type.equalsIgnoreCase("doubleclick")) {
                action.doubleClick(element).build().perform();
            } else if (type.equalsIgnoreCase("rightclick")) {
                action.contextClick(element).build().perform();
            } else if (type.equalsIgnoreCase("movetoelement")) {
                action.moveToElement(element).build().perform();
            } else if (type.equalsIgnoreCase("clickandhold")) {
                action.clickAndHold(element).build().perform();
            } else if (type.equalsIgnoreCase("release")) {
                action.release(element).build().perform();
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING ACTION METHOD");
        }
    }

    protected static void frameMethod(WebElement element, String type) {
        try {
            if (type.equalsIgnoreCase("webelement")) {
                driver.switchTo().frame(element);
            } else if (type.equalsIgnoreCase("index")) {
                driver.switchTo().frame(element);
            } else if (type.equalsIgnoreCase("nameorid")) {
                driver.switchTo().frame(element);
            } else if (type.equalsIgnoreCase("defaultcontent")) {
                driver.switchTo().defaultContent();
            } else if (type.equalsIgnoreCase("parentframe")) {
                driver.switchTo().parentFrame();
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING FRAME METHOD");
        }
    }

    protected static void elementValidation(WebElement element, String type) {
        try {
            if (type.equalsIgnoreCase("displayed")) {
                Assert.assertTrue("ELEMENT IS NOT DISPLAYED", element.isDisplayed());
            } else if (type.equalsIgnoreCase("enabled")) {
                Assert.assertTrue("ELEMENT IS NOT ENABLED", element.isEnabled());
            } else if (type.equalsIgnoreCase("selected")) {
                Assert.assertTrue("ELEMENT IS NOT SELECTED", element.isSelected());
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING ELEMENT VALIDATION");
        }
    }

    protected static void browserMethod(String type) {
        try {
            if (type.equalsIgnoreCase("gettitle")) {
                String title = driver.getTitle();
                System.out.println(title);
            } else if (type.equalsIgnoreCase("getcurrenturl")) {
                String currentUrl = driver.getCurrentUrl();
                System.out.println(currentUrl);
            } else if (type.equalsIgnoreCase("getpagesource")) {
                String pageSource = driver.getPageSource();
                System.out.println(pageSource);
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING BROWSER METHOD");
        }
    }

    protected static void getAttributeMethod(WebElement element, String attributeName) {
        try {
            String attributeName1 = element.getAttribute("attributeName");
            System.out.println(attributeName1);
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING GET ATTRIBUTE METHOD");
        }
    }

    protected static void getTextMethod(WebElement element) {
        try {
            String text = element.getText();
            System.out.println(text);
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING GET TEXT METHOD");
        }
    }

    protected static void implicitWait(int num) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(num));
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING IMPLICIT WAIT");
        }
    }

    protected static void explicitWait(WebElement element, int time, String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        try {
            if (type.equalsIgnoreCase("visibility")) {
                wait.until(ExpectedConditions.visibilityOf(element));
            } else if (type.equalsIgnoreCase("clickable")) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } else if (type.equalsIgnoreCase("selected")) {
                wait.until(ExpectedConditions.elementToBeSelected(element));
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING EXPLICIT WAIT");
        }
    }

    protected static void fluentWaitMethod(WebElement element, int timeout, int pollingTime, String type) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);
        try {
            if (type.equalsIgnoreCase("visibility")) {
                wait.until(ExpectedConditions.visibilityOf(element));
            } else if (type.equalsIgnoreCase("clickable")) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            } else if (type.equalsIgnoreCase("selected")) {
                wait.until(ExpectedConditions.elementToBeSelected(element));
            }
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING FLUENT WAIT METHOD");
        }
    }

    protected static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center',inline:'nearest'});",element);
    }

    protected static void jsClickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected static void takeScreenshotMethod(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File("C://Users//thouf//IdeaProjects//Maven_Project//Screenshot//Demo.png");
            FileHandler.copy(source, destination);
            System.out.println("SCREENSHOT CAPTURE SUCCESSFULLY");
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING TAKING SCREENSHOT");
        }
    }

    public static void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING CLOSING BROWSER");
        }
    }

    public static void extentReportStart(String location) {
        try {
            extentReports = new ExtentReports();
            file = new File(location);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("0S", System.getProperty("os, name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("Java.version"));
        } catch (Exception e) {
            Assert.fail("ERROR OCCUR DURING EXTENT REPORT START");
        }
    }

    public static void extentReportTearDown(String location) {
        try {
            extentReports.flush();
            file = new File(location);
            Desktop.getDesktop().browse((file).toURI());
        } catch(IOException e) {
            Assert.fail("ERROR OCCUR DURING EXTENT REPORT TEAR DOWN");
        }
    }

    public String takeScreenshot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
        File destfile = new File("Screenshort\\.png" + "_" + timeStamp + " .png");
        FileHandler.copy(scrfile, destfile);
        return destfile.getAbsolutePath();
    }

}