package nmcsoftware.myhome_iot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoorFragment extends Fragment {

    public interface OutwardComm {

        public void GetDoorValues( );

    }

    //  @Override
    public void onRefresh() {
        Toast.makeText(this.getContext(),"Refreshing Data",Toast.LENGTH_SHORT);
    }


    public void onValuesRefreshed() {
        // if (mSwipeRefreshLayout.isRefreshing()) {
        //    mSwipeRefreshLayout.setRefreshing(false);
        //  }
    }

    public DoorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_door, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity)getActivity()).RefreshDweetDoors();
    }
}
