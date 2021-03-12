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
        return el_topWrapper.findElement(By.xpath(".//button[contains(text(), '" + sButtonName + "')]"));
    }
}
