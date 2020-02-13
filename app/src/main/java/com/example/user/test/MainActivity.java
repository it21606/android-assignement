package com.example.user.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbHelper = new DBHelper(this);

        Button insertButton = findViewById(R.id.saveButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fnameET = findViewById(R.id.fnameInput);
                EditText lnameET = findViewById(R.id.lnameInput);
                EditText ageET = findViewById(R.id.ageInput);
                String fname = fnameET.getText().toString();
                String lname = lnameET.getText().toString();
                String age = ageET.getText().toString();
                Long tsLong = System.currentTimeMillis() / 1000;
                String ts = tsLong.toString();
                DataContract dataContract = new DataContract(fname, lname, age, ts);
                try {
                    if (fname.length() > 0 && lname.length() > 0 && age.length() > 0) {
                        dbHelper.insert(dataContract);
                        Context context = getApplicationContext();

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, "Data saved successfully", duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, "Something is missing. Please fill all the inputs", duration);
                        toast.show();
                    }
                } catch (Exception e) {
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Error saving data", duration);
                    toast.show();
                }


            }
        });
    }

    public void gotoNext(View view) {
        EditText searchET = (EditText) findViewById(R.id.searchInput);
        String searchFname = searchET.getText().toString();
        Intent intent = new Intent(this, Second.class);
        intent.putExtra("SEARCH_FNAME", searchFname);
        startActivity(intent);
    }
}