package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * Common Operations class
 */
public class CommonOps extends Base
{
    /**
     * This method runs once before the execution of test methods in a current class.
     * @param PlatformName This parameter is read from the relevant TestNG xml file and determines on which platform to run.
     */
    @BeforeClass
    @Parameters({"PlatformName"})
    public void startSession(String PlatformName)
    {
        platform = PlatformName;
        switch (platform)
        {
            case "web":
                initBrowser(getData("BrowserName"));
                initTimeouts();
                initWebPages();
                break;
            default:
                throw new RuntimeException("Invalid platform name");
        }

        softAssert = new SoftAssert();
        screen = new Screen();
    }

    /**
     * This method will be executed after all the test methods of a current class have been invoked.
     */
    @AfterClass
    public void closeSession()
    {
        driver.quit();
    }

    /**
     * This method will be invoked before the execution of each test method.
     * @param method Full name of executed test method.
     */
    @BeforeMethod
    public void beforeMethod(Method method)
    {
        try
        {
            MonteScreenRecorder.startRecord(method.getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method will be invoked after the execution of each test method.
     */
    @AfterMethod
    public void afterMethod()
    {
        if(platform.equals("web"))
            driver.get(getData("Url"));

    }

    //****************************************************************************

    /**
     * For Web Testing:
     * This method initialises the driver / browser. Maximizes the window and navigates to the URL specified in the DataConfig file.
     * Also initialises the required timeouts for the tests.
     * @param browserType The required browser type (Chrome / FF / IE).
     */
    public static void initBrowser(String browserType)
    {
        if(browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if(browserType.equalsIgnoreCase("fire fox"))
            driver = initFireFoxDriver();
        else if(browserType.equalsIgnoreCase("ie"))
            driver = initIEDriver();
        else
            throw new RuntimeException("Invalid browser type");

        driver.manage().window().maximize();
        driver.get(getData("Url"));

        action = new Actions(driver);

        //Timeouts
        initTimeouts();
    }

    //*******************************************

    /**
     * Initialises Chrome driver.
     * @return Chrome driver.
     */
    public static WebDriver initChromeDriver()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    /**
     * Initialises FireFox driver.
     * @return FireFox driver.
     */
    public static WebDriver initFireFoxDriver()
    {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    /**
     * Initialises Internet Explorer driver.
     * @return Internet Explorer driver.
     */
    public static WebDriver initIEDriver()
    {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    //*******************************************

    /**
     * Initialises the elements of the Web pages.
     */
    public static void initWebPages()
    {
        ManagePages.initWebPages();
    }


    /**
     * Initialises the timeouts for the tests according to the timeout specified in the DataConfig file.
     */
    public static void initTimeouts()
    {
        wait = new WebDriverWait(driver, Long.parseLong(getData("TimeOut")));
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
    }

    /**
     * Reads data from DataConfig.xml file.
     * @param nodeName The name of the node in the file to read the date from.
     * @return the data found in the node.
     */
    public static String getData (String nodeName)
    {
        File fXmlFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc;
        String textToReturn = null;

        try
        {
            fXmlFile = new File("./Configurations/DataConfig.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            textToReturn = doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
        catch(NullPointerException e)
        {
            System.out.println("XML file does not contain node: " + nodeName);
        }
        catch(Exception e)
        {
            System.out.println("Exception in reading XML file: " + e);
        }

        return textToReturn;
    }

    //*******************************************

    /**
     * Creates a new timestamp.
     * @return a new timestamp.
     */
    public Timestamp getNewTimeStamp()
    {
         return new Timestamp(System.currentTimeMillis());
    }
}
