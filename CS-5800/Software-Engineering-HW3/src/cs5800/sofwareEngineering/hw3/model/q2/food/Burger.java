package cs5800.sofwareEngineering.hw3.model.q2.food;

public class Burger extends Food {

    private double foodBasePrice;

    public Burger() {
        this.foodBasePrice = 4.99;
    }

    @Override
    public double calculatePrice() {
        return foodBasePrice;
    }

    @Override
    public double getFoodBasePrice() {
        return foodBasePrice;
    }

}
