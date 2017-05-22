package nmcsoftware.myhome_iot;

import android.content.Context;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmc on 5/31/16.
 */

public class TankCard {

    MainActivity activity = new MainActivity();

    int tank1;
    int tank2;
    int tank3;
    int tank4;

    Context AppCon;

    String tankname;
    String tankpercent;
    int tank_value;

    TankCard(int tank_value, String tankname, String tankpercent) {
        this.tankname = tankname;
        this.tank_value = tank_value;
        this.tankpercent = tankpercent;
    }


    private List<TankCard> TankCards;


    private void initializeData() {

        new GetDweetAsyncTask_integer(AppCon,
                tank1,
                tank2,
                tank3,
                tank4, new JsonObject(), "nmcunix-dev", activity).execute();

        TankCards = new ArrayList<>();
        TankCards.add(new TankCard(tank1, "Tank 1", Integer.toString(tank1)));
        TankCards.add(new TankCard(tank2, "Tank 2", Integer.toString(tank2)));
        TankCards.add(new TankCard(tank3, "Tank 3", Integer.toString(tank3)));
        TankCards.add(new TankCard(tank4, "Tank 4", Integer.toString(tank4)));

    }
}