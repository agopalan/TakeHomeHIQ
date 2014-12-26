package com.example.ambika.webpicsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageResults implements Serializable{

    private String fullURL;
    private String thumbnailURL;
    private static final long serialVersionUID = -8758440615820744812L;

    //construct full and thumbnail URLs from json object
    public ImageResults(JSONObject json){
        try{
            this.fullURL = json.getString("url");
            this.thumbnailURL = json.getString("tbUrl");
        } catch(JSONException e){
            this.fullURL = null;
            this.thumbnailURL = null;
        }
    }

    //get image results from json object
    public static ArrayList<ImageResults> fromJSONArray(JSONArray jsonArray){
        ArrayList<ImageResults> imageResults = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            try{
                imageResults.add(new ImageResults(jsonArray.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return imageResults;
    }

    public String toString(){
        return this.thumbnailURL;
    }

    //getter and setters for full and thumbnail URLs
    public String getFullURL(){
        return this.fullURL;
    }

    public String getThumbnailURL(){
        return this.thumbnailURL;
    }

    public void setFullURL(String fullURL){
        this.fullURL = fullURL;
    }

    public void setThumbnailURL(String thumbnailURL){
        this.thumbnailURL = thumbnailURL;
    }

}
