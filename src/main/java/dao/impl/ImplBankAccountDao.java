/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import dao.inter.IBankAccountDao;
import entity.Activities;
import entity.BankAccount;
import entity.User;

/**
 *
 * @author SE150699
 */
public class ImplBankAccountDao implements IBankAccountDao {
    String fileUser = "user.dat";
    String fileAccount = "bank.dat";
    String fileLog = "log.dat";
    public ImplBankAccountDao(){

    }
    @Override
    public ArrayList<User> readFromFileUser() {
        // TODO Auto-generated method stub
        ArrayList<User> list = null;
        try {
            FileInputStream fis = new FileInputStream("user.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = new ArrayList<>();
            User user;
            while (true) {
                user = (User)ois.readObject();
                list.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            if(e instanceof EOFException){
                System.out.println(e);
            }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public ArrayList<BankAccount> readFromFileAccount() {
        ArrayList<BankAccount> list = null;
        try {
            FileInputStream fis = new FileInputStream("bank.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = new ArrayList<>();
            BankAccount account;
            while (true) {
                account = (BankAccount) ois.readObject();
                list.add(account);
            }
        } catch (FileNotFoundException e) {
            //TODO: handle exception
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int writeListAccountToFileAccount(Collection listAccount) {
        // TODO Auto-generated method stub\
        emptyFileAccount();
        try {
            FileOutputStream fos = new FileOutputStream(fileAccount);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Object account : listAccount) {
                oos.writeObject(account);
            }
            fos.close();
            oos.close();
            return 1;
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
    }

    @Override
    public int writeListUserToFileUser(Set listUser) {
        // TODO Auto-generated method stub
        emptyFileUser();
        try {
            FileOutputStream fos = new FileOutputStream(fileUser);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Object user : listUser) {
                oos.writeObject(user);
            }
            fos.close();
            oos.close();
            return 1;
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
    }

    @Override
    public void emptyFileUser() {
        // TODO Auto-generated method stub
        try {
            File file = new File(fileUser);
            if(file.exists()){
            file.delete();
        }
            file.createNewFile();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
    }

    @Override
    public void emptyFileAccount() {
        // TODO Auto-generated method stub
        try {
            File file = new File(fileAccount);
            if(file.exists()){
            file.delete();
        }
            file.createNewFile();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
    }
    @Override
    public int writeListLogToFileLog(ArrayList<Activities> LogList) {
        // TODO Auto-generated method stub
        emptyFileLog();
        try {
            FileOutputStream fos = new FileOutputStream(fileLog);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Activities activities : LogList) {
                oos.writeObject(activities);
            }
            fos.close();
            oos.close();
            return 1;
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
    }
    @Override
    public ArrayList<Activities> readFromFileLog() {
        // TODO Auto-generated method stub
        ArrayList<Activities> LogList = null;
        try {
            LogList = new ArrayList<>();
            FileInputStream fis = new FileInputStream(fileLog);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Activities log;
            while (true) {
                log = (Activities)ois.readObject();
                LogList.add(log);
            }
        } catch (FileNotFoundException e) {
            //TODO: handle exception
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return LogList;
    }
    @Override
    public void emptyFileLog() {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        try {
            File file = new File(fileLog);
            if(file.exists()){
            file.delete();
        }
            file.createNewFile();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
        
    }

}
 