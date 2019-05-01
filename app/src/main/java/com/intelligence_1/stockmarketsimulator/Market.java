package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.SetUpData;
import com.intelligence_1.stockmarketsimulator.model.Transaction;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.CompanyObserver;
import com.intelligence_1.stockmarketsimulator.model.utilities.MarketObserverInterface;
import com.intelligence_1.stockmarketsimulator.model.utilities.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Market implements Subject<MarketObserverInterface> {

    private int threadCounter = 0;

    private Stack<Company> companiesStack;

    private List<Company> companies = SetUpData.SetUpCompanies(1);
    private List<Company> inactiveCompanies;
    private List<Investor> investors = SetUpData.SetUpInvestors(100);
    private MarketObserverInterface observer;
    private int i = 0;

    public void addInactiveCompany(Company inactiveCompany) {
        this.inactiveCompanies.add(inactiveCompany);
    }

    public  Market(){
        observer = null;
        companiesStack = new Stack<>();
        inactiveCompanies = new ArrayList<Company>();
        new CompanyObserver(this);
    }

    public void start(){
//        while(threadCounter < 4 && (i < 11)){
//        while(threadCounter < 2 && companies.size() > 0){
//        while(companies.size() > 0){
            try {
                int randomInvestorIndex1 = (int)(Math.random() * investors.size());
                Investor randomInvestor1 = investors.get(randomInvestorIndex1);

                int randomCompanyIndex1 = (int)(Math.random() * companies.size());
                Company randomCompany1 = companies.get(randomCompanyIndex1);
                TransactionManager thread1 = new TransactionManager("Transaction 1: " + i, randomInvestor1, randomCompany1, this);
                thread1.start();
                try {
                    thread1.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }
            }catch (Exception e){
                System.out.println("Interrupted");
            }
            try {
                int randomInvestorIndex2 = (int)(Math.random() * investors.size());
                Investor randomInvestor2 = investors.get(randomInvestorIndex2);

                int randomCompanyIndex2 = (int)(Math.random() * companies.size());
                Company randomCompany2 = companies.get(randomCompanyIndex2);
                TransactionManager thread2 = new TransactionManager("Transaction 2: " + i, randomInvestor2, randomCompany2, this);
                thread2.start();
                try {
                    thread2.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }
            }catch (Exception e){
                System.out.println("Interrupted");
            }
            try {
                int randomInvestorIndex3 = (int)(Math.random() * investors.size());
                Investor randomInvestor3 = investors.get(randomInvestorIndex3);

                int randomCompanyIndex3 = (int)(Math.random() * companies.size());
                Company randomCompany3 = companies.get(randomCompanyIndex3);
                TransactionManager thread3 = new TransactionManager("Transaction 3: " + i, randomInvestor3, randomCompany3, this);
                thread3.start();
                try {
                    thread3.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }
            }catch (Exception e){
                System.out.println("Interrupted");
            }
            try {
                int randomInvestorIndex4 = (int)(Math.random() * investors.size());
                Investor randomInvestor4 = investors.get(randomInvestorIndex4);

                int randomCompanyIndex4 = (int)(Math.random() * companies.size());
                Company randomCompany4 = companies.get(randomCompanyIndex4);
                TransactionManager thread4 = new TransactionManager("Transaction 4: " + i, randomInvestor4, randomCompany4, this);
                thread4.start();
                try {
                    thread4.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }
            }catch (Exception e){
                System.out.println("Interrupted");
            }

//            }
            System.out.println("companies.size(): " + companies.size());
            i++;
//        }

    }

    public void updateCompaniesList() {


        for (Company c : companies) {
//            if(!c.canSellShare()){
            this.addInactiveCompany(c);
            this.getCompanies().remove(c);
            System.out.println("updateCompaniesList");
            System.out.println("incompanies.size: " + companies.size());
            System.out.println("companies.size: " + inactiveCompanies.size());
//            }
        }

    }

    public void printShit(){
        while (Thread.activeCount() > 2) {
//            System.out.println(Thread.activeCount());
        }
        System.out.println(Thread.getAllStackTraces().keySet());
        System.out.println("Print investor");
        System.out.println(Thread.activeCount());

        for (Investor i : investors) {
            if(i.getInvestorNumberOfBoughtShares() > 0){
                System.out.println(i.getInvestorName() + "\n\tBudget " + i.getInvestorBudget() + "\n\tshares: " + i.getInvestorNumberOfBoughtShares());
            }
        }
        System.out.println("COmpanies size: " + companies.size());
    }

    public void decreaseThreadCounter(Market market){
        threadCounter--;
        System.out.println("threadCounter: " + threadCounter);
        start();
    }

    public void addCompanyToStack(Company company){
        if(this.companiesStack.size() == 10){
            notifyObservers();
            this.companiesStack.clear();
        }
        this.companiesStack.add(company);
    }

    @Override
    public void register(MarketObserverInterface observer) {
        this.observer = observer;
    }


    @Override
    public void unregister() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        this.observer.update(this.companies, this.companiesStack);
    }

    @Override
    public Object getUpdate() {
        return null;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
