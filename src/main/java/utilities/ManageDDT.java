package utilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * Manage Data Driven Testing class
 */
public class ManageDDT extends CommonOps
{
    /**
     * Gets data provider with all users written in users.csv file.
     * @return data object[][].
     */
    @DataProvider(name = "data-provider-users")
    public static Object[][] getData_Users()
    {
        return getDataObject("users");
    }

    //*********************************************************

    /**
     * Returns data object[][] with information read from a csv file.
     * @param fileName the csv file name.
     * @return data object[][].
     */
    public static Object[][] getDataObject(String fileName)
    {
        return getDataFromCSV(getData("DDTFilePath" ) + fileName + ".csv");
    }


    /**
     * Returns data from a csv file.
     * @param filePath path to the csv file.
     * @return the data read from the csv file as an object[][].
     */
    public static Object[][] getDataFromCSV(String filePath)
    {
        List<String> csvData = readCSV(filePath);
        int numberOfCol = csvData.get(0).split(",").length;

        Object[][] data = new Object[csvData.size()][numberOfCol];

        for(int i=0; i < csvData.size() ; i++)
        {
            data[i][0] =  csvData.get(i).split(",")[0];
            data[i][1] =  csvData.get(i).split(",")[1];
        }
        return  data;
    }

    /**
     * Reads data from a csv file.
     * @param CsvFile path to the csv file.
     * @return all lines from the csv file.
     */
    public static List<String> readCSV(String CsvFile)
    {
        List<String> lines = null;

        File file = new File(CsvFile);
        try
        {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        return  lines;
    }
}
