package filesprocessing.orders;

import filesprocessing.fileExceptions.TypeOneException;

import java.io.File;

/**
 * a Order comparator the compare on the base of the file type
 */
public class typeOrder extends Order {
    private static final int EVEN = 0;
    private static final String DEFULT_NAME = "File";
    private static final int NO_TYPE = 1;

    typeOrder(String[] OrderArgs) throws TypeOneException {
        super(OrderArgs);
    }

    @Override
    public int compare(File file1, File file2) {
        String StringFile1 = file1.getName();
        String[] TypeArrayFile1 = StringFile1.split("\\.");
        String TypeFile1 = DEFULT_NAME;
        //if there is a type the type is the word after the last dot
        if (TypeArrayFile1.length > NO_TYPE) {
            TypeFile1 = TypeArrayFile1[TypeArrayFile1.length - 1];
        }
        String[] TypeArrayfile2 = file2.getName().split("\\.");
        String TypeFile2 = DEFULT_NAME;
        if (TypeArrayfile2.length > NO_TYPE) {
            TypeFile2 = TypeArrayfile2[TypeArrayfile2.length - 1];
        }
        if (TypeFile1.compareTo(TypeFile2) == EVEN) {
            if (this.REVERSE) {//reverse the abs like school solution
                return orderFactory.GetDefault().compare(file2, file1);
            } else {
                return orderFactory.GetDefault().compare(file1, file2);
            }
        }
        if (!this.REVERSE) {
            return TypeFile1.compareTo(TypeFile2);
        } else {
            return (DOREVERSE * TypeFile1.compareTo(TypeFile2));
        }
    }
}
