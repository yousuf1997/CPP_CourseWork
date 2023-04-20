package cs5800.sofwareEngineering.quiz2.model.payment.adaptee;

import cs5800.sofwareEngineering.quiz2.model.payment.gateways.PayPalPaymentAPI;

public class PayPalGatewayAdapter implements PaymentGatewayProcessor {
    private PayPalPaymentAPI payPalPaymentAPI;

    public PayPalGatewayAdapter(PayPalPaymentAPI payPalPaymentAPI) {
        this.payPalPaymentAPI = payPalPaymentAPI;
    }

    @Override
    public void processPayment(Double paymentAmount, String... otherPaymentDetails) {
        System.out.println("---------------- PayPal Payment Processor ----------------");
        String userEmail = otherPaymentDetails[0];
        payPalPaymentAPI.processPayPalPayment(paymentAmount, userEmail);
    }
}
