package extensions;

import java.util.Random;

/**
 * General Helpers class
 */
public class Helpers
{
    /**
     * Gets a random number between 2 numbers
     * @param minvalue lowest number
     * @param maxValue highest number
     * @return random number
     */
    public static int getRandomNumber(int minvalue, int maxValue)
    {
        Random rand = new Random();
        return minvalue + rand.nextInt(maxValue - minvalue + 1);
    }

    /**
     * Checks if a String value is numeric or not
     * @param strNum String value
     * @return true or false if the String is numeric
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null)
        {
            return false;
        }
        try
        {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a double number is Integer (or represents a decimal number)
     * @param number a double number
     * @return true or false if the number is Integer
     */
    public static boolean isInteger(double number)
    {
        return (number % 1 == 0);
    }
}
