package cs5800.softwareEngineering.hw1.model.q2;

public class Ship {

    private String name;
    private Integer builtYear;

    public Ship(String name, Integer builtYear) {
        this.name = name;
        this.builtYear = builtYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBuiltYear() {
        return builtYear;
    }

    public void setBuiltYear(Integer builtYear) {
        this.builtYear = builtYear;
    }

    public void print(){
        System.out.println("[Ship] Ship name is " + this.name + " , and built in " + this.builtYear + ".");
    }
}
