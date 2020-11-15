package sample;

import java.util.ArrayList;

public class Order implements Customizable{
        public static int lineNumber; //reset for each new order
        private ArrayList<OrderLine> orderlines;


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
                System.out.println("TESTING");
                return -1;

        }

        /**
         * This method adds a objectline generic obj to the ArrayList.
         * @param obj to be added to the ArrayList.
         * @return true is obj is added.
         */
        public boolean add(Object obj){ //take in a sandwich object
                /*Sandwich addSandwich = (Sandwich) obj;
                lineNumber = orderlines.size();
                double price = addSandwich.price(); //create price getter method in sandwich

                OrderLine orderLine = new OrderLine(lineNumber, addSandwich, price);
                 */
                OrderLine orderline = (OrderLine) obj;
                orderlines.add(orderline);
                lineNumber++;
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
                OrderLine orderline = (OrderLine) obj;
                int sandwichIndex = find(orderline); // save index of sandwich into sandwichIndex

                if (sandwichIndex == -1) {
                        return false;
                } else {
                        orderlines.remove(orderline);
                        lineNumber--;
                        return true;
                }
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
