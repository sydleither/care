package com.care.model;

import android.widget.TextView;

import com.care.R;

import java.util.ArrayList;
import java.util.List;

public class TweetListModel {
    private static TweetListModel single_instance = null;
    private List<Tweet> tweetList = new ArrayList<>();
    private String newestDate = "";

    public interface UpdateListCompletionHandler {
        void didComplete();
    }

    private TweetListModel() {}

    public static TweetListModel getInstance() {
        if(single_instance == null)
            single_instance = new TweetListModel();
        return single_instance;
    }

    public void updatedList(TweetListModel.UpdateListCompletionHandler handler, String twitter) {
        TweetWebServiceModel model = new TweetWebServiceModel();
        model.getTweets(new TweetWebServiceModel.GetTweetsResponse() {
            @Override
            public void response(ArrayList<TweetWebService> tweets) {
                tweetList.clear();
                for (TweetWebService tweetWebService : tweets) {
                    Tweet tweet = new Tweet(tweetWebService.twitter, tweetWebService.date, tweetWebService.link, tweetWebService.text);
                    tweetList.add(tweet);
                }
                handler.didComplete();
            }

            @Override
            public void error() {
                tweetList.clear();
                Tweet tweet = new Tweet("Error", "", "", "");
                tweetList.add(tweet);
                handler.didComplete();
            }
        }, twitter);
    }

    public void newestDate(TweetListModel.UpdateListCompletionHandler handler) {
        TweetWebServiceModel model = new TweetWebServiceModel();
        model.getNewestDate(new TweetWebServiceModel.GetTweetsResponseDate() {
            @Override
            public void response(String date) {
                newestDate = date;
                handler.didComplete();
            }

            @Override
            public void error() {
                newestDate = "ERROR";
                handler.didComplete();
            }
        });
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public String getNewestDate() {
        return newestDate;
    }
}
