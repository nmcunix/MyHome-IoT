/*
 * Created by nmc on 5/21/16.
 */

package nmcsoftware.myhome_iot;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.google.gson.JsonObject;


public class GetDoorDweetsAsyncTask extends AsyncTask<Void, Void, String> {

    private MainActivity activity;
    private Context context;
    private Switch sw_door1, sw_door2, sw_door3, sw_door4, sw_door5;
    JsonObject json;
    Dweet dweet;
    String thingName;

    public GetDoorDweetsAsyncTask(Context context, View sw_door1, View sw_door2, View sw_door3, View sw_door4, View sw_door5, JsonObject json, String thingName, MainActivity activity) {

        this.context = context;
        this.activity = activity;
        this.sw_door1 = (Switch) sw_door1;
        this.sw_door2 = (Switch) sw_door2;
        this.sw_door3 = (Switch) sw_door3;
        this.sw_door4 = (Switch) sw_door4;
        this.sw_door5 = (Switch) sw_door5;
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
                        sw_door1.setChecked((json.get("Door1").getAsBoolean()));
                            if (sw_door1.isChecked()) { sw_door1.setText("CLOSED");} else { sw_door1.setText("OPEN"); }

                         sw_door2.setChecked((json.get("Door2").getAsBoolean()));
                                if (sw_door2.isChecked()) { sw_door2.setText("CLOSED");} else { sw_door2.setText("OPEN"); }

                             sw_door3.setChecked((json.get("Door3").getAsBoolean()));
                                    if (sw_door3.isChecked()) { sw_door3.setText("CLOSED");} else { sw_door3.setText("OPEN"); }

                                 sw_door4.setChecked((json.get("Door4").getAsBoolean()));
                                        if (sw_door4.isChecked()) { sw_door4.setText("CLOSED");} else { sw_door4.setText("OPEN"); }

                                      sw_door5.setChecked((json.get("Door5").getAsBoolean()));
                                          if (sw_door5.isChecked()) { sw_door5.setText("CLOSED");} else { sw_door5.setText("OPEN"); }

                        DoorFragment f = new DoorFragment();
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
