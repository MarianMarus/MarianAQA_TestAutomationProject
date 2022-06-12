package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileUtils {
    public static String getResourceFilePath(String fileName) {
        File file = null;
        try {
            file = Paths.get(FileUtils.class.
                    getClassLoader().
                    getResource(fileName).
                    toURI()).
                    toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
