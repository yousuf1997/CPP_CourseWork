package cs5800.sofwareEngineering.hw3.model.q2.customer;

import cs5800.sofwareEngineering.hw3.model.q2.food.Food;

import java.util.List;

public class Customer extends LoyaltyStatus {
    private List<Food> orders;
    private boolean loyalCustomer;

    public Customer(boolean loyalCustomer) {
        this.loyalCustomer = loyalCustomer;
    }

    public void addOrder(Food food) {
        orders.add(food);
    }

    public double calculatePriceForOrders() {
        return orders.stream().mapToDouble(Food::getFoodBasePrice).sum();
    }

    @Override
    public boolean isDiscountable() {
        return loyalCustomer;
    }
}
