package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents a smaller than filter type. this type returns all files with a size smaller than
 * the given threshold.
 */
public class SmallerThanFilter extends Filter {

    /**
     * creates new smaller than filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    SmallerThanFilter(String[] filterArgs) throws TypeOneException{
        super(filterArgs, FilterFactory.Filters.smaller_than.getExpectedArgsNum());
        numArgsValidityCheck(filterArgs);
    }

    @Override
    protected boolean checkFile(File file) {
        return (double)file.length()/BYTES < numValue;
    }
}
