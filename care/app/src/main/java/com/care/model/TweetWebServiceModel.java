package com.care.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.care.ServiceClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TweetWebServiceModel {
    public interface GetTweetsResponse {
        void response(ArrayList<TweetWebService> tweets);
        void error();
    }

    public void getTweets(TweetWebServiceModel.GetTweetsResponse handler, String twitter) {
        ServiceClient serviceClient = ServiceClient.getSharedInstance(null);

        serviceClient.get_tweets(twitter, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<TweetWebService> tweets = new ArrayList<>();
                        JSONArray data = null;
                        JSONObject tweetJson = null;
                        try {
                            data = (JSONArray) response.get("data");
                        } catch (JSONException e) {
                            //TODO
                        }
                        for(int i = 0; i < data.length(); i++) {
                            try {
                                tweetJson = (JSONObject) data.get(i);
                            } catch (JSONException e) {
                                //TODO
                            }
                            Gson gson = new Gson();
                            TweetWebService tweet = gson.fromJson(tweetJson.toString(), TweetWebService.class);
                            tweets.add(tweet);
                        }
                        handler.response(tweets);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.error();
                    }
                });
    }
}
