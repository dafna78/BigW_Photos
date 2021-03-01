package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pageObjects.WebPages.HeaderPage;
import utilities.CommonOps;

import java.util.ArrayList;
import java.util.List;


/**
 * Common Web flows
 */
public class WebFlows extends CommonOps
{
    /**
     * Logins to Grafana
     * @param userName user name
     * @param password password
     */
    /*@Step("Test Flow: Login")
    public static void login(String userName, String password)
    {
        String currentUrl = UIActions.getCurrentUrl();

        if(currentUrl.contains("login"))
        {
            UIActions.setText(grafanaLoginPage.txt_userName, userName);
            UIActions.setText(grafanaLoginPage.txt_password, password);
            UIActions.click(grafanaLoginPage.btn_login);

            UIActions.click(grafanaLoginPage.btn_skip);
        }
        else
        {
            System.out.println("Not at login page. Current page: " + currentUrl);
        }
    }*/

    /**
     * Navigates and selects from Grafana's left menu
     * @param element1 menu item 1 to hover over
     * @param element2 menu item 2 to click on
     */
  /*  @Step("Test Flow: Select From Left Menu")
    public static void selectFromLeftMenu(final WebElement element1, final WebElement element2)
    {
        List<WebElement>elements = new ArrayList<WebElement>(){ {add(element1); add(element2);}};

        UIActions.mouseHoverElements(elements);

        action.click();
        action.build().perform();
    }*/

    /**
     * Performs a search in the search field. Types the word in a paste of a human
     * @param searchWord the word to search
     */
    @Step("Test Flow: Perform a search")
    public static void performSearch(String searchWord)
    {
        //Enter the search word
        enterSearchWord(searchWord);

        //Click the search button
        UIActions.click(headerPage.btn_search);
    }

    /**
     * Enters a search word in the search field. Types the word in a paste of a human
     * @param searchWord the word to search
     */
    @Step("Test Flow: Enter a search word in the search field")
    public static void enterSearchWord(String searchWord)
    {
        UIActions.setTextAsHuman(headerPage.txt_searchField, searchWord);
    }
}
