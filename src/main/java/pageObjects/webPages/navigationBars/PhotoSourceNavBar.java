package pageObjects.webPages.navigationBars;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.UploadedPhotosPageBase;
import utilities.CommonOps;

/**
 * Photo Source pages navigation buttons
 */
public class PhotoSourceNavBar extends CommonOps
{
    @FindBy(xpath =  "//a[@href='/photo-selection/recently-used']")
    public WebElement btn_recentlyUsed;

    @FindBy(xpath =  "//a[@href='/photo-selection/computer']")
    public WebElement btn_computer;

    @FindBy(xpath =  "//a[@href='/photo-selection/my-albums']")
    public WebElement btn_myAlbums;

    @FindBy(xpath =  "//a[@href='/photo-selection/facebook']")
    public WebElement btn_facebook;

    /*@FindBy(id =  "file-uploader")
    public WebElement inpt_dragAndDropArea;

    @FindBy(xpath =  "//button[contains(text(), 'Browse files')]")
    public WebElement btn_browseFiles;

    @FindBy(linkText =  "Connect")
    public WebElement btn_connectFacebook;*/
}
