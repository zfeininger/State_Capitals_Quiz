package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastQuizFragment extends Fragment {
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    private TextView textview6;
    private TextView textview7;
    private TextView textview8;
    private TextView textview9;
    private TextView textview10;


    private readQuizzesData readQuizzesData = null;
    private static List<readQuizzes> readQuizzesList;



    public PastQuizFragment() {
        // Required empty public constructor
    }

    public static PastQuizFragment newInstance(String param1, String param2) {
        PastQuizFragment fragment = new PastQuizFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_quiz, container, false);
        readQuizzesData = new readQuizzesData(getActivity());
        if (readQuizzesData != null) {
            readQuizzesData.open();
        }
        readQuizzesList = new readQuizzesDBReader().doInBackground();
        long id = readQuizzesList.size();
        int startIndex = Math.max(0, readQuizzesList.size() - 10);
        List<readQuizzes> readQuizzesListClean = readQuizzesList.subList(startIndex, readQuizzesList.size());
        String firstAttemptClean = clean(readQuizzesListClean, 0);
        String secondAttemptClean = clean(readQuizzesListClean, 1);
        String thirdAttemptClean = clean(readQuizzesListClean, 2);
        String fourthAttemptClean = clean(readQuizzesListClean, 3);
        String fifthAttemptClean = clean(readQuizzesListClean, 4);
        String sixthAttemptClean = clean(readQuizzesListClean, 5);
        String seventhAttemptClean = clean(readQuizzesListClean, 6);
        String eighthAttemptClean = clean(readQuizzesListClean, 7);
        String ninthAttemptClean = clean(readQuizzesListClean, 8);
        String tenthAttemptClean = clean(readQuizzesListClean, 9);
        textview1 = view.findViewById(R.id.textView14);
        textview1.setText(tenthAttemptClean);
        textview2 = view.findViewById(R.id.textView15);
        textview2.setText(ninthAttemptClean);
        textview3 = view.findViewById(R.id.textView16);
        textview3.setText(eighthAttemptClean);
        textview4 = view.findViewById(R.id.textView17);
        textview4.setText(seventhAttemptClean);
        textview5 = view.findViewById(R.id.textView18);
        textview5.setText(sixthAttemptClean);
        textview6 = view.findViewById(R.id.textView19);
        textview6.setText(fifthAttemptClean);
        textview7 = view.findViewById(R.id.textView20);
        textview7.setText(fourthAttemptClean);
        textview8 = view.findViewById(R.id.textView21);
        textview8.setText(thirdAttemptClean);
        textview9 = view.findViewById(R.id.textView22);
        textview9.setText(secondAttemptClean);
        textview10 = view.findViewById(R.id.textView23);
        textview10.setText(firstAttemptClean);




        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (readQuizzesData != null && !readQuizzesData.isDB2Open())
            readQuizzesData.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (readQuizzesData != null && readQuizzesData.isDB2Open()) {
            readQuizzesData.close();
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

    public String clean(List<readQuizzes> readQuizzesListClean, int pos) {
        String firstAttemptClean = null;
        if (pos >= readQuizzesListClean.size()) {
            return "";
        }
        if (!readQuizzesListClean.isEmpty()) {
            readQuizzes firstAttempt = readQuizzesListClean.get(pos);
            firstAttemptClean = firstAttempt.toString();
            String[] words = firstAttemptClean.split("\\s+");
            StringBuilder resultBuilder = new StringBuilder();
            for (String word : words) {
                if (word.matches("[a-zA-Z]+")) {
                    continue;
                }
                resultBuilder.append(word).append(" ");
            }
            firstAttemptClean = resultBuilder.toString().trim();
        }
        return firstAttemptClean;

    }
}





