import extensions.Parse;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;

/**
 * Grafana Web tests
 */
@Listeners(utilities.Listeners.class)
public class SearchTests extends CommonOps
{
    @Test(description = "Test01 - Verify Results Page Header")
    @Description("This test performs a search in the search field and verifies the results' page header's text")
    public void test01_VerifySearchHeader()
    {
        String searchWord = "Pencil Case";

        //Perform a search
        WebFlows.performSearch(searchWord);

        Verifications.verifyTextInElementContains(searchResultPage.header_searchResults,"Searchh results for \"" + searchWord + "\"");
    }

    /*@Test(description = "Test01 - Verify Home Page Header")
    @Description("This test logins to Grafana and verifies the home page header's text")
    public void test01_VerifyHomePageHeader()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.verifyTextInElement(grafanaHomePage.header_welcome,"Welcome to Grafana");
    }*/

    /*@Test(description = "Test02 - Verify Default Users")
    @Description("This test navigates to Users page from left menu and verifies the default user")
    public void test02_VerifyDefaultUsers()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, 99);
    }

    @Test(description = "Test03 - Verify New User")
    @Description("This test navigates to Users page from left menu, ads a new user and verifies it was added")
    public void test03_VerifyNewUser()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        WebFlows.createNewUser("daf", "gen", "d@g.com", "12345");

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, 2);

        WebFlows.deleteLastUser();
    }
    @Test(description = "Test04 - Verify User Deletion")
    @Description("This test navigates to Users page from left menu, deletes a user and verifies it was deleted")
    public void test04_VerifyUserDeletion()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        int numberOfUsers = WebFlows.getNumberOfUsers();

        //If there is only 1 user, create a new one to prevent deleting the admin
        if(numberOfUsers == 1)
            WebFlows.createNewUser("daf", "gen", "d@g.com", "12345");

        numberOfUsers = WebFlows.getNumberOfUsers();

        WebFlows.deleteLastUser();

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, --numberOfUsers);
    }

    @Test(description = "Test05 - Verify Home Page Help Links Display")
    @Description("This test verifies all 'Help links' display on home page")
    public void test05_VerifyHomePageHelpLinksDisplay()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.visibilityOfElements(grafanaHomePage.list_needHelpLinks);
    }

    @Test(description = "Test06 - Verify Avatar Icon")
    @Description("This test verifies the Avatar icon using Sikuli tool")
    public void test06_VerifyAvatarIcon()
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        Verifications.visualElement("grafanaAvatar");
    }

    @Test(description = "test07 - Search Users", dataProvider = "data-provider-users", dataProviderClass = utilities.ManageDDT.class)
    @Description("This test searches using Data Driven Testing")
    public void test07_SearchUsers(String user, String sExpectedNumberOfUsers)
    {
        //Login as admin
        WebFlows.login(getData("GrafanaAdminUser"), getData("GrafanaAdminPassword"));

        WebFlows.selectFromLeftMenu(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);

        WebFlows.searchUser(user);

        Verifications.verifyNumberOfElements(grafanaServerAdminPage.rows, Parse.toInt(sExpectedNumberOfUsers));
    }*/
}
