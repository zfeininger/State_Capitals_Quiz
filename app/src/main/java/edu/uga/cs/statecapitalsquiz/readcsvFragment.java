package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link readcsvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class readcsvFragment extends Fragment {

//    private readcsvData readcsvData = null;

    public readcsvFragment() {
        // Required empty public constructor
    }

    public static readcsvFragment newInstance() {
        readcsvFragment fragment = new readcsvFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_readcsv, container, false);

    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState);
//        readcsvData = new readcsvData( getActivity() );
    }
}