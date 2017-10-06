package com.example.rkrit.test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rkrit.test.R;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Intent intent = this.getIntent();
        ((TextView) findViewById(R.id.textView)).setText(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
    }

}
