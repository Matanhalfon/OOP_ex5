package filesprocessing.fileExceptions;

/**
 * a class representing a type 2 exception
 */
public class TypeTwoException extends Exception{

    /**
     * constructs a new type 2 exception
     * @param message the message to be presented for the user when this exception is thrown
     */
    public TypeTwoException(String message){
        super(message);
    }
}
