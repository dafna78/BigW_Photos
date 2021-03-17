package pageObjects.webPages.dialogs;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.dialogs.UploadToAlbumDialogBase;

/**
 * Photo Selection Header elements
 */
public class SelectAlbumDialog extends UploadToAlbumDialogBase
{
    @FindBy(xpath = "//button[text()='Confirm']")
    public WebElement btn_confirm;

    @Step("Click on 'Confirm' button")
    public void confirm()
    {
        UIActions.click(btn_confirm);
    }
}
