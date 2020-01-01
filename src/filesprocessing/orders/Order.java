package filesprocessing.orders;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;
import java.util.Comparator;

/**
 * an abstract class representing the order to sort by, implements Comparator
 */
public abstract class Order implements Comparator<File> {


    private static final String IS_REVERSE = "REVERSE";
    boolean REVERSE;
    private static final int NUM_OF_ARGS=2;
    private static final int REVERSE_LOCTION=1;
    static final int DOREVERSE=-1;
    String[] args;
    static OrderFactory orderFactory = new OrderFactory();

    /**
     * the constructor for a general order
     *
     * @param OrderArgs an args that by them the order type will be created
     * @throws TypeOneException if the args was not legal
     */
    Order(String[] OrderArgs) throws TypeOneException {
        if (!CheckInput(OrderArgs)) {
            throw new TypeOneException();
        }
    }

    /**
     * a general method that checks the input
     *
     * @param OrderArgs the args that the order created by
     * @return if the args to create an order was legal
     */

    private boolean CheckInput(String[] OrderArgs) {
        if (OrderArgs.length > NUM_OF_ARGS) {
            return false;
        }
        if (OrderArgs.length == NUM_OF_ARGS) {
            if (OrderArgs[REVERSE_LOCTION].equals(IS_REVERSE)) {
                this.REVERSE = true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * an abstract method that implicate the comparator
     *
     * @param file1 the first file
     * @param file2 the second file
     * @return if file 1 is bigger smaller or equals to file 2
     */
    public abstract int compare(File file1, File file2);
}

