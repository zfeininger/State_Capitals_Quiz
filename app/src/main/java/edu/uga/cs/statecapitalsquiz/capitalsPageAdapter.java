package edu.uga.cs.statecapitalsquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * The `capitalsPageAdapter` class is a custom FragmentStateAdapter designed for managing
 * fragments within a ViewPager2. It is specifically tailored for handling state quizzes
 * related to U.S. state capitals.
 */
public class capitalsPageAdapter extends FragmentStateAdapter {


    /*
     * Constructor for capitalsPageAdapter
     * @param fragmentManager
     * @param lifecycle
     */
    public capitalsPageAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }



    /*
     * Creates a new fragment given a swipe.
     * @param position
     * @return Fragment
     */
    @Override
    public Fragment createFragment(int position){
        return StartQuizFragment
                .newInstance( position );
    }



    /*
     * Returns the total number of fragments managed by this swiper.
     * @return int
     */
    @Override
    public int getItemCount() {
        return  8;
    }


}
