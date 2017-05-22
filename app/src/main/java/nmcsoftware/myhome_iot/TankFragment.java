package nmcsoftware.myhome_iot;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TankFragment extends Fragment  {

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
        this.InterfaceImplementer = (OutwardComm) context;
    }


    public TankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_tank, container, false);

    }


    public void displayTankValues(int tank1, int tank2, int tank3, int tank4) {

        ProgressBar p_tank1 = (ProgressBar) getActivity().findViewById(R.id.p_tank1);
        ProgressBar p_tank2 = (ProgressBar) getActivity().findViewById(R.id.p_tank2);
        ProgressBar p_tank3 = (ProgressBar) getActivity().findViewById(R.id.p_tank3);
        ProgressBar p_tank4 = (ProgressBar) getActivity().findViewById(R.id.p_tank4);
        Switch sw_door1 = (Switch) getActivity().findViewById(R.id.sw_door1);

        p_tank1.setProgress(tank1);
        p_tank2.setProgress(tank2);
        p_tank3.setProgress(tank3);
        p_tank4.setProgress(tank4);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity)getActivity()).RefreshDweetTanks();
    }
}
