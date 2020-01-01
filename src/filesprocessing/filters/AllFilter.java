package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents an all filter type. this type returns all files.
 */
public class AllFilter extends Filter {

    /**
     * creates new all filter object
     *
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
    AllFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.all.getExpectedArgsNum());
    }

    @Override

    protected boolean checkFile(File file) {
        return true;
    }

    @Override
    public boolean checkValidInput(String[] filterArgs) {
        if (filterArgs.length > expectedArrayLength)
            return false;
        if (filterArgs.length == expectedArrayLength) {
            if (filterArgs[expectedArrayLength - 1].equals(DONT_APPLY))
                doFilter = false;
            else
                return false;
        } else
            doFilter = true;
        return true;
    }
}
