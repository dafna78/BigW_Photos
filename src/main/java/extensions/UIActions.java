package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;
import utilities.SystemOps;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static extensions.WaitActions.sleepUninterruptibly;
import static extensions.WaitActions.waitAlert;

/**
 * Common actions for Web
 */
public class UIActions extends CommonOps
{
    public static void focusWindow()
    {
        ((JavascriptExecutor) driver).executeScript("window.focus();");
    }

    public void focusElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
    }

    /**
     * Click on element.
     * @param element WebElement to click on.
     */
    @Step("Click on element")
    public static void click(WebElement element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Send text to the element.
     * @param element WebElement to send the text to.
     * @param text the text to set.
     */
    @Step("Set element's text")
    public static void setText(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public static String getText(WebElement element)
    {
        return isElementVisible(element)? element.getText() : null;
    }

    public static String getText(By by)
    {
        WebElement element = WaitActions.waitForVisibilityOf(by);
        return (element != null)? element.getText() : null;
    }

    /**
     * Clear text field send text to the element.
     * @param element WebElement to send the text to.
     * @param text the text to set.
     */
    @Step("Set element's text after clearing teh field")
    public static void clearAndSetText(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.click();
        element.sendKeys(text);
    }

    /**
     * Clear text field send text to the element.
     * @param element WebElement to send the text to.
     * @param text the text to set.
     */
    @Step("Set element's text after clearing teh field")
    public static void clearAndTypeText(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        setTextAsHuman(element, text);
    }

    /**
     * Sends special keys to the element.
     * @param element WebElement to send the text to.
     * @param key key to send of type Keys.
     */
    @Step("Send key")
    public static void sendKeys(WebElement element, Keys key)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(key);
    }

    /**
     * Type element's text in the paste of a human.
     * @param element WebElement to type the text in.
     * @param text the text to type.
     */
    @Step("Type element's text as human")
    public static void setTextAsHuman(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        for (char ch : text.toCharArray())
        {
            WaitActions.sleepUninterruptibly(500);
            element.sendKeys(ch + "");
        }
    }

    @Step("Set element's value")
    public static void setValue(WebElement element, String value)
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='" + value + "';", element);
    }

    /**
     * Select an item from a drop down.
     * @param element WebElement of the drop down.
     * @param text the text to select in the drop down.
     */
    @Step("Select from drop down")
    public static void selectFromDropDown(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /**
     * Mouse hover an element.
     * @param element WebElement to hover over.
     */
    @Step("Mouse hover element")
    public static void mouseHover(WebElement element)
    {
        action.moveToElement(element);
        action.build().perform();
    }

    /**
     * Mouse hover an element and click on the element.
     * @param element WebElement to hover over and click.
     */
    @Step("Mouse hover element and click")
    public static void mouseHoverAndClick(WebElement element)
    {
        action.moveToElement(element).click();
        action.build().perform();
    }

    /**
     * Mouse hover a list of elements.
     * @param elements a list of elements to hover over.
     */
    @Step("Mouse hover number of elements")
    public static void mouseHoverElements(List<WebElement> elements)
    {
        for (WebElement element : elements)
        {
            action.moveToElement(element);
        }
        action.build().perform();
    }


    @Step("Upload file")
    public static void uploadFile(String filePath) throws IOException
    {
        //Copy the file path to the clipboard
        SystemOps.setClipboardData(filePath);

        //Execute the exe
        Runtime.getRuntime().exec(getData("ScriptFiles")+ "uploadFile.exe");

        sleepUninterruptibly(2000);
    }
    /**
     * Get the current browser's url.
     * @return the current url.
     */
    public static String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    /**
     * Navigates to page
     * @param url the url to navigate to
     */
    @Step("Navigate to URL")
    public static void navigateTo(String url)
    {
        driver.get(url);
    }

    /**
     * @param webElement the found element
     * @return true / false if the webElement exists and displays
     */
    public static boolean exists(WebElement webElement)
    {
        try
        {
            if((webElement != null) && (webElement.isDisplayed()))
            {
                return true;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }

    /**
     * @param element the element
     * @return true or false if the element is visible after waiting for it to be visible
     */
    public static boolean isElementVisible(WebElement element)
    {
        return (WaitActions.waitForVisibilityOf(element) != null);
    }

    /**
     * @param by the element locator
     * @return true or false if the element is visible after waiting for it to be visible
     */
    public static boolean isElementVisible(By by)
    {
        return (WaitActions.waitForVisibilityOf(by) != null);
    }

    /**
     * Refresh the page
     */
    @Step("Refresh page")
    public static void refreshPage()
    {
        driver.navigate().refresh();
    }
}
