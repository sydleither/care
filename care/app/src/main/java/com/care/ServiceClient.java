package com.care;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceClient {
    private static ServiceClient serviceClient;
    private static Context context;
    private RequestQueue requestQueue;
    private String serviceEndpoint = "https://mopsdev.bw.edu/~sleither17/care/www/ws.php/";

    private ServiceClient(Context context) {
        ServiceClient.context = context;
    }

    synchronized public static ServiceClient getSharedInstance(Context context) {
        if (serviceClient == null) {
            serviceClient = new ServiceClient(context);
        }
        return serviceClient;
    }

    synchronized public static ServiceClient getSharedInstance() {
        return serviceClient;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    private void addRequestToQueue(Request request) {
        getRequestQueue().add(request);
    }

    public void get(JSONObject jsonObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.serviceEndpoint+"politicians", jsonObject, listener, errorListener);
        this.addRequestToQueue(request);
    }

    public void get_tweets(String twitter, JSONObject jsonObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.serviceEndpoint+"tweets/"+twitter, jsonObject, listener, errorListener);
        this.addRequestToQueue(request);
    }

    public void post(JSONArray jsonArray, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, this.serviceEndpoint+"tweets", jsonArray, listener, errorListener); //TODO jsonobject listener
        this.addRequestToQueue(request);
    }

    public void get_newest_date(JSONObject jsonObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.serviceEndpoint+"tweets?query=newest_date", jsonObject, listener, errorListener);
        this.addRequestToQueue(request);
    }
}