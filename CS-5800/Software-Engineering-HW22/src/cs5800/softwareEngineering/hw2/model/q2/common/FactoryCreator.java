package cs5800.softwareEngineering.hw2.model.q2.common;

import cs5800.softwareEngineering.hw2.model.q2.airplane.AirBusFactory;
import cs5800.softwareEngineering.hw2.model.q2.airplane.AirplaneFactory;
import cs5800.softwareEngineering.hw2.model.q2.airplane.BoeingFactory;
import cs5800.softwareEngineering.hw2.model.q2.airplane.EmbraerFactory;
import cs5800.softwareEngineering.hw2.model.q2.boat.BertramFactory;
import cs5800.softwareEngineering.hw2.model.q2.boat.BoatFactory;
import cs5800.softwareEngineering.hw2.model.q2.boat.MasterCraftFactory;
import cs5800.softwareEngineering.hw2.model.q2.boat.SeaRayFactory;
import cs5800.softwareEngineering.hw2.model.q2.car.CarFactory;
import cs5800.softwareEngineering.hw2.model.q2.car.HondaFactory;
import cs5800.softwareEngineering.hw2.model.q2.car.PorscheFactory;
import cs5800.softwareEngineering.hw2.model.q2.car.TeslaFactory;

public class FactoryCreator {

    public static CarFactory createCarFactory(FactoryType factoryType) {
        switch (factoryType){
            case HONDA:
                    return HondaFactory.getInstance();
            case TESLA:
                    return TeslaFactory.getInstance();
            case PORSCHE:
                    return PorscheFactory.getInstance();
            default:
                    throw new IllegalArgumentException("Invalid Car Factory type.");
        }
    }

    public static AirplaneFactory createAirplaneFactory(FactoryType factoryType) {
        switch (factoryType){
            case BOEING:
                return BoeingFactory.getInstance();
            case EMBRAER:
                return EmbraerFactory.getInstance();
            case AIRBUS:
                return AirBusFactory.getInstance();
            default:
                throw new IllegalArgumentException("Invalid Airplane Factory type.");
        }
    }

    public static BoatFactory createBoatFactory(FactoryType factoryType) {
        switch (factoryType){
            case SEA_RAY:
                return SeaRayFactory.getInstance();
            case MASTER_CRAFT:
                return MasterCraftFactory.getInstance();
            case BERTRAM:
                return BertramFactory.getInstance();
            default:
                throw new IllegalArgumentException("Invalid Boat Factory type.");
        }
    }
}
