package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileSettings extends AppCompatActivity {

    private final int FEMALE_CALORIES = 13; //calories per pound a woman needs
    private final int MALE_CALORIES = 15; //calories per pound a man needs
    private int caloriesPerPound; //holds either FEMALE_CALORIES or MALE_CALORIES
    private final int AUTO_DECREASE = 300; //The auto calorie adjustment

    //holder variables are responsible for allowing onTextChange listeners to communicate with each other
    private String genderHolder;
    private int startWeightHolder;
    private int currentWeightHolder;
    private int goalWeightHolder;
    private int calorieMaxHolder;
    private String calorieLimitSettingHolder;
    private int calorieLimitHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        final SharedPreferences userSettings = PreferenceManager.getDefaultSharedPreferences(this);

        //programmatically sets the calorie max number to be non-editable
        EditText calorieMaxNumber = findViewById(R.id.calorieMaxNumber);
        calorieMaxNumber.setEnabled(false);

        Button cancelButton = findViewById(R.id.cancelSettingsButton);
        Button saveButton = findViewById(R.id.saveSettingsButton);
        EditText currentWeightNumber = findViewById(R.id.currentWeightNumber);
        EditText calorieLimitNumber = findViewById(R.id.calorieLimitNumber);
        Spinner calorieLimitSpinner = findViewById(R.id.calorieLimitSpinner);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        EditText startWeightNumber = findViewById(R.id.startWeightNumber);
        EditText goalWeightNumber = findViewById(R.id.goalWeightNumber);

        //will assign user settings to each holder if the preferences exist
        boolean firstRun = userSettings.getBoolean("firstRun", true);
        if (firstRun) {
            SharedPreferences.Editor editor = userSettings.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
        }
        else {
            genderHolder = userSettings.getString("gender", "Male");
            startWeightHolder = userSettings.getInt("startWeight", 0);
            currentWeightHolder = userSettings.getInt("currentWeight", 0);
            goalWeightHolder = userSettings.getInt("goalWeight", 0);
            calorieMaxHolder = userSettings.getInt("calorieMax", 0);
            calorieLimitSettingHolder = userSettings.getString("calorieLimitSetting", "Manual");
            calorieLimitHolder = userSettings.getInt("calorieLimit", 0);
            if (genderHolder.equals("Male"))
                genderSpinner.setSelection(0); //Male
            else
                genderSpinner.setSelection(1); //Female

            if (calorieLimitSettingHolder.equals("Manual"))
                calorieLimitSpinner.setSelection(0); //Manual
            else
                calorieLimitSpinner.setSelection(1); //Auto

            startWeightNumber.setText(String.valueOf(startWeightHolder));
            currentWeightNumber.setText(String.valueOf(currentWeightHolder));
            goalWeightNumber.setText(String.valueOf(goalWeightHolder));
            calorieMaxNumber.setText(String.valueOf(calorieMaxHolder));
            calorieLimitNumber.setText(String.valueOf(calorieLimitHolder));
        }

        //cancel button
        cancelButton.setOnClickListener(v -> startActivity(new Intent(ProfileSettings.this, MainActivity.class)));

        //save button to store settings changes to the user settings file
        saveButton.setOnClickListener(v -> {
            if (genderHolder == null || startWeightHolder == 0 || currentWeightHolder == 0 || goalWeightHolder == 0 ||
                    calorieMaxHolder == 0 || calorieLimitSettingHolder == null || calorieLimitHolder == 0) {
                Toast.makeText(ProfileSettings.this, "Missing Information", Toast.LENGTH_SHORT).show();
            }
            else {
                //save data into user settings
                SharedPreferences.Editor editor = userSettings.edit();
                editor.putString("gender", genderHolder);
                editor.putInt("startWeight", startWeightHolder);
                editor.putInt("currentWeight", currentWeightHolder);
                editor.putInt("goalWeight", goalWeightHolder);
                editor.putInt("calorieMax", calorieMaxHolder);
                editor.putString("calorieLimitSetting", calorieLimitSettingHolder);
                editor.putInt("calorieLimit", calorieLimitHolder);
                editor.apply();
                startActivity(new Intent(ProfileSettings.this, MainActivity.class));
            }
        });

        //gets the current weight and updates the auto calorie limit and max calorie limit
        currentWeightNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    currentWeightHolder = Integer.parseInt(String.valueOf(s));
                    calorieMaxHolder = currentWeightHolder * caloriesPerPound;
                    calorieMaxNumber.setText(String.valueOf(calorieMaxHolder));
                    if (calorieLimitSpinner.getSelectedItem().toString().equals("Auto")) {
                        calorieLimitSettingHolder = "Auto";
                        calorieLimitHolder = calorieMaxHolder - AUTO_DECREASE;
                        calorieLimitNumber.setText(String.valueOf(calorieLimitHolder));
                    }
                    else {
                        calorieLimitSettingHolder = "Manual";
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //gets the manual calorie limit set by the user
        calorieLimitNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    calorieLimitHolder = Integer.parseInt(String.valueOf(s));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //gets the current state of the limit--auto or manual--and assigns values and edits accordingly
        calorieLimitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (calorieLimitSpinner.getSelectedItem().toString().equals("Auto")) {
                    calorieLimitSettingHolder = "Auto";
                    calorieLimitHolder = calorieMaxHolder - AUTO_DECREASE;
                    calorieLimitNumber.setText(String.valueOf(calorieLimitHolder));
                    calorieLimitNumber.setEnabled(false);
                    calorieLimitNumber.setBackgroundResource(R.drawable.back_uneditable);
                }
                else {
                    calorieLimitSettingHolder = "Manual";
                    calorieLimitNumber.setEnabled(true);
                    calorieLimitNumber.setBackgroundResource(R.drawable.back);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //gets the state of the gender--male or female-- and assigns values and edits accordingly
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (genderSpinner.getSelectedItem().toString().equals("Female")) {
                    caloriesPerPound = FEMALE_CALORIES;
                    genderHolder = "Female";
                }
                else {
                    caloriesPerPound = MALE_CALORIES;
                    genderHolder = "Male";
                }
                calorieMaxHolder = currentWeightHolder * caloriesPerPound;
                calorieMaxNumber.setText(String.valueOf(calorieMaxHolder));
                if (calorieLimitSpinner.getSelectedItem().toString().equals("Auto")) {
                    calorieLimitSettingHolder = "Auto";
                    calorieLimitHolder = calorieMaxHolder - AUTO_DECREASE;
                    calorieLimitNumber.setText(String.valueOf(calorieLimitHolder));
                }
                else {
                    calorieLimitSettingHolder = "Manual";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //stores the text typed in start weight
        startWeightNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    startWeightHolder = Integer.parseInt(String.valueOf(s));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //stores the text typed in goal weight
        goalWeightNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    goalWeightHolder = Integer.parseInt(String.valueOf(s));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}