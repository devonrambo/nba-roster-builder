package models;

import java.math.BigDecimal;

public class Player implements Taxable {

    private String name;
    private BigDecimal salary;
    private String position;

    public Player(String name, String position, BigDecimal salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public Player(String name, BigDecimal salary){
        this.name = name;
        this.salary = salary;

    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }
}
