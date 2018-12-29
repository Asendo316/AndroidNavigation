package app.android.cordis.com.refuel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import app.android.cordis.com.refuel.R;
import app.android.cordis.com.refuel.fragment.HomeFragment;
import app.android.cordis.com.refuel.fragment.MapsFragment;
import app.android.cordis.com.refuel.fragment.NotificationFragment;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private BottomNavigationViewEx bottomNavigationViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager(new HomeFragment());
        setupBottomNavigationView();
    }

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
        getMenuInflater().inflate(R.menu.home, menu);
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

        switch (id) {
            case R.id.nav_camera:
                fragmentManager(new HomeFragment());
                break;
            case R.id.nav_gallery:
                fragmentManager(new MapsFragment());
                break;
            case R.id.nav_slideshow:
                fragmentManager(new NotificationFragment());
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextSize(9);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                FrameLayout content = (FrameLayout) findViewById(R.id.content);
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.replace(R.id.content, new HomeFragment()).commit();

                        if (!getSupportActionBar().isShowing()) {
                            getSupportActionBar().show();
                        }
                        return true;
                    case R.id.navigation_maps:
                        transaction.replace(R.id.content, new MapsFragment()).commit();
                        return true;
                    case R.id.navigation_notif:
                        transaction.replace(R.id.content, new NotificationFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     *
     * @param fragment
     */
    private void fragmentManager(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FrameLayout content = (FrameLayout) findViewById(R.id.content);
        transaction.replace(R.id.content, fragment).commit();

    }
}
