package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private String location;
    private BigDecimal softCap = new BigDecimal(109);
    private BigDecimal taxLine = new BigDecimal(132.6);
    private BigDecimal payroll = new BigDecimal(0);

    // turn these two into methods so they can be accessed outside

    // private BigDecimal overspentPayroll = new BigDecimal(0);
    // private BigDecimal pastSoftCap = new BigDecimal(0);


    private BigDecimal luxuryTax = new BigDecimal(0);
    private List<Taxable> roster = new ArrayList<Taxable>();
    private List<Taxable> guards = new ArrayList<Taxable>();
    private List<Taxable> forwards = new ArrayList<Taxable>();
    private List<Taxable> centers = new ArrayList<Taxable>();


    public Team(String name, String location){
        this.name = name;
        this.location = location;
    }


   public void addToGuardList(Taxable name){
       guards.add(name);
   }

    public void addToForwardsList(Taxable name){
        forwards.add(name);
    }

    public void addToCentersList(Taxable name){
        centers.add(name);
    }




    public void addPlayer(Taxable name) {
        roster.add(name);
        this.payroll = this.payroll.add(name.getSalary());

    }

    @Override
    public String toString() {
        String str = "---------------------------- \n";
        str += this.location + " " + this.name + "\n";
        str += "---------------------------- \n";

        for (Taxable person : centers) {

            str += person.getPosition() + " " + person.getName() + " $" + person.getSalary() + " million \n";

        }
        for (Taxable person : forwards) {

            str += person.getPosition() + " " + person.getName() + " $" + person.getSalary() + " million \n";

        }
        for (Taxable person : guards) {

            str += person.getPosition() + " " + person.getName() + " $" + person.getSalary() + " million \n";

        }



        str += "--------------------------- \n";
        str += "Payroll is at $" + this.payroll + " million \n";

        if(this.getPastSoftCap().setScale(2, RoundingMode.HALF_UP).compareTo(new BigDecimal(0)) >= 0 && this.getOverspentPayroll().setScale(2, RoundingMode.HALF_UP).compareTo(new BigDecimal(0)) >= 0) {
            str += "Which is $" + this.getPastSoftCap().setScale(2, RoundingMode.HALF_UP) + " million past the soft cap and $" + this.getOverspentPayroll().setScale(2, RoundingMode.HALF_UP) + " million past the tax line \n";
            str += "This roster as it stands owes an additional $" + this.getLuxuryTax().setScale(2, RoundingMode.HALF_UP) + " million in luxury tax ";
        }
        else if(this.getPastSoftCap().setScale(2, RoundingMode.HALF_UP).compareTo(new BigDecimal(0)) >= 0){
            str += "Which is $" + this.getPastSoftCap().setScale(2, RoundingMode.HALF_UP) + " million past the soft cap but $" + this.getOverspentPayroll().setScale(2, RoundingMode.HALF_UP).abs() + " million under the tax line \n";
            str += "This roster as it stands does not owe any luxury tax";

        }
        else{
            str += "Which is $" + this.getPastSoftCap().setScale(2, RoundingMode.HALF_UP).abs() + " million under the soft cap and $" + this.getOverspentPayroll().setScale(2, RoundingMode.HALF_UP).abs() + " million under the tax line \n";
            str += "This roster as it stands does not owe any luxury tax";
        }

        return str;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public BigDecimal getTaxLine() {
        return taxLine;
    }

    public BigDecimal getPayroll() {
        return payroll;
    }

    public BigDecimal getOverspentPayroll() {
        BigDecimal returnValue = new BigDecimal(0);
        returnValue = this.getPayroll().subtract(this.getTaxLine());
        return returnValue;
    }

    public BigDecimal getPastSoftCap() {
        BigDecimal returnValue = new BigDecimal(0);
        returnValue = this.getPayroll().subtract(this.getSoftCap());
        return returnValue;
    }


    public BigDecimal getLuxuryTax() {
        LuxuryTaxCalculator calc = new LuxuryTaxCalculator();
        return calc.TaxOwed(this.getOverspentPayroll());
    }

    public List<Taxable> getRoster() {
        return roster;
    }
}
