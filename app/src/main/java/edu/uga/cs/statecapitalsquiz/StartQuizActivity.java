
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

/*
 * This is the StartQuizActivity which is specifically used t o call the StartQuizFragment. It
 * is called by the MainActivity and is used for all of the quiz functionality.
 */

public class StartQuizActivity extends AppCompatActivity {
    public static final String TAG = "StartQuizActivity";

    //private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    //private NavigationView navigationView;
    //private ActionBarDrawerToggle drawerToggle;

    /*
     * This is the onCreate method for this class. It stands out because it is the focus of the
     * slider mechanism.
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);



        // Assign the Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        ViewPager2 pager = findViewById( R.id.viewpager );
        capitalsPageAdapter avpAdapter = new
                capitalsPageAdapter(
                getSupportFragmentManager(), getLifecycle() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );



    }




    public void selectDrawerItem( MenuItem menuItem ) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();


        // Create a new fragment based on the used selection in the nav drawer
        int itemId = menuItem.getItemId();
        if (itemId == R.id.start_quiz) {
            Intent intent = new Intent(StartQuizActivity.this, StartQuizActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.past_quiz) {
            fragment = new PastQuizFragment();
            fragmentManager.beginTransaction().replace( R.id.fragmentContainerView, fragment).addToBackStack("start quiz fragment" ).commit();
        } else if (itemId == R.id.menu_close) {
           // finish();
            finishAffinity();
        } else {
            return;
        }


    }


   public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//             Handle the up button press
            onBackPressed(); // This will behave like the back button
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}



