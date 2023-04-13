package cs5800.sofwareEngineering.hw3.model.q2.toppings;

import cs5800.sofwareEngineering.hw3.model.q2.food.Food;

public class Onions extends ToppingDecorater {
    private Food food;

    public Onions(Food food) {
        super.toppingPrice = 0.99;
        this.food = food;
    }

    @Override
    public double calculatePrice() {
        return food.calculatePrice() + super.toppingPrice;
    }

    @Override
    public double getFoodBasePrice() {
        return food.getFoodBasePrice();
    }
}
