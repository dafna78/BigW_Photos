package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import utilities.CommonOps;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Common verifications / assertions class
 */
public class Verifications extends CommonOps
{
    /**
     * Verifies the text in the element is as expected
     * @param element WebElement element
     * @param expected the expected text
     */
    @Step("Verify text in element")
    public static void verifyTextInElement(WebElement element, String expected)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        assertEquals(element.getText(), expected);
    }

    /**
     * Verifies the text in the element contains the expected
     * @param element WebElement element
     * @param expected the expected text
     */
    @Step("Verify text in element contains")
    public static void verifyTextInElementContains(WebElement element, String expected)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
        assertTrue(element.getText().contains(expected));
    }

    /**
     * Verifies the number of elements are as expected
     * @param elements a list of WebElements found
     * @param expected expected number of elements
     */
    @Step("Verify number of elements")
    public static void verifyNumberOfElements(List<WebElement> elements, int expected)
    {
        if(expected != 0)
            wait.until(ExpectedConditions.visibilityOf(elements.get(elements.size()-1)));

        assertEquals(elements.size(), expected);
    }

    /**
     * Verifies all elements are displayed. This method uses soft assert, therefore checks ALL elements in the list and
     * will print all elements that do not display, without failing the test in the middle.
     * @param elements a list of WebElements found
     */
    @Step("Verify visibility of elements")
    public static void visibilityOfElements(List<WebElement> elements)
    {
        for(WebElement element : elements)
            softAssert.assertTrue(element.isDisplayed(), "Element " + element.getText() + " does not display.");

        softAssert.assertAll();
    }

    /**
     * Verifies element is displayed.
     * @param element WebElement found
     */
    @Step("Verify visibility of an element")
    public static void visibilityOfElement(WebElement element)
    {
        assertTrue(element.isDisplayed(), "Element " + element.getText() + " does not display.");
    }

    /**
     * Verifies element is not displayed.
     * @param element WebElement found
     */
    @Step("Verify invisibility of an element")
    public static void invisibilityOfElement(WebElement element)
    {
        assertFalse(element.isDisplayed(), "Element " + element.getText() + " is display.");
    }

    /**
     * Verifies an image on the screen. This method enables you to take an image/region from the screen and save it
     * under 'ImageRepository' folder with extension .png, and then verify it exists.
     * @param expectedImageName the image name under ImageRepository folder, (without the .png)
     */
    @Step("Verify element visually")
    public static void visualElement(String expectedImageName)
    {
        try
        {
            screen.find(getData("ImageRego") + expectedImageName + ".png");
        }
        catch (FindFailed findFailed)
        {
            System.out.println("Error comparing image file: " + findFailed);
            fail("Error comparing image file: " + findFailed);
        }
    }

    /**
     * Verifies two ints are the same. If they are not, an AssertionError is thrown.
     * @param actual actual int
     * @param expected expected int
     */
    @Step("Verify ints are equal")
    public static void verifyEquals(int actual, int expected)
    {
        assertEquals(actual, expected);
    }

    /**
     * Verifies two Strings are the same. If they are not, an AssertionError is thrown.
     * @param actual the actual text
     * @param expected the expected text
     */
    @Step("Verify strings are equal")
    public static void verifyEquals(String actual, String expected)
    {
        assertEquals(actual, expected);
    }

    /**
     * Verifies the condition/(parameter received) is true. If it is not, an AssertionError is thrown.
     * @param expected the condition to evaluate
     */
    @Step("Verify the expected is true")
    public static void verifyTrue(boolean expected)
    {
        assertTrue(expected);
    }

    /**
     * Verifies two types are equal. This assertion is a soft assertion and allows multiple assertions calls without
     * failing the test, even if the types are not equal.
     * After calling the multiple soft assertions you must call assertAll() method to invoke all soft assertions.
     * @param actual the actual
     * @param expected the expected
     * @param <T> the type that is asserted (String, int, etc.)
     */
    @Step("Soft verify parameters are equal")
    public static <T> void softVerifyEquals(T actual, T expected)
    {
        softAssert.assertEquals(actual, expected);
    }

    @Step("Soft verify parameters are equal")
    public static void softVerifyExists(Object object)
    {
        softAssert.assertNotNull(object);
    }

    /**
     * Verifies the object exists and is not null. If it is, an AssertionError is thrown
     * @param object the assertion object
     */
    @Step("Verify the object exists")
    public static void verifyExists(Object object)
    {
        assertNotNull(object);
    }

    /**
     * Verifies the object does not exist / that an object is null. If it is not, an AssertionError, is thrown
     * @param object the assertion object
     */
    @Step("Verify the object does not exist")
    public static void verifyDoesNotExist(Object object)
    {
        assertNull(object);
    }

    /**
     * Invokes all soft asserts that were called before.
     */
    public static void assertAll()
    {
        softAssert.assertAll();
    }
}
