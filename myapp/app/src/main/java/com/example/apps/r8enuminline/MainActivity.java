package com.example.apps.r8enuminline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apps.myapplication.Api;

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
