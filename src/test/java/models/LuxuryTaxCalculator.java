package models;

import java.math.BigDecimal;

public class LuxuryTaxCalculator {

    private BigDecimal capOverTaxLine;

    public BigDecimal TaxOwed(BigDecimal capOverTaxLine) {

        // the luxury tax is a progressive tax system that kicks in once the spending passes the "TaxLine" of 132.6 million
        // This whole class is super dense so perhaps there are cleaner ways to do this(?)
        // Although I'll partially blame BigDecimals for making all the math looks so wordy!!
        // 1.5x the first 5 mill
        // 1.75x the next 5
        // 2.5x
        // 3.25x
        // add another .5x for every 5 mill

        BigDecimal taxOwed = new BigDecimal(0);
        BigDecimal taxBracketValue = new BigDecimal(1);
        BigDecimal capNotAddedYet = capOverTaxLine;

        BigDecimal firstBracketCost = new BigDecimal(5).multiply(new BigDecimal(1.5));
        BigDecimal secondBracketCost = new BigDecimal(5).multiply(new BigDecimal(1.75));
        BigDecimal thirdBracketCost = new BigDecimal(5).multiply(new BigDecimal(2.5));
        BigDecimal fourthBracketCost = new BigDecimal(5).multiply(new BigDecimal(3.25));


        // first 5 mill over the cap is charged times 1.5
        if (capOverTaxLine.compareTo(new BigDecimal(5)) <= 0) {
            taxOwed = taxBracketValue.multiply(capNotAddedYet).multiply(new BigDecimal(1.5));

        }
        // the next 5 mill over the cap is charged times 1.75
        else if (capOverTaxLine.compareTo(new BigDecimal(10)) <= 0) {
            BigDecimal baseLine = firstBracketCost;
            BigDecimal moneyInThisBracket = capOverTaxLine.subtract(new BigDecimal(5));
            taxOwed = baseLine.add((moneyInThisBracket).multiply(new BigDecimal(1.75)));

        }
        // next 5 mill charged times 2.5
        else if (capOverTaxLine.compareTo(new BigDecimal(15)) <= 0) {
            BigDecimal baseLine = firstBracketCost.add(secondBracketCost);
            BigDecimal moneyInThisBracket = capOverTaxLine.subtract(new BigDecimal(10));
            taxOwed = baseLine.add((moneyInThisBracket).multiply(new BigDecimal(2.5)));
        }
        // next 5 mill charged times 3.25
        else if (capOverTaxLine.compareTo(new BigDecimal(20)) <= 0) {
            BigDecimal baseLine = firstBracketCost.add(secondBracketCost).add(thirdBracketCost);
            BigDecimal moneyInThisBracket = capOverTaxLine.subtract(new BigDecimal(15));
            taxOwed = baseLine.add((moneyInThisBracket).multiply(new BigDecimal(3.25)));

        }
        // amounts raise by 50 cents on the dollar for every 5 million
        // a loop here will go though as many times as needed to sum up any luxury tax from cap over 20 million over
        else {
            BigDecimal baseLine = firstBracketCost.add(secondBracketCost).add(thirdBracketCost).add(fourthBracketCost);

            capNotAddedYet = capOverTaxLine.subtract(new BigDecimal(20));
            boolean anotherLoop = true;
            BigDecimal penaltyRate = new BigDecimal(3.75);

            do {

                if (capNotAddedYet.compareTo(new BigDecimal(5)) > 0) {
                    baseLine = baseLine.add(penaltyRate.multiply(new BigDecimal(5)));
                    penaltyRate = penaltyRate.add(new BigDecimal(0.5));
                    capNotAddedYet = capNotAddedYet.subtract(new BigDecimal(5));
                    taxOwed = baseLine;
                } else {
                    taxOwed = baseLine.add(penaltyRate.multiply(capNotAddedYet));
                    capNotAddedYet = new BigDecimal(0);
                    anotherLoop = false;
                }


            } while (anotherLoop);

            return taxOwed;

        }

        return taxOwed;


    }
}
