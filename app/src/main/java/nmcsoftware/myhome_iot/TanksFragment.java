package nmcsoftware.myhome_iot;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.RecoverySystem;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.os.Handler;
import android.os.AsyncTask;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.RunnableFuture;
import java.util.logging.LogRecord;

import com.google.gson.JsonObject;

/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TanksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TanksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TanksFragment extends Fragment {

  ProgressBar p_tank1;
  ProgressBar p_tank2;
  ProgressBar p_tank3;
  ProgressBar p_tank4;
 //   Context AppCon;

    //Context AppCon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private int mParam3;
    private int mParam4;

    //private OnFragmentInteractionListener mListener;

    public TanksFragment() {



       /* new GetDweetAsyncTask(AppCon,
                getView().findViewById(R.id.p_tank1),
                getView().findViewById(R.id.p_tank2),
                getView().findViewById(R.id.p_tank3),
                getView().findViewById(R.id.p_tank4), "nmcunix-dev", activity).execute(); */
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * // * @param param1 Parameter 1.
     * // * @param param2 Parameter 2.
     *
     * @return A new instance of fragment TanksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TanksFragment newInstance(String param1, String param2, String param3, String param4) {


            TanksFragment fragment = new TanksFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1,"param1");
            args.putString(ARG_PARAM2,"param2");
            args.putString(ARG_PARAM3,"param3");
            args.putString(ARG_PARAM4,"param4");
            fragment.setArguments(args);

            return fragment;


    }

        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            if (getArguments() != null) {

                mParam1 = Integer.parseInt(getArguments().getString(ARG_PARAM1));
                mParam2 = Integer.parseInt(getArguments().getString(ARG_PARAM2));
                mParam3 = Integer.parseInt(getArguments().getString(ARG_PARAM3));
                mParam4 = Integer.parseInt(getArguments().getString(ARG_PARAM4));

            }
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View fragView = inflater.inflate(R.layout.fragment_tanks, container, false);


            return (fragView);

        }


 /*   // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    } */

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            ((MainActivity)getActivity()).RefreshDeet();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    } */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }

/*

    public static class GetDweetAsyncTask extends AsyncTask<Void, Void, String> {

        private MainActivity activity;
        private Context context;
        private ProgressBar p_tank1, p_tank2, p_tank3, p_tank4;
        JsonObject json;
        Dweet dweet;
        String thingName;

        public GetDweetAsyncTask(Context context,  View p_tank1, View p_tank2, View p_tank3, View p_tank4, String thingName, MainActivity activity){

            this.context = context;
            this.activity = activity;
            this.p_tank1 = (ProgressBar) p_tank1;
            this.p_tank2 = (ProgressBar) p_tank2;
            this.p_tank3 = (ProgressBar) p_tank3;
            this.p_tank4 = (ProgressBar) p_tank4;
            this.json = new JsonObject();
            this.thingName = thingName;

        }


        @Override
        protected String doInBackground(Void... params) {
            try{

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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (Exception e) {

            }
            return null;
        }

    } */
}

