package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.Transaction;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

public class TransactionManager extends Thread{
    private Thread t;
    private String threadName;

    private Investor investor;
    private Company company;
    private Market program;
    private final Transaction transaction;

    public TransactionManager(String name, Investor investor, Company company, Market program){
        this.threadName = name;
        this.investor = investor;
        this.company = company;
        this.program = program;
        this.transaction = Transaction.getInstance();

//        System.out.println("Creating: " + threadName);
    }

    public void run(){
//        System.out.println("Running: " + threadName);
        try {
//            Transaction.getInstance().doTransaction(investor, company);
            transaction.doTransaction(investor, company);
            program.addCompanyToStack(company);
        }catch (InterruptedException e){
//            System.out.println("Thread: " + threadName + " stopped");
        }finally {
            if(Thread.activeCount() <= 4){
                program.start();
            }
        }

//        program.decreaseThreadCounter(program);
        //System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start(){
//        System.out.println("Starting: " + threadName);
        if(t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
