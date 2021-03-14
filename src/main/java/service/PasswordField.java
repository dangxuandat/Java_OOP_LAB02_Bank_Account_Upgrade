package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordField {
    public static String readPassword(String promt){
        EraserThread et = new EraserThread(promt);
        Thread mask = new Thread(et);
        mask.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = null;;
        try {
            password = in.readLine();
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        et.stopMasking();
        return password;
    }
}
