package cs5800.softwareEngineering.hw6.models;

import cs5800.softwareEngineering.hw6.models.handler.SnackDispenseHandler;

import java.util.List;
import java.util.stream.IntStream;

public class VendingMachine {
    private StateOfVendingMachine stateOfVendingMachine;
    private List<Snack> snackStock;
    private SnackDispenseHandler snackDispenseHandler;

    public VendingMachine(SnackDispenseHandler snackDispenseHandler) {
        this.stateOfVendingMachine = new StateOfVendingMachine();
        loadAllSnacks();
        this.snackDispenseHandler = snackDispenseHandler;
        stateOfVendingMachine.setIdleState();
    }

    private void loadAllSnacks() {
        this.snackStock = List.of(
                new Snack("Coke", 2.99, 15),
                new Snack("Pepsi", 1.99, 10),
                new Snack("Cheetos", 1.49, 20),
                new Snack("Doritos", 3.49, 12),
                new Snack("Snickers", 1.29, 25),
                new Snack("KitKat", 2.49, 18),
                new Snack("Snickers", 1.99, 3),
                new Snack("Trail Mix", 2.99, 14),
                new Snack("Milano Cookies", 3.99, 10),
                new Snack("Beef Jerky", 4.49, 8)
        );
    }

    public void listAllAvailableSnacks() {
        IntStream.rangeClosed(1, this.snackStock.size()).forEach(value -> System.out.println(value + ") " + this.snackStock.get(value - 1).getName() + " : " + this.snackStock.get(value - 1).getPrice()));
    }

    public StateOfVendingMachine getStateOfVendingMachine() {
        return this.stateOfVendingMachine;
    }

    public List<Snack> getSnackStock() {
        return this.snackStock;
    }

    public void selectSnack(int snackItemIndex) {
        if(!isValidTransitionState(StateOfVendingMachine.VendingState.PICK_SNACK)) {
            System.out.println("Not valid transition state.");
            return;
        }
        this.stateOfVendingMachine.setCurrentSnackSelection(snackItemIndex);
        this.stateOfVendingMachine.setPickSnackState();
        this.snackDispenseHandler.handle(this);
    }

    public void insertMoney(double money) {
        if(!isValidTransitionState(StateOfVendingMachine.VendingState.WAITING_FOR_PAYMENT)) {
            System.out.println("Not valid transition state.");
            return;
        }
        this.stateOfVendingMachine.setCurrentInsertedMoney(money);
        this.stateOfVendingMachine.setWaitingForMoneyState();
        this.snackDispenseHandler.handle(this);
    }

    public void dispenseSnack() {
        if(!isValidTransitionState(StateOfVendingMachine.VendingState.DISPENSING)) {
            System.out.println("Not valid transition state.");
            return;
        }
        this.stateOfVendingMachine.setDispensingState();
        this.snackDispenseHandler.handle(this);
        // its does not matter if snack has been dispensed or not
        // at this level we should progress to the idle state
        this.stateOfVendingMachine.setIdleState();
    }

    public StateOfVendingMachine.VendingState getCurrentState() {
        return stateOfVendingMachine.getVendingMachineState();
    }

    public boolean isValidTransitionState(StateOfVendingMachine.VendingState vendingState) {
        StateOfVendingMachine.VendingState currentState = getCurrentState();
        switch (currentState) {
            case IDLE :
                return vendingState == StateOfVendingMachine.VendingState.PICK_SNACK;
            case PICK_SNACK:
                return vendingState == StateOfVendingMachine.VendingState.WAITING_FOR_PAYMENT;
            case WAITING_FOR_PAYMENT:
                return vendingState == StateOfVendingMachine.VendingState.DISPENSING;
            default:
                return false;
        }
    }

}
