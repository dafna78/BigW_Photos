package pageObjects.webPages;

import extensions.Parse;
import extensions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.controls.ThumbsControl;
import utilities.CommonOps;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Page that displays the uploaded photos
 */
public class UploadedPhotosPageBase extends CommonOps
{
    //Upload more photos button
    @FindBy(xpath = "//div[text()='Upload More Photos']")
    public WebElement btn_uploadMorePhotos;

    /**
     * @return A list of all photo/images thumbnails displayed on page
     */
    public List<WebElement> getListThumbsElements()
    {
        return thumbsControl.getListThumbsElements();
    }

    public <T> List<T> getListThumbs(Class<T> thumbType) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        return thumbsControl.getListThumbs(thumbType);
    }
}
