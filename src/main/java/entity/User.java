package entity;

import java.io.Serializable;

public class User implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String account_id;
    String account_name;
    String encrypt_password;


    public User(String account_id) {
        this.account_id = account_id;
    }


    public User() {
    }


    public User(String account_id, String account_name, String encrypt_password) {
        super();
        this.account_id = account_id;
        this.account_name = account_name;
        this.encrypt_password = encrypt_password;
    }


    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getEncrypt_password() {
        return encrypt_password;
    }

    public void setEncrypt_password(String encrypt_password) {
        this.encrypt_password = encrypt_password;
    }

    @Override
    public String toString() {
        return account_id + " - " + account_name +  " - "  + encrypt_password;
    }
    
    @Override
    public boolean equals(Object obj){
        return this.account_id.equals(((User)obj).getAccount_id());
    }
}
