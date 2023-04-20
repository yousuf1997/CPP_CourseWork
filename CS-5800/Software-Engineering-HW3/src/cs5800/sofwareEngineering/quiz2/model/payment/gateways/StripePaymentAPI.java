package cs5800.sofwareEngineering.quiz2.model.payment.gateways;

/**
 * Payment Gateway for Stripe
 */
public class StripePaymentAPI {
    public void processStripePayment(double paymentAmount, String userEmail, String cardHolderName, String cardNumber, String expirationDate) {
        System.out.println("StripePaymentAPI >> processing $"+  paymentAmount +" of payments for user : " + userEmail);
        System.out.println("StripePaymentAPI >> credit card info : CardHolderName: "+  cardHolderName +",  CardNumber : " + cardNumber + ", and expires: " + expirationDate);
    }
}
