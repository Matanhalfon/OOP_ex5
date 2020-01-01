package filesprocessing.orders;

import java.io.File;

import filesprocessing.fileExceptions.*;
import filesprocessing.fileExceptions.TypeOneException;

/**
 * an order comparator that compare by the size of the file
 */
public class SizeOrder extends Order {
    private static final int EVEN=0;


    SizeOrder(String[] OrderArgs) throws TypeOneException {
        super(OrderArgs);
    }


    @Override
    public int compare(File file1, File file2) {
        long file1size = file1.length();
        long file2size = file2.length();
        if (file1size - file2size == EVEN) {
            if (this.REVERSE) {
                return orderFactory.GetDefault().compare(file2, file1);
            }
            return orderFactory.GetDefault().compare(file1, file2);
        }
        if (this.REVERSE) {
            return (int) (file1size - file2size) * (DOREVERSE);
        }
        return (int) (file1size - file2size);
    }
}
