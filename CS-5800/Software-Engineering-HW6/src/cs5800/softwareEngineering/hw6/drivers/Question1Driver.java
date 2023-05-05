package cs5800.softwareEngineering.hw6.drivers;

import cs5800.softwareEngineering.hw6.models.VendingMachine;
import cs5800.softwareEngineering.hw6.models.handler.*;

public class Question1Driver {

    public static void main(String[] args) {

        SnackDispenseHandler initialHandler = new SnackSelectionHandler();
        initialHandler.setNextHandler(new PaymentHandler()).setNextHandler(new DispenserHandler()).setNextHandler(new IdleStateHandler());

        VendingMachine vendingMachine = new VendingMachine(initialHandler);

        vendingMachine.listAllAvailableSnacks();

        // coke
        vendingMachine.selectSnack(0);
        vendingMachine.insertMoney(3);

        // pepsi
        vendingMachine.selectSnack(1);
        vendingMachine.insertMoney(4);

        // cheetos
        vendingMachine.selectSnack(2);
        vendingMachine.insertMoney(3);

        // Doritos
        vendingMachine.selectSnack(3);
        vendingMachine.insertMoney(5);

        // KitKat
        vendingMachine.selectSnack(5);
        vendingMachine.insertMoney(4);

        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);

        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);

        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);

        // Snickers -- should hit zero!!!
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);
    }


}
