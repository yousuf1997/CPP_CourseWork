package cs5800.softwareEngineering.hw2.model.q1;

import cs5800.softwareEngineering.hw2.model.q1.enums.PizzaChainType;
import cs5800.softwareEngineering.hw2.model.q1.enums.PizzaSize;

public class LittleCaesars implements PizzaChain {

    public LittleCaesars(){

    }

    @Override
    public Pizza.PizzaBuilder pizzaBuilder(PizzaSize size) {
        return new Pizza.PizzaBuilder(size, PizzaChainType.LITTLE_CAESARS);
    }
}
