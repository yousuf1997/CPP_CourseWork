package cs5800.softwareEngineering.hw6.models.handler;

import cs5800.softwareEngineering.hw6.models.Snack;
import cs5800.softwareEngineering.hw6.models.StateOfVendingMachine;
import cs5800.softwareEngineering.hw6.models.VendingMachine;

public class PaymentHandler extends SnackDispenseHandler {
    @Override
    public void handle(VendingMachine vendingMachine) {
        if (vendingMachine.getStateOfVendingMachine().getVendingMachineState() == StateOfVendingMachine.VendingState.WAITING_FOR_PAYMENT) {
            int userSelectedSnackIndex = vendingMachine.getStateOfVendingMachine().getCurrentSnackSelection();
            double userInsertedMoney = vendingMachine.getStateOfVendingMachine().getCurrentInsertedMoney();
            Snack userSelectedSnack = vendingMachine.getSnackStock().get(userSelectedSnackIndex);
            if (userInsertedMoney < userSelectedSnack.getPrice()) {
                System.out.println("PaymentHandler : User does not have enough money to dispense item. The snack cost $" + userSelectedSnack.getPrice());
                return;
            }
            System.out.println("PaymentHandler : User has enough money to buy snack.");
            vendingMachine.dispenseSnack();
        } else {
            // move to next
            this.handleNext().handle(vendingMachine);
        }

    }
}
