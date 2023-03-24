package cs5800.softwareEngineering.hw2.drivers;

import cs5800.softwareEngineering.hw2.model.q2.airplane.AirplaneFactory;
import cs5800.softwareEngineering.hw2.model.q2.boat.BoatFactory;
import cs5800.softwareEngineering.hw2.model.q2.car.CarFactory;
import cs5800.softwareEngineering.hw2.model.q2.common.FactoryCreator;
import cs5800.softwareEngineering.hw2.model.q2.common.FactoryType;

public class Question2Driver {

    public static void main(String[] args) {

        CarFactory lvHondaFactory = FactoryCreator.createCarFactory(FactoryType.HONDA);
        CarFactory lvTeslaFactory = FactoryCreator.createCarFactory(FactoryType.TESLA);
        CarFactory lvPorscheFactory = FactoryCreator.createCarFactory(FactoryType.PORSCHE);

        AirplaneFactory lvBoeing = FactoryCreator.createAirplaneFactory(FactoryType.BOEING);
        AirplaneFactory lvAirbus = FactoryCreator.createAirplaneFactory(FactoryType.AIRBUS);
        AirplaneFactory lvEmbrayer = FactoryCreator.createAirplaneFactory(FactoryType.EMBRAER);

        // run all the car factories
        System.out.println("Testing car factories..");
        lvHondaFactory.build();
        lvHondaFactory.repair();
        lvHondaFactory.restore();
        System.out.println("-------------");
        lvTeslaFactory.build();
        lvTeslaFactory.repair();
        lvTeslaFactory.restore();
        System.out.println("-------------");
        lvPorscheFactory.build();
        lvPorscheFactory.repair();
        lvPorscheFactory.restore();
        System.out.println("-------------");

        // run all the airplane factories
        System.out.println("Testing airplane factories..");
        lvBoeing.build();
        lvBoeing.repair();
        lvBoeing.restore();
        System.out.println("-------------");
        lvAirbus.build();
        lvAirbus.repair();
        lvAirbus.restore();
        System.out.println("-------------");
        lvEmbrayer.build();
        lvEmbrayer.repair();
        lvEmbrayer.restore();
        System.out.println("-------------");


        // attempt to create second factory of Tesla and airbus
        CarFactory lvTeslaFactory2 = FactoryCreator.createCarFactory(FactoryType.TESLA);
        AirplaneFactory lvAirbus2 = FactoryCreator.createAirplaneFactory(FactoryType.AIRBUS);

        // trying to run the attempt 2 factories of tesla and airbus
        System.out.println("Test attempt 2 factory");
        lvTeslaFactory2.build();
        lvTeslaFactory2.repair();
        lvTeslaFactory2.restore();

        System.out.println("Test attempt 2 factory");
        lvAirbus2.build();
        lvAirbus2.repair();
        lvAirbus2.restore();

        // added boat factories
        BoatFactory lvSeaRay = FactoryCreator.createBoatFactory(FactoryType.SEA_RAY);
        BoatFactory lvMasterCraft = FactoryCreator.createBoatFactory(FactoryType.MASTER_CRAFT);
        BoatFactory lvBertram = FactoryCreator.createBoatFactory(FactoryType.BERTRAM);

        // run all the boat factories
        System.out.println("Testing boat factories..");
        lvSeaRay.build();
        lvSeaRay.repair();
        lvSeaRay.restore();
        System.out.println("-------------");
        lvMasterCraft.build();
        lvMasterCraft.repair();
        lvMasterCraft.restore();
        System.out.println("-------------");
        lvBertram.build();
        lvBertram.repair();
        lvBertram.restore();
        System.out.println("-------------");

    }

}
