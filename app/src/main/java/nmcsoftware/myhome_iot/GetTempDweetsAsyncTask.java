/*
 * Created by nmc on 5/21/16.
 */

package nmcsoftware.myhome_iot;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonObject;


public class GetTempDweetsAsyncTask extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private Context context;
    private ProgressBar temp_pbar, humid_pbar;
    private TextView temp_TextView, humid_TextView;
    JsonObject json;
    Dweet dweet;
    String thingName;

    public GetTempDweetsAsyncTask(Context context, View temp_pbar, View temp_TextView, View humid_pbar, View humid_TextView, JsonObject json, String thingName, MainActivity activity) {

        this.context = context;
        this.activity = activity;
        this.temp_pbar = (ProgressBar) temp_pbar;
        this.humid_pbar = (ProgressBar) humid_pbar;
        this.temp_TextView = (TextView) temp_TextView;
        this.humid_TextView = (TextView) humid_TextView;
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
                        temp_pbar.setProgress(Integer.parseInt(json.get("Temp1").toString()));
                        temp_TextView.setText((json.get("Temp1").toString())+" F");

                       humid_pbar.setProgress(Integer.parseInt(json.get("Humid1").toString()));
                        humid_TextView.setText((json.get("Humid1").toString()));

                        THFragment f = new THFragment();
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
