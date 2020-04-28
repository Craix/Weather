package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    int LAUNCH_SECOND_ACTIVITY = 1;
    EditText input;


    public SpannableStringBuilder TextProperies(String Colors, String noColor)
    {
        final SpannableStringBuilder sb = new SpannableStringBuilder(Colors + " " + noColor);

        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(192, 0, 0));

        sb.setSpan(fcs, 0, Colors.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return sb;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.t);

        loadData();
    }

    public void Weather(View view) {

        String text = input.getText().toString();

        Intent i = new Intent(this, Weather.class);
        i.putExtra("NAME",text);
        startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                String result = data.getStringExtra("result");
                input.setText(TextProperies("Wrong city name:", result));
            }
        }
    }//onActivityResult

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String data = sharedPreferences.getString("NAME_KEY", "City Name");
        input.setText(data);
    }
}