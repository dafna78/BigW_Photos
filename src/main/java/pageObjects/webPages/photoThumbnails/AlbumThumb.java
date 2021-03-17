package pageObjects.webPages.photoThumbnails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlbumThumb extends PhotoThumbBase
{
    private WebElement buttonEl;
    private WebElement numberLabelEl;
    private WebElement linkEl;

    public AlbumThumb(WebElement _element)
    {
        super(_element);
        buttonEl = element.findElement(By.tagName("button"));
        linkEl = element.findElement(By.tagName("a"));
        numberLabelEl = element.findElement(By.xpath(".//div[@data-qa-node='TextWrapper']/div[@data-qa-node='Text']"));
    }

    /**
     * @return the number of the photos in the album as '2 photos'
     */
    public String getNumberOfPhotosInAlbum()
    {
        return numberLabelEl.getText();
    }

    /**
     * Returns the Rename button on the album
     */
    public WebElement getRenameButton()
    {
        return element.findElement(By.xpath(".//div[@id=0]"));
    }

    /**
     * Returns the Delete button on the album
     */
    public WebElement getDeleteButton()
    {
        return element.findElement(By.xpath(".//div[@id=1]"));
    }

    public String getLink()
    {
        return linkEl.getAttribute("href");
    }

    /**
     * Click to open the album
     */
    public void openAlbum()
    {
        element.click();
    }

    public void openAlbumMenu()
    {
        buttonEl.click();
    }
}
