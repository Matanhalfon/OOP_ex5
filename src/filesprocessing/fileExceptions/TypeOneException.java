package filesprocessing.fileExceptions;

/**
 * a class representing a type 1 exception
 */
public class TypeOneException extends Exception {

    /**
     * constructs a new type 1 exception (with default message)
     */
    public TypeOneException(){
        super("type one error found");
    }
}
