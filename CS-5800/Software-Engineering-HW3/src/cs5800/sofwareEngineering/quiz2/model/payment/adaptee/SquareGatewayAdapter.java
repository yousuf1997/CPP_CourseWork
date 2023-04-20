package cs5800.sofwareEngineering.quiz2.model.payment.adaptee;

import cs5800.sofwareEngineering.quiz2.model.payment.gateways.SquarePaymentAPI;

public class SquareGatewayAdapter implements PaymentGatewayProcessor {
    private SquarePaymentAPI squarePaymentAPI;

    public SquareGatewayAdapter(SquarePaymentAPI squarePaymentAPI) {
        this.squarePaymentAPI = squarePaymentAPI;
    }

    @Override
    public void processPayment(Double paymentAmount, String... otherPaymentDetails) {
        System.out.println("---------------- Stripe Payment Processor ----------------");
        String locationId = otherPaymentDetails[0];
        this.squarePaymentAPI.processPayPalPayment(paymentAmount, locationId);
    }
}
