/*
 * Created by nmc on 5/21/16.
 */

package nmcsoftware.myhome_iot;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;


public class GetTankDweetsAsyncTask extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private Context context;
    private ProgressBar p_tank1, p_tank2, p_tank3, p_tank4;
    JsonObject json;
    Dweet dweet;
    String thingName;

    public GetTankDweetsAsyncTask(Context context, View p_tank1, View p_tank2, View p_tank3, View p_tank4, JsonObject json, String thingName, MainActivity activity) {

        this.context = context;
        this.activity = activity;
        this.p_tank1 = (ProgressBar) p_tank1;
        this.p_tank2 = (ProgressBar) p_tank2;
        this.p_tank3 = (ProgressBar) p_tank3;
        this.p_tank4 = (ProgressBar) p_tank4;
        this.json = json;
        this.thingName = thingName;

    }


    @Override
    protected String doInBackground(Void... params) {
        try {

            dweet = DweetIO.getLatestDweet(thingName);

            json = dweet.getContent();

            // run on Ui Thread
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        p_tank1.setProgress(Integer.parseInt(json.get("Tank1").toString()));
                        p_tank2.setProgress(Integer.parseInt(json.get("Tank2").toString()));
                        p_tank3.setProgress(Integer.parseInt(json.get("Tank3").toString()));
                        p_tank4.setProgress(Integer.parseInt(json.get("Tank4").toString()));

                        TankFragment f = new TankFragment();
                        f.onValuesRefreshed();

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
