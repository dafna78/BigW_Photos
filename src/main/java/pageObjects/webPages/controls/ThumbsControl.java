package pageObjects.webPages.controls;

import extensions.WaitActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.photoThumbnails.AlbumThumb;
import pageObjects.webPages.photoThumbnails.PhotoThumbBase;
import utilities.CommonOps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        try
        {
            return WaitActions.waitForVisibilityOfAllElements(list_photoThumbs);
        }
        catch (Exception e)
        {
            if(list_photoThumbs.size() == 0)
                return list_photoThumbs;
            else
                throw e;
        }
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

    public <T> List<T> getThumbsByName (Class<T> classType, String thumbName) throws Exception
    {
        List<PhotoThumbBase> photoThumbsFilteredListByName = getListThumbs(PhotoThumbBase.class).stream().filter(c -> c.getName().equals(thumbName)).collect(Collectors.toList());

        List<T> listToReturn = new ArrayList<>();

        for (PhotoThumbBase thumb : photoThumbsFilteredListByName)
        {
            listToReturn.add(newInstance (classType, thumb.element));
        }
        return listToReturn;
    }
}
