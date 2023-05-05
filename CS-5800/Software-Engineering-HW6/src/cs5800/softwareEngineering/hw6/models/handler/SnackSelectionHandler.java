package cs5800.softwareEngineering.hw6.models.handler;

import cs5800.softwareEngineering.hw6.models.StateOfVendingMachine;
import cs5800.softwareEngineering.hw6.models.VendingMachine;

public class SnackSelectionHandler extends SnackDispenseHandler {
    @Override
    public void handle(VendingMachine vendingMachine) {
        if (vendingMachine.getStateOfVendingMachine().getVendingMachineState() == StateOfVendingMachine.VendingState.PICK_SNACK) {
            if (vendingMachine.getStateOfVendingMachine().getCurrentSnackSelection() >= vendingMachine.getSnackStock().size()) {
                System.out.println("Invalid Snack, please select valid snack");
                return;
            }
            System.out.println("Its valid snack option, the snack option is available");
            return;
        }
        // move to next handler
        this.handleNext().handle(vendingMachine);
    }
}
