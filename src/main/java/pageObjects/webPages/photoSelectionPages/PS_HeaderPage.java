package pageObjects.webPages.photoSelectionPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Photo Selection Header elements
 */
public class PS_HeaderPage
{
    @FindBy(xpath = "//div[contains(@class, 'src__Box-sc-1sbtrzs-0 sc-tilXH fgSCFR')][1]/*")
    public WebElement btn_photoSource;

    @FindBy(xpath = "//div[contains(@class, 'src__Box-sc-1sbtrzs-0 sc-tilXH fgSCFR')][2]/*")
    public WebElement btn_selectedPhotos;

    @FindBy(xpath = "//h1[@id='design-screen-title']/following-sibling::button[contains(@class, 'PointerButton')]")
    public WebElement btn_openMenu;

    @FindBy(xpath = "//span[contains(text(), 'PHOTOS TO PROJECT')]/..")
    public WebElement btn_addXphotosToProject;


}
