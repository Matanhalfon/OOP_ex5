package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;
import java.lang.Exception;

/**
 * This class represents a between filter type. this type returns all files with the size of a given range.
 */
public class BetweenFilter extends Filter {

    /* the file size lower bound */
    private double startValue;
    /* the file size upper bound*/
    private double endValue;

    private static final int START_LOC = 1;  // the lower bound location in the array
    private static final int END_LOC = 2; // the upper bound location in the array

    /**
     * creates new between filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
   BetweenFilter(String[] filterArgs) throws TypeOneException{
       super(filterArgs, FilterFactory.Filters.between.getExpectedArgsNum());

       try{
           startValue = Double.parseDouble(filterArgs[START_LOC]);
           endValue = Double.parseDouble(filterArgs[END_LOC]);
       }
       catch (Exception NumberFormatException){
           throw new TypeOneException();
       }
       if(startValue < 0 || endValue < 0 || endValue < startValue)
           throw new TypeOneException();
    }

    @Override
    protected boolean checkFile(File file) {
        return ((double)file.length()/BYTES >= startValue && (double)file.length()/BYTES <= endValue);
    }
}
