package sample;

import java.util.ArrayList;

public class Order implements Customizable{
        public static int lineNumber; //reset for each new order
        private ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();

        /**
         * Getter method to get the holder field of an Account object.
         * @return the holder Profile of the account.
         */
        public int getLineNumber() {
                if(orderlines.isEmpty())
                        lineNumber = 1;
                else
                        lineNumber = orderlines.size();
                return lineNumber;
        }

        /**
         * Getter method to get the orderlines ArrayList.
         * @return the orderlines ArrayList
         */
        public ArrayList getorderlines() {
                return orderlines;
        }


        /**
         * Helper method to find an orderline in ArrayList.
         * @param orderline to be searched for in ArrayList.
         * @return index of the objectline in the ArrayList, or -1 if not found.
         */
        private int find(OrderLine orderline) {
                for (int i = 0; i < orderlines.size(); i++) {
                        // compare each sandwich in order to sandwich you are searching for
                        if (orderlines.equals(orderlines.get(i))) {
                                return i;
                        }
                }
                return -1;
        }

        /**
         * This method adds a objectline generic obj to the ArrayList.
         * @param obj to be added to the ArrayList.
         * @return true is obj is added.
         */
        public boolean add(Object obj){ //take in a sandwich object
                OrderLine orderline = (OrderLine) obj;
                orderlines.add(orderline);
                //lineNumber++;
                System.out.println(orderlines.get(0));
                return true;
        }

        /**
         * This method removes a objectline generic obj from the ArrayList.
         * @param obj to be removed from ArrayList.
         * @return true if obj found and removed, false if obj doesn't exist.
         */
        public boolean remove(Object obj){ //take in a sandwich object
                /*Sandwich addSandwich = (Sandwich) obj;
                lineNumber = orderlines.size();
                double price = addSandwich.price(); //create price getter method in sandwich

                OrderLine orderLine = new OrderLine(lineNumber, addSandwich, price);
                 */
                if (orderlines.isEmpty()){ return false;}
                OrderLine orderline = (OrderLine) obj;
                orderlines.remove(orderline);
                //lineNumber--;
                for (int i = 0; i < orderlines.size(); i++){
                        OrderLine.setLineNumber(orderlines.get(i), i); //reset line nums for all obj in orderlines list
                }
                return true;

        }

        /**
         * This method displays all accounts in the database unsorted.
         */
        public String printOrder() {
                String result = "";

                for (int i = 0; i < orderlines.size(); i++) {
                        result += orderlines.get(i).toString() +"\n"; // print items using the format defined by the toString method
                }
                result += "\n";
                return result;
        }

}
