package sample;

public class OrderLine {
    private int lineNumber; //a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;

    /**
     *Constructor for OrderLine class, used to instantiate an object of type OrderLine.
     * //@param lineNumber
     * @param sandwich
     * //@param price
     */

    public OrderLine(Sandwich sandwich){
        Order order = new Order();
        this.lineNumber = order.getLineNumber();
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     * Getter method to get the holder field of an Account object.
     * @return the holder Profile of the account.
     */
    public Sandwich getSandwich() {
        return sandwich;
    }

    /**
     * Getter method to get the holder field of an Account object.
     * @return the holder Profile of the account.
     */
    public double getPrice() {
        return price;
    }


    public static void setLineNumber(OrderLine orderLine, int index) {

        orderLine.lineNumber = index + 1;
    }



}
