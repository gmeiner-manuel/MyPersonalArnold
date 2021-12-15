package at.htl.personalarnold;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ser {
    /**
     *
     * @param user User object that gets created in the SignUp screen
     * @param context The Application.Context of the App
     *
     */
    public static void writeObject(User user, Context context){
        FileOutputStream fileOut;
        try
        {
            fileOut = context.openFileOutput("user.ser", Context.MODE_PRIVATE);
            System.out.println("test");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    /**
     *
     * @param context The Application.Context of the App
     * @return returns the read .ser File as an User object
     */
    public static User readObject(Context context){
        FileInputStream fileIn;
        User newUser = null;

        try
        {
            fileIn = context.openFileInput("user.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newUser = (User) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();

        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();

        }
        return newUser;
    }
}
