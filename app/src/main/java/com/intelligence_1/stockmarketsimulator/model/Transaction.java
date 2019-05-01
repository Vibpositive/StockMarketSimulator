package com.intelligence_1.stockmarketsimulator.model;

import android.app.PendingIntent;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

public class Transaction {
    private static Transaction instance;

    private Transaction(){}

    public static synchronized Transaction getInstance(){
        if(instance == null){
            instance = new Transaction();
        }
        return instance;
    }

    public void doTransaction(Investor investor, Company company) throws InterruptedException{
        if(investor.getInvestorBudget() >= company.getSharePrice() && company.canSellShare()){
//        if(investor.getInvestorBudget() >= company.getSharePrice()){
            investor.buyShare(company.getCompanyID(), company.getSharePrice());
            company.soldAShare();
        }else if(investor.getInvestorBudget() < company.getSharePrice()){
            throw new InterruptedException("Investor: " + investor.getInvestorName() + " does not have enough funds!");
        }else if(!company.canSellShare()){
            throw new InterruptedException("Company does not have shares to sell");
        }
    }

}
