package com.example.gpa_calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Declaring the buttons and text fields used in the UI
    Button computeGpaButton; // Initializing the "Calculate" button
    EditText courseOne, courseTwo, courseThree, courseFour, courseFive; // Initializing the EditTexts for course grade and credits
    TextView text; // Initializing the TextView for GPA
    int count = 0; // A counter to keep track of the button clicks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the UI elements
        courseOne = (EditText) findViewById(R.id.courseinput1);
        courseTwo = (EditText) findViewById(R.id.courseinput2);
        courseThree = (EditText) findViewById(R.id.courseinput3);
        courseFour = (EditText) findViewById(R.id.courseinput4);
        courseFive = (EditText) findViewById(R.id.courseinput5);

        text = (TextView) findViewById(R.id.totalgpa);
        text.setBackgroundColor(Color.WHITE);

        // Adding a click listener to the button
        computeGpaButton = (Button) findViewById(R.id.computegpabutton);

        computeGpaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Checking if the count is even or odd
                if (count % 2 == 0) {
                    if (courseOne.getText().toString().equals("") || courseTwo.getText().toString().equals("") || courseThree.getText().toString().equals("") || courseFour.getText().toString().equals("") || courseFive.getText().toString().equals("")) {
                        showAlert("GRADES MISSING!", "Please enter grades in all fields.");
                    } else {
                        float grade1, grade2, grade3, grade4, grade5;
                        try {
                            // Try parsing the input strings as floats
                            grade1 = Float.parseFloat(courseOne.getText().toString());
                            grade2 = Float.parseFloat(courseTwo.getText().toString());
                            grade3 = Float.parseFloat(courseThree.getText().toString());
                            grade4 = Float.parseFloat(courseFour.getText().toString());
                            grade5 = Float.parseFloat(courseFive.getText().toString());
                        } catch (NumberFormatException e) {
                            // If parsing fails, show an error message
                            showAlert("INVALID GRADE ENTERED!", "Please enter a valid grade.");
                            return;
                        }
                        // Check if any of the grades are out of range (0-100)
                        if (grade1 < 0 || grade1 > 100 || grade2 < 0 || grade2 > 100 || grade3 < 0 || grade3 > 100 ||
                                grade4 < 0 || grade4 > 100 || grade5 < 0 || grade5 > 100) {
                            // Show an alert if any grade is out of range
                            showAlert("GRADE OUT OF RANGE (0-100)!", "Please enter a grade within the range of 0 to 100.");
                        } else {
                            // Calculate the average of the five grades
                            float avg = grade1 + grade2 + grade3 + grade4 + grade5;
                            avg = avg / 5;
                            // Check the average and set the text and background color accordingly
                            if (avg < 60) {
                                text.setText(Float.toString(avg));
                                text.setBackgroundColor(Color.RED);
                            } else if (avg > 60 && avg < 80) {
                                text.setText(Float.toString(avg));
                                text.setBackgroundColor(Color.YELLOW);
                            } else if (avg >= 80 && avg <= 100) {
                                text.setText(Float.toString(avg));
                                text.setBackgroundColor(Color.GREEN);
                            }
                            // Increment the count and update the text on the computeGpaButton
                            count++;
                            computeGpaButton.setText("Clear");
                        }
                    }

                } else {
                    // Clear the input fields for the courses and grade
                    courseOne.setText("");
                    courseTwo.setText("");
                    courseThree.setText("");
                    courseFour.setText("");
                    courseFive.setText("");
                    text.setText(""); // Reset the display text
                    computeGpaButton.setText("Compute GPA");
                    text.setBackgroundColor(Color.WHITE); // Reset the background color
                    count++;
                }
            }

            private void showAlert(String title, String message) {
                // Create an instance of the AlertDialog.Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Set the title of the alert dialog
                builder.setTitle(title);
                // Set the message of the alert dialog
                builder.setMessage(message);
                // Add a positive button to the alert dialog with the text "OK"
                builder.setPositiveButton("OK", null);
                // Show the alert dialog
                builder.show();
            }
        });
    }
}