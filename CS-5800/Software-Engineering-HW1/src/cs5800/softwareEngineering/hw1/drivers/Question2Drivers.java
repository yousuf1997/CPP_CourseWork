package cs5800.softwareEngineering.hw1.drivers;

import cs5800.softwareEngineering.hw1.model.q2.CargoShip;
import cs5800.softwareEngineering.hw1.model.q2.CruiseShip;
import cs5800.softwareEngineering.hw1.model.q2.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * This file consists drivers for Question 2
 */
public class Question2Drivers {
    public static void main(String[] args) {

        List<Ship> shipList = new ArrayList<>();

        shipList.add(new Ship("Fishing Ship", 1985));
        shipList.add(new CargoShip("Los Angeles Carrier", 1999, 25000));
        shipList.add(new CruiseShip("Florida Cruise", 2004, 150));

        shipList.forEach(ship -> ship.print());
    }
}
