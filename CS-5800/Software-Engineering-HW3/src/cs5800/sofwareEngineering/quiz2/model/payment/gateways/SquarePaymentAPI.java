package cs5800.sofwareEngineering.quiz2.model.payment.gateways;

/**
 * Payment Gateway for Square
 */
public class SquarePaymentAPI {
    public void processPayPalPayment(double paymentAmount, String locationId) {
        System.out.println("SquarePaymentAPI >> processing $"+  paymentAmount +" for location  : " + locationId);
    }
}
