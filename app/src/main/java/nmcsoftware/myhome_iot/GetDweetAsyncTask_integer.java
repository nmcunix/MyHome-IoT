/*
 * Created by nmc on 5/21/16.
 */

package nmcsoftware.myhome_iot;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;


public class GetDweetAsyncTask_integer extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private Context context;
    int tank1;
    int tank2;
    int tank3;
    int tank4;
    JsonObject json;
    Dweet dweet;
    String thingName;

    public GetDweetAsyncTask_integer(Context context, int tank1, int tank2, int tank3, int tank4, JsonObject json, String thingName, MainActivity activity) {

        this.context = context;
        this.activity = activity;

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
                        tank1 = Integer.parseInt(json.get("Tank1").toString());
                        tank2 = Integer.parseInt(json.get("Tank2").toString());
                        tank3 = Integer.parseInt(json.get("Tank3").toString());
                        tank4 = Integer.parseInt(json.get("Tank4").toString());

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
