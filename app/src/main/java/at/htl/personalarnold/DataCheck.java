package at.htl.personalarnold;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCheck {
    /**
     * Checks if the E-Mail and Password input matches the saved User
     * @param txt_emailAddress E-Mail adress input
     * @param txt_password Password input
     * @param applicationContext Context of the App
     * @return Retruns true if the textfield input E-Mail and Password equals the saved Users E-Mail and Password
     */
    public boolean checkLoginData(String txt_emailAddress, String txt_password, Context applicationContext){
        User user = Ser.readObject(applicationContext);
        System.out.println(user.toString());

        if(txt_emailAddress.equals(user.getEmail()) && txt_password.equals(user.getPassword())){
            System.out.println("success login");
            return true;
        }
        else{
            System.out.println("karbonat erol");
            return false;
        }
    }

    /**
     * Checks if the password (that the user wants to sign up with) fulfils the conditions
     * @param pw Password as String
     * @return Returns true if the password fulfils every condition
     */
    public boolean checkSignUpPW(String pw){
        return checkLength(pw) && checkNumeric(pw) && checkSymbol(pw);
    }

    private boolean checkLength(String pw){
        if(pw.length() >= 8){
            return true;
        }
        else {
            System.out.println("Password lenght under 8: " + pw.length());
            return false;
        }
    }

    private boolean checkNumeric(String pw){
        //regex to check if string contains any numeric
        if(pw.matches(".*\\d.*")){
            return true;
        }
        else {
            System.out.println("Numeric is missing");
            return false;
        }
    }

    private boolean checkSymbol(String pw){
        //pattern to determate if strings contains any Symbol (!, ?, $,....)
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        Matcher m = p.matcher(pw);
        if(m.find()){
            return true;
        }
        else{
            System.out.println("Symbol is missing");
            return false;
        }
    }
}
