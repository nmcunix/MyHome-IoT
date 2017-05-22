package nmcsoftware.myhome_iot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TankCardsFragment extends Fragment {

    TextView TankName;
    TextView TankPercent;
    ProgressBar TankBar;
    int tank1;
    int tank2;
    int tank3;
    int tank4;

    Context AppCon;

    MainActivity activity = new MainActivity();

    private List<TankCard> TankCards;
    private RecyclerView rv;

    // SwipeRefreshLayout.OnRefreshListener
    // private SwipeRefreshLayout mSwipeRefreshLayout;

    private OutwardComm InterfaceImplementer;

    //  @Override
    public void onRefresh() {
        Toast.makeText(this.getContext(),"Refreshing Data",Toast.LENGTH_SHORT);
    }


    public void onValuesRefreshed() {
        // if (mSwipeRefreshLayout.isRefreshing()) {
        //    mSwipeRefreshLayout.setRefreshing(false);
        //  }
    }

    public interface OutwardComm {

        public void GetTankValues();

    }

    @Override
   public void onAttach(Activity context) {
       super.onAttach(context);

      // this.InterfaceImplementer = (OutwardComm) context;
   }


    public TankCardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_recyclerview, container, false);

    }


    public void displayTankValues(int tank1, int tank2, int tank3, int tank4) {

        ProgressBar p_tank1 = (ProgressBar) getActivity().findViewById(R.id.p_tank1);
        ProgressBar p_tank2 = (ProgressBar) getActivity().findViewById(R.id.p_tank2);
        ProgressBar p_tank3 = (ProgressBar) getActivity().findViewById(R.id.p_tank3);
        ProgressBar p_tank4 = (ProgressBar) getActivity().findViewById(R.id.p_tank4);

        p_tank1.setProgress(tank1);
        p_tank2.setProgress(tank2);
        p_tank3.setProgress(tank3);
        p_tank4.setProgress(tank4);

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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setContentView(R.layout.fragment_tank_cards);

       // ((MainActivity)getActivity()).RefreshDweetTanks();
       /* TankName = (TextView)getView().findViewById(R.id.tankname);
        TankPercent = (TextView)getView().findViewById(R.id.tankpercent);
        TankBar = (ProgressBar)getView().findViewById(R.id.p_tank100);

        TankName.setText("Tank 1");
        TankPercent.setText("90");
        TankBar.setProgress(90); */

       getActivity().setContentView(R.layout.activity_recyclerview);
        rv=(RecyclerView)getView().findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(AppCon);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();


    }
}

