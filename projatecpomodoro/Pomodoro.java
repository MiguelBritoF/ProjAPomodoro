package com.example.projatecpomodoro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro extends AppCompatActivity {

    TextView txtTimer, txtTodo;
    Button btnStopEStart;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        getSupportActionBar().hide();

        txtTodo = (TextView) findViewById(R.id.txtTodo);
        txtTimer = (TextView) findViewById(R.id.txtTimePlay);
        btnStopEStart = (Button) findViewById(R.id.btnStartEStop);

        timer = new Timer();

        txtTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pomodoro.this, ToDoList.class));
            }
        });
    }


    public void resetTapped(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Resetar Pomodoro");
        resetAlert.setMessage("Tem certeza de que dejesa resetar o pomodoro?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    setButtonUI("START", R.color.verde);
                    time = 0.0;
                    timerStarted = false;
                    txtTimer.setText(formatTime(0,0,0));

                }
            }
        });

        resetAlert.setNeutralButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //Vazio
            }
        });

        resetAlert.show();

    }

    public void startStopTapped(View view)
    {
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("STOP", R.color.vermelho);

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START", R.color.verde);

            timerTask.cancel();
        }
    }

    private void setButtonUI(String start, int color)
    {
        btnStopEStart.setText(start);
        btnStopEStart.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        txtTimer.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }


    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600); // Não ira para o timer

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format( String.format("%02d",minutes) + " : " + String.format("%02d",seconds));
    }

}