package cs5800.sofwareEngineering.quiz2.model.payment.drivers;

import cs5800.sofwareEngineering.quiz2.model.payment.adaptee.PayPalGatewayAdapter;
import cs5800.sofwareEngineering.quiz2.model.payment.adaptee.PaymentGatewayProcessor;
import cs5800.sofwareEngineering.quiz2.model.payment.adaptee.SquareGatewayAdapter;
import cs5800.sofwareEngineering.quiz2.model.payment.adaptee.StripeGatewayAdapter;
import cs5800.sofwareEngineering.quiz2.model.payment.gateways.PayPalPaymentAPI;
import cs5800.sofwareEngineering.quiz2.model.payment.gateways.SquarePaymentAPI;
import cs5800.sofwareEngineering.quiz2.model.payment.gateways.StripePaymentAPI;

public class Quiz2Driver {
    public static void main(String[] args) {
        // default
        PaymentGatewayProcessor paymentGateway = new PayPalGatewayAdapter(new PayPalPaymentAPI());
        // process payment using payPal
        paymentGateway.processPayment(24.0,"John.patric@gmail.com");

        // change the payment gateway to stripe
        paymentGateway = new StripeGatewayAdapter(new StripePaymentAPI());
        // process payment using stripe
        paymentGateway.processPayment(34.0, "walter.jackson@gmail.com",
                                    "Jackson Walter", "2342-34345-234234", "05/19/2024");

        // recently added square payment and change the payment gateway to Square
        paymentGateway = new SquareGatewayAdapter(new SquarePaymentAPI());
        // process payment using square
        paymentGateway.processPayment(87.0, "LAX-Terminal-1");
    }

}
