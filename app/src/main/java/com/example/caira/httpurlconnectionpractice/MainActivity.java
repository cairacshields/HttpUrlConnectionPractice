package com.example.caira.httpurlconnectionpractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Get an instance of our textView
    TextView texty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate our textView
        texty = (TextView)findViewById(R.id.texty);
    }

    //Inflate our menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    //Handle option item selections 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.data){
            requestData("http://services.hanselandpetal.com/feeds/flowers.json");
        }
        return super.onOptionsItemSelected(item);
    }

    //Custom method that creates an instance of our AsyncTask class
    public void requestData(String uri){
        myTask task = new myTask();
        //execute our AsyncTask class object and pass in our String object 
        task.execute(uri);
    }
    //Custom method to handle the UI 
    public void updateDisplay(String message){
        //here we just append the data that we recieve from the server to our current textView so that we can see it
        texty.append(message + "\n");
    }

    //Here is our AsyncTask class that is necessary, because connecting to a server using HttpUrlConnection is a longrunning task
    //And remember that any longrunning task can potentially block the main thread and cause fatal errors 
    public class myTask extends AsyncTask<String,String, String>{

        //We dont really need to implement the onPreExecute method, but I'm doing it here just to show its functionality 
        @Override
        protected void onPreExecute() {
            updateDisplay("Starting");
            super.onPreExecute();
        }

        //The doInBackground method is required when using AsyncTask, this is where all of our potentially longrunning 
        //code should be written
        @Override
        protected String doInBackground(String... params) {
            //Here we simply call the getData method that is defined in the HttpManager class and pass it in our string url
            return HttpManager.getData(params[0]);
        }

        //We need to use onPostExectue to handle the data that is returned from doInBackground
        @Override
        protected void onPostExecute(String s) {
            //We'll simply pass our result to our updateDisplay method, which will append them to our textView
            updateDisplay(s);
            super.onPostExecute(s);
        }
    }
}
