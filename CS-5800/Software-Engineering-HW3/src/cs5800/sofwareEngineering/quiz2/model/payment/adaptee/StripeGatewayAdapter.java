package cs5800.sofwareEngineering.quiz2.model.payment.adaptee;

import cs5800.sofwareEngineering.quiz2.model.payment.gateways.StripePaymentAPI;

public class StripeGatewayAdapter implements PaymentGatewayProcessor {
    private StripePaymentAPI stripePaymentAPI;

    public StripeGatewayAdapter(StripePaymentAPI stripePaymentAPI) {
        this.stripePaymentAPI = stripePaymentAPI;
    }

    @Override
    public void processPayment(Double paymentAmount, String... otherPaymentDetails) {
        System.out.println("---------------- Square Payment Processor ----------------");
        String userEmail = otherPaymentDetails[0];
        String cardHolderName = otherPaymentDetails[1];
        String cardNumber = otherPaymentDetails[2];
        String expirationDate = otherPaymentDetails[3];
        this.stripePaymentAPI.processStripePayment(paymentAmount, userEmail, cardHolderName, cardNumber, expirationDate);
    }
}
