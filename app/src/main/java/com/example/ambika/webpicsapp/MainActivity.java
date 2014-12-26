package com.example.ambika.webpicsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    EditText searchQuery;
    Button searchButton;
    GridView searchResults;
    ImageArrayAdapter imageAdapter;
    ArrayList<ImageResults> imageResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
        //set adapter for gridview
        imageAdapter = new ImageArrayAdapter(this, imageResults);
        searchResults.setAdapter(imageAdapter);
    }

    //called when search button is clicked
    //parses json object
    public void onImageSearch(View v) {
        String query = searchQuery.getText().toString();
        int startNum = 0;
        Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG).show();

        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchQuery.getWindowToken(), 0);

        AsyncHttpClient client = new AsyncHttpClient();
        String queryString = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
                + "start=" + startNum + "&v=1.0&q=" + Uri.encode(query);

        client.get(queryString, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray imageJsonResults  = null;
                try {
                    imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
                    imageResults.clear();
                    imageAdapter.addAll(ImageResults.fromJSONArray(imageJsonResults));
                    Log.d("DEBUG", imageResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    //set up UI views
    public void setUp(){
        searchQuery = (EditText) findViewById(R.id.editTextQuery);
        searchButton = (Button) findViewById(R.id.buttonSearch);
        searchResults = (GridView) findViewById(R.id.gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
