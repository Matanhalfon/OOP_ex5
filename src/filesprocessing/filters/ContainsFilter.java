package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a contains filter type. this type returns all files which contain a certain string
 * in their name.
 */
public class ContainsFilter extends Filter {

    /**
     * creates new contains filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    ContainsFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.contains.getExpectedArgsNum());
        stringValue = filterArgs[STRING_LOC];
    }

    @Override
    protected boolean checkFile(File file) {
        return file.getName().contains(stringValue);
    }
}
