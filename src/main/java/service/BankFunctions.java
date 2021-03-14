package service;

import java.util.Scanner;

import entity.BankAccount;
import entity.User;
import repo.impl.ImplBankAccountRepo;
import repo.inter.IBankAccountRepo;

public class BankFunctions {
    AcceptInput tool = new AcceptInput();
    EncryptedPassword encrypt = new EncryptedPassword();
    IBankAccountRepo list = new ImplBankAccountRepo();
    Scanner sc = new Scanner(System.in);
    public BankFunctions() {
    }

    public void addToList(){
        list.addToList();
        
    }
    
    public int createAcc(){
        String account_id;
        String account_name;
        String password;
        String encrypt_password = null;
        String confirm_password;
        User checkDuplicated;
        boolean duplicated = false;
        do {
            account_id = tool.acceptID("Enter new account id: ");
            checkDuplicated = new User(account_id);
            duplicated = list.checkDuplicated(checkDuplicated);
            if( duplicated == true ) System.out.println("ID account is existed!!!");
        } while (duplicated == true);

        do {
            account_name = tool.acceptName("Enter your name: ");
            if(account_name == null) System.out.println("Name does not contain special characters or admin!!");
        } while (account_name == null);

        do {
            do {
                password = tool.acceptPassword("Enter password  at least 6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character:  ");
                if(password == null) System.out.println("Wrong format password!!!!");
            } while (password == null);
    
            do {
                confirm_password = tool.acceptPassword("Confirm Password: ");
                if(confirm_password == null) System.out.println("Wrong format password!!!!");
            } while ( confirm_password == null);
    
            if(password.equalsIgnoreCase(confirm_password)){
                encrypt_password = encrypt.toHexString(encrypt.getSHA(password));
            }
        } while (!password.equalsIgnoreCase(confirm_password));
        User user = new User(account_id, account_name, encrypt_password);
        BankAccount account = new BankAccount(account_id);
        return list.createAcc(user, account);
    }


    public BankAccount login(){
        String account_id;
        String password;
        account_id = tool.acceptID("Enter id account: ");
        do {
            password = tool.acceptPassword("Enter password: ");
            if(password == null) System.out.println("Wrong format password!!");
        } while (password == null);
        password = encrypt.toHexString(encrypt.getSHA(password));
        return list.loginAcc(account_id, password);
    }

    public int withDraw(BankAccount curAccount){
        double withDrawMoney = 0;
        try {
            withDrawMoney = tool.acceptMoney("Enter how much money you want to withdraw: ");
            return list.withDrawMoney(curAccount, withDrawMoney);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Please enter number!!!");
            return -1;
        }
    }

    public int depositMoney(BankAccount curAccount){
        double depositMoney = 0;
        int result = 0;
        do {
            try {
                depositMoney = tool.acceptMoney("Enter money you want to deposit: ");
                System.out.print("Do you want to deposit?(Yes/No): ");
                String confirm = sc.nextLine();
                if(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("Y")) return list.depositMoney(curAccount, depositMoney);
                else return result = -3;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter number please!!!");
                return result = -2;
            }
        } while (result == -2);
    }

    public int transferMoney(BankAccount curAccount){
        if(curAccount.getBalance() >= 1){
            String receiveAccountId;
        double transferMoney = 0;
        receiveAccountId = tool.acceptID("Enter id account that you want to transfer to: ");
        if(list.findAccount(receiveAccountId) == null) return -2;
        else{
            transferMoney = tool.acceptMoney("Enter money you want to transfer: ");
            if(transferMoney > curAccount.getBalance()) return -1;
            else{
                System.out.print("Do you want to transfer ?? (Yes/No): ");
                String confirm = sc.nextLine();
                if(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("Y")) return list.transferMoney(curAccount, receiveAccountId, transferMoney);
                else return -3;
            }
        }
        }
        else return -4;
    }

    public int removeAcc(BankAccount curAccount){
        if(curAccount.getBalance() >= 1){
            System.out.print("Do you want to remove this account?(Yes/No): ");
        String confirm = sc.nextLine();
        if(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("Y")) return list.removeAcc(curAccount);
        else return -3;
        }
        else return -4;
    }
    
    public int changePassword(BankAccount curAccount){
        String oldPassword;
        String newPassword;
        String confirm_new_password;
        do {
            oldPassword = tool.acceptPassword("Enter old password: ");
            if(oldPassword == null) System.out.println("Wrong format password!! ");
        } while (oldPassword == null);
        do {
            newPassword = tool.acceptPassword("Enter new password: ");
            if(newPassword == null) System.out.println("Wrong format password!! ");
        } while (newPassword == null);
        do {
            confirm_new_password = tool.acceptPassword("Confirm new password: ");
            if(confirm_new_password== null) System.out.println("Wrong format password!! ");
        } while (confirm_new_password == null);
        oldPassword = encrypt.toHexString(encrypt.getSHA(oldPassword));
        newPassword = encrypt.toHexString(encrypt.getSHA(newPassword));
        confirm_new_password = encrypt.toHexString(encrypt.getSHA(confirm_new_password));
        return list.changePassword(curAccount, oldPassword, newPassword, confirm_new_password);
    }
    public void printLogHistory(BankAccount curAccount){
        list.printLogOfAccount(curAccount);
    }
}
