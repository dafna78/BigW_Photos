import extensions.Enums.*;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.webPages.photoThumbnails.AlbumThumb;
import utilities.CommonOps;
import workflows.WebFlows;

import static extensions.Helpers.getRandomNumber;

/**
 * My Photos Page tests
 */
@Listeners(utilities.Listeners.class)
public class MyPhotosTests extends CommonOps
{
    @Test(description = "Test01 - Verify upload photo to new album")
    @Description("This test uploads a photo to a new album and verifies the correct album was created and photo was uploaded")
    public void test01_VerifyUploadPhotoToNewAlbum() throws Exception
    {
        String imageName = "img2.jpg";
        String newAlbumName = "New Album_" + getRandomNumber(0, 100);

        int currentNumberOfAlbums;

        //Sign in
        WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos'
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        currentNumberOfAlbums = myPhotosPage.getNumberOfAlbums();

        //Click 'Upload more photos'
        UIActions.click(myPhotosPage.btn_uploadMorePhotos);

        //Add the photo to a new album
        WebFlows.addToNewAlbum(newAlbumName);

        //Click 'Computer' to browse files and upload the photo
        UIActions.click(uploadPhotosToAccountDialog.btn_computer);

        //Windows browsing window to select the image
        WebFlows.uploadPhotoFromComputer(imageName);

        //Soft assert that the top header displays the correct number of albums
        Verifications.softVerifyEquals(myPhotosPage.getNumberOfAlbums(), ++currentNumberOfAlbums);

        AlbumThumb albumThumb = myPhotosPage.getAlbumThumb(newAlbumName);

        //Assert the album was created
        Verifications.verifyExists(albumThumb);

        //Soft assert there is only one photo in the album
        Verifications.softVerifyEquals(albumThumb.getNumberOfPhotosInAlbum(), "1 photos");

        //Open album
        albumThumb.open();

        //Verify the correct photo was uploaded
        Verifications.softVerifyExists((albumPage.getPhotoThumb(imageName)));

        //Verify the name of the album is correct in the header
        Verifications.softVerifyEquals(albumPage.getAlbumName(), newAlbumName);

        Verifications.assertAll();
    }

    /*//Add the photo to a new album
        WebFlows.addToNewAlbum(newAlbumName);

    //Soft assert that the button at the bottom displays the correct number of photos
        Verifications.softVerifyEquals(ps_HeaderPage.btn_addXphotosToProject.getText(), WebFlows.getExpectedTextOnAddPhotosButton(1));

    //Soft assert the correct image was uploaded
        Verifications.softVerifyExists(ps_PhotoSourcePage.getPhotoThumb(imageName));

        Verifications.assertAll();*/

    /*@Test(description = "Test02 - Verify search auto complete")
    @Description("This test types a word in the search field and verifies the auto complete field")
    public void test02_VerifySearchAutoComplete()
    {
        String searchWord = "Pencil";

        Verifications.invisibilityOfElement(headerPage.el_searchAutoComplete);

        WebFlows.enterSearchWord(searchWord);

        Verifications.visibilityOfElement(headerPage.el_searchAutoComplete);
    }*/

    /*@Test(description = "Test01 - Verify Home Page Header")
    @Description("This test logins to Grafana and verifies the home page header's text")
    public void test01_VerifyHomePageHeader()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.verifyTextInElement(grafanaHomePage.header_welcome,"Welcome to Grafana");
    }*/

    /*@Test(description = "Test02 - Verify Default Users")
    @Description("This test navigates to Users page from left menu and verifies the default user")
    public void test02_VerifyDefaultUsers()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, 99);
    }

    @Test(description = "Test03 - Verify New User")
    @Description("This test navigates to Users page from left menu, ads a new user and verifies it was added")
    public void test03_VerifyNewUser()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        WebFlows.createNewUser("daf", "gen", "d@g.com", "12345");

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, 2);

        WebFlows.deleteLastUser();
    }
    @Test(description = "Test04 - Verify User Deletion")
    @Description("This test navigates to Users page from left menu, deletes a user and verifies it was deleted")
    public void test04_VerifyUserDeletion()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        int numberOfUsers = WebFlows.getNumberOfUsers();

        //If there is only 1 user, create a new one to prevent deleting the admin
        if(numberOfUsers == 1)
            WebFlows.createNewUser("daf", "gen", "d@g.com", "12345");

        numberOfUsers = WebFlows.getNumberOfUsers();

        WebFlows.deleteLastUser();

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, --numberOfUsers);
    }

    @Test(description = "Test05 - Verify Home Page Help Links Display")
    @Description("This test verifies all 'Help links' display on home page")
    public void test05_VerifyHomePageHelpLinksDisplay()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.visibilityOfElements(grafanaHomePage.list_needHelpLinks);
    }

    @Test(description = "Test06 - Verify Avatar Icon")
    @Description("This test verifies the Avatar icon using Sikuli tool")
    public void test06_VerifyAvatarIcon()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.visualElement("grafanaAvatar");
    }

    @Test(description = "test07 - Search Users", dataProvider = "data-provider-users", dataProviderClass = utilities.ManageDDT.class)
    @Description("This test searches using Data Driven Testing")
    public void test07_SearchUsers(String user, String sExpectedNumberOfUsers)
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        WebFlows.searchUser(user);

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, Parse.toInt(sExpectedNumberOfUsers));
    }*/
}
