package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Common actions for Web
 */
public class UIActions extends CommonOps
{
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
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            element.sendKeys(ch + "");
        }
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

    /**
     * Get the current browser's url.
     * @return the current url.
     */
    public static String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
}
