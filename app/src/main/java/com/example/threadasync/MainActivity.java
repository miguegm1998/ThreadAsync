package com.example.threadasync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean finished = true;
    private int cont;
    private Button btThread, btAsync;
    private TextView tvResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        btThread = findViewById(R.id.btThread);
        btAsync = findViewById(R.id.btAsync);
        tvResultado = findViewById(R.id.tvResultado);
        initEvents();

    }

    private void initEvents() {
        btThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finished){
                    cont = 20;
                    MyThread mythread = new MyThread();
                    mythread.start();
                }

            }
        });
        btAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finished){
                    cont = 20;
                    new WaitThread().execute();
                }

            }
        });
    }




    private class WaitThread extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            finished = false;
            cont = 20;
            for (int i = 20; i > 1; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tvResultado.post(new Runnable() {
                    @Override
                    public void run() {
                        cont--;
                        tvResultado.setText(""+cont);
                    }
                });

            }
            finished = true;
            return null;

        }
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            finished = false;
            cont = 20;
            for (int i = 20; i > 1; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tvResultado.post(new Runnable() {
                    @Override
                    public void run() {
                     cont--;
                     tvResultado.setText(""+cont);
                    }
                });

                }
            finished = true;
            }
        }
    }

