package pageObjects.webPages.dialogs;

import extensions.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Rename Album dialog
 */
public class RenameAlbumDialog extends UploadToAlbumDialogBase
{
    @FindBy(xpath = "//label[contains(text(), 'Album Name')]//input")
    public WebElement txt_albumNameField;

    @FindBy(xpath = "//button[contains(text(), 'Save Changes')]")
    public WebElement btn_saveChanges;

    @FindBy(xpath = "//button[contains(text(), 'Save Changes')]/following-sibling::button")
    public WebElement btn_cancel;

    /**
     * Set the new album name
     * @param albumName new album name
     */
    public void renameAlbum(String albumName)
    {
        if(!txt_albumNameField.getAttribute("value").equals(albumName))
        {
            txt_albumNameField.click();
            UIActions.setValue(txt_albumNameField, "");
            UIActions.setText(txt_albumNameField, albumName);
        }
    }

    /**
     * Save changes
     */
    public void saveChanges()
    {
        UIActions.click(btn_saveChanges);
    }

    /**
     * Cancel
     */
    public void cancel()
    {
        UIActions.click(btn_cancel);
    }
}
