package entity;

import java.io.Serializable;

/*
LocalDateTime myDateobj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateobj.format(myFormatObj);
*/
public class Activities implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static String LOGIN = "Login function";
    public static String WITHDRAW_FUNCTION = "Withdraw function";
    public static String DEPOSIT_FUNCTION = "Deposit function";
    public static String TRANSFER_FUNCTION = "Transfer function";
    public static String CHANGE_PASSWORD = "Change password";

    String activity;
    String id;
    String date;

    public Activities(String id,String formattedDate, String activity){ 
        this.id = id;
        this.date = formattedDate;
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

     
    @Override
    public String toString(){
        return "ID: " + id + ", Date/Time: " + date + ", Function: " + activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
