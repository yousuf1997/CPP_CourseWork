package cs5800.softwareEngineering.hw6.models;


public class StateOfVendingMachine {

    public enum VendingState {
        IDLE,
        PICK_SNACK,
        WAITING_FOR_PAYMENT,
        DISPENSING
    }

    private VendingState vendingMachineState;
    private int currentSnackSelection;
    private double currentInsertedMoney;

    public void setIdleState() {
        System.out.println("The Vending machine is transitioning to Idle state.");
        this.vendingMachineState = VendingState.IDLE;
    }

    public void setWaitingForMoneyState() {
        System.out.println("The Vending machine is transitioning to Waiting for Money state.");
        this.vendingMachineState = VendingState.WAITING_FOR_PAYMENT;
    }

    public void setPickSnackState() {
        System.out.println("The Vending machine is transitioning to Picking Snack state.");
        this.vendingMachineState = VendingState.PICK_SNACK;
    }

    public void setDispensingState() {
        System.out.println("The Vending machine is transitioning to Waiting for Dispensing state.");
        this.vendingMachineState = VendingState.DISPENSING;
    }

    public void currentStateOfVendingMachine() {
        System.out.println("The Vending machine is in state of : " + this.vendingMachineState);
    }

    public int getCurrentSnackSelection() {
        return currentSnackSelection;
    }

    public void setCurrentSnackSelection(int currentSnackSelection) {
        this.currentSnackSelection = currentSnackSelection;
    }

    public double getCurrentInsertedMoney() {
        return currentInsertedMoney;
    }

    public void setCurrentInsertedMoney(double currentInsertedMoney) {
        this.currentInsertedMoney = currentInsertedMoney;
    }

    public VendingState getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(VendingState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }
}
