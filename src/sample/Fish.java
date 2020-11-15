package sample;

public class Fish extends Sandwich{
    static final double BASE_PRICE = 12.99;

    /**
     * This method calculates the price of a Fish Sandwich.
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

    /**
     * This method returns a formatted string representing the toppings on a Fish Sandwich object.
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "Fish Sandwich; Grilled Snapper, Cilantro, Lime, Extra: " + super.toString();
    }
}
