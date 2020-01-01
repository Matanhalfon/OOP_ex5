package filesprocessing;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import filesprocessing.filters.Filter;
import filesprocessing.orders.Order;

/**
 * A class that holds the commands and data for part of the command file
 */
class Section {
    //data members

    private Filter sectionFilter;
    private Order sectionOrder;
    String[] sectionLines;
    private List<Integer> WarninngLines;
    private ArrayList<File> GoodFiles;

    /**
     * the constructor for the section
     *
     * @param filter       the filter of  the section
     * @param order        the order for  the section
     * @param WarningLines the num of lines that in them  there is type one error
     */
    Section(Filter filter, Order order, List<Integer> WarningLines) {
        sectionFilter = filter;
        sectionOrder = order;
        this.WarninngLines = WarningLines;
    }

    /**
     * a method that preform a filtration on the received files
     *
     * @param FileList file to filter
     * @return the filtered files
     */

    private ArrayList<File> FilterFile(ArrayList<File> FileList) {
        return sectionFilter.doFilter(FileList);
    }

    /**
     * sort files by  the section order
     */


    private void SortFile() {
        Collections.sort(GoodFiles, sectionOrder);
    }

    /**
     * get list of files  filter and order them by the section commands
     *
     * @param DirFiles the file to filter and order
     */


    protected void SetInput(ArrayList<File> DirFiles) {
        GoodFiles = FilterFile(DirFiles);
        SortFile();
    }

    /**
     * print the lines that there is a type one error in
     */

    protected void PrintWarnings() {
        for (int lineNumber : WarninngLines) {
            System.err.println(String.format("Warning in line %d",
                    lineNumber));
        }
    }

    /**
     * print the section files
     */

    protected void PrintGoodFiles() {
        for (File file : this.GoodFiles) {
            System.out.println(file.getName());
        }

    }


}
