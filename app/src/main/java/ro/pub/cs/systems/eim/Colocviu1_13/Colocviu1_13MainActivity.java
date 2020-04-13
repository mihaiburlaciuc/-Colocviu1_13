package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    Button northB;
    Button southB;
    Button eastB;
    Button westB;
    Button navB;
    TextView coordsTextView;

    Integer buttonCount = 0;

    boolean serviceStarted = false;

    private IntentFilter intentFilter = new IntentFilter();

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

        intentFilter.addAction(Constants.BROADCAST_ACTION);
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

        // start service
        if (buttonCount >= 4) {
            Log.d(Constants.TAG, "Intent to start service");
            Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
            intent.putExtra(Constants.SERVICE_COMMAND_INTENT, coords);

            getApplicationContext().startService(intent);
            serviceStarted = true;
        }
    }

    private  void navButtonOnClick() {
        Intent intent = new Intent(this, Colocviu1_13SecondaryActivity.class);
        intent.putExtra(Constants.INTENT_COMMAND, coordsTextView.getText().toString());

        // reinit
        coordsTextView.setText("");
        buttonCount = 0;

        startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
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


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Constants.REGISTER_CODE) {
                Toast.makeText(this, "REGISTER " + resultCode, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "CANCEL " + resultCode, Toast.LENGTH_LONG).show();
            }
        }
    }
}
