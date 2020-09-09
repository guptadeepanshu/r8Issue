package com.example.apps.r8enuminline;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apps.mylib.Api;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> {
            String input = ((EditText) findViewById(R.id.input)).getText().toString();
            String responseFromLib = (new Api()).findMessage(input);
            ((TextView) findViewById(R.id.textView)).setText(responseFromLib);
        });
    }
}
