package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class CalorieRecording extends AppCompatActivity {

    private TextView dateTextBox;
    private String dateTextBoxHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_recording);

        TextView weight1 = findViewById(R.id.weight1);TextView weight2 = findViewById(R.id.weight2);
        TextView weight3 = findViewById(R.id.weight3);TextView weight4 = findViewById(R.id.weight4);
        TextView weight5 = findViewById(R.id.weight5);TextView weight6 = findViewById(R.id.weight6);
        TextView weight7 = findViewById(R.id.weight7);TextView weight8 = findViewById(R.id.weight8);
        TextView weight9 = findViewById(R.id.weight9);TextView weight10 = findViewById(R.id.weight10);
        TextView weight11 = findViewById(R.id.weight11);TextView weight12 = findViewById(R.id.weight12);
        TextView weight13 = findViewById(R.id.weight13);TextView weight14 = findViewById(R.id.weight14);
        TextView weight15 = findViewById(R.id.weight15);TextView name1 = findViewById(R.id.name1);
        TextView name2 = findViewById(R.id.name2);TextView name3 = findViewById(R.id.name3);
        TextView name4 = findViewById(R.id.name4);TextView name5 = findViewById(R.id.name5);
        TextView name6 = findViewById(R.id.name6);TextView name7 = findViewById(R.id.name7);
        TextView name8 = findViewById(R.id.name8);TextView name9 = findViewById(R.id.name9);
        TextView name10 = findViewById(R.id.name10);TextView name11 = findViewById(R.id.name11);
        TextView name12 = findViewById(R.id.name12);TextView name13 = findViewById(R.id.name13);
        TextView name14 = findViewById(R.id.name14);TextView name15 = findViewById(R.id.name15);
        TextView calories1 = findViewById(R.id.calories1);TextView calories2 = findViewById(R.id.calories2);
        TextView calories3 = findViewById(R.id.calories3);TextView calories4 = findViewById(R.id.calories4);
        TextView calories5 = findViewById(R.id.calories5);TextView calories6 = findViewById(R.id.calories6);
        TextView calories7 = findViewById(R.id.calories7);TextView calories8 = findViewById(R.id.calories8);
        TextView calories9 = findViewById(R.id.calories9);TextView calories10 = findViewById(R.id.calories10);
        TextView calories11 = findViewById(R.id.calories11);TextView calories12 = findViewById(R.id.calories12);
        TextView calories13 = findViewById(R.id.calories13);TextView calories14 = findViewById(R.id.calories14);
        TextView calories15 = findViewById(R.id.calories15);

        TextView[] weights = {weight1, weight2, weight3, weight4, weight5, weight6, weight7,
                weight8, weight9, weight10, weight11, weight12, weight13, weight14, weight15};
        TextView[] names = {name1, name2, name3, name4, name5, name6, name7, name8, name9,
                name10, name11, name12, name13, name14, name15};
        TextView[] calories = {calories1, calories2, calories3, calories4, calories5, calories6,
                calories7, calories8, calories9, calories10, calories11, calories12, calories13,
                calories14, calories15};

        final SharedPreferences userSettings = PreferenceManager.getDefaultSharedPreferences(this);

        TextView currentCalorieText = findViewById(R.id.currentCalorieText);
        TextView maxCalorieText = findViewById(R.id.maxCalorieText);
        int maxCalories = userSettings.getInt("calorieLimit", 0);
        maxCalorieText.setText(String.valueOf(maxCalories));
        dateTextBox = findViewById(R.id.dateText);
        Button dateButton = findViewById(R.id.dateButton);
        Calendar calendar = Calendar.getInstance();
        DateFormat txtDate = DateFormat.getDateInstance();
        Button cancelButton = findViewById(R.id.cancelRecordButton);

        //performs a date set and table data grab on creation of activity as text watcher do not hear this
        dateTextBox.setText(txtDate.format(System.currentTimeMillis()));
        getTableDateData(userSettings, weights, names, calories);

        //performs a current calorie calculation on creation of activity as text watchers do not hear this
        currentCalorieText.setText(calculateCurrentCalories(calories));

        // a list of text watchers to watch for changes made to the calories section of the table.
        calories1.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories2.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories3.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories4.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories5.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories6.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories7.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories8.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories9.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories10.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories11.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories12.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories13.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories14.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        calories15.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { currentCalorieText.setText(calculateCurrentCalories(calories));}
            @Override public void afterTextChanged(Editable s) {}
        });
        //end calorie listeners



        //cancel button will cancel any changes made to the table and return to today's date
        cancelButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = userSettings.edit();
            editor.putString("date", txtDate.format(System.currentTimeMillis()));
            editor.apply();

            startActivity(new Intent(CalorieRecording.this, MainActivity.class));
        }); //end on click listener

        //save button to save the table data. It will not save table data a row is partially filled.
        Button saveButton = findViewById(R.id.saveRecordButton);
        saveButton.setOnClickListener(v -> {
            if (dateTextBox.getText().toString().equals("")) {
                Toast.makeText(CalorieRecording.this, "Select a date to save data", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = userSettings.edit();
            //save table data as a String
            String tableDataString = "";
            for (int i = 0; i < 15; i++) {
                String weight = weights[i].getText().toString();
                String name = names[i].getText().toString();
                String calorie = calories[i].getText().toString();
                if ((!weight.isEmpty() && !name.isEmpty() && !calorie.isEmpty()) || //if all columns of a row are filled
                     weight.isEmpty() && name.isEmpty() && calorie.isEmpty()) { //if no columns of a row are filled
                    tableDataString = tableDataString + "-" + weight +
                                                        "-" + name +
                                                        "-" + calorie;
                }
                else {
                    Toast.makeText(CalorieRecording.this, "Fill all columns of rows with data", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            tableDataString = tableDataString.substring(1);
            editor.putString(dateTextBoxHolder, tableDataString);
            editor.apply();

            Toast.makeText(CalorieRecording.this, "Table Saved", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(CalorieRecording.this, MainActivity.class));
        }); //end on click listener

        //gets the calendar date of what the user chose and sets the date text box to that date
        DatePickerDialog.OnDateSetListener dataSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            dateTextBox.setText(txtDate.format(calendar.getTime()));
            dateTextBoxHolder = dateTextBox.getText().toString();

            SharedPreferences.Editor editor = userSettings.edit();
            editor.putString("date", dateTextBoxHolder);
            editor.apply();

        }; //end date set listener

        //date on click listener to create a calendar dialog for the user to use
        dateButton.setOnClickListener(v -> new DatePickerDialog(CalorieRecording.this, dataSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()); //end on click listener

        //if the date text box date is changed, then it will provide the table data for that date
        dateTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getTableDateData(userSettings, weights, names, calories);
            }
            @Override
            public void afterTextChanged (Editable s){

            }
        }); //end text change listener

    } //end OnCreate

    //gets the values of each calorie cell, sums them together, then returns the result.
    private String calculateCurrentCalories(TextView[] calories) {
        int currentCalorie = 0;
        for (int i = 0; i < 15; i++) {
            if (!calories[i].getText().toString().equals("")) {
                currentCalorie = currentCalorie + Integer.parseInt(calories[i].getText().toString());
            }
        }
        return String.valueOf(currentCalorie);
    } //end calculateCurrentCalories


    //retrieves table data for a specific date if the data is found in the shared preferences
    private void getTableDateData(SharedPreferences userSettings, TextView[] weights, TextView[] names, TextView[] calories) {
        dateTextBoxHolder = dateTextBox.getText().toString();
        if (dateTextBoxHolder.equals("")) {
            Toast.makeText(CalorieRecording.this, "Select a date before entering data", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("GOTTEN DATE TEXT", dateTextBoxHolder);
        if (userSettings.contains(dateTextBoxHolder)) {
            Log.d("CONTAINS DATE", "YES");
            String[] tableDataArray = userSettings.getString(dateTextBoxHolder, "").split("-");
            Log.d("GOTTEN STRING ARRAY", Arrays.toString(tableDataArray));
            int i = 0;
            int j = 0;
            while (i < tableDataArray.length) {
                weights[j].setText(tableDataArray[i]);
                names[j].setText(tableDataArray[i + 1]);
                calories[j].setText(tableDataArray[i + 2]);
                j = j + 1;
                i = i + 3;
            }
            while (i < 45) {
                weights[j].setText("");
                names[j].setText("");
                calories[j].setText("");
                i = i + 3;
            }
        }
        else {
            int j = 0;
            while (j < 15) {
                weights[j].setText("");
                names[j].setText("");
                calories[j].setText("");
                j = j + 1;
            }
        }

        SharedPreferences.Editor editor = userSettings.edit();
        editor.putString("date", dateTextBoxHolder);
        editor.apply();
    } //end getTableDateData
}