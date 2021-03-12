package pageObjects.webPages.photoThumbnails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import static extensions.WaitActions.waitForVisibilityOf;

public class PhotoThumbBase extends CommonOps
{
    public WebElement element;
    private WebElement labelEl;
    public WebElement img;

    public PhotoThumbBase(WebElement _element)
    {
        element = _element;
        labelEl = element.findElement(By.xpath("//div[@data-qa-node='TextWrapper']/div[@data-qa-node='Title'] | //div[@data-qa-node='Label']"));
        img = waitForVisibilityOf(element.findElement(By.tagName("img")));
    }

    /**
     * @return the name of the photo
     */
    public String getName()
    {
        return labelEl.getText();
    }
}
