package com.example.rkrit.test.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rkrit.test.R;
import com.example.rkrit.test.fordb.TestDBHelper;
import com.example.rkrit.test.fragment.BlankFragment;
import com.example.rkrit.test.fragment.DBFragment;

import static java.security.AccessController.getContext;

public class MainActivity extends FragmentActivity implements BlankFragment.OnFragmentInteractionListener,DBFragment.OnFragmentInteractionListener {

    public static final String EXTRA_MESSAGE = "com.example.app.MESSAGE";
    private int i;
    private Button button;
    private Button clicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        button = (Button) findViewById(R.id.button);
        clicker = (Button) findViewById(R.id.clicker);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }


            BlankFragment fragment = new BlankFragment();
            Bundle args = new Bundle();
            args.putString(EXTRA_MESSAGE, ((EditText) findViewById(R.id.editText)).getText().toString() );
            fragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        i = getPreferences(Context.MODE_PRIVATE).getInt("i",0);
        setListeners();

    }

    private void setListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
        clicker.setText("" + i);
        clicker.setOnClickListener((v)->
        {
            ++i;
            clicker.setText("" + i);
        });
    }

    private void sendMessage(View v) {
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra(EXTRA_MESSAGE, ((EditText) findViewById(R.id.editText)).getText().toString());
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction() {
        ((TextView)findViewById(R.id.textView2)).setText(((EditText) findViewById(R.id.editText)).getText().toString());
    }

    @Override
    public void onChangeFragment() {
        DBFragment dbFragment = new DBFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, dbFragment);
        transaction.addToBackStack("Just name of tr");

        transaction.commit();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("i",i);
        editor.apply();
    }
}
