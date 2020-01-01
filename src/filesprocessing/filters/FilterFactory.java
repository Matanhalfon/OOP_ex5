package filesprocessing.filters;

import filesprocessing.fileExceptions.TypeOneException;

/**
 * this class is responsible for creating filter objects
 */
public class FilterFactory {


    private static final int FILTER_TYPE = 0; // the location in the array where the filter name is placed

    private static final String[] DEFAULT_FILTER = new String[]{"all"};  // representing the default filter
    // array input for filter constructor

    /**
     * this enum contains all filter names and their expected number of args written in the file
     */
    enum Filters {
        greater_than(3),
        between(4),
        smaller_than(3),
        file(3),
        contains(3),
        prefix(3),
        suffix(3),
        writable(3),
        executable(3),
        hidden(3),
        all(2);

        /* the number of args needed to apply a filter*/
        private int expectedArgsNum;

        /* constructs the filters enums */
        Filters(int expectedArgsNum) {
            this.expectedArgsNum = expectedArgsNum;
        }

        /**
         * @return the expected args num for a certain filter
         */
        public int getExpectedArgsNum() {
            return expectedArgsNum;
        }
    }

    /**
     * this method is creating the requested filter
     * @param filterArgs the args needed for a certain filter
     * @return the requested filter object
     * @throws TypeOneException when cannot create a filter object
     */
    public Filter createFilter(String[] filterArgs) throws TypeOneException {
        if (filterArgs[FILTER_TYPE].equals(Filters.greater_than.toString()))
            return new GreaterThanFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.smaller_than.toString()))
            return new SmallerThanFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.between.toString()))
            return new BetweenFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.file.toString()))
            return new FileFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.contains.toString()))
            return new ContainsFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.prefix.toString()))
            return new PrefixFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.suffix.toString()))
            return new SuffixFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.writable.toString()))
            return new AbilityFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.executable.toString()))
            return new AbilityFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.hidden.toString()))
            return new AbilityFilter(filterArgs);
        if (filterArgs[FILTER_TYPE].equals(Filters.all.toString()))
            return new AllFilter(filterArgs);
        else {
            throw new TypeOneException();
        }
    }

    /**
     * @return an "all" filter object as the default filter
     */
    public Filter GetDefaultFilter() {
        try {
            return new AllFilter(DEFAULT_FILTER);
        } catch (TypeOneException e) {
            //this line will never be reached
            return null;
        }
    }
}