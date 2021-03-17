package pageObjects.webPages.navigationBars;

import extensions.Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopNavBar
{
    @FindBy(xpath = "//div[@data-qa-node='TopWrapper']")
    private WebElement el_topWrapper;

    /**
     * @param buttonName The navigation bar button name from enum
     * @return the button element
     */
    public WebElement getButton(Enums.TopNavBarButtons buttonName)
    {
        String sButtonName = buttonName.getButtonName();
        try
        {
            return el_topWrapper.findElement(By.xpath(".//button[contains(text(), '" + sButtonName + "')]"));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * @return true / false if the Sign In button displays. (otherwise the Sign Out button displays)
     */
    public boolean isInSignInState()
    {
        return getButton(Enums.TopNavBarButtons.SignIn) != null;
    }
}
