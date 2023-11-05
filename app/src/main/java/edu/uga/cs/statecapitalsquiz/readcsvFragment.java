package edu.uga.cs.statecapitalsquiz;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        if (readcsvData != null)
            readcsvData.open();
        SaveButtonClickListener saveButtonClickListener = new SaveButtonClickListener(getActivity().getAssets());
        saveButtonClickListener.onClick(null); // Pass a dummy view (null) since onClick method expects a View paramete
    }

    public class readcsvDBWriter extends AsyncTask<readcsv, readcsv> {
        @Override
        protected readcsv doInBackground (readcsv... readcsvs) {
            readcsvData.storereadcsv( readcsvs[0]);
            return readcsvs[0];
        }
        @Override
        protected void onPostExecute(readcsv readcsvToken) {
//            Toast.makeText( getActivity(), "state: " + readcsvToken.getState(),
//                    Toast.LENGTH_SHORT).show();
        }
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
    private class SaveButtonClickListener implements  View.OnClickListener {
        private AssetManager assetManager;
        SaveButtonClickListener(AssetManager assetManager) {
            this.assetManager = assetManager;
        }
        @Override
        public void onClick(View v) {
            List<readcsv> readcsvList = new readcsvDBReader().doInBackground();
            Log.d("TAGGGGGG", "Rows: " + readcsvList.size());
            if (readcsvList.size() < 50) {
                try {
                    InputStream inputStream = assetManager.open("StateCapitals.csv");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    int track = 0;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (track == 0) {
                            track++;
                            line = bufferedReader.readLine();
                        }
                        if (line == null) {
                            break;
                        }
                        String[] data = line.split(",");
                        if (data.length >= 4) {
                            String state = data[0];
                            String capitalCity = data[1];
                            String additionalCity1 = data[2];
                            String additionalCity2 = data[3];
                            readcsv readcsvTokenExecute = new readcsv(state, capitalCity, additionalCity1, additionalCity2);
                            new readcsvDBWriter().execute(readcsvTokenExecute);
                        }
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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