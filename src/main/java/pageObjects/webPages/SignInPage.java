package pageObjects.webPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage
{
    @FindBy(name = "email")
    public WebElement txt_email;

    @FindBy(name = "password")
    public WebElement txt_password;

    @FindBy(xpath = "//button[@data-test-id='sign-in-button']")
    public WebElement btn_signIn;
}
