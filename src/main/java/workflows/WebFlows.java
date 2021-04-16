package workflows;

import extensions.Enums;
import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.webPages.photoThumbnails.AlbumThumb;
import utilities.CommonOps;
import utilities.SystemOps;

import java.util.Set;

import static extensions.WaitActions.*;



/**
 * Common Web flows
 */
public class WebFlows extends CommonOps
{
    @Step("Test Flow: Sign in")
    public static void signIn(boolean clickSignInButton, String email, String password)
    {
        if(clickSignInButton)
            UIActions.click(topNavBarPage.getButton(Enums.TopNavBarButtons.SignIn));

        String currentUrl = UIActions.getCurrentUrl();

        if(currentUrl.contains("sign-in"))
        {
            UIActions.setText(signInPage.txt_email, email);
            UIActions.setText(signInPage.txt_password, password);
            UIActions.click(signInPage.btn_signIn);
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("sign-in")));
            //sleepUninterruptibly(2000);
        }
        else
        {
            System.out.println("Not at sign in page. Current page: " + currentUrl);
        }
    }

    @Step("Test Flow: Upload a photo from the computer")
    public static void uploadPhotoFromComputer(String photoName) throws Exception
    {
        String filePath = SystemOps.getAbsolutePath(getData("ImageRego") + photoName);

        sleepUninterruptibly(2000);

        UIActions.uploadFile(filePath);
    }

    @Step("Test Flow: Navigate to URL")
    public static void navigateToUrl(String url)
    {
        UIActions.navigateTo(url);
    }

    @Step("Test Flow: Navigate to a page by clicking a navigation button")
    public static void navigateToByNavButton(Enums.TopNavBarButtons buttonName)
    {
        UIActions.click(topNavBarPage.getButton(buttonName));
    }

    @Step("Test Flow: select existing album radio button and select the album name")
    public static void selectExistingAlbum(String albumName)
    {
        //Click existing album radio button
        UIActions.click(selectAlbumDialog.rdBtn_existingAlbum);

        //Select the album name
        UIActions.selectFromDropDown(selectAlbumDialog.slct_existingAlbumDropDown, albumName);

        //Click on 'Confirm' button
        UIActions.click(selectAlbumDialog.btn_confirm);
    }

    /**
     * Add photo to new album
     * @param albumName Album name
     */
    @Step("Test Flow: Select 'Add to new album' and enter the album name")
    public static void addToNewAlbum(String albumName)
    {
        String currentUrl = UIActions.getCurrentUrl();

        uploadToAlbumDialogBase.addToNewAlbum(albumName);

        if(currentUrl.contains("photo-selection/computer"))
        {
            //Click on 'Confirm' button
            selectAlbumDialog.confirm();
            sleepUninterruptibly(2000);
        }
    }

    /**
     * Delete an album
     * @param albumName album name (album to to delete)
     * @throws Exception Throws and exception in case the album cannot be found.
     */
    @Step("Test Flow: Delete album {0}")
    public static void deleteAlbum(String albumName) throws Exception
    {
        try
        {
            //Get the relevant album
            AlbumThumb albumThumb = myPhotosPage.getAlbumThumb(albumName);

            deleteAlbum(albumThumb);
        }
        catch (Exception e)
        {
            throw new Exception("Cannot delete album '" + albumName + "'. An album with such name cannot be found");
        }
    }

    /**
     * Delete album
     * @param albumThumb Album to delete
     */
    @Step("Test Flow: Delete album")
    public static void deleteAlbum(AlbumThumb albumThumb)
    {
        //Click to open the album menu
        albumThumb.openAlbumMenu();

        //Click the delete button
        UIActions.click(albumThumb.getDeleteButton());

        //Click Delete album to confirm
        deleteAlbumDialog.deleteAlbum();

        sleepUninterruptibly(2000);
    }

    /**
     * Rename an album
     * @param oldAlbumName album name (album to to rename)
     * @param newAlbumName the new album name
     * @throws Exception Throws and exception in case the album cannot be found.
     */
    @Step("Test Flow: Rename album")
    public static void renameAlbum(String oldAlbumName, String newAlbumName) throws Exception
    {
        try
        {
            //Get the relevant album
            AlbumThumb albumThumb = myPhotosPage.getAlbumThumb(oldAlbumName);

            renameAlbum(albumThumb, newAlbumName);
        }
        catch (Exception e)
        {
            throw new Exception("Cannot rename album '" + oldAlbumName + "'. An album with such name cannot be found");
        }
    }

    /**
     * Rename album
     * @param albumThumb Album to rename
     * @param newAlbumName the new album name
     */
    @Step("Test Flow: Rename album")
    public static void renameAlbum(AlbumThumb albumThumb, String newAlbumName)
    {
        //Click to open the album menu
        albumThumb.openAlbumMenu();

        //Click the rename button
        UIActions.click(albumThumb.getRenameButton());

        //Set the new album name
        renameAlbumDialog.renameAlbum(newAlbumName);

        //Save the changes
        renameAlbumDialog.saveChanges();

        sleepUninterruptibly(2000);
    }

    /**
     * Open album
     * @param albumName album name to open
     * @throws Exception exception if album cannot be found
     */
    @Step("Test Flow: Open album")
    public static void openAlbum(String albumName) throws Exception
    {
        AlbumThumb albumThumb = myPhotosPage.getAlbumThumb(albumName);

        //Open album
        albumThumb.openAlbum();
    }

    /**
     * @param numberOfPhotos the number of photos that were uploaded
     * @return The expected text on 'Add photos to prpject' button on Photo selection page
     */
    public static String getExpectedTextOnAddPhotosButton(int numberOfPhotos)
    {
        return String.join(" ", "ADD", String.valueOf(numberOfPhotos) ,"PHOTOS TO PROJECT");
    }

    /**
     * Create a new album in My Photos page (albums page) and uploads a photo
     */
    public static void myPhotos_uploadPhotoToNewAlbum(String albumName, String imageName) throws Exception
    {
        //Click 'Upload more photos'
        myPhotosPage.clickUploadMorePhotos();

        //Add the photo to a new album
        WebFlows.addToNewAlbum(albumName);

        //Click 'Computer' to browse files and upload the photo
        uploadPhotosToAccountDialog.clickcomputerBtn();

        uploadPhotoFromComputer(imageName);

        sleepUninterruptibly(1000);
    }

}
