package gui;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> functionMenu = new ArrayList<>();
    public Menu(){
        super();
    }
    public void addMenu(){
        this.add("Create new account");
        this.add("Login Function");
        this.add("Quit");
    }

    public void addMenuFunction(){
        functionMenu.add("Withdrawal Function");
        functionMenu.add("Deposit Function");
        functionMenu.add("Transfer Function");
        functionMenu.add("Remove account");
        functionMenu.add("Change password");
        functionMenu.add("Show log history");
        functionMenu.add("Log out");
    }

    public void printLoginScreen(){
        for (int i = 0; i < 2; i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
    }

    public void printFunctionScreen(){
        for(int i = 0; i < functionMenu.size(); i++){
            System.out.println((i + 1) + " - " + functionMenu.get(i));
        }
    }

    public int getUserChoice(){
        int result = 0;
        boolean flag = false;
        do {
            try {
                System.out.print("Choice: ");
                result = Integer.parseInt(sc.nextLine());
                flag = true;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Please enter number!!");
            }
        } while (flag == false);
        return result;
    }

}

