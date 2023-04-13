package cs5800.sofwareEngineering.hw3.drivers;

import cs5800.sofwareEngineering.hw3.model.q2.customer.Customer;
import cs5800.sofwareEngineering.hw3.model.q2.customer.LoyalCustomer;
import cs5800.sofwareEngineering.hw3.model.q2.food.Burger;
import cs5800.sofwareEngineering.hw3.model.q2.food.Food;
import cs5800.sofwareEngineering.hw3.model.q2.food.Pizza;
import cs5800.sofwareEngineering.hw3.model.q2.toppings.Cheese;
import cs5800.sofwareEngineering.hw3.model.q2.toppings.Onions;
import cs5800.sofwareEngineering.hw3.model.q2.toppings.Pickle;
import cs5800.sofwareEngineering.hw3.model.q2.toppings.ToppingDecorater;

import java.text.DecimalFormat;

public class Question2Driver {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        Food pizza = new Pizza();
        Food burger = new Burger();

        // adding toppings to pizza
        ToppingDecorater cheesePizza = new Cheese(pizza);
        ToppingDecorater cheeseAndPicklePizza = new Pickle(cheesePizza);
        ToppingDecorater cheeseAndPickleAndOnionPizza = new Onions(cheeseAndPicklePizza);

        Customer customer1 = new Customer(false);
        customer1.addOrder(cheeseAndPickleAndOnionPizza);
        LoyalCustomer nonLoyalCustomer = new LoyalCustomer(customer1);
        System.out.println("Not loyal customer, so no discount : (Pizza) $" + decimalFormat.format(nonLoyalCustomer.applyDiscountAndCalculatePrice()));

        // another customer with same order, but the customer is loyal customer so discount will be applied
        Customer customer2 = new Customer(true);
        customer2.addOrder(cheeseAndPickleAndOnionPizza);
        LoyalCustomer loyalCustomer = new LoyalCustomer(customer2);
        System.out.println("Loyal customer, so applying discount : (Pizza) $" + decimalFormat.format(loyalCustomer.applyDiscountAndCalculatePrice()));

        // adding toppings to burger
        ToppingDecorater cheeseBurger = new Cheese(burger);
        ToppingDecorater onionCheeseBurger = new Onions(cheeseBurger);

        // customer 3
        Customer customer3 = new Customer(false);
        customer3.addOrder(onionCheeseBurger);
        LoyalCustomer nonLoyalCustomer1 = new LoyalCustomer(customer3);
        System.out.println("Not loyal customer, so no discount : (Burger) $" + decimalFormat.format(nonLoyalCustomer1.applyDiscountAndCalculatePrice()));

        // customer 4 and loyal customer
        Customer customer4 = new Customer(true);
        customer4.addOrder(onionCheeseBurger);
        LoyalCustomer loyalCustomer1 = new LoyalCustomer(customer4);
        System.out.println("Loyal customer, so applying discount : (Burger) $" + decimalFormat.format(loyalCustomer1.applyDiscountAndCalculatePrice()));

    }
}
