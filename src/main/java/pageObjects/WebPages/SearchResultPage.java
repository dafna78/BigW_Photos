package pageObjects.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Header elements
 */
public class SearchResultPage
{
    @FindBy(tagName = "h1")
    public WebElement header_searchResults;

    @FindBy(className = "productGridItem ")
    public List<WebElement> list_products;

    @FindBy(xpath = "//li[contains(@class,'next')][1]")
    public WebElement btn_nextArrowTop;

    @FindBy(xpath = "//li[contains(@class,'prev')][1]")
    public WebElement btn_prevArrowTop;

    @FindBy(xpath = "//li[contains(@class,'next')][2]")
    public WebElement btn_nextArrowBottom;

    @FindBy(xpath = "//li[contains(@class,'prev')][2]")
    public WebElement btn_prevArrowBottom;

    /**
     * @return a list of displayed items on search result page, as ProductItem objects
     */
    public List<ProductItem> getProductsList()
    {
        List<ProductItem> products = new ArrayList<>();

        for(WebElement item : list_products)
        {
            products.add(new ProductItem(item));
        }
        return products;
    }
}
