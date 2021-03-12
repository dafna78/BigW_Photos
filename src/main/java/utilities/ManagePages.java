package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.webPages.*;
import pageObjects.webPages.controls.ThumbsControl;
import pageObjects.webPages.controls.ThumbsHeaderControl;
import pageObjects.webPages.dialogs.UploadPhotosToAccountDialog;
import pageObjects.webPages.dialogs.UploadToAlbumDialogBase;
import pageObjects.webPages.navigationBars.PhotoSourceNavBar;
import pageObjects.webPages.navigationBars.TopNavBar;
import pageObjects.webPages.photoSelectionPages.PS_HeaderPage;
import pageObjects.webPages.photoSelectionPages.PS_PhotoSource_ComputerPage;
import pageObjects.webPages.dialogs.SelectAlbumDialog;

public class ManagePages extends Base
{
    /**
     * Initialise web pages elements
     */
    public static void initWebPages()
    {
        //Pages
        ps_HeaderPage = PageFactory.initElements(driver, PS_HeaderPage.class);
        ps_PhotoSourcePage = PageFactory.initElements(driver, PS_PhotoSource_ComputerPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        uploadedPhotosPageBase = PageFactory.initElements(driver, UploadedPhotosPageBase.class);
        albumPage = PageFactory.initElements(driver, AlbumPage.class);
        myPhotosPage = PageFactory.initElements(driver, MyPhotosPage.class);

        //Dialogs
        selectAlbumDialog = PageFactory.initElements(driver, SelectAlbumDialog.class);
        uploadPhotosToAccountDialog = PageFactory.initElements(driver, UploadPhotosToAccountDialog.class);
        uploadToAlbumDialogBase = PageFactory.initElements(driver, UploadToAlbumDialogBase.class);

        //Navigation bars
        photoSourceNavBar = PageFactory.initElements(driver, PhotoSourceNavBar.class);
        topNavBarPage = PageFactory.initElements(driver, TopNavBar.class);

        //Controls
        thumbsControl = PageFactory.initElements(driver, ThumbsControl.class);
        thumbsHeaderControl = PageFactory.initElements(driver, ThumbsHeaderControl.class);

    }
}
