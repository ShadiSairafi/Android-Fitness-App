package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private int elapsedTime = 0;
    private boolean isRunning = false;  // To keep track of stopwatch state
    private Handler stopwatchHandler = new Handler();
    private TextView stopwatchTextView;
    private Button startStopButton;
    private Button resetButton;
    private Button BackB;
    private ListView classDetailsListView;

    private Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                elapsedTime++;
                updateStopwatch();
                stopwatchHandler.postDelayed(this, 1000); // Update every second
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Get the data passed from MainActivity2
        String className = getIntent().getStringExtra("className");
        int classTime = getIntent().getIntExtra("classTime", 0);
        String[] classDetails = getIntent().getStringArrayExtra("classDetails");

        TextView titleTimeTextView = findViewById(R.id.classTitleTime);
        stopwatchTextView = findViewById(R.id.stopwatch);
        startStopButton = findViewById(R.id.startStopButton);
        resetButton = findViewById(R.id.resetButton);
        BackB = findViewById(R.id.BackB);
        classDetailsListView = findViewById(R.id.classDetails);  // Find ListView by ID

        // Set the class name and time to the classTitleTime TextView
        titleTimeTextView.setText(className + " - " + classTime + " minutes");

        // Set up the ListView adapter for class details
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_custom_layout, classDetails);
        classDetailsListView.setAdapter(adapter);

        // Start/Stop button click handling
        startStopButton.setOnClickListener(v -> toggleStopwatch());

        // Reset button click handling
        resetButton.setOnClickListener(v -> resetStopwatch());

        BackB.setOnClickListener(e -> finish());

        // Start the stopwatch when the activity is created
        isRunning = true;
        stopwatchHandler.post(stopwatchRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;  // Stop the stopwatch when paused
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isRunning) {
            isRunning = true;
            stopwatchHandler.post(stopwatchRunnable);
        }
    }
    //changing the seconds to minutes when we reach 60 seconds
    private void updateStopwatch() {
        int minutes = elapsedTime / 60;
        int seconds = elapsedTime % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        stopwatchTextView.setText(time);
    }
    //change button text when clicked
    private void toggleStopwatch() {
        if (isRunning) {
            isRunning = false;
            startStopButton.setText("Start");
        } else {
            isRunning = true;
            stopwatchHandler.post(stopwatchRunnable);
            startStopButton.setText("Stop");
        }
    }
    //set the time to 0
    private void resetStopwatch() {
        isRunning = false;
        elapsedTime = 0;
        updateStopwatch();
        startStopButton.setText("Start");
    }
}
