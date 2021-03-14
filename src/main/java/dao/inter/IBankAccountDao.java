/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import entity.Activities;
import entity.BankAccount;
import entity.User;

/**
 *
 * @author SE150699
 */
public interface IBankAccountDao {
    public int writeListAccountToFileAccount(Collection listAccount);
    public int writeListUserToFileUser(Set listUser);
    public void emptyFileUser();
    public void emptyFileAccount();
    public ArrayList<User> readFromFileUser();
    public ArrayList<BankAccount> readFromFileAccount();
    public int writeListLogToFileLog(ArrayList<Activities> LogList);
    public ArrayList<Activities> readFromFileLog();
    public void emptyFileLog();
}
