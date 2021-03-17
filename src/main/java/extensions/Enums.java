package extensions;

import java.util.HashMap;
import java.util.Map;

public class Enums
{
    public enum TopNavBarButtons
    {
        NULL(""),
        Shop("Shop"),
        MyProjects("My Projects"),
        MyPhotos("My Photos"),
        CreateAccount("Create Account"),
        SignIn("Sign In"),
        SignOut("Sign Out");

        private String button;

        TopNavBarButtons(String _button) {
            this.button = _button;
        }

        public String getButtonName() {
            return button;
        }
    }

    public enum Colors
    {
        NULL(""),
        RED("background: rgb(255, 103, 93);"),
        ORANGE("background: rgb(249, 167, 77);"),
        YELLOW("background: rgb(245, 206, 83);"),
        GREEN("background: rgb(114, 204, 87);"),
        BLUE("background: rgb(87, 185, 244);"),
        PURPLE("background: rgb(210, 137, 226);"),
        GREY("background: rgb(165, 165, 167);");

        private String color;

        Colors(String _color) {
            this.color = _color;
        }

        public String getBgRGB() {
            return color;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, Colors> lookup = new HashMap<>();

        static
        {
            for(Colors col : Colors.values())
            {
                lookup.put(col.getBgRGB(), col);
            }
        }

        /**
         * Gets Color name by its background value. This method can be used for reverse lookup purpose.
         * @param bgColor bgColor value. example: "background: rgb(255, 103, 93);"
         * @return the name of the color. example: RED
         */
        public static Colors getColorName(String bgColor)
        {
            return lookup.get(bgColor);
        }

        /**
         * Get color by its key name
         * @param keyName color key name. Example: Red
         * @return the Color. Example: RED("background: rgb(255, 103, 93);")
         */
        public static Colors getColorByKeyName(String keyName)
        {
            for (Colors color : Colors.values())
            {
                if(color.name().toLowerCase().equals(keyName.toLowerCase()))
                    return color;
            }
            throw  new RuntimeException("Color '" + keyName + "' does not exists in the enum list");
        }

        /**
         * Gets the colors from the enum as array
         * @return Object[] array of color names and RGB values
         */
        public static Object[] getColorsAsArray()
        {
            Object[] arr = new Object[8];
            int indexKey = 0;
            for (Colors color : Colors.values())
            {
                arr[indexKey] = color;
                indexKey++;
            }
            return arr;
        }
    }
}
