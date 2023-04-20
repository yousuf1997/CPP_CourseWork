package cs5800.sofwareEngineering.quiz2.model.payment.gateways;

/**
 * Payment Gateway for Paypal
 */
public class PayPalPaymentAPI {
    public void processPayPalPayment(double paymentAmount, String userEmail) {
        System.out.println("PayPalPaymentAPI >> processing $"+  paymentAmount +" of payments for user : " + userEmail);
    }
}
