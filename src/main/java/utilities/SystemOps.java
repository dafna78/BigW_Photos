package utilities;

import io.qameta.allure.Step;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static extensions.WaitActions.sleepUninterruptibly;

/**
 * System operation class
 */
public class SystemOps
{
    /**
     * Run executable file path
     * @param executablePath executable file path
     */
    @Step("Run exe file")
    public static void runExecutable(String executablePath)
    {
        try
        {
            Path path = Paths.get(executablePath);
            if(Files.exists(path))
            {
                Process process = new ProcessBuilder(executablePath).start();
            }
            else
            {
                System.out.println("Cannot execute exe file. File cannot be found: " + executablePath);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error running exe file: " + executablePath);
            e.printStackTrace();
        }
    }

    /**
     * @param relativePath the relative path to the file/folder
     * @return the absolute path
     * @throws Exception
     * java.io.IOException – If an I/O error occurs, which is possible because the construction of the canonical pathname may require filesystem queries
     * SecurityException – If a required system property value cannot be accessed, or if a security manager exists and its SecurityManager
     */
    public static String getAbsolutePath(String relativePath) throws Exception
    {
        return new File(relativePath).getCanonicalPath();
    }


    /**
     * Sets any parameter string to the system's clipboard.
     * @param string the string to copy to the clipboard
     */
    public static void setClipboardData(String string)
    {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    /**
     * Uploads a file. Handles the upload window
     * @param filePath the file path
     */
    @Step("Upload a file")
    public static void uploadFile(String filePath) throws AWTException
    {
        //Copy the file path to the clipboard
        SystemOps.setClipboardData(filePath);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        sleepUninterruptibly(3000);
    }
}
