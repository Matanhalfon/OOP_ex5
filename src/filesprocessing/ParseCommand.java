package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import filesprocessing.fileExceptions.*;
import filesprocessing.filters.*;
import filesprocessing.orders.*;

/**
 * the class that parse the command file into sections
 */
public class ParseCommand {
    //data members
    private BufferedReader bufferReader;
    private File commandsFile;
    private int currentLine;
    private static FilterFactory filterFactory;
    private static filesprocessing.orders.OrderFactory OrderFactory;
    private static final String VAR_FILTER = "case1";
    private static final String GET_FILTER = "case2";
    private static final String VAR_ORDER = "case3";
    private static final String GET_ORDER = "case4";
    private static final String BUILD_SECTION = "case5";
    private static final String FILTER = "FILTER";
    private static final String FILE_DELIMITER = "#";
    private static final String UNREADABLE="Command FIle unreadable";
    private static final String LINE_UNREADABLE="could not read command line ";
    private static final String NO_FILTER_HEADER="no FILTER header in line ";
    private static final String NO_ORDER_HEADER="ORDER sub-section missing in line ";
    private static final int START_LINE=0;

    /**
     * constrictor for the parser
     *
     * @param commandsFile the path to the command file
     * @throws TypeTwoException if there is IO EXCEPTION trows TYPETWO error
     */

    ParseCommand(File commandsFile) throws TypeTwoException {
        this.commandsFile = commandsFile;
        try {
            this.bufferReader = new BufferedReader(new FileReader(commandsFile));
        } catch (IOException e) {
            throw new TypeTwoException(UNREADABLE);
        }

        this.currentLine = START_LINE;
        filterFactory = new FilterFactory();
        OrderFactory = new OrderFactory();
    }

    /**
     * a method that move to the next line
     *
     * @return the next line read
     * @throws TypeTwoException if there is an Io exception
     */

    private String moveToNextLine() throws TypeTwoException {
        currentLine++;
        try {
            return bufferReader.readLine();
        } catch (IOException e) {
            throw new TypeTwoException(LINE_UNREADABLE);
        }
    }

    /**
     * a method that add a new section to the section collection
     *
     * @param sections     a sections collection
     * @param filter       the filter of the new section
     * @param order        the order of the new section
     * @param WarningLines the warning lines of the new section
     */


    private void AddNewSection(Collection<Section> sections, Filter filter, Order order, List<Integer> WarningLines) {

        sections.add(new Section(filter, order, WarningLines));
    }

    /**
     * a method that checks if the Filter headed is legal
     *
     * @param line the line
     * @throws TypeTwoException if the header is not FILTER
     */
    private void VerifyFilter(String line) throws TypeTwoException {
        if (!line.equals(FILTER)) {
            throw new TypeTwoException(NO_FILTER_HEADER + this.currentLine);//type2 we are looking
            // at the
            // filter title

        }
    }

    /**
     * a method that create a new filter by the input
     *
     * @param line the line of the filter args
     * @return the new filter
     * @throws TypeOneException if case that there is a problem with the parameters or the filter name
     */
    private Filter createFilter(String line) throws TypeOneException {
        String[] FilterInput = line.split(FILE_DELIMITER);
        return filterFactory.createFilter(FilterInput);
    }

    /**
     * a method that ge a line and create the wanted order
     *
     * @param line the line that by it args the order is created
     * @return the wanted order
     * @throws TypeOneException if the order name is not legal or the parameters
     */
    private Order createOrder(String line) throws TypeOneException {
        if (line.equals(FILTER)) {
            return OrderFactory.GetDefault();
        } else {
            String[] OrderInput = line.split(FILE_DELIMITER);
            return OrderFactory.createOrder(OrderInput);

        }
    }

    /**
     * a method that copy an Intger array
     *
     * @param list the list to copy
     * @return the copied list
     */
    private ArrayList<Integer> copyArray(ArrayList<Integer> list) {
        ArrayList<Integer> copiedArray = new ArrayList<>();
        copiedArray.addAll(list);
        return copiedArray;
    }

    /**
     * make sure that the header is"ORDER"
     *
     * @throws TypeTwoException if it is not "ORDER"
     */
    private void VerifyOrder() throws TypeTwoException {
        String line = moveToNextLine();
        if (!line.equals("ORDER")) {
            throw new TypeTwoException(NO_ORDER_HEADER + this.currentLine);
        }
    }

    /**
     * the main method that read the commandsFile and parse it into sections
     */
    Collection<Section> parse() throws TypeTwoException {

        Collection<Section> sections = new ArrayList<>();
        Filter filter = null;
        Order order = null;
        String TextLine = moveToNextLine();
        ArrayList<Integer> Warnings = new ArrayList<>();
        String state = VAR_FILTER;

        /*
        while not at the end of the file we will move from one state to the anther
         */
        while (TextLine != null) {
            switch (state) {//verify that the filter header is in the right place
                case VAR_FILTER: {
                    VerifyFilter(TextLine);
                    state = GET_FILTER;
                }
                break;

                case GET_FILTER: {//get the filter that in the filter sub-section
                    try {
                        TextLine = moveToNextLine();
                        filter = createFilter(TextLine);
                    } catch (TypeOneException e) {
                        Warnings.add(currentLine);
                        filter = filterFactory.GetDefaultFilter();//if type one error get a all filter
                    }
                    state = VAR_ORDER;
                    break;
                }

                case VAR_ORDER: {//verify that the order header is in the right place
                    VerifyOrder();
                    state = GET_ORDER;
                    break;
                }

                case GET_ORDER: {//get the order that in the order sub-section
                    TextLine = moveToNextLine();
                    if (TextLine == null) {
                        //if at the section end with no order sub section get abs order and end section
                        order = OrderFactory.GetDefault();
                        AddNewSection(sections, filter, order, Warnings);
                        break;
                    }
                    try {
                        order = createOrder(TextLine);
                    } catch (TypeOneException e) {
                        Warnings.add(currentLine);
                        order = OrderFactory.GetDefault();
                    }
                }
                state = BUILD_SECTION;
                break;

                case BUILD_SECTION: {
                    //move the wanted args to a new section and start new one
                    ArrayList<Integer> sectionWarnings = copyArray(Warnings);
                    AddNewSection(sections, filter, order, sectionWarnings);
                    filter = null;
                    order = null;
                    Warnings.clear();
                    state = VAR_FILTER;
                    if (!TextLine.equals(FILTER))
                        TextLine = moveToNextLine();
                    if (TextLine != null)
                        state = VAR_FILTER;
                    break;
                }
            }
        }
        return sections;
    }
}






