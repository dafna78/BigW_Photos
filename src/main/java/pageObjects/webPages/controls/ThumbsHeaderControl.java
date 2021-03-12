package pageObjects.webPages.controls;

import extensions.Parse;
import extensions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.webPages.photoThumbnails.PhotoThumb;
import pageObjects.webPages.photoThumbnails.PhotoThumbBase;
import utilities.CommonOps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * The header above the thumbnails that holds the details of the number of thumbs on page
 */
public class ThumbsHeaderControl extends CommonOps
{
    @FindBy(xpath = "//p[contains(@class, 'TitleSub')]")
    private WebElement hdr_pageDetailsHeader;

    /**
     * @return The full text displays on the header like: '9 Albums' / '3 Photos'
     */
    public String getUploadedNumberText()
    {
        return hdr_pageDetailsHeader.getText();
    }

    /**
     * @return The number of photos / albums that were uploaded
     */
    public int getUploadedNumber()
    {
        return Parse.toInt(getUploadedNumberText().split(" ")[0]);
    }
}
