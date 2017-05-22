package nmcsoftware.myhome_iot;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;

/**
 * Created by nmc on 5/21/16.
 */
public class GetDweetAsyncTask1 extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private Context context;
    private int p_tank1, p_tank2, p_tank3, p_tank4;
    JsonObject json;
    Dweet dweet;
    String thingName;

    public GetDweetAsyncTask1(Context context, int p_tank1, int p_tank2, int p_tank3, int p_tank4, JsonObject json, String thingName, MainActivity activity){

        this.context = context;
        this.activity = activity;
        this.p_tank1 = p_tank1;
        this.p_tank2 = p_tank2;
        this.p_tank3 = p_tank3;
        this.p_tank4 = p_tank4;
        this.json = json;
        this.thingName = thingName;

    }


    @Override
    protected String doInBackground(Void... params) {
        try{

            dweet = DweetIO.getLatestDweet(thingName);

            json = dweet.getContent();

            // run on Ui Thread
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        p_tank1 = (Integer.parseInt(json.get("Tank1").toString()));
                        p_tank2 = (Integer.parseInt(json.get("Tank2").toString()));
                        p_tank3 = (Integer.parseInt(json.get("Tank3").toString()));
                        p_tank4 =(Integer.parseInt(json.get("Tank4").toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {

        }
        return null;
    }

}