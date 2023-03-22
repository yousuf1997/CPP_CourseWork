package cs5800.softwareEngineering.hw2.model.q1.enums;

public enum PizzaChainType {

    PIZZA_HUT("Pizza Hut"),
    DOMINOS("Dominos"),
    LITTLE_CAESARS("Little Caesars");
    public final String name;

    PizzaChainType(String name) {
        this.name = name;
    }
}
