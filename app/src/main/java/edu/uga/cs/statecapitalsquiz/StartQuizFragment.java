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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartQuizFragment extends Fragment {
    private static String question1 = null;
    private static String question2 = null;
    private static String question3 = null;
    private static String question4 = null;
    private static String question5 = null;
    private static String question6 = null;
    private static int number_correct_answers = 0;
    private static int number_completed_answers = 1;







    private String previousState;
    private readQuizzesData readQuizzesData = null;
    private static List<readcsv> readcsvList;
    private static List<readQuizzes> readQuizzesList;
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
        readQuizzesData = new readQuizzesData(getActivity());
        if (readcsvData != null) {
            readcsvData.open();
        }
        if (readQuizzesData != null) {
            readQuizzesData.open();
        }
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
//        previousState = tokens[0];
//        String[] parts = previousState.split(":");
//        previousState = parts[0];
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
        previousState = state;



        Log.d("JUST TO CHECK SOON TO DELETE", "FragmentCreationCount: " + fragmentCreationCount);
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
        if (readQuizzesData != null && !readQuizzesData.isDB2Open())
            readQuizzesData.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean correct = false;
        int direction = radiogroup.getCheckedRadioButtonId();
        if (direction == 2131231228) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + capitalCity);
            correct = true;
        } else if (direction == 2131231229) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + additionalCity1);
            correct = false;
        } else if (direction == 2131231230) {
            Log.d("just to see", "INPUT FOR RADIOBUTTON: " + additionalCity2);
            correct = false;
        } else {
            Log.d("just to see", "NO INPUT FOR THE RADIOBUTTONS");
            correct = false;
        }
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String quizDate = dateFormatter.format(currentDate);

        if (fragmentCreationCount == 2) {
            question1 = previousState;
            if (correct) {
                number_correct_answers++;
            }
            readQuizzes readQuizzesTokenExecute = new readQuizzes(quizDate, question1, question2, question3, question4, question5, question6, number_correct_answers, number_completed_answers);
            new readQuizzesDBWriter().execute(readQuizzesTokenExecute);
        } else if (fragmentCreationCount == 3) {
            number_completed_answers++;
            if (correct) {
                number_correct_answers++;
            }
            readQuizzesList = new readQuizzesDBReader().doInBackground();
            long id = readQuizzesList.size();
            question2 = previousState;
            readQuizzes readQuizzesTokenExecute = new readQuizzes(quizDate, question1, question2, question3, question4, question5, question6, number_correct_answers, number_completed_answers);
            new updateQuizzesDBWriter(id).execute(readQuizzesTokenExecute);
        } else if (fragmentCreationCount == 4) {
            number_completed_answers++;
            if (correct) {
                number_correct_answers++;
            }
            readQuizzesList = new readQuizzesDBReader().doInBackground();
            long id = readQuizzesList.size();
            question3 = previousState;
            readQuizzes readQuizzesTokenExecute = new readQuizzes(quizDate, question1, question2, question3, question4, question5, question6, number_correct_answers, number_completed_answers);
            new updateQuizzesDBWriter(id).execute(readQuizzesTokenExecute);
        }


//        if ((readQuizzesData != null && readQuizzesData.isDB2Open()) && fragmentCreationCount > 6) {
//            readQuizzesData.close();
//        }
        if (readcsvData != null && readcsvData.isDBOpen()) {
            readcsvData.close();
        }
    }

    public class readQuizzesDBWriter extends AsyncTask<readQuizzes, readQuizzes> {
        @Override
        protected readQuizzes doInBackground (readQuizzes... readQuizzesMulti) {
            readQuizzesData.storeReadQuizzes( readQuizzesMulti[0]);
            return readQuizzesMulti[0];
        }
        @Override
        protected void onPostExecute(readQuizzes readQuizzesToken) {
            //            Toast.makeText( getActivity(), "state: " + readcsvToken.getState(),
//                    Toast.LENGTH_SHORT).show();
        }
    }

    public class updateQuizzesDBWriter extends AsyncTask<readQuizzes, readQuizzes> {
        private long idToUpdate;
        public updateQuizzesDBWriter(long id) {
            this.idToUpdate = id;
        }
        @Override
        protected readQuizzes doInBackground(readQuizzes... readQuizzesMulti) {
            readQuizzesData.updateReadQuizzes(idToUpdate, readQuizzesMulti[0]);
            return readQuizzesMulti[0];
        }
        @Override
        protected void onPostExecute(readQuizzes readQuizzesToken) {

        }
    }

    public class readQuizzesDBReader extends AsyncTask<Void, List<readQuizzes>> {
        @Override
        protected List<readQuizzes> doInBackground(Void... params) {
            List<readQuizzes> readQuizzesList = readQuizzesData.retrieveAllreadQuizzesLeads();
            Log.d("TAG", "Recieved: " + readQuizzesList.size() );
            return readQuizzesList;
        }
        @Override
        protected void onPostExecute(List<readQuizzes> readQuizzes) {

        }
    }
}