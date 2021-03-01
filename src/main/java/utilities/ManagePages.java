package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.WebPages.*;

public class ManagePages extends Base
{
    /**
     * Initialise web pages elements
     */
    public static void initWebPages()
    {
        desktopMenuPage = PageFactory.initElements(driver, DesktopMenuPage.class);
        headerPage = PageFactory.initElements(driver, HeaderPage.class);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
    }
}
