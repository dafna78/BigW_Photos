package pageObjects.webPages.dialogs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadPhotosToAccountDialog extends UploadToAlbumDialogBase
{
    @FindBy(xpath = "//div[@data-qa-node='Title' and text()='Computer']")
    public WebElement btn_computer;
}
