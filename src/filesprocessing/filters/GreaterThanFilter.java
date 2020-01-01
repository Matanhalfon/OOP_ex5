package filesprocessing.filters;
import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a greater than filter type. this type returns all files with the size greater than
 * a given threshold.
 */
public class GreaterThanFilter extends Filter {

    /**
     * creates new greater than filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    GreaterThanFilter(String[] filterArgs) throws TypeOneException{
        super(filterArgs, FilterFactory.Filters.greater_than.getExpectedArgsNum());
        numArgsValidityCheck(filterArgs);
    }

    @Override
    protected boolean checkFile(File file) {
        return (double)file.length()/BYTES > numValue;
    }
}