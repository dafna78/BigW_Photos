package pageObjects.webPages.photoSelectionPages;

import extensions.Parse;
import extensions.WaitActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.*;
import pageObjects.webPages.photoThumbnails.PhotoThumb;

import java.util.ArrayList;
import java.util.List;

/**
 * Photo Source page elements
 */
public class PS_PhotoSource_ComputerPage extends UploadedPhotosPageBase
{
    @FindBy(xpath =  "//a[@href='/photo-selection/recently-used']")
    public WebElement btn_recentlyUsed;

    @FindBy(xpath =  "//a[@href='/photo-selection/computer']")
    public WebElement btn_computer;

    @FindBy(xpath =  "//a[@href='/photo-selection/my-albums']")
    public WebElement btn_myAlbums;

    @FindBy(xpath =  "//a[@href='/photo-selection/facebook']")
    public WebElement btn_facebook;

    @FindBy(id =  "file-uploader")
    public WebElement inpt_dragAndDropArea;

    @FindBy(xpath =  "//button[contains(text(), 'Browse files')]")
    public WebElement btn_browseFiles;

    @FindBy(linkText =  "Connect")
    public WebElement btn_connectFacebook;
}
