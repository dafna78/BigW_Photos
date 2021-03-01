package utilities;

import io.qameta.allure.Step;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
