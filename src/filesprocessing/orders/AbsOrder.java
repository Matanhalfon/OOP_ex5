package filesprocessing.orders;

import filesprocessing.fileExceptions.*;
import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * an order comparator that compare by absolute path
 */
public class AbsOrder extends Order {

    AbsOrder(String[] OrderArgs) throws TypeOneException {
        super(OrderArgs);
    }

    @Override
    public int compare(File file1, File file2) {
        String file1abs = file1.getAbsolutePath();
        String file2abs = file2.getAbsolutePath();
        if (!this.REVERSE ) {
            return file1abs.compareTo(file2abs);
        } else {
            return (DOREVERSE * file1abs.compareTo(file2abs));
        }
    }

}


