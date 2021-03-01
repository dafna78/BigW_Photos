package pageObjects.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

/**
 * A single product item on the search result page
 */
public class ProductItem extends CommonOps
{
    private WebElement element;
    private WebElement thumbnailEl;
    private WebElement priceEl;
    private WebElement productNameEl;
    private WebElement btnShopNow;

    public ProductItem(WebElement _element)
    {
        element = _element;
        thumbnailEl = element.findElement(By.xpath(".//div[@class='thumb']/a"));
        priceEl = element.findElement(By.className("price"));
        productNameEl = element.findElement(By.xpath(".//span[@class='details']//a"));
        btnShopNow = element.findElement(By.xpath(".//a[contains(@class, 'btn btn-orange')]"));
    }

    /**
     * @return the name of the product
     */
    public String getName()
    {
        return productNameEl.getText();
    }

}
