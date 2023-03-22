package cs5800.softwareEngineering.hw2.model.q1;

import cs5800.softwareEngineering.hw2.model.q1.enums.PizzaSize;

public interface PizzaChain {
    public Pizza.PizzaBuilder pizzaBuilder(PizzaSize size);
}
