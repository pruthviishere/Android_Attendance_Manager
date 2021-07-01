package com.swarajya.schoolAttendance.ext;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.swarajya.schoolAttendance.R;
import com.swarajya.schoolAttendance.activities.MainActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Attendance extends AppCompatActivity {

    private ArrayList<Student> students= new ArrayList<>();
    private ListView lv;
    private StudentsAttendanceList list;
    private String courseKey;
    private String courseName;
    private String class_selected;

    // Set up  .menu the
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Listener for clicks.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // If the back button is pressed.
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        // Create new intent.
        Intent login = new Intent(Attendance.this, MainActivity.class);
        startActivity(login);
        // Finish currentActivity.
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_attendance);
        // Set the activity title.
        setTitle("Attendance");
        // ListView Reference.

        lv = (ListView) findViewById(R.id.lvStudents);

        // Grab the course name from intent.
        Intent intent = getIntent();
        // Extract the name.
        class_selected = intent.getStringExtra("courseName");



                                                                                                  // Grab data from database.
        // Database Reference.
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference dbRef = database.getReference("courses");


        // Add Listener to fill the data.
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {

//                if (dataSnapshot.exists()) {
//                    Boolean found = false;
//                    for (DataSnapshot courseSnapshot: dataSnapshot.getChildren()) {
                        // Grab the course name.
//                        String tempCourseName = courseSnapshot.child("name").getValue().toString();
                        // Grab the course key.
//                        if (tempCourseName.equals(courseName)) {
//                            for (DataSnapshot subStd: courseSnapshot.child("students").getChildren()) {
//                                String key = subStd.child("studentNo").getValue().toString();
//        for (int i =0;i<10;i++) {
//            studentKeys.add(String.valueOf(i));
//        }
//                            }
//                            found = true;
//                        }
                        // Break when key is found.
//                        if (found) break;
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });


        // Finally grab the students.
        // Build Query for the table.


//        final Query stdQuery = database.getReference("students");
//
//        stdQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot courseSnapshot: dataSnapshot.getChildren()) {
                    // Grab the key.
//                    String stdKey = courseSnapshot.getKey();
                    // Match the key.
//                    if (studentKeys.contains(stdKey)) {
                        // Grab the student.
//                        Student std = courseSnapshot.getValue(Student.class);

//        Student std000 = new Student ("name0 ","email","Attendance","P");
//        Student std00 = new Student("name1 ","email","Attendance","P");
//        Student std0 = new Student ("name2 ","email","Attendance","P");
//        Student std1 = new Student ("name3 ","email","Attendance","P");
//        Student std2 = new Student ("name 4","email","Attendance","P");
//        Student std3 = new Student ("name5 ","email","Attendance","P");
//        Student std4 = new Student ("name6 ","email","Attendance","P");
//        Student std5 = new Student ("name7 ","email","Attendance","P");
//         Add to the list.
//                        students.add(std);
//        students.add(std000);
//        students.add(std00);
//        students.add(std0);
//        students.add(std1);
//        students.add(std2);
//        students.add(std3);
//        students.add(std4);
//        students.add(std5);
                // Creating object of custom view item.
//                list = new com.swarajya.schoolAttendance.ext.StudentsAttendanceList(Attendance.this, students);
//                lv.setAdapter(list);
//            }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//
//            }
//        });


        // Add Listener to search the data.
        // Grab the edit text view.
        final EditText editTxt = (EditText)findViewById(R.id.etSearch);
        editTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Grab radio button to identify search type.
                RadioGroup rg = (RadioGroup)findViewById(R.id.rgSearch);
                // Grab the value of radio button.
                String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();
                // Get the Search Query.
                String text = editTxt.getText().toString().toLowerCase(Locale.getDefault());

                // Check the search type.
                if (value.equals("Name")) {
                    list.filterByName(text);
                } else {
                    list.filterByRollNo(text);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Marks all the student present in current view.
     *
      * @param view Current View
     */
    public void markAllPresent(View view) {
        // Iterate over each view and mark the attendance.
        for (int i = 0; i < lv.getCount(); i++) {
            // Grab the child.
            View v = lv.getChildAt(i);
            // Grab radio group.
            RadioGroup rg = v.findViewById(R.id.rgAttendance);
            // Grab the present button.
            RadioButton rb = rg.findViewById(R.id.rbPresent);
            // Set it true.
            rb.setChecked(true);
        }
    }

    /**
     * Marks all the student absent in current view.
     *
     * @param view Current View
     */
    public void markAllAbsent(View view) {
        // Iterate over each view and mark the attendance.
        for (int i = 0; i < lv.getCount(); i++) {
            // Grab the child.
            View v = lv.getChildAt(i);
            // Grab radio group.
            RadioGroup rg = v.findViewById(R.id.rgAttendance);
            // Grab the present button.
            RadioButton rb = rg.findViewById(R.id.rbAbsent);
            // Set it true.
            rb.setChecked(true);
        }
    }

    /**
     * Saves the current Attendance.
     *
     * @param view Current View.
     */
    public void saveAttendance(View view) {
        list.saveAttendance(courseName);
    }
}
