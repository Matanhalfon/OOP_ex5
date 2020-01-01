package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a file filter type. this type returns all files with the given name.
 */
public class FileFilter extends Filter {

    /**
     * creates new file filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    FileFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.file.getExpectedArgsNum());
        stringValue = filterArgs[STRING_LOC];
    }

    @Override
    protected boolean checkFile(File file) {
        return file.getName().equals(stringValue);
    }
}