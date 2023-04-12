package cs5800.sofwareEngineering.hw3.model.q2.food;

public class Burger extends Food {

    private double foodBasePrice;

    public Burger(double basePrice) {
        this.foodBasePrice = basePrice;
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
