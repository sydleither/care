package com.care.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.care.ServiceClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TweetWebServiceModel {
    public interface GetTweetsResponse {
        void response(ArrayList<TweetWebService> tweets);
        void error();
    }
    public interface GetTweetsResponsePost {
        void response();
        void error();
    }
    public interface GetTweetsResponseDate {
        void response(String date);
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

    public void postTweets(List<Tweet> request, GetTweetsResponsePost handler) {
        Gson gson = new Gson();
        String json = gson.toJson(request);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        }
        catch(JSONException e) {
            handler.error();
        }

        ServiceClient serviceClient = ServiceClient.getSharedInstance(null);

        serviceClient.post(jsonArray,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    handler.response();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    handler.error();
                }
            });
    }

    public void getNewestDate(TweetWebServiceModel.GetTweetsResponseDate handler) {
        ServiceClient serviceClient = ServiceClient.getSharedInstance(null);

        serviceClient.get_newest_date(null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String date = "";
                    JSONArray data = null;
                    try {
                        data = (JSONArray) response.get("data");
                        date = data.getString(0).substring(14,24);
                    } catch (JSONException e) {
                        date = "ERROR";
                    }
                    handler.response(date);
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
