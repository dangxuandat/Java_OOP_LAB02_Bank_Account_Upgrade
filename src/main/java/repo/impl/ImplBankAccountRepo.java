/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repo.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import dao.impl.ImplBankAccountDao;
import dao.inter.IBankAccountDao;
import entity.Activities;
import entity.BankAccount;
import entity.User;
import repo.inter.IBankAccountRepo;

/**
 *
 * @author SE150699
 */
public class ImplBankAccountRepo implements IBankAccountRepo {
    IBankAccountDao dao = new ImplBankAccountDao();;
    HashMap<User,BankAccount> list = new HashMap<>();
    ArrayList<User> userList = null;
    ArrayList<BankAccount> accountList =  null;
    ArrayList<Activities> log_history = null;
    LocalDateTime dateTime;
    DateTimeFormatter myFormatObj;
    String formattedDate;
    int transactionFees = 1;
    public ImplBankAccountRepo(){
    }
    @Override
    public BankAccount findAccount(String id){
        BankAccount Account = new BankAccount(id);
        for (BankAccount   account  : list.values()) {
            if(account.getId_account().equals(Account.getId_account())) return account;
        }
        return null;
    }

    @Override
    public void addToList(){
            log_history = dao.readFromFileLog();
            userList = dao.readFromFileUser();
            accountList = dao.readFromFileAccount();
            for(int i = 0; i < userList.size(); i++){
                User user = userList.get(i);
                BankAccount bankAccount = accountList.get(i);
                if(user.getAccount_id().equalsIgnoreCase(bankAccount.getId_account())) list.put(user, bankAccount);
            }

    }
    @Override
    public boolean checkDuplicated(User checkedUser){
            for (User user : list.keySet()) {
                if(user.getAccount_id().equals(checkedUser.getAccount_id())) return true;
            }
            return false;
    }

    @Override
    public int createAcc(User newUser,BankAccount newBankAccount) {
        // TODO Auto-generated method stub
        try {
            if(checkDuplicated(newUser) == false){
                list.put(newUser, newBankAccount);
                dao.writeListUserToFileUser(list.keySet());
                dao.writeListAccountToFileAccount(list.values());
                return 1;
            }
            else return -1;
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
    }

    @Override
    public BankAccount loginAcc(String id, String password) {
        // TODO Auto-generated method stub
        
        BankAccount curAccount = null;
        try {
            for (User user : list.keySet()){
                if(user.getAccount_id().equalsIgnoreCase(id) && user.getEncrypt_password().equalsIgnoreCase(password)){
                    curAccount = list.get(user);
                }
            }
            dateTime = LocalDateTime.now();
            myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            formattedDate = dateTime.format(myFormatObj);
            log_history.add(new Activities(id, formattedDate, Activities.LOGIN));
            dao.writeListLogToFileLog(log_history);
            return curAccount;
        } catch (Exception e) {
            //TODO: handle exception
            return null;
        }
    }

    @Override
    public int withDrawMoney(BankAccount curAccount,double withDrawMoney) {
        // TODO Auto-generated method stub
        double oldBalance = curAccount.getBalance();
        String id = curAccount.getId_account();
        try {
                curAccount.setBalance(curAccount.getBalance() - withDrawMoney - transactionFees);
                if(curAccount.getBalance() < 0){
                    curAccount.setBalance(oldBalance);
                    return -1;
                }
                else{
                    dao.writeListAccountToFileAccount(list.values());
                    dateTime = LocalDateTime.now();
                    myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    formattedDate = dateTime.format(myFormatObj);
                    log_history.add(new Activities(id, formattedDate, Activities.WITHDRAW_FUNCTION));
                    dao.writeListLogToFileLog(log_history);
                    return 1;
                }
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }

    @Override
    public int depositMoney(BankAccount curAccount,double depositMoney) {
        // TODO Auto-generated method stub
        double oldBalance = curAccount.getBalance();
        String id = curAccount.getId_account();
        try {
            if(depositMoney <= 0) return -1;
            else{
                curAccount.setBalance(curAccount.getBalance() + depositMoney - transactionFees);
                if(curAccount.getBalance() < 0){
                    curAccount.setBalance(oldBalance);
                    return -1;
                }
                else{
                    dao.writeListAccountToFileAccount(list.values());
                    dateTime = LocalDateTime.now();
                    myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    formattedDate = dateTime.format(myFormatObj);
                    log_history.add(new Activities(id, formattedDate, Activities.DEPOSIT_FUNCTION));
                    dao.writeListLogToFileLog(log_history);
                    return 1;
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }

    @Override
    public int transferMoney(BankAccount curAccount,String desAccountID,double transferMoney) {
        // TODO Auto-generated method stub
        String id = curAccount.getId_account();
        if(curAccount.getBalance() >= 1){
            BankAccount desAccount = this.findAccount(desAccountID);
        try {
            if(curAccount.getBalance() < transferMoney) return -1;
            else{
                curAccount.setBalance(curAccount.getBalance() - transferMoney);
                desAccount.setBalance(desAccount.getBalance() + transferMoney);
                dao.writeListAccountToFileAccount(list.values());
                dao.writeListAccountToFileAccount(list.values());
                dateTime = LocalDateTime.now();
                myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                formattedDate = dateTime.format(myFormatObj);
                log_history.add(new Activities(id, formattedDate, Activities.TRANSFER_FUNCTION));
                dao.writeListLogToFileLog(log_history);
                return 1;
            }
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }
        else return -4;
        }

    @Override
    public int removeAcc(BankAccount curAccount) {
        // TODO Auto-generated method stub
        if(curAccount.getBalance() >= 1){
            int result = -1;
            try {
            User user = new User(curAccount.getId_account());
            for (User delUser : list.keySet()) {
                if(delUser.getAccount_id().equals(user.getAccount_id())){
                    list.remove(delUser, curAccount);
                    dao.emptyFileUser();
                    dao.emptyFileAccount();
                    dao.writeListUserToFileUser(list.keySet());
                    dao.writeListAccountToFileAccount(list.values());
                    return result = 1;
                    }
                }
            return result;
            } catch (Exception e) {
            //TODO: handle exception
            return result;
            }
        }
        else return -4;
        }
    @Override
    public int changePassword(BankAccount curAccount,String oldPassword, String newPassword, String confirm_new_password) {
        User find_user = null; // account that chang password
        String id = curAccount.getId_account();
        for (User user : list.keySet()) {
            if(user.getAccount_id().equalsIgnoreCase(curAccount.getId_account())) find_user = user;
        }
        try {
            if(find_user.getEncrypt_password().equalsIgnoreCase(oldPassword)){
                if(newPassword.equalsIgnoreCase(confirm_new_password)){
                    find_user.setEncrypt_password(newPassword);
                    dao.emptyFileUser();
                    dao.writeListUserToFileUser(list.keySet());
                    dateTime = LocalDateTime.now();
                    myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    formattedDate = dateTime.format(myFormatObj);
                    log_history.add(new Activities(id, formattedDate, Activities.CHANGE_PASSWORD));
                    dao.writeListLogToFileLog(log_history);
                    return 1;
                }
                else return -2;
            }
            else return -1;
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }
    @Override
    public void printLogOfAccount(BankAccount curAccount) {
        // TODO Auto-generated method stub
        String id = curAccount.getId_account();
        try {
            for (Activities history  : log_history) {
                if(history.getId().equalsIgnoreCase(id)) System.out.println(history);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
}
