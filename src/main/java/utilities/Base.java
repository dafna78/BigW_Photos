package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import pageObjects.webPages.*;
import pageObjects.webPages.controls.ThumbsControl;
import pageObjects.webPages.controls.ThumbsHeaderControl;
import pageObjects.webPages.dialogs.*;
import pageObjects.webPages.navigationBars.PhotoSourceNavBar;
import pageObjects.webPages.navigationBars.TopNavBar;
import pageObjects.webPages.photoSelectionPages.*;


import java.text.SimpleDateFormat;

public class Base
{
    //General
    protected static WebDriverWait wait;
    protected static Actions action;
    protected static SoftAssert softAssert;
    protected static Screen screen;
    protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    protected static final SimpleDateFormat tf = new SimpleDateFormat("HH.mm.ss");
    protected static String platform;

    //Web
    protected static WebDriver driver;


    //Page objects - web

    //Pages
    protected static PS_HeaderPage ps_HeaderPage;
    protected static PS_PhotoSource_ComputerPage ps_PhotoSourcePage;
    protected static SignInPage signInPage;
    protected static MyPhotosPage myPhotosPage;
    protected static UploadedPhotosPageBase uploadedPhotosPageBase;
    protected static AlbumPage albumPage;

    //Dialogs
    protected static SelectAlbumDialog selectAlbumDialog;
    protected static UploadPhotosToAccountDialog uploadPhotosToAccountDialog;
    protected static UploadToAlbumDialogBase uploadToAlbumDialogBase;
    protected static RenameAlbumDialog renameAlbumDialog;
    protected static DeleteAlbumDialog deleteAlbumDialog;

    //Navigation bars
    protected static TopNavBar topNavBarPage;
    protected static PhotoSourceNavBar photoSourceNavBar;

    //Controls
    protected static ThumbsControl thumbsControl;
    protected static ThumbsHeaderControl thumbsHeaderControl;

}
