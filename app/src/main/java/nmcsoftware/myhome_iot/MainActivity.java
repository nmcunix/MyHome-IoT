package nmcsoftware.myhome_iot;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;

import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity
        implements DoorFragment.OutwardComm, TankFragment.OutwardComm, NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    int tank1, tank2, tank3, tank4;
    int p1_tank1;
    int p1_tank2;
    int p1_tank3;
    int p1_tank4;

    Context AppCon;

    Handler handler = new Handler();

    @Override
    public void GetTankValues() {

        //int tank1,int tank2,int tank3,int tank4
        new GetDweetAsyncTask_integer(this.getApplicationContext(),
                p1_tank1,
                p1_tank2,
                p1_tank3,
                p1_tank4, new JsonObject(), "nmcunix-dev", this).execute();

        tank1 = p1_tank1;
        tank2 = p1_tank2;
        tank3 = p1_tank3;
        tank4 = p1_tank4;

        TankFragment tankfragment = new TankFragment();
        tankfragment.displayTankValues(tank1, tank2, tank3, tank4);

    }

    @Override
    public void GetDoorValues() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home Climate");

        THFragment fragment = new THFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        // getSupportFragmentManager().executePendingTransactions();

         /* TankFragment fragment = new TankFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions(); */

        //RefreshDweetTanks();
        //GetTankValues();

// wipe down event
      /* new GetTankDweetsAsyncTask(this.getApplicationContext(),
                findViewById(R.id.p_tank1),
                findViewById(R.id.p_tank2),
                findViewById(R.id.p_tank3),
                findViewById(R.id.p_tank4), new JsonObject(), "nmcunix-dev",  this).execute(); */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
                    RefreshDweetTH();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    RefreshDweetDoors();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    RefreshDweetTanks();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /* MainActivity activity = new MainActivity();

                new GetTankDweetsAsyncTask(AppCon,
                        findViewById(R.id.p_tank1),
                        findViewById(R.id.p_tank2),
                        findViewById(R.id.p_tank3),
                        findViewById(R.id.p_tank4), new JsonObject(), "nmcunix-dev",activity).execute(); */
                try {
                    Snackbar.make(view, "Refreshing Data...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    public void RefreshDweetTanks() {

        new GetTankDweetsAsyncTask(this.getApplicationContext(),
                findViewById(R.id.p_tank1),
                findViewById(R.id.p_tank2),
                findViewById(R.id.p_tank3),
                findViewById(R.id.p_tank4), new JsonObject(), "nmcunix-dev", this).execute();
    }

    public void RefreshDweetTH() {

        new GetTempDweetsAsyncTask(this.getApplicationContext(),
                findViewById(R.id.temp_pbar),
                findViewById(R.id.temp_textview),
                findViewById(R.id.humid_pbar),
                findViewById(R.id.humid_textview), new JsonObject(), "nmcunix-dev", this).execute();
    }

    public void RefreshDweetDoors() {

        new GetDoorDweetsAsyncTask(this.getApplicationContext(),
                findViewById(R.id.sw_door1),
                findViewById(R.id.sw_door2),
                findViewById(R.id.sw_door3),
                findViewById(R.id.sw_door4),
                findViewById(R.id.sw_door5), new JsonObject(), "nmcunix-dev", this).execute();
    }

    // new GetDweetAsyncTask1(this.getApplicationContext(),
    //       p1_tank1,
    //       p1_tank2,
    //       p1_tank3,
    //       p1_tank4, new JsonObject(), "nmcunix-dev",  this).execute(); */


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_watertanks) {

            setTitle("Home Water Supply");
            TankFragment fragment = new TankFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            getSupportFragmentManager().executePendingTransactions();

            //RefreshDweetTanks();


        } else if (id == R.id.nav_homedoors) {

            setTitle("Home Doors");
            DoorFragment fragment = new DoorFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            getSupportFragmentManager().executePendingTransactions();
            //RefreshDweetTanks();

        } else if (id == R.id.nav_home_comfort) {

            setTitle("Home Climate");
            THFragment fragment = new THFragment();
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            getSupportFragmentManager().executePendingTransactions();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
