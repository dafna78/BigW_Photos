package pageObjects.WebPages;

import extensions.Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Header elements
 */
public class HeaderPage
{
    @FindBy(xpath = "//input[contains(@class, 'siteSearchInput')]")
    public WebElement txt_searchField;

    @FindBy(xpath = "//button[contains(@class, 'siteSearchSubmit btn')]")
    public WebElement btn_search;

    @FindBy(id = "get-started")
    public WebElement btn_loginRegister;
}
