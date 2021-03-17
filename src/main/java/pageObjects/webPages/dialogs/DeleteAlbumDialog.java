package pageObjects.webPages.dialogs;

import extensions.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Delete Album dialog
 */
public class DeleteAlbumDialog
{
    //Delete Album button on alert pop up
    @FindBy(xpath = "//button[text()='Delete Album']")
    public WebElement btn_deleteAlbum;

    @FindBy(xpath = "//button[text()='Delete Album']/following-sibling::button")
    public WebElement btn_cancel;

    /**
     * Delete the album
     */
    public void deleteAlbum()
    {
        UIActions.click(btn_deleteAlbum);
    }

    /**
     * Cancel
     */
    public void cancel()
    {
        UIActions.click(btn_cancel);
    }
}
