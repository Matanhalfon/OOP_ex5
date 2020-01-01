package filesprocessing.orders;


import filesprocessing.fileExceptions.TypeOneException;

/**
 * a factory that create orders by input
 */

public class OrderFactory {
    //the default args that create an absolute order
    private static final String[] DEFAULT_ORDER = new String[]{"abs"};
    private static final int ORDER_NAME=0;

    /**
     * the args of the Orders
     */
    public enum Orders {
        abs,
        type,
        size,
    }

    /**
     * the factory main methode that get an input an by that create the wanted Order
     *
     * @param OrderArgs the args that by the order is created
     * @return the right order for the args received
     * @throws TypeOneException if the args are illegal
     */

    public Order createOrder(String[] OrderArgs) throws TypeOneException {
        if (OrderArgs[ORDER_NAME].equals(Orders.abs.toString())) {
            return new AbsOrder(OrderArgs);
        }
        if (OrderArgs[ORDER_NAME].equals(Orders.type.toString())) {
            return new typeOrder(OrderArgs);
        }
        if (OrderArgs[ORDER_NAME].equals(Orders.size.toString())) {
            return new SizeOrder(OrderArgs);
        }
        throw new TypeOneException();
    }

    /**
     * a method that return an abs order filter that is the default filter
     *
     * @return default filter
     */

    public Order GetDefault() {
        try {
            return new AbsOrder(DEFAULT_ORDER);
        } catch (TypeOneException e) {
            return null;
        }
    }
}



