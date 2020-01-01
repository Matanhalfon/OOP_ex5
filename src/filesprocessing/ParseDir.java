package filesprocessing;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * A class that parse the files in the directory into an array
 */
public class ParseDir {
    /**
     * the method that parse the DIR FILE
     *
     * @param DirPath the path to the directory
     * @return an array of all files that is in the directory
     */


    public static ArrayList<File> ParseFile(String DirPath) {
        ArrayList<File> ParsedFiles = new ArrayList<File>();
        File dir = new File(DirPath);
        File[] DicList = dir.listFiles();
        if (DicList != null) {
            for (File file : DicList) {
                if (file.isFile()) {
                    ParsedFiles.add(file);
                }
            }

        }
        return ParsedFiles;
    }
}
