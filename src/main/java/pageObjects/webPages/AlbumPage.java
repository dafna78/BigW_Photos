package pageObjects.webPages;

import extensions.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.photoThumbnails.AlbumThumb;
import pageObjects.webPages.photoThumbnails.PhotoThumb;

import java.util.ArrayList;
import java.util.List;

public class AlbumPage extends UploadedPhotosPageBase
{
    //Album name
    @FindBy(xpath = "//h2[contains(@class, 'Large')]")
    private WebElement txt_albumName;

    //Back to albums link
    @FindBy(xpath = "//h2[contains(@class, 'Small')]")
    private WebElement lnk_backToAlbums;

    /**
     * Gets a photo thumb from the list
     * @param photoName the photo's name
     * @return an object of type PhotoThumb
     */
    public PhotoThumb getPhotoThumb(String photoName) throws Exception
    {
        return thumbsControl.getThumb(PhotoThumb.class, photoName);
    }

    /**
     * @return The album's name
     */
    public String getAlbumName()
    {
        return txt_albumName.getText();
    }

    public void goBackToAlbums()
    {
        lnk_backToAlbums.click();
    }
}
