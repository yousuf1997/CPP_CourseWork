package cs5800.sofwareEngineering.quiz2.model.payment.adaptee;

/**
 * Market interface for our payment gateways system
 */
public interface PaymentGatewayProcessor {
    void processPayment(Double paymentAmount, String ...otherPaymentDetails);
}
