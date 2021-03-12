package pageObjects.webPages.controls;

import extensions.WaitActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.photoThumbnails.PhotoThumbBase;
import utilities.CommonOps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The area that holds photo's thumbnails
 */
public class ThumbsControl extends CommonOps
{
    @FindBy(xpath = "//div[contains(@class, 'ReactVirtualized')]//img[contains(@src, 'https://')]/../../..")
    private List<WebElement> list_photoThumbs;

    /**
     * @return A list of all photo/images thumbnails displayed on page
     */
    public List<WebElement> getListThumbsElements()
    {
        return WaitActions.waitForVisibilityOfAllElements(list_photoThumbs);
    }

    /**
     * @param <T> Type of thumb
     * @param thumbType Type of thumb (class) like AlbumThumb/PhotoThumb
     * @return A list of thumbs found on page
     * @throws Exception ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
     */
    public <T> List<T> getListThumbs(Class<T> thumbType) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        List<WebElement> list_el = getListThumbsElements();

        List<T> list_PT = new ArrayList<>();

        for (WebElement el: list_el)
        {
            list_PT.add(newInstance(thumbType, el));
        }
        return list_PT;
    }

    /**
     * @param classType the thumbs class, like AlbumThumb / PhotoThumb
     * @param thumbName the thumb name that displays under the image
     * @param <T> Type of thumb
     * @return A thumb object by its name
     * @throws Exception Exception
     */
    public <T> T getThumb (Class<T> classType, String thumbName) throws Exception
    {
        for (PhotoThumbBase thumb : getListThumbs(PhotoThumbBase.class))
        {
            if (thumb.getName().equals(thumbName))
            {
                return newInstance (classType, thumb.element);
            }
        }
        return null;
    }
}
