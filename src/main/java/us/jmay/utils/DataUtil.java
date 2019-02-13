package us.jmay.utils;

import java.io.File;
import java.net.URL;

public class DataUtil {

    public URL getLocationFile()
    {
        return this.getClass().getResource("locations.json");
    }
}
