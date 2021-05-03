package com.care.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.care.ServiceClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PoliticianWebServiceModel {
    public interface GetPoliticiansResponse {
        void response(ArrayList<PoliticianWebService> politicians);
        void error();
    }

    public void getPoliticians(GetPoliticiansResponse handler) {
        ServiceClient serviceClient = ServiceClient.getSharedInstance(null);

        serviceClient.get(null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ArrayList<PoliticianWebService> politicians = new ArrayList<>();
                    JSONArray data = null;
                    JSONObject politicianJson = null;
                    try {
                        data = (JSONArray) response.get("data");
                    } catch (JSONException e) {
                        politicians.add(new PoliticianWebService(0, "Error Getting Politicians", "", "", "", ""));
                        handler.response(politicians);
                    }
                    for(int i = 0; i < data.length(); i++) {
                        try {
                            politicianJson = (JSONObject) data.get(i);
                        } catch (JSONException e) { }
                        Gson gson = new Gson();
                        PoliticianWebService politician = gson.fromJson(politicianJson.toString(), PoliticianWebService.class);
                        politicians.add(politician);
                    }
                    handler.response(politicians);
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
