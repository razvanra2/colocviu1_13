package ro.pub.cs.systems.eim.Colocviu1_13;

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

    Button south, east, west, north;
    Button button;
    TextView text;
    int buttonClicks;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private NavListener navListener = new NavListener();

    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("action", intent.getStringExtra("extra"));
        }
    }



    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String textStr = text.getText().toString();

            if (textStr != "")
            {
                textStr += ",";
            }

            switch(view.getId()) {
                case R.id.eastButton:
                    textStr += "east";
                    break;
                case R.id.westButton:
                    textStr += "west";
                    break;
                case R.id.northButton:
                    textStr += "north";
                    break;
                case R.id.southButton:
                    textStr += "south";
                    break;
            }
            buttonClicks += 1;

            if (buttonClicks >= 4) {
                Intent intent = new Intent(getApplicationContext(), Colocviu13_Service.class);
                intent.putExtra("extra", textStr);
                getApplicationContext().startService(intent);
            }

            Log.d("incremented_tag", "buttonClicks is now: " + buttonClicks);
            text.setText(textStr);
        }
    }

    private class NavListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                    intent.putExtra("str", text.getText().toString());
                    startActivityForResult(intent, 3);
                    buttonClicks = 0;
                    text.setText("");
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 3) {
            String s = "";
            switch (resultCode) {
                case 0:
                    s = "Cancel";
                    break;
                case 1:
                    s = "Register";
            }
            Toast.makeText(this, "toast message: " + s, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        south = findViewById(R.id.southButton);
        east = findViewById(R.id.eastButton);
        west = findViewById(R.id.westButton);
        north = findViewById(R.id.northButton);

        text = findViewById(R.id.textView);
        text.setText("");

        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);
        north.setOnClickListener(buttonClickListener);


        button = findViewById(R.id.button);
        button.setOnClickListener(navListener);

        intentFilter.addAction("action");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("clicks", buttonClicks);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("clicks")) {
            buttonClicks = savedInstanceState.getInt("clicks");
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu13_Service.class);
        stopService(intent);
        super.onDestroy();
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
}
