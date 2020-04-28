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
import android.widget.TextView;

import com.example.weatherapp.DTO.dto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Weather extends AppCompatActivity {

    TextView output1, output0;

    private final String appid = "749561a315b14523a8f5f1ef95e45864";
    private final String units= "metric";

    public SpannableStringBuilder TextProperies(String NoColorData, String colorText)
    {
        final SpannableStringBuilder sb = new SpannableStringBuilder(colorText + "\n" + NoColorData);

        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(0, 0, 198));

        sb.setSpan(fcs, 0, colorText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return sb;
    }

    public void sendText(final String name) {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

                Call<dto> call = jsonPlaceholderAPI.getDto(name, units, appid);

                call.enqueue(new Callback<dto>() {
                    @Override
                    public void onResponse(Call<dto> call, Response<dto> response) {

                        if (!response.isSuccessful()) {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result",name);
                            setResult(Activity.RESULT_CANCELED,returnIntent);
                            finish();
                            return;
                        }

                        dto weather = response.body();

                        output0.setText(TextProperies("City name", weather.getName()));

                        output1.setText("\n");
                        output1.append(TextProperies("\tTemperature", "\t" + String.valueOf(weather.getMain().getTemp()) + " *C"));
                        output1.append("\n \n");
                        output1.append(TextProperies(" \tTemperature Max", "\t" +String.valueOf(weather.getMain().getTemp_max())  + " *C"));
                        output1.append("\n \n");
                        output1.append(TextProperies("\tTemperature Min", "\t" +String.valueOf(weather.getMain().getTemp_min())  + " *C"));
                        output1.append("\n \n");
                        output1.append(TextProperies(" \tPressure", "\t" + String.valueOf(weather.getMain().getPressure())  + " hPa"));
                        output1.append("\n \n");
                        output1.append(TextProperies(" \tHumidity", "\t" + String.valueOf(weather.getMain().getHumidity())  + " %"));

                        saveData(name);

                    }

                    @Override
                    public void onFailure(Call<dto> call, Throwable t) {

                        output1.setText(t.getMessage());
                    }
                });
            }
            catch(Exception e)
            {
                output0.setText(e.getMessage());
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Intent intent =getIntent();
        output0 = findViewById(R.id.t0);
        output1 = findViewById(R.id.t1);
        String city_name = intent.getStringExtra("NAME");

        sendText(removePolishSymbol(city_name));
    }

    private String removePolishSymbol(String name)
    {
        //ą, ć, ę, ł, ń, ó, ś, ź, ż.
        name = name.replace('ą', 'a');
        name = name.replace('ć', 'c');
        name = name.replace('ę', 'e');
        name = name.replace('ł', 'l');
        name = name.replace('ń', 'n');
        name = name.replace('ó', 'o');
        name = name.replace('ś', 's');
        name = name.replace('ź', 'z');
        name = name.replace('ż', 'z');

        //uper leeters
        name = name.replace('Ą', 'A');
        name = name.replace('Ć', 'C');
        name = name.replace('Ę', 'E');
        name = name.replace('Ł', 'L');
        name = name.replace('Ń', 'N');
        name = name.replace('Ó', 'O');
        name = name.replace('Ś', 'S');
        name = name.replace('Ź', 'Z');
        name = name.replace('Ż', 'Z');

        return name;
    }

    private void saveData(String input)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME_KEY", input);
        editor.apply();
    }
}


