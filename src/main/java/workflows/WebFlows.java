package workflows;

import extensions.Enums;
import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonOps;
import utilities.SystemOps;

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
    @Step("Test Flow: select Add to new album and enter the album name")
    public static void addToNewAlbum(String albumName)
    {
        String currentUrl = UIActions.getCurrentUrl();

        uploadToAlbumDialogBase.addToNewAlbum(albumName);

        if(currentUrl.contains("photo-selection/computer"))
        {
            //Click on 'Confirm' button
            UIActions.click(selectAlbumDialog.btn_confirm);
            sleepUninterruptibly(2000);
        }
    }

    @Step("Test Flow: Delete album")
    public static void deleteAlbum(String albumName)
    {
        //UIActions.click();
    }

    /**
     * @param numberOfPhotos the number of photos that were uploaded
     * @return The expected text on 'Add photos to prpject' button on Photo selection page
     */
    public static String getExpectedTextOnAddPhotosButton(int numberOfPhotos)
    {
        return String.join(" ", "ADD", String.valueOf(numberOfPhotos) ,"PHOTOS TO PROJECT");
    }


}
