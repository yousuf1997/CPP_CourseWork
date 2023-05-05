package cs5800.softwareEngineering.hw6.models.handler;

import cs5800.softwareEngineering.hw6.models.StateOfVendingMachine;
import cs5800.softwareEngineering.hw6.models.VendingMachine;

public class IdleStateHandler extends SnackDispenseHandler {
    @Override
    public void handle(VendingMachine vendingMachine) {
        if (vendingMachine.getStateOfVendingMachine().getVendingMachineState() == StateOfVendingMachine.VendingState.IDLE) {
            // refresh the state
            vendingMachine.getStateOfVendingMachine().setCurrentSnackSelection(-1);
            vendingMachine.getStateOfVendingMachine().setCurrentInsertedMoney(0);
            vendingMachine.getStateOfVendingMachine().setIdleState();
        } else {
            this.handleNext().handle(vendingMachine);
        }
    }
}
