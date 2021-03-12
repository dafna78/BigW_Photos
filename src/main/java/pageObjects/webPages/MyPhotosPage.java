package pageObjects.webPages;

import org.openqa.selenium.WebElement;
import pageObjects.webPages.photoThumbnails.AlbumThumb;

import java.lang.reflect.InvocationTargetException;

/**
 * My photos page that displays all the albums: https://4.app.fujifilmimagine.com/my-photos/albums
 */
public class MyPhotosPage extends UploadedPhotosPageBase
{
    /**
     * @return The number of photos / albums that were uploaded
     */
    public int getNumberOfAlbums()
    {
        //Wait for all thumbs to be visible
        getListThumbsElements();
        return thumbsHeaderControl.getUploadedNumber();
    }

    /**
     * @return The full text displays on the header like: '9 Albums'
     */
    public String getNumberOfAlbumsText()
    {
        return thumbsHeaderControl.getUploadedNumberText();
    }

    public AlbumThumb getAlbumThumb(String albumName) throws Exception
    {
        return thumbsControl.getThumb(AlbumThumb.class, albumName);
    }
}
