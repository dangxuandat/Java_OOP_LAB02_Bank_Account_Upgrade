/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repo.inter;

import java.util.ArrayList;

import entity.BankAccount;
import entity.User;


/**
 *
 * @author SE150699
 */
public interface IBankAccountRepo {
    public BankAccount findAccount(String id);
    public void addToList();
    public int createAcc(User newUser, BankAccount newBankaAccount);
    public boolean checkDuplicated(User checkedUser);
    public BankAccount loginAcc(String id,String password);
    public int withDrawMoney(BankAccount curAccount,double withDrawMoney);
    public int depositMoney(BankAccount curAccount ,double depositMoney);
    public int transferMoney(BankAccount curAccount,String desAccountID,double transferMoney);
    public int removeAcc(BankAccount curAccount);
    public int changePassword(BankAccount curAccount,String oldPassword, String newPassword, String confirm_new_password);
    public void printLogOfAccount(BankAccount curAccount);
}
