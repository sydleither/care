package com.care.model;

public class TweetWebService {
    public int tweetId;
    public String twitter;
    public String date;
    public String link;
    public String text;

    public TweetWebService(int tweetId, String twitter, String date, String link, String text) {
        this.tweetId = tweetId;
        this.twitter = twitter;
        this.date = date;
        this.link = link;
        this.text = text;
    }
}
