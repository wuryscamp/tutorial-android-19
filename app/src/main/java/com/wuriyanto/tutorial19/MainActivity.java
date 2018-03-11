package com.wuriyanto.tutorial19;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonDownload;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDownload = (Button) findViewById(R.id.btn_download);

        buttonDownload.setOnClickListener(new ButtonDownloadListener());
    }

    private class ButtonDownloadListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setMessage("Downloading file");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (progressStatus < 100) {

                        progressStatus += 10;

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setProgress(progressStatus);
                            }
                        });
                    }

                    // dismiss

                    if(progressStatus >= 100) {

                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }
            }).start();
        }
    }
}
