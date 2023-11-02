package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link readcsvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class readcsvFragment extends Fragment {

    private readcsvData readcsvData = null;

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
        readcsvData = new readcsvData( getActivity() );
        SaveButtonClickListener saveButtonClickListener = new SaveButtonClickListener();
        saveButtonClickListener.onClick(null); // Pass a dummy view (null) since onClick method expects a View parameter
    }

    public class readcsvDBWriter extends AsyncTask<readcsv, readcsv> {
        @Override
        protected readcsv doInBackground (readcsv... readcsvs) {
            readcsvData.storereadcsv( readcsvs[0]);
            return readcsvs[0];
        }
        @Override
        protected void onPostExecute(readcsv readcsvToken) {
            Toast.makeText( getActivity(), "state: " + readcsvToken.getState(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private class SaveButtonClickListener implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
        String state = "test";
        String capitalCity = "test";
        String additionalCity1 = "test";
        String additionalCity2 = "test";
        readcsv readcsvTokenExecute = new readcsv(state, capitalCity, additionalCity1, additionalCity2);
        new readcsvDBWriter().execute(readcsvTokenExecute);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (readcsvData != null)
            readcsvData.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (readcsvData != null) {
            readcsvData.close();
        }
    }
}