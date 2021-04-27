package models;

import java.math.BigDecimal;

public interface Taxable {

    public BigDecimal getSalary();
    public String getName();
    public String getPosition();
}
