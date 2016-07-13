package com.example.caira.httpurlconnectionpractice;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Caira on 7/13/2016.
 */
public class HttpManager {

    //Create the getData method that we will call from our AsyncTask doInBackground Method
    //This method will require a String parameter to be passed in
    public static String getData(String uri){
        //Set our BufferedReader object to null at the beginning so that we can have global
        //access to this object later in our try/catch/finally block to close the reader.
        BufferedReader reader = null;

        //We need to surround our connection code with a try/catch block to handle any exceptions
        //and prevent errors
        try {
            //Create a new URL object using the uri param that will be passed in from our AsyncTask class
            URL url = new URL(uri);
            //Establish a connection to our URL using HttpURLConnection
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            //We use a string builder to append the data that our reader is reading
            StringBuilder sb = new StringBuilder();
            //Establish a reader with our open connection object to read our data from the input stream
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            //Create an instance variable called 'line'
            String line;
            //Set line to equal to whatever data is being read by the reader
            while ((line = reader.readLine()) != null){
                //Each line will be appended to the stringbuilder object
                //when the while loop is complete I will have recieved all of the data from the reader
                sb.append(line + "\n");
            }
            //Return our data so that we can use it in our application
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            Log.d("httpManager", e.toString());
            return null;
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
