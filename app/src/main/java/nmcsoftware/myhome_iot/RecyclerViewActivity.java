package nmcsoftware.myhome_iot;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import com.google.gson.JsonObject;
        import java.util.ArrayList;
        import java.util.List;

public class RecyclerViewActivity extends Activity {

    int tank1;
    int tank2;
    int tank3;
    int tank4;

    Context AppCon;

    MainActivity activity = new MainActivity();

    private List<TankCard> TankCards;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }


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

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(TankCards);
        rv.setAdapter(adapter);
    }
}