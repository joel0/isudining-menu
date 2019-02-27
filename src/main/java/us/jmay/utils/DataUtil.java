package us.jmay.utils;

import java.io.File;

public class DataUtil {

    /**
     * Gets the locations.json file from the resources directory.
     *
     * @return location file
     */
    public static File getLocationFile() {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return new File(loader.getResource("locations.json").getFile());
    }
}
