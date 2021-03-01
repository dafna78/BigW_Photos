package extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utilities.CommonOps;
import workflows.WebFlows;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Wait actions class
 */
public class WaitActions extends CommonOps
{
    /**
     * This is just a template for future wait methods
     * @param elements template
     * @param expected template
     * @return template
     */
    public ExpectedCondition<Boolean> NumberOfElements(List<WebElement> elements, int expected)
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
    }
}
