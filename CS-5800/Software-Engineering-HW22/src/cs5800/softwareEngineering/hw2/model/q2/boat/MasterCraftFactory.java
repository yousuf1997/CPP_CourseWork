package cs5800.softwareEngineering.hw2.model.q2.boat;

public class MasterCraftFactory implements BoatFactory {
    private static BoatFactory instance = null;

    private MasterCraftFactory() {

    }

    public static BoatFactory getInstance() {
        if (instance == null) {
            instance = new MasterCraftFactory();
        }
        return instance;
    }

    @Override
    public void build() {
        System.out.println("Master built a boat.");
    }

    @Override
    public void repair() {
        System.out.println("Master repaired a boat.");
    }

    @Override
    public void restore() {
        System.out.println("Master restored a boat.");
    }
}
