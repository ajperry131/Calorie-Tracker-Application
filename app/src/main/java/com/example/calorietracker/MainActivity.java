package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences userSettings = PreferenceManager.getDefaultSharedPreferences(this);

        TextView currentWeightText = findViewById(R.id.currentWeightText);
        TextView lostWeightText = findViewById(R.id.lostWeightText);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView progressBarStartText = findViewById(R.id.progressBarStartText);
        TextView progressBarGoalText = findViewById(R.id.progressBarGoalText);

        //gets data from user settings and makes appropriate calculations to be assigned to objects
        int startWeight = userSettings.getInt("startWeight", 0);
        int currentWeight = userSettings.getInt("currentWeight", 0);
        int goalWeight = userSettings.getInt("goalWeight", 0);
        int lostWeight = startWeight - currentWeight;
        int startAndGoalDifference = startWeight - goalWeight;
        int startAndCurrentDifference = startWeight - currentWeight;

        //sets the objects to reflect the user settings
        currentWeightText.setText(String.format("%s lbs", currentWeight));
        lostWeightText.setText(String.format("You have lost %s lbs of fat", lostWeight));
        progressBar.setMax(startAndGoalDifference);
        progressBar.setProgress(startAndCurrentDifference);
        progressBarStartText.setText(String.format("%s lbs", startWeight));
        progressBarGoalText.setText(String.format("%s lbs", goalWeight));

        Button editProfileButton = findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileSettings.class)));

        Button recordButton = findViewById(R.id.recordButton);
        recordButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CalorieRecording.class)));
    }
}