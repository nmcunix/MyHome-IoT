package nmcsoftware.myhome_iot;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TankCardViewActivity extends Activity {

    TextView TankName;
    TextView TankPercent;
    ProgressBar TankBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tank_card_view);
        TankName = (TextView)findViewById(R.id.tankname);
        TankPercent = (TextView)findViewById(R.id.tankpercent);
        TankBar = (ProgressBar) findViewById(R.id.p_tank100);

        TankName.setText("Tank 1");
        TankPercent.setText("50");
        TankBar.setProgress(50);
    }
}