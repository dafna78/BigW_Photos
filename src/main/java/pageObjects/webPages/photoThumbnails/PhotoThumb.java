package pageObjects.webPages.photoThumbnails;

import extensions.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PhotoThumb extends PhotoThumbBase
{
    private WebElement buttonEl;

    public PhotoThumb(WebElement _element)
    {
        super(_element);
        buttonEl = element.findElement(By.tagName("button"));
    }


    /**
     * @return true/false if the photo is ticked
     */
    public boolean isTicked()
    {
        if(buttonEl.getAttribute("class").contains("bNyJTA"))
        {
            return UIActions.exists(buttonEl.findElement(By.xpath("./div[@data-qa-node='TickWrapper']")));
        }
        return false;
    }

}
