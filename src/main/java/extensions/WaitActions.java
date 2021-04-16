package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonOps;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Wait actions class
 */
public class WaitActions extends CommonOps
{
    /**
     * This is just a template for future wait methods
     */
    /*public ExpectedCondition<Boolean> NumberOfElements(List<WebElement> elements, int expected)
    {
        return new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                try
                {
                    //driver.findElement(LEAVE_BTN);
                    return true;
                }
                catch (NoSuchElementException ign)
                {
                    //driver.findElement(CONTENT).click();
                }
                return false;
            }
        };
    }*/

    public static void waitAlert()
    {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void sleepUninterruptibly(long milliseconds)
    {
        Uninterruptibles.sleepUninterruptibly(milliseconds, TimeUnit.MILLISECONDS);
    }

    public static List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElements)
    {
        try
        {
            return wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        }
        catch (Exception e)
        {
            System.out.println("Not all elements are visible after " + getData("TimeOut") + " seconds.");
            return null;
        }
    }

    public static void waitForInvisibilityOf(WebElement webElement)
    {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static WebElement waitForVisibilityOf(WebElement webElement)
    {
        try
        {
            return wait.until(ExpectedConditions.visibilityOf(webElement));
        }
        catch (Exception e)
        {
            System.out.println("Element is not visible after " + getData("TimeOut") + " seconds.");
            return null;
        }
    }

    public static WebElement waitForVisibilityOf(By by)
    {
        try
        {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (Exception e)
        {
            System.out.println("Element is not visible after " + getData("TimeOut") + " seconds.");
            return null;
        }
    }

    public static void waitElementNotToBeClickable(WebElement webElement)
    {
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
    }
}
