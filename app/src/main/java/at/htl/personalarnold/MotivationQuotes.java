package at.htl.personalarnold;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MotivationQuotes {

    private Context mContext;

    /**
     * Constructor has a Context as the parameter which will help instance the Class
     * in the main Class (LoadingScreen)
     * @param context
     */
    public MotivationQuotes(Context context) {
        this.mContext = context;
    }

    /**
     * Method for reading a file line by line and saving the content in an ArrayList
     * The Method will return a random String Object from the List.
     * @return
     */
    public String getQuotes() {
        List<String> mLines = new ArrayList<>();
        try {
            InputStream is = mContext.getAssets().open("motivationQuotes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            //Read until the File has ended
            while ((line = reader.readLine()) != null)
                mLines.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mLines.get((int) (Math.random() * mLines.size() - 1));
    }
}
