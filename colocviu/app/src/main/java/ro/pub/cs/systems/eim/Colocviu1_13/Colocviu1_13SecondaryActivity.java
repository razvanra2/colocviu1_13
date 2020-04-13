package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    private TextView textView;
    private Button cancel, register;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cancelButton:
                    setResult(0, null);
                    break;
                case R.id.registerButton:
                    setResult(1, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        cancel = (Button)findViewById(R.id.cancelButton);
        register = (Button)findViewById(R.id.registerButton);

        textView = (TextView)findViewById(R.id.secondaryText);

        cancel.setOnClickListener(buttonClickListener);
        register.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        String str = "";
        if (intent != null && intent.getExtras().containsKey("str")) {
            str = intent.getExtras().get("str").toString();
        }
        textView.setText(str);
        Log.d("test", str);
    }
}
