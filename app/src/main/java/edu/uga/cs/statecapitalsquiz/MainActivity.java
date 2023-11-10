package edu.uga.cs.statecapitalsquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

/**
 * The main activity class.  It just sets listeners for the two buttons.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    /*
     * The onCreate method for this activity which builds the view (dependent on fragments) and
     * works on navigation.
     * @params savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = new readcsvFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack("main screen").commit();
        } else {
            String fragmentTag = savedInstanceState.getString("currentFragmentTag");
            if (fragmentTag != null) {
                Fragment fragment;
                if (fragmentTag.equals("PastQuizFragment")) {
                    fragment = new PastQuizFragment();
                } else {
                    fragment = new readcsvFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).addToBackStack("main screen").commit();
            }
        }

        // assigning ID of the toolbar to a variable
        toolbar = findViewById(R.id.toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        // Find our drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Connect DrawerLayout events to the ActionBarToggle
        drawerLayout.addDrawerListener(drawerToggle);

        // Find the drawer view
        navigationView = findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });



    }




    /*
     * SelectDrawerItem method that is used to determine which navigational path has been
     * chosen.
     * @param menuItem
     * @return void
     */
    public void selectDrawerItem( MenuItem menuItem ) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();


        // Create a new fragment based on the used selection in the nav drawer
        int itemId = menuItem.getItemId();
        if (itemId == R.id.start_quiz) {
            Intent intent = new Intent(MainActivity.this, StartQuizActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.past_quiz) {
            fragment = new PastQuizFragment();
            fragmentManager.beginTransaction().replace( R.id.fragmentContainerView, fragment).addToBackStack("main screen" ).commit();
        } else if (itemId == R.id.menu_close) {
            finish();
        } else {
            return;
        }


        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    /*
     * This is the setupDrawerToggle method that is used to return the actionBarDrawerToggle along
     * with whether it is opened or closed.
     * @return ActionBarDrawerToggle
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open,
                R.string.drawer_close );
    }

    /*
     * onPostCreate is called when activity start-up is complete after onStart()
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onPostCreate( Bundle savedInstanceState ) {
        super.onPostCreate( savedInstanceState );
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /*
     * This method is used to handle the changing of configuration.
     * @params newConfig
     * @return void
     */
    @Override
    public void onConfigurationChanged( @NonNull Configuration newConfig ) {
        super.onConfigurationChanged( newConfig );
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged( newConfig );
    }

    /*
     * This method is designed to return where an option is selected or not.
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if( drawerToggle.onOptionsItemSelected( item ) ) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    /*
     * This method is used to save the Instance state.
     * @param outState
     * @return void
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (currentFragment != null) {
            outState.putString("currentFragmentTag", currentFragment.getTag());
        }
    }



}




