package com.example.timezoneconversionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    // Array of city names
    String[] cityTitles = {"Sydney", "Cairo", "Venice", "Los Angeles", "Tokyo"};

    // Array of timezone IDs
    String[] cities = {"Australia/NSW", "Egypt", "Europe/Venice", "America/Los_Angeles", "Japan"};

    // Array of image IDs
    int[] cityImages= {R.drawable.sydney_img,R.drawable.egypt_img, R.drawable.venice_img, R.drawable.los_angeles_img, R.drawable.japan_img};

    private String[] id;
    private TextView[] textViews = new TextView[5];
    private ConstraintLayout[] constraint = new ConstraintLayout[5];
    private int temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = new String[]{"sydney", "egypt", "venice", "los_angeles", "japan"};


        // Shows the initial city name, time and image when app opens
        for(int i=0; i<id.length; i++) {
            // Get the id string within the Resources instance
            temp = getResources().getIdentifier(id[i], "id", getPackageName());

            constraint[i] = findViewById(temp);

            // Finding the parent (ConstraintLayout) id
            ConstraintLayout cl = constraint[i];

            // Finding the id of the textView that will display city name
            TextView tv = cl.findViewById(R.id.textViewCityName);

            // Display city name when app opens
            tv.setText(cityTitles[i]);

            // Finding the id of the textView that will output the time
            TextView innerTv = cl.findViewById(R.id.textViewCityTime);
            textViews[i] = innerTv;

            // Display 12hr city time format when app opens
            innerTv.setText(getTime(cities[i], "hh:mm a"));

            // Display city image when app opens
            ImageView iv = cl.findViewById(R.id.imageView);
            iv.setImageResource(cityImages[i]);
        }

        //Assign button variables to the button ids
        Button button12hr = findViewById(R.id.btn12hr);
        Button button24hr = findViewById(R.id.btn24hr);

        //Calling the time conversion onClickListeners
        button12hr.setOnClickListener(convertTo12hr);
        button24hr.setOnClickListener(convertTo24hr);


    }

    // 12hr conversion
    View.OnClickListener convertTo12hr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setText(getTime(cities[i], "hh:mm:ss a"));
            }
        }
    };

    //24hr conversion
    View.OnClickListener convertTo24hr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < textViews.length; i++) {
                textViews[i].setText(getTime(cities[i], "HH:mm:ss"));
            }
        }
    };

    // Method to get the time of the specified timezone
    private String getTime(String city, String timeFormat){
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        Date date = new Date();

        TimeZone timeZone = TimeZone.getTimeZone(city);
        dateFormat.setTimeZone(timeZone);
        String currentTime = dateFormat.format(date);
        return currentTime;

    }
}
