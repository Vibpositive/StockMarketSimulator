/**
 * Class: Investor
 *
 * Purpose: To create instances of Investors by using the Builder Pattern.
 */
package com.intelligence_1.stockmarketsimulator.model.investors;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.utilities.Subject;

import java.util.*;

/**
 * Stock Market Project
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class Investor {
    private final int investorID; // Investor ID
    private String investorName; // Investor Name
    private int investorNumberOfBoughtShares; // Investor number of shares
    private HashMap<Integer, Integer> shares;// Shares that the Investors have bought
    private double investorBudget; // Investor Budget

    /**
     * This is the Constructor of the Investor.
     * It is declare private because the class is instantiated using the builder pattern.
     *
     * It takes the Investor Builder as a parameter
     * @param builder InvestorBuilder Object
     */
    private Investor(InvestorBuilder builder) {
        this.investorID = builder.investorID;
        this.investorName = builder.investorName;
        this.investorNumberOfBoughtShares = builder.investorNumberOfBoughtShares;
        this.investorBudget = builder.investorBudget;
        this.shares = builder.shares;
    }

    /**
     * Getter for the investor ID
     * @return investor ID
     */
    public int getInvestorID() {
        return investorID;
    }

    /**
     * Getter for the Investor Name
     * @return investor name
     */
    public String getInvestorName() {
        return investorName;
    }

    /**
     * Setter for the investor name
     * @param investorName investor name
     */
    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    /**
     * Getter for the investor number of bought shares
     * @return investor number of bought shares
     */
    public int getInvestorNumberOfBoughtShares() {
        return investorNumberOfBoughtShares;
    }

    /**
     * Setter for the investor number of bought shares
     * @param investorNumberOfBoughtShares investor number of bought shares
     */
    public void setInvestorNumberOfBoughtShares(int investorNumberOfBoughtShares) {
        this.investorNumberOfBoughtShares = investorNumberOfBoughtShares;
    }

    /**
     * Getter for the Map of companies the investor has bought from (key) and how many have bought from them (value)
     * @return shares the investors have bought from companies.
     */
    public HashMap getShares() {
        return shares;
    }

    /**
     * Setter for the Map of companies the investor has bought from (key) and how many have bought from them (value)
     * @param shares shares the investors have bought from companies.
     */
    public void setShares(HashMap shares) {
        this.shares = shares;
    }

    /**
     * Getter for the investor budget
     * @return investor budget
     */
    public Double getInvestorBudget() {
        return investorBudget;
    }

    /**
     * Setter for the investor budget
     * @param investorBudget investor budget
     */
    public void setInvestorBudget(double investorBudget) {
        this.investorBudget = investorBudget;
    }

    /**
     * To string method used for printing companies in the console.
     * @return Investor String
     */
    @Override
    public String toString() {
        return "ID: " + this.investorID +
                "\n\tName: " + this.investorName +
                "\n\tShares: " + this.investorNumberOfBoughtShares +
                "\n\tTotal: " + this.investorBudget;
    }

    /**
     * Buys a share from a company
     * @param companyID Company ID
     */
    public void buyShare(int companyID, double cost){

        this.investorBudget = investorBudget - cost;
        this.investorNumberOfBoughtShares++;

        if (this.shares.containsKey(companyID)){
            int currentShares = this.shares.get(companyID);
            this.shares.put(companyID, currentShares + 1);
        }else{
            this.shares.put(companyID, 1);
        }
    }

    /**
     * Prints out the ID of the companies
     * the investor bought the most shares
     */
    public void getMostInvestedCompanies(){
        if(shares.size() > 0){
            int maxValueInMap=(Collections.max(shares.values()));
            for (Map.Entry<Integer, Integer> entry : shares.entrySet()) {
                if (entry.getValue()==maxValueInMap) {
                    System.out.println(entry.getKey());
                }
            }
        }
    }

    public int companiesInvestedNumber(){
        return shares.size();
    }

    public int pickACompany(int companiesSize){
        return (int)(Math.random() * companiesSize);
    }

    /**
     * Public Static Inner Class. This is the Builder Class to instantiate the main class.
     * It has the same attributes as the main class.
     */
    public static class InvestorBuilder {

        private static int auxiliaryID = 0; // // Auxiliary value to calculate investor builder ID
        private final int investorID; // investor builder ID
        private String investorName; // investor builder name
        private int investorNumberOfBoughtShares; // investor builder number of bought shares
        private HashMap<Integer, Integer> shares; // investor builder shares bought from companies
        private double investorBudget; // investor builder budget

        /**
         * Constructor for the inner static class, the builder class
         * @param investorName investor builder name
         * @param investorBudget investor builder budget
         */
        public InvestorBuilder(String investorName, double investorBudget) {
            this.investorID = auxiliaryID;
            this.investorName = investorName;
            this.investorNumberOfBoughtShares = 0; // Number of bought shares are 0 at the beginning of the program
            this.investorBudget = investorBudget;
            this.shares = new HashMap<Integer, Integer>();

            auxiliaryID++;
        }

        /**
         * Builder Method use to instantiated the Main Class
         * @return Investor Object
         */
        public Investor build() {
            return new Investor(this);
        }
    }

    
}

