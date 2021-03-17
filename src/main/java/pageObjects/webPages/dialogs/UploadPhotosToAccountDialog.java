package pageObjects.webPages.dialogs;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static extensions.WaitActions.sleepUninterruptibly;

public class UploadPhotosToAccountDialog extends UploadToAlbumDialogBase
{
    @FindBy(xpath = "//div[@data-qa-node='Title' and text()='Computer']")
    public WebElement btn_computer;

    @Step("Click 'Computer'")
    public void clickcomputerBtn()
    {
        UIActions.click(btn_computer);
        sleepUninterruptibly(2000);
    }
}
