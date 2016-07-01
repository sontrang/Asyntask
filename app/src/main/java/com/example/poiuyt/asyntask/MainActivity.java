package com.example.poiuyt.asyntask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_run);
        time = (EditText) findViewById(R.id.in_time);
        finalResult = (TextView) findViewById(R.id.tv_result);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_run:
                new LongOperation().execute(time.getText().toString());
                break;
        }
    }

    private class LongOperation extends AsyncTask<String, String, String> {
        private ProgressDialog progress;
        private String result;

        @Override
        protected String doInBackground(String... strings) {

            publishProgress("Looking for....");

            int time = Integer.parseInt(strings[0]) * 1000;
            try {
                Thread.sleep(time);
                result = "Slept for " + strings[0] + " second";
            } catch (InterruptedException e) {
                e.printStackTrace();
                result = e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            finalResult.setText(s);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, "Loading", "Please wait");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            finalResult.setText(values[0]);
        }
    }
}
