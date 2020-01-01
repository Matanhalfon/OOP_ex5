package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;
import java.util.ArrayList;

/**
 * A class representing a general filter (all filter types will extend it)
 */
public abstract class Filter {

    /**
     * an array contains all the filter necessary arguments
     */
    String[] filterArgs;
    /**
     * a flag which indicates if the filter should be applied or not
     */
    boolean doFilter;
    /**
     * the maximal args number for a certain filter
     */
    int expectedArrayLength;
    /**
     * the string to check. (for string filters)
     */
    String stringValue;
    /**
     * the size to check. (for size filters)
     */
    double numValue;


    static final int BYTES = 1024; // conversion to bytes factor
    static final String DONT_APPLY = "NOT";
    static final int FILTER_TYPE = 0; // the location in the array where the filter name is placed
    static final int ILLEGAL_LENGTH = 1; // an illegal filter args array length
    static final int STRING_LOC = 1; // the location of the string filters string value is located
    private static final int SIZE_LOC = 1; // the location of the size filters size value is located

    /**
     * constructs a filter object
     *
     * @param filterArgs      an array contains the values needed for a certain filter
     * @param expectedArgsNum the number of args allowed
     * @throws TypeOneException Type one exception
     */
    Filter(String[] filterArgs, int expectedArgsNum) throws TypeOneException {
        expectedArrayLength = expectedArgsNum;
        if (!checkValidInput(filterArgs)) {
            throw new TypeOneException();
        }
        this.filterArgs = filterArgs;
    }

    /**
     * checks the validity of the given arguments for the filter
     *
     * @param filterArgs an array contains the values needed for a certain filter
     * @return true if the input is valid, false otherwise
     */
    protected boolean checkValidInput(String[] filterArgs) {
        if (filterArgs.length == ILLEGAL_LENGTH || filterArgs.length > expectedArrayLength)
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

    /**
     * this method is checking the given number arg validity. (for now, relevant for greater than and
     * smaller than filters)
     *
     * @param filterArgs the number of args allowed
     * @throws TypeOneException type one exception
     */
    void numArgsValidityCheck(String[] filterArgs) throws TypeOneException {
        try {
            numValue = Double.parseDouble(filterArgs[SIZE_LOC]);
        } catch (Exception NumberFormatException) {
            throw new TypeOneException();
        }
        if (numValue < 0) //
            throw new TypeOneException();
    }

    /**
     * this method is filtering the given files according to given
     */
    public ArrayList<File> doFilter(ArrayList<File> files) {
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File file : files) {
            if (checkFile(file) == doFilter) {
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    /**
     * @return true if the specific filter is applicable for the given file
     */
    protected abstract boolean checkFile(File file);

}

