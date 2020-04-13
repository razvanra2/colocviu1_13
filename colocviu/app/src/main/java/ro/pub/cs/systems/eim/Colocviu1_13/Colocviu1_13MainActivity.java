package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    Button south, east, west, north;
    TextView text;
    int buttonClicks;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
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
            Log.d("incremented_tag", "buttonClicks is now: " + buttonClicks);
            text.setText(textStr);
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
}
