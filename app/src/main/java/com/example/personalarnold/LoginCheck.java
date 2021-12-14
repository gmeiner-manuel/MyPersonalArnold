package com.example.personalarnold;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCheck {
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
        if(pw.matches(".*\\d.*")){
            return true;
        }
        else {
            System.out.println("Numeric is missing");
            return false;
        }
    }

    private boolean checkSymbol(String pw){
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
