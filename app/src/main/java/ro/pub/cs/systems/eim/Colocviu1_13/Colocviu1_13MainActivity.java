package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    Button northB;
    Button southB;
    Button eastB;
    Button westB;
    Button navB;
    TextView coordsTextView;

    Integer buttonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        northB = findViewById(R.id.northB);
        northB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordButtonOnClick("North");
            }
        });
        eastB = findViewById(R.id.eastB);
        eastB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordButtonOnClick("East");
            }
        });
        westB = findViewById(R.id.westB);
        westB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordButtonOnClick("West");
            }
        });
        southB = findViewById(R.id.southB);
        southB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordButtonOnClick("South");
            }
        });
        navB = findViewById(R.id.navB);
        navB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navButtonOnClick();
            }
        });
        coordsTextView = findViewById(R.id.coordsTextView);



    }

    private  void coordButtonOnClick(String buttonName) {

        String coords = coordsTextView.getText().toString();
        buttonCount++;
        Log.d(Constants.TAG, "Buttons pressed " + buttonCount);

        if (coords.equals("")) {
            coords += buttonName;
        } else {
            coords += "," + buttonName;
        }

        coordsTextView.setText(coords);
    }

    private  void navButtonOnClick() {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(Constants.B_COUNT, buttonCount);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.B_COUNT)) {
            buttonCount = savedInstanceState.getInt(Constants.B_COUNT);
        }
    }

}
