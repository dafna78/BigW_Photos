import extensions.Enums.TopNavBarButtons;
import extensions.Parse;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.webPages.photoThumbnails.AlbumThumb;
import pageObjects.webPages.photoThumbnails.PhotoThumb;
import utilities.CommonOps;
import workflows.WebFlows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static extensions.Helpers.getRandomNumber;

/**
 * My Photos Page tests
 */
@Listeners(utilities.Listeners.class)
public class MyPhotosTests extends CommonOps
{
    List<String> albumsTested = new ArrayList<>();

    @Test(description = "Test01 - Verify upload photo to new album")
    @Description("This test uploads a photo to a new album with a new name and verifies the correct album was created")
    public void test01_VerifyUploadPhotoToNewAlbum() throws Exception
    {
        String imageName = "img1.jpg";
        String newAlbumName = "New Album_" + getRandomNumber(0, Parse.toInt(getData("ImgMaxRandomNumber")));
        albumsTested.add(newAlbumName);

        //region *******    Steps ****************************************************

        //Sign in
        if(topNavBarPage.isInSignInState())
            WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos' page
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        //Get current number albums
        int currentNumberOfAlbums = myPhotosPage.getNumberOfAlbums();

        //Upload to a new album
        WebFlows.myPhotos_uploadPhotoToNewAlbum(newAlbumName, imageName);

        //endregion

        //region *******    Assertions ***********************************************

        //Soft assert that the top header displays the correct number of albums
        Verifications.softVerifyEquals("Soft assert that the top header displays the correct number of albums", myPhotosPage.getNumberOfAlbums(), ++currentNumberOfAlbums);

        AlbumThumb albumThumb = myPhotosPage.getAlbumThumb(newAlbumName);

        //Assert the album was created
        Verifications.verifyExists("Assert the album " + newAlbumName +" was created", albumThumb);

        //Soft assert there is only one photo in the album
        Verifications.softVerifyEquals("Soft assert there is only one photo in the album", albumThumb.getNumberOfPhotosInAlbum(), "1 photos");

        Verifications.assertAll();

        //endregion
    }

    @Test(description = "Test02 - Verify new album is deleted")
    @Description("This test creates a new album with the name already allocated to it (today) and then deletes it and verify it was deleted.")
    public void test02_VerifyNewAlbumIsDeleted() throws Exception
    {
        String imageName = "img2.jpg";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String newAlbumName = dateFormat.format(new Date());

        //region *******    Steps ****************************************************

        //Sign in
        if(topNavBarPage.isInSignInState())
            WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos' page
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        //Click 'Upload more photos'
        WebFlows.myPhotos_uploadPhotoToNewAlbum(newAlbumName, imageName);

        //Refresh the page
        UIActions.refreshPage();

        int currentNumberOfAlbums = myPhotosPage.getNumberOfAlbums();

        //Delete the album
        WebFlows.deleteAlbum(newAlbumName);

        //endregion

        //region *******    Assertions ***********************************************

        //Verify that the top header displays the correct number of albums
        Verifications.verifyEquals("Verify that the top header displays the correct number of albums", myPhotosPage.getNumberOfAlbums(), --currentNumberOfAlbums);

        //endregion
    }

    @Test(description = "Test03 - Verify upload to new album with same name")
    @Description("This test uploads a photo to 2 new albums with the same name and verifies the correct number of albums were created")
    public void test03_VerifyUploadToNewAlbumsWithSameName() throws Exception
    {
        String imageName = "img3.jpg";
        String newAlbumName = "New Album_" + getRandomNumber(0, Parse.toInt(getData("ImgMaxRandomNumber")));
        albumsTested.add(newAlbumName);
        albumsTested.add(newAlbumName);

        //region *******    Steps ****************************************************

        //Sign in
        if(topNavBarPage.isInSignInState())
            WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos'
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        int currentNumberOfAlbumThumbs = myPhotosPage.getListThumbs(AlbumThumb.class).size();

        //Create the 1st album and upload the photo
        WebFlows.myPhotos_uploadPhotoToNewAlbum(newAlbumName, imageName);

        //Create the 2nd album and upload the photo
        WebFlows.myPhotos_uploadPhotoToNewAlbum(newAlbumName, imageName);

        //endregion

        //region *******    Assertions ***********************************************

        //Soft assert the current number of albums
        Verifications.softVerifyEquals("Soft assert the current number of albums", myPhotosPage.getListThumbs(AlbumThumb.class).size(), currentNumberOfAlbumThumbs+2);

        List<AlbumThumb> newAlbums = myPhotosPage.getListThumbs(AlbumThumb.class).stream().filter(c -> c.getName().equals(newAlbumName)).collect(Collectors.toList());

        //Verify that only 2 albums are with the same name as the new album name
        Verifications.softVerifyEquals("Verify that only 2 albums are with the same name as the new album name", newAlbums.size(), 2);

        //Soft assert there is only one photo in each album
        for (AlbumThumb album: newAlbums)
            Verifications.softVerifyEquals("Soft assert there is only one photo in album", album.getNumberOfPhotosInAlbum(), "1 photos");

        Verifications.assertAll();

        //endregion

    }

    @Test(description = "Test04 - Verify rename new album")
    @Description("This test uploads a photo to a new album then renames it and verifies the album was renamed correctly")
    public void test04_VerifyRenameNewAlbum() throws Exception
    {
        String imageName = "img4.jpg";
        String albumName = "New Album_" + getRandomNumber(0, Parse.toInt(getData("ImgMaxRandomNumber")));
        String renamedAlbumName = "Renamed Album_" + getRandomNumber(0, Parse.toInt(getData("ImgMaxRandomNumber")));
        albumsTested.add(albumName);
        albumsTested.add(renamedAlbumName);

        //region *******    Steps ****************************************************

        //Sign in
        if(topNavBarPage.isInSignInState())
            WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos' page
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        //Create the 1st album and upload a photo
        WebFlows.myPhotos_uploadPhotoToNewAlbum(albumName, imageName);

        //Create the 2nd album and upload a photo
        WebFlows.myPhotos_uploadPhotoToNewAlbum(albumName, imageName);

        //Get the current displayed albums
        int currentNumberOfAlbumThumbs = myPhotosPage.getListThumbs(AlbumThumb.class).size();

        //Rename one album
        WebFlows.renameAlbum(albumName, renamedAlbumName);

        //endregion

        //region *******    Assertions ***********************************************

         //Soft assert the current number of albums hasn't changed
        Verifications.softVerifyEquals("Soft assert the current number of albums hasn't changed after the rename", myPhotosPage.getListThumbs(AlbumThumb.class).size(), currentNumberOfAlbumThumbs);

        //Soft assert one album remained with old name and one with new name
        Verifications.softVerifyEquals("Soft assert one album remained with old name", myPhotosPage.getThumbsByName(AlbumThumb.class, albumName).size(), 1);
        Verifications.softVerifyEquals("Soft assert one album remained with new name and one with new name", myPhotosPage.getThumbsByName(AlbumThumb.class, renamedAlbumName).size(), 1);

        Verifications.assertAll();

        //endregion
    }

    @Test(description = "Test05 - Verify photo in new album")
    @Description("This test uploads a photo to a new album and verifies the photo was uploaded correctly")
    public void test05_VerifyPhotoInNewAlbum() throws Exception
    {
        String imageName = "img5.jpg";
        String newAlbumName = "New Album_" + getRandomNumber(0, Parse.toInt(getData("ImgMaxRandomNumber")));
        albumsTested.add(newAlbumName);

        //region *******    Steps ****************************************************

        //Sign in
        if(topNavBarPage.isInSignInState())
            WebFlows.signIn(true, getData("User"), getData("Password"));

        //Go to 'My Photos' page
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        WebFlows.myPhotos_uploadPhotoToNewAlbum(newAlbumName, imageName);

        //endregion

        //region *******    Assertions ***********************************************

        //Open the album
        WebFlows.openAlbum(newAlbumName);

        //Verify the correct photo was uploaded
        Verifications.softVerifyExists("Soft verify that the photo exists", albumPage.getPhotoThumb(imageName));

        //Verify the number of photos in the album is correct
        Verifications.softVerifyEquals("Verify the number of photos in the album is correct", albumPage.getThumbsByName(PhotoThumb.class, imageName).size(), 1);

        //Verify the name of the album is correct in the header
        Verifications.softVerifyEquals("Verify the name of the album is correct in the header", albumPage.getAlbumName(), newAlbumName);

        Verifications.assertAll();

        //endregion
    }

    /**
     * Delete the albums for clean up purpose
     */
    @AfterClass(description = "Deleting all used albums")
    public void deleteAllUsedAlbums()
    {
        //Go to 'My Photos' page
        WebFlows.navigateToByNavButton(TopNavBarButtons.MyPhotos);

        for (String albumName: albumsTested)
        {
            try
            {
                UIActions.refreshPage();
                WebFlows.deleteAlbum(albumName);
            }
            catch (Exception e)
            {
                continue;
            }
        }
    }
}
