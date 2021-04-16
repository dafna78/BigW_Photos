package pageObjects.webPages.dialogs;

import extensions.UIActions;
import extensions.WaitActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Photo Selection Header elements
 */
public class UploadToAlbumDialogBase
{
    @FindBy(xpath = "//div[@data-qa-node='Inner' and @open]")
    public WebElement win_dialog;

    //Existing album radio button
    @FindBy(xpath = "//input[@type='radio' and @value='existing']")
    public WebElement rdBtn_existingAlbum;

    //New album radio button
    @FindBy(xpath = "//div[@data-qa-node='Inner' and @open]//input[@type='radio' and @value='new']")
    public WebElement rdBtn_newAlbum;

    //New album text field
    @FindBy(xpath = "//label[text()='Add to new album']/following-sibling::div/input")
    public WebElement txt_newAlbumField;

    //Existing album Select
    @FindBy(xpath = "//div[@data-qa-node='SelectField']")
    public WebElement slct_existingAlbumDropDown;

    //The X button that closes the dialog
    @FindBy(xpath = "//div[@data-qa-node='Inner']//div[@data-qa-node='CloseWrapper']//div[contains(@class, 'CloseButton')]")
    public WebElement btn_xButton;

    /**
     * Closes the dialog
     */
    public void close()
    {
        UIActions.click(btn_xButton);
    }

    /**
     * Clear the 'Add to new album' text field and add the new name in human paste
     * @param albumName new album name
     */
    public void addToNewAlbum(String albumName)
    {
        WaitActions.waitForVisibilityOf(win_dialog);
        WaitActions.sleepUninterruptibly(1000);

        rdBtn_newAlbum.click();

        if(!txt_newAlbumField.getAttribute("value").equals(albumName))
        {
            txt_newAlbumField.click();
            UIActions.setValue(txt_newAlbumField, "");
            UIActions.setText(txt_newAlbumField, albumName);
        }
    }
}
