package cs5800.sofwareEngineering.hw3.model.q2.toppings;

import cs5800.sofwareEngineering.hw3.model.q2.food.Food;

public class Cheese extends ToppingDecorater {
    private Food food;

    public Cheese(Food food) {
        super.toppingPrice = 0.99;
        this.food = food;
    }

    @Override
    public double calculatePrice() {
        return food.calculatePrice() + super.toppingPrice;
    }

    @Override
    public double getFoodBasePrice() {
        return calculatePrice();
    }
}
