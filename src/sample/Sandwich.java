package sample;

import java.util.ArrayList;

public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras = new ArrayList<Extra>();

    /**
     * This is an abstract method that is overridden in the Chicken, Beef, or Fish classes.
     */
    public abstract double price();

    /**
     *This is an abstract method that is overridden in the Chicken, Beef, or Fish classes.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * This method returns a formatted string representing a Sandwich object.
     * It will be overridden in the Chicken, Beef, or Fish classes.
     *
     * @return formatted string.
     */
    public String toString() {
        String temp = "";
        if (extras.size() == 0)
            return "none.";
        else {
            for (Extra extra : extras)
                temp += extra + ", ";
            return temp;
        }
    }

    /**
     * Helper method to find an extra topping in the extras ArrayList.
     *
     * @param extra_topping to be searched for in the extras ArrayList.
     * @return index of the extra_topping in the ArrayList, or -1 if not found.
     */
    private int find(Extra extra_topping) {
        for (int i = 0; i < extras.size(); i++) {
            if (extras.get(i).equals(extra_topping)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method adds an extra topping to the extras ArrayList.
     *
     * @param obj to be added to the extras ArrayList.
     * @return true is obj is added, false if the obj exists in the .
     */
    public boolean add(Object obj) {
        Extra extra_topping = (Extra) obj;

        if (find(extra_topping) != -1) {
            return false;
        }

        if (extras.size() < MAX_EXTRAS) {
            extras.add(extra_topping);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean remove(Object obj){
        Extra extra_topping = (Extra) obj;
        int toppingIndex = find(extra_topping);

        if (toppingIndex == -1)
            return false;

        else{
            extras.remove(extra_topping);
            return true;
        }
    }
}