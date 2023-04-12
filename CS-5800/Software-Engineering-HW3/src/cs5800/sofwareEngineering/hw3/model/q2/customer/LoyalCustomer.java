package cs5800.sofwareEngineering.hw3.model.q2.customer;

public class LoyalCustomer {

    private double discountRate = 0.15;
    private Customer customer;

    public LoyalCustomer(Customer customer) {
        this.customer = customer;
    }

    public double applyDiscountAndCalculatePrice() {
        double lvTotalCost = customer.calculatePriceForOrders();
        return customer.isDiscountable() ? lvTotalCost - (lvTotalCost * discountRate) : lvTotalCost;
    }
}
