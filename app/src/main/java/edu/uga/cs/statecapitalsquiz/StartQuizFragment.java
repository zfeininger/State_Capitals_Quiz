package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private static List<readcsv> readcsvList;
    private String state;

    private String capitalCity;
    private String additionalCity1;
    private String additionalCity2;
    private RadioGroup radiogroup;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton thirdRadioButton;
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
        radiogroup = view.findViewById(R.id.radioGroup);
        firstRadioButton = view.findViewById(R.id.radioButton);
        secondRadioButton = view.findViewById(R.id.radioButton2);
        thirdRadioButton = view.findViewById(R.id.radioButton3);

        if (fragmentCreationCount == 1) {
            readcsvList = new readcsvDBReader().doInBackground();
            Collections.shuffle(readcsvList, new Random());
        }
        readcsv item = readcsvList.get(fragmentCreationCount);
        String itemString = item.toString();
        String[] tokens = itemString.split("\\s+");
        String previousState = tokens[0];
        state = tokens[1];
        capitalCity = tokens[2];
        additionalCity1 = tokens[3];
        additionalCity2 = tokens[4];
        Log.d("JUST TO CHECK SOON TO DELETE", state);
        if (state.equals("New") || state.equals("West") || state.equals("North") || state.equals("South") || state.equals("Rhode")) {
            state = tokens[1] + " " + tokens[2];
            capitalCity = tokens[3];
            additionalCity1 = tokens[4];
            additionalCity2 = tokens[5];
            if (state.equals("New Jersey") || state.equals("North Dakota") || state.equals("South Carolina")) {
                capitalCity = tokens[3];
                additionalCity1 = tokens[4];
                additionalCity2 = tokens[5] + " " + tokens[6];
            }
            if (state.equals("New Mexico")) {
                capitalCity = tokens[3] + " " + tokens[4];
                additionalCity1 = tokens[5];
                additionalCity2 = tokens[6] + " " + tokens[7];
            }
            if (state.equals("New York")) {
                capitalCity = tokens[3];
                additionalCity1 = tokens[4];
                additionalCity2 = tokens[5] + " " + tokens[6] + " " + tokens[7];
            }
            if (state.equals("South Dakota")) {
                capitalCity = tokens[3];
                additionalCity1 = tokens[4] + " " + tokens[5];
                additionalCity2 = tokens[6] + " " + tokens[7];
            }
        }
        if (state.equals("Arkansas") || state.equals("Iowa") || state.equals("Louisiana") || state.equals("Nevada")) {
            capitalCity = tokens[2] + " " + tokens[3];
            additionalCity1 = tokens[4] + " " + tokens[5];
            additionalCity2 = tokens[6];
        }
        if (state.equals("California")) {
            capitalCity = tokens[2];
            additionalCity1 = tokens[3] + " " + tokens[4];
            additionalCity2 = tokens[5] + " " + tokens[6];
        }
        if (state.equals("Colorado") || state.equals("Indiana")) {
            capitalCity = tokens[2];
            additionalCity1 = tokens[3] + " " + tokens[4];
            additionalCity2 = tokens[5];
        }
        if (state.equals("Connecticut") || state.equals("Idaho") || state.equals("Kansas") || state.equals("Michigan") || state.equals("Wisconsin")) {
            capitalCity = tokens[2];
            additionalCity1 = tokens[3];
            additionalCity2 = tokens[4] + " " + tokens[5];
        }
        if (state.equals("Missouri")) {
            capitalCity = tokens[2] + " " + tokens[3];
            additionalCity1 = tokens[4] + " " + tokens[5];
            additionalCity2 = tokens[6] + " " + tokens[7];
        }
        if (state.equals("Oklahoma")) {
            capitalCity = tokens[2] + " " + tokens[3];
            additionalCity1 = tokens[4];
            additionalCity2 = tokens[5];
        }
        if (state.equals("Utah")) {
            capitalCity = tokens[2] + " " + tokens[3] + " " + tokens[4];
            additionalCity1 = tokens[5];
            additionalCity2 = tokens[6];
        }
        if (state.equals("Minnesota")) {
            capitalCity = tokens[2] + tokens[3];
            additionalCity1 = tokens[4];
            additionalCity2 = tokens[5] + tokens[6];
        }



        Log.d("JUST TO CHECK SOON TO DELETE", "test: " + state);
        textview.setText(fragmentCreationCount + ". What is the capital of " + state + "?");
        firstRadioButton.setText(capitalCity);
        secondRadioButton.setText(additionalCity1);
        thirdRadioButton.setText(additionalCity2);
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
        int direction = radiogroup.getCheckedRadioButtonId();
        if (direction == 2131231228) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + capitalCity);
        } else if (direction == 2131231229) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + additionalCity1);
        } else if (direction == 2131231230) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + additionalCity2);
        } else {
            Log.d("just to see", "NO INPUT FOR THE RADIOBUTTONS");

        }
    }
}