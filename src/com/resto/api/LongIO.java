package com.resto.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.conn.HttpHostConnectException;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.os.AsyncTask;

public class LongIO extends AsyncTask<String, Void, HashMap<Boolean, String>> {

    public static final String HOST_URL = "http://192.168.0.103:3000/";
    public static final String CLASS_NAME = LongIO.class.toString();

    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);
            if (n > 0) out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    @Override
    protected HashMap<Boolean, String> doInBackground(String... url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(HOST_URL + url[0]);
        HashMap<Boolean, String> hm = new HashMap<Boolean, String>();
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            hm = handleResponse(response);
        } catch (HttpHostConnectException e){
            Log.e(CLASS_NAME,"Oops,seems the server is down.Somebody restart the server " + e.getClass().toString() + e.getLocalizedMessage());
            hm.put(false, "Server seems to be Down. Please try after sometime");
        }
        catch (Exception e) {
            Log.e(CLASS_NAME, "PRASANN: " + e.getClass().toString() + e.getLocalizedMessage());
            hm.put(false, "There seems to be an error");
        }
        return hm;
    }

    protected HashMap<Boolean, String> handleResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        HashMap hm = new HashMap();
        if(statusCode/100 == 2)
            hm.put(true, getASCIIContentFromEntity(entity));
        else if(statusCode == 404)
            hm.put(false, "Invalid data entered. Please entered correct data");
        else if(statusCode == 500)
            hm.put(false, "Something went wrong. Please try later");
        return hm;
    }
}
