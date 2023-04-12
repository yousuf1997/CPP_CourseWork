package cs5800.sofwareEngineering.hw3.model.q2.toppings;

import cs5800.sofwareEngineering.hw3.model.q2.food.Food;

public class Cheese extends ToppingDecorater {
    private Food food;

    public Cheese(Food food, double toppingPrice) {
        super.toppingPrice = toppingPrice;
    }

    @Override
    public double calculatePrice() {
        return getFoodBasePrice() + super.toppingPrice;
    }

    @Override
    public double getFoodBasePrice() {
        return food.getFoodBasePrice();
    }
}
