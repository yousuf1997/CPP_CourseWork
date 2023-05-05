package cs5800.softwareEngineering.hw6.drivers;

import cs5800.softwareEngineering.hw6.models.VendingMachine;
import cs5800.softwareEngineering.hw6.models.handler.*;

public class Question1Driver {

    public static void main(String[] args) {

        SnackDispenseHandler initialHandler = new IdleStateHandler();
        // this is kind of cycle, once the snack is properly dispensed the state will move to the idle
        // next state of the ide should be the snack selection for next snack!
        initialHandler.setNextHandler(new SnackSelectionHandler()).setNextHandler(new PaymentHandler()).setNextHandler(new DispenserHandler())
                .setNextHandler(initialHandler);

        VendingMachine vendingMachine = new VendingMachine(initialHandler);

        vendingMachine.listAllAvailableSnacks();

        // coke
        vendingMachine.selectSnack(0);
        vendingMachine.insertMoney(3);
        vendingMachine.dispenseSnack();

        // pepsi
        vendingMachine.selectSnack(1);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

        // cheetos
        vendingMachine.selectSnack(2);
        vendingMachine.insertMoney(3);
        vendingMachine.dispenseSnack();

        // Doritos
        vendingMachine.selectSnack(3);
        vendingMachine.insertMoney(5);
        vendingMachine.dispenseSnack();

        // KitKat
        vendingMachine.selectSnack(5);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

        // in order to make snikcers to zero quantity, we will be keep buying it, and the original quantity is only 3
        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

        // Snickers
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

        // Snickers -- should hit zero!!!
        vendingMachine.selectSnack(6);
        vendingMachine.insertMoney(4);
        vendingMachine.dispenseSnack();

    }


}
