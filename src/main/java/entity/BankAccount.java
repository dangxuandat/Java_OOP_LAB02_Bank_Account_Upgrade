/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author SE150699
 */
public class BankAccount implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String id_account;
    double balance = 0;

    public BankAccount(String id_account) {
        this.id_account = id_account;
        this.balance = 0;
    }

    public BankAccount() {
    }

    public String getId_account() {
        return id_account;
    }

    public void setId_account(String id_account) {
        this.id_account = id_account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString(){
        return id_account + " - " + balance;
    }
    
    @Override
    public boolean equals(Object obj){
        return this.getId_account().equals(((BankAccount)obj).getId_account());
    }
}
