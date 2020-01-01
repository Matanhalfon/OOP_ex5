package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a prefix filter type. this type returns all files with the given prefix in their
 * name.
 */
public class PrefixFilter extends Filter {

    /**
     * creates new prefix filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    PrefixFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.prefix.getExpectedArgsNum());
        stringValue = filterArgs[STRING_LOC];
    }

    @Override
    protected boolean checkFile(File file) {
        return file.getName().startsWith(stringValue);
    }
}
