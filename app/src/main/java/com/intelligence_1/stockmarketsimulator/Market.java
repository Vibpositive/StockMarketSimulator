package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.SetUpData;
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

    private List<Company> companies = SetUpData.SetUpCompanies(100);
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
//        System.out.println("Market.start()");
        /*synchronized (companies){
            if(companies.isEmpty()) {
                System.out.println("Market.stop");
                return;
            }
        }*/
//        for (Investor investor : investors){
        for (int c = 0; c < 10; c++){
            try{
                synchronized (companies){
                    if(!companies.isEmpty()){
                        int randomCompanyIndex = (int)(Math.random() * companies.size());
                        Company randomCompany = companies.get(randomCompanyIndex);

                        int randomInvestorIndex = (int)(Math.random() * investors.size());
                        Investor investor = investors.get(randomInvestorIndex);

                        TransactionManager thread = new TransactionManager(
                                "Transaction 1: " + i, investor, randomCompany, this
                        );
                        thread.start();
                        try {
                            thread.join();
                        } catch ( Exception e) {
                            System.out.println("Interrupted1: " + e.getMessage());
                        }
                    }
                }

            }catch (Exception e){
                System.out.println("Interrupted2: ");
                System.out.println(e.getStackTrace()[0]);
                System.out.println(e.getStackTrace()[1]);
                System.out.println(e.getStackTrace()[2]);
                System.out.println(e.getStackTrace()[3]);
                System.out.println(e.getStackTrace()[4]);
                System.out.println(e.getStackTrace().length);
            }
        }
            /*try {
                int randomInvestorIndex1 = (int)(Math.random() * investors.size());
                Investor randomInvestor1 = investors.get(randomInvestorIndex1);

                int randomCompanyIndex1 = (int)(Math.random() * companies.size());
                Company randomCompany1 = companies.get(randomCompanyIndex1);
                TransactionManager thread1 = new TransactionManager("Transaction 1: " + i, randomInvestor1, randomCompany1, this);
                thread1.start();
                try {
                    thread1.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted: " + e.getStackTrace());
                }
            }catch (Exception e){
                System.out.println("Interrupted: " + e.getStackTrace());
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
                    System.out.println("Interrupted: " + e.getStackTrace());
                }
            }catch (Exception e){
                System.out.println("Interrupted: " + e.getStackTrace());
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
                    System.out.println("Interrupted: " + e.getStackTrace());
                }
            }catch (Exception e){
                System.out.println("Interrupted: " + e.getStackTrace());
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
                    System.out.println("Interrupted: " + e.getStackTrace());
                }
            }catch (Exception e){
                System.out.println("Interrupted: " + e.getStackTrace());
            }*/

//            }
//            System.out.println("companies.size(): " + companies.size());
//            System.out.println("companies: " + companies);
            i++;
    }

    public void updateCompaniesList(Company company) {
            synchronized (companies){
                if(!companies.isEmpty()){
                    this.companies.remove(company);
                    this.addInactiveCompany(company);
                    System.out.println("updateCompaniesList");
                    System.out.println("companies.size: " + companies.size());
                }else{
                    System.out.println("It wont create any threads");
                }

//                System.out.println("companies.size: " + inactiveCompanies.size());
            }
    }

    public void printShit(){
//        while (Thread.activeCount() > 2) {
//        }

//        System.out.println(Thread.getAllStackTraces().keySet());
//        System.out.println("Print investor");
//        System.out.println(Thread.activeCount());
        synchronized (companies){
//            if(companies.isEmpty()){
                for (Investor i : investors) {
                    if(i.getInvestorNumberOfBoughtShares() > 0){
                        System.out.println(i.getInvestorName() + "\n\tBudget " + i.getInvestorBudget() + "\n\tshares: " + i.getInvestorNumberOfBoughtShares());
                    }
                }

                for (Company c : inactiveCompanies) {
//            if(i.getInvestorNumberOfBoughtShares() > 0){
                    System.out.println(c);
//            }
//                }
//        System.out.println("Comp " + companies);
            }
        }

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
