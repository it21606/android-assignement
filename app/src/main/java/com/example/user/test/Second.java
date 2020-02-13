package com.example.user.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String searchTerm;
        if (savedInstanceState == null) {
            Bundle extraItems = getIntent().getExtras();
            searchTerm = extraItems.getString("SEARCH_FNAME");
        } else {
            searchTerm = new String();
        }


        final DBHelper dbHelper = new DBHelper(this);
        ArrayList<Result> results = dbHelper.getSelected(searchTerm);
        TableLayout table = findViewById(R.id.table);
        if (results != null && results.size() > 0) {
            for (Result result : results) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                TextView textFname = new TextView(this);
                textFname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                textFname.setPadding(30, 0, 80, 0);
                textFname.setGravity(11);
                textFname.setTextColor(Color.WHITE);
                textFname.setText(result.getFname());

                TextView textLname = new TextView(this);
                textLname.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                textLname.setPadding(20, 0, 80, 0);
                textLname.setGravity(11);
                textLname.setTextColor(Color.WHITE);
                textLname.setText(result.getLname());


                TextView textAge = new TextView(this);
                textAge.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                textAge.setPadding(100, 0, 0, 0);
                textAge.setGravity(11);
                textAge.setTextColor(Color.WHITE);
                textAge.setText(result.getAge());


                row.setBackgroundResource(R.drawable.border);
                row.addView(textFname);
                row.addView(textLname);
                row.addView(textAge);
                table.addView(row);
            }
        } else {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            row.setBackgroundColor(Color.rgb(255, 0, 0));
            TextView noResults = new TextView(this);
            noResults.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            noResults.setPadding(30, 0, 80, 0);
            noResults.setGravity(11);
            noResults.setTextColor(Color.WHITE);
            noResults.setText("No results found");
            row.setBackgroundResource(R.drawable.border_error);
            row.addView(noResults);
            table.addView(row);
        }
    }

}
