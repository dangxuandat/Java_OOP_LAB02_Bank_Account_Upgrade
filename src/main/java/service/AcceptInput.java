package service;

import java.util.Scanner;

public class AcceptInput {
    Scanner sc = new Scanner(System.in);

    public AcceptInput() {

    }

    public String acceptID(String msg){
        String id;
        System.out.print(msg);
        id = sc.nextLine();
        return id;
    }

    public String acceptPassword(String msg){
        String password;
        password = PasswordField.readPassword(msg);
        if(password.matches("^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)" + "(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?]).{6,}$")) return password;
        else return null;
    }

    public String acceptName(String msg){
        String name;
        System.out.print(msg);
        name = sc.nextLine().toUpperCase();
        String admin="admin";
        if(name.matches("^[a-zA-Z0-9 ]+$")){
            if(name.contains(admin.toUpperCase())) return null;
            else return name;
        }
        else return null;
    }

    public double acceptMoney(String msg) throws NumberFormatException {
        double money = 0;
        System.out.print(msg);
        money = Double.parseDouble(sc.nextLine());
        return money;
    }

    
}
