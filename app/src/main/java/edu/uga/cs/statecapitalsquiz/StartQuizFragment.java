package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartQuizFragment extends Fragment {
    private static int fragmentCreationCount = 0;

    private TextView textview;
    private readcsvData readcsvData = null;

    private int position;

    public StartQuizFragment() {
        // Required empty public constructor
    }

    public static StartQuizFragment newInstance(int position) {
        StartQuizFragment fragment = new StartQuizFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            // Handle the 'position' value as needed

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCreationCount++;
        readcsvData = new readcsvData(getActivity());
        if (readcsvData != null)
            readcsvData.open();
        View view = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        textview = view.findViewById(R.id.textview1);


        List<readcsv> readcsvList = new readcsvDBReader().doInBackground();
        Collections.shuffle(readcsvList, new Random());
        readcsv item = readcsvList.get(0);
        String itemString = item.toString();
        String[] tokens = itemString.split("\\s+");
        String state = tokens[1];
        String capitalCity = tokens[2];
        String additionalCity1 = tokens[3];
        String additionalCity2 = tokens[4];


        Log.d("JUST TO CHECK SOON TO DELETE", "test: " + fragmentCreationCount);
        textview.setText(item.toString());
        return view;
    }


    public class readcsvDBReader extends AsyncTask<Void, List<readcsv>> {
        @Override
        protected List<readcsv> doInBackground(Void... params) {
            List<readcsv> readcsvList = readcsvData.retrieveAllreadcsvLeads();
            Log.d("TAG", "States Recieved: " + readcsvList.size());
            return readcsvList;
        }

        @Override
        protected void onPostExecute(List<readcsv> readcsv) {
            Log.d("Tag", "Job List size");
//            readcsvList.addAll(readcsvList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (readcsvData != null && !readcsvData.isDBOpen())
            readcsvData.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (readcsvData != null && readcsvData.isDBOpen()) {
            readcsvData.close();
        }
    }
}