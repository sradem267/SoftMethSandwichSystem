package sample;

public class Chicken extends Sandwich {
    static final double BASE_PRICE = 8.99;

    /**
     * This method calculates the price of a Chicken Sandwich.
     * @return price.
     */
    @Override
    public double price(){
        if(extras.size() == 0){
            return BASE_PRICE;
        }
        else{
            double additional_cost = 0.0;
            for(Extra extra: extras)
                additional_cost += PER_EXTRA;
            return BASE_PRICE + additional_cost;
        }
    }

    @Override
    public boolean equals(Object obj){
        return true;
    }

    /**
     * This method returns a formatted string representing the toppings on a Chicken Sandwich object.
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "Chicken Sandwich; Fried Chicken, Spicy Sauce, Pickles, Extra: " + super.toString();
    }
}
