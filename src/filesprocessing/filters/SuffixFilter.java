package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a suffix filter type. this type returns all files with the given suffix in their
 * name.
 */
public class SuffixFilter extends Filter  {

    /**
     * creates new suffix filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    SuffixFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.suffix.getExpectedArgsNum());
        stringValue = filterArgs[STRING_LOC];
    }

    @Override
    protected boolean checkFile(File file) {
        return file.getName().endsWith(stringValue);
    }
}
