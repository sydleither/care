package com.care.model;

public class PoliticianWebService {
    public int politicianId;
    public String name;
    public String type;
    public String state;
    public String party;
    public String twitter;

    public PoliticianWebService(int politicianId, String name, String type, String state, String party, String twitter) {
        this.politicianId = politicianId;
        this.name = name;
        this.type = type;
        this.state = state;
        this.party = party;
        this.twitter = twitter;
    }
}
