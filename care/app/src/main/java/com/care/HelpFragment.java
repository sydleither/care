package com.care;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.care.model.Tweet;
import com.care.model.TweetListModel;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_help, container, false);

        fragView.findViewById(R.id.buttonBack2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        fragView.findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            TweetListModel tweetListModel = TweetListModel.getInstance();
                            List<Tweet> tweets = tweetListModel.getTweetList();
                            updateTweets(tweets.get(tweets.size()-1).date);
                        } catch (Exception e) { }
                    }
                });
                thread.start();
            }
        });

        return fragView;
    }

    private void updateTweets(String date) {
        int cur_year = Integer.parseInt(date.substring(0,4));
        int cur_month = Integer.parseInt(date.substring(5,7));
        int cur_day = Integer.parseInt(date.substring(8,10));

        for (int year = cur_year; year < 2100; year++) {
            for (int month = cur_month; month <= 12; month++) {
                for (int day = cur_day+1; day <= 31; day++){
                    String month_f = String.format("%02d", month);
                    String day_f = String.format("%02d", day);
                    try {
                        URL url = new URL(String.format("https://alexlitel.github.io/congresstweets/data/%s-%s-%s.json", Integer.toString(year), month_f, day_f));
                        InputStreamReader reader = new InputStreamReader(url.openStream());
                        TweetJson[] idk = new Gson().fromJson(reader, TweetJson[].class);
                        String temp = idk[0].screen_name;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }

    private class TweetJson {
        String id;
        String screen_name;
        String user_id;
        String time;
        String link;
        String text;
        String source;
    }
}