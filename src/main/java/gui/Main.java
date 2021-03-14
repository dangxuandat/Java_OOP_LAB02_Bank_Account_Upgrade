package gui;

import entity.BankAccount;
import service.BankFunctions;

public class Main {
    public static void main(String[] args) {
        BankFunctions function = new BankFunctions();
        Menu menu = new Menu();
        int choice = 0;
        BankAccount curAccount = null;
        boolean quit = false;
        int result = 0;
        menu.addMenu();
        menu.addMenuFunction();
        function.addToList();
        do {
            do {
                menu.printLoginScreen();
                System.out.println("3 - Quit");
                choice = menu.getUserChoice();
                switch (choice) {
                    case 1:
                        result = function.createAcc();
                        if(result == 1) System.out.println("Create successfully");
                        break;
                    case 2:
                        curAccount = function.login();
                        if(curAccount == null) System.out.println("Wrong id account or password!!!");
                        else quit = true;
                        break;
                    case 3:
                        quit = true;
                        break;
                }
            }while ( quit != true);

            while (curAccount != null){
                do {
                System.out.println("\n----- ACCOUNT INFO -----");
                System.out.println("ID ACCOUNT: " + curAccount.getId_account());
                System.out.println("Balance: " + curAccount.getBalance());
                System.out.println("-------------------------\n");
                menu.printFunctionScreen();
                choice = menu.getUserChoice();
                    switch (choice) {
                        case 1:
                            result = function.withDraw(curAccount);
                            if(result == -1) System.out.println("Your balance is not enough to withdraw!! Withdraw money including transaction fe");
                            if(result == 1 ) System.out.println("Withdraw successfully!!");
                            if(result == 0) System.out.println("Withdraw failed!!");
                        break;
                    
                        case 2:
                            result =  function.depositMoney(curAccount);
                            if(result == -1 ) System.out.println("Deposit process failed because deposit money is not greater than 1 after including transaction fees!!!");
                            if(result == 0) System.out.println("Deposit process is failed!!");
                            if(result == 1) System.out.println("Deposit successfully!!");
                            if(result == -3) System.out.println("Deposit Canceled!!");
                        break;
                        
                        case 3:
                            result = function.transferMoney(curAccount);
                            if(result == -1) System.out.println("Your balance is not enough to execute this transfer");
                            if(result == 1) System.out.println("Transfer successfully!!");
                            if(result == 0) System.out.println("Transfer failed!!!");
                            if(result == -3) System.out.println("Transfer canceled!!");
                            if(result == -2) System.out.println("ID Account doesn't exist!!!");
                            if(result == -4) System.out.println("Your balance must have at lease 1 usd!!!!!");
                        break;
                        case 4:
                            result = function.removeAcc(curAccount);
                            if(result == 1){
                                curAccount = null;
                                quit = false;
                                System.out.println("Remove account successfully!!");
                            }
                            if(result == -1) System.out.println("Remove account failed!!!");
                            if(result == -3){
                                System.out.println("Remove account canceled!!");
                            }
                            if(result == -4) System.out.println("Your balance must have at least 1 usd!!!");
                        break;
                            case 5:
                                result = function.changePassword(curAccount);
                                if(result == 1) System.out.println("Change password successfully!!");
                                if(result == -1) System.out.println("wrong old password");
                                if(result == -2) System.out.println("New password and confirm new password doesn't match");
                                break;
                            case 6:
                                function.printLogHistory(curAccount);
                                break;
                        case 7:
                            curAccount = null;
                            quit = false;
                            break;
                    }
                } while (choice >= 1 && choice < 4);
                
            }
        } while (quit != true);
    }
}