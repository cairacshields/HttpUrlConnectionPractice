package com.example.caira.httpurlconnectionpractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView texty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texty = (TextView)findViewById(R.id.texty);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.data){
            requestData("http://services.hanselandpetal.com/feeds/flowers.json");
        }
        return super.onOptionsItemSelected(item);
    }

    public void requestData(String uri){

        myTask task = new myTask();
        task.execute(uri);
    }
    public void updateDisplay(String message){
        texty.append(message + "\n");
    }

    public class myTask extends AsyncTask<String,String, String>{

        @Override
        protected void onPreExecute() {
            updateDisplay("Starting");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            updateDisplay(s);
            super.onPostExecute(s);
        }
    }
}
