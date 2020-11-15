package sample;

public class OrderLine {
    private int lineNumber; //a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;

    /**
     *Constructor for OrderLine class, used to instantiate an object of type OrderLine.
     * @param lineNumber
     * @param sandwich
     * @param price
     */

    public OrderLine(int lineNumber, Sandwich sandwich, double price){
        this.lineNumber = lineNumber;
        this.sandwich = sandwich;
        this.price = price;
    }


}
