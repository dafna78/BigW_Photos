package pageObjects.WebPages;

import extensions.Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Menu navigation bar
 */
public class DesktopMenuPage
{
    @FindBy(xpath = "//div[@id='navmenu-fixed-left']//ul[contains(@class, \"desk-menu\")]//a[text() = 'Calendars']")
    public WebElement el_menu;

    @FindBy(xpath = "//div[@id='navmenu-fixed-left']//ul[contains(@class, \"desk-menu\")]/li/a")
    public List<WebElement> list_MenuLinks;

    public WebElement getMenuButton(Enums.DesktopMenuButtons buttonName) throws Exception
    {
        try
        {
            WebElement button = el_menu.findElement(By.xpath("//a[text() = '" + buttonName.getButtonName() + "']"));
            return button;
        }
        catch (Exception e)
        {
            throw new Exception("Cannot find button '" + buttonName.getButtonName() + "' on menu nav bar");
        }
    }
}
