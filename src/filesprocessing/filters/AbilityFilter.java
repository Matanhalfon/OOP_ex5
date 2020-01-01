package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * This class represents an ability filter for the file (for now we have 3 kinds: writable, executable,
 * hidden. more can be added later on)
 */
public class AbilityFilter extends Filter {

    /* indicates if the filter should be applied or not */
    private static final String DONT_APPLY = "NO";
    private static final String APPLY = "YES";
    private static final int FILTER_APPLY = 1; // the location in the array where the filter application status is
    // located (YES,NO)


    /**
     * creates new ability filter object
     * @param filterArgs the arguments needed fo the filter
     * @throws TypeOneException type one exception
     */
      AbilityFilter(String[] filterArgs) throws TypeOneException {
        super(filterArgs, FilterFactory.Filters.writable.getExpectedArgsNum());

    }

    @Override
    protected boolean checkFile(File file) {
        if (filterArgs[FILTER_TYPE].equals(FilterFactory.Filters.writable.toString()))
            return file.canWrite();
        if (filterArgs[FILTER_TYPE].equals(FilterFactory.Filters.executable.toString()))
            return file.canExecute();
        if (filterArgs[FILTER_TYPE].equals(FilterFactory.Filters.hidden.toString()))
            return file.isHidden();
        return false;
    }

    @Override
    protected boolean checkValidInput(String[] filterArgs) {
          if(super.checkValidInput(filterArgs)){
              switch(filterArgs[FILTER_APPLY]){
                  case DONT_APPLY:
                      doFilter = !doFilter;
                      break;
                  case APPLY:
                      break;
                  default:
                      return false;
                }
        }
        else
            return false;
        return true;
    }
}
