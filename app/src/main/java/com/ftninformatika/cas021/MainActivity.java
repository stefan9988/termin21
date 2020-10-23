package com.ftninformatika.cas021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvText;
    private Switch svSmer;
    private Button bStart;
    private boolean smer=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText=findViewById(R.id.tvText);
        svSmer=findViewById(R.id.svSmer);
        bStart=findViewById(R.id.bStart);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThread();
            }
        });
        svSmer.setText(smer ? "Pozitivan" :"Negativan");
        svSmer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                smer=!smer;
                svSmer.setText(smer ? "Pozitivan" :"Negativan");
            }
        });
    }
    private void startThread(){
        bStart.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sec=10;
                while (sec>1){
                    sec=smer ? +sec+1:sec-1;
                    updateTextViewA(sec+"");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                updateTextViewA("BOOM!!!");
                bStart.post(new Runnable() {
                    @Override
                    public void run() {
                        bStart.setEnabled(true);
                    }
                });
            }
        }).start();
    }
    private void updateTextViewA(final String text){
        tvText.post(new Runnable() {
            @Override
            public void run() {
                tvText.setText(text);
            }
        });
    }



}