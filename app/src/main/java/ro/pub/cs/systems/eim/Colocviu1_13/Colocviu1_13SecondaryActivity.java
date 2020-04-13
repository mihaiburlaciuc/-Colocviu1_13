package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    Button registerB;
    Button cancelB;
    TextView cmdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        Intent intent = getIntent();
        String cmds = intent.getExtras().getString(Constants.INTENT_COMMAND);

        cmdTextView = findViewById(R.id.commandTV);
        cmdTextView.setText(cmds);

        registerB = findViewById(R.id.registerB);
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(Constants.REGISTER_CODE);
            }
        });
        cancelB = findViewById(R.id.cancelB);
        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(Constants.CANCEL_CODE);
            }
        });
    }

    void buttonOnClick(int returnCode) {
        setResult(returnCode, null);
        finish();
    }
}
