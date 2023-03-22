package cs5800.softwareEngineering.hw2.drivers;

import cs5800.softwareEngineering.hw2.model.q1.Pizza;
import cs5800.softwareEngineering.hw2.model.q1.PizzaChain;
import cs5800.softwareEngineering.hw2.model.q1.PizzaHut;
import cs5800.softwareEngineering.hw2.model.q1.enums.PizzaSize;
import cs5800.softwareEngineering.hw2.model.q1.enums.PizzaTopping;

public class Question1Driver {
    public static void main(String[] args) {

        pizzaHutDriverWithAllSizes(new PizzaHut());

    }

    public static void pizzaHutDriverWithAllSizes(PizzaChain pizzaHut){

        Pizza smallPizza = pizzaHut.pizzaBuilder(PizzaSize.SMALL)
                .addTopping(PizzaTopping.CHICKEN)
                .addTopping(PizzaTopping.EXTRA_CHEESE)
                .addTopping(PizzaTopping.ONIONS)
                .build();

        Pizza mediumPizza = pizzaHut.pizzaBuilder(PizzaSize.MEDIUM)
                .addTopping(PizzaTopping.CHICKEN)
                .addTopping(PizzaTopping.EXTRA_CHEESE)
                .addTopping(PizzaTopping.ONIONS)
                .addTopping(PizzaTopping.PEPPERS)
                .addTopping(PizzaTopping.OLIVES)
                .addTopping(PizzaTopping.MUSHROOMS)
                .build();

        Pizza largePizza = pizzaHut.pizzaBuilder(PizzaSize.LARGE)
                .addTopping(PizzaTopping.CHICKEN)
                .addTopping(PizzaTopping.EXTRA_CHEESE)
                .addTopping(PizzaTopping.ONIONS)
                .addTopping(PizzaTopping.PEPPERS)
                .addTopping(PizzaTopping.OLIVES)
                .addTopping(PizzaTopping.MUSHROOMS)
                .addTopping(PizzaTopping.BEEF)
                .addTopping(PizzaTopping.HAM_AND_PINEAPPLE)
                .addTopping(PizzaTopping.HAM)
                .build();

        smallPizza.eat();
        mediumPizza.eat();
        largePizza.eat();
    }
}
