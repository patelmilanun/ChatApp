package com.hexazexa.chatapp;

import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.Timer;


public class MainActivity extends ActionBarActivity {


    private Toolbar toolbar;
    ListView lvadd;
    EditText etadd;
    Button badd;
    Firebase mRef;
    ArrayList<String> mMessage = new ArrayList<>();
    String status = "Last Sseen", temp;
    Timer timer;
    private int mPreviousLength;
    private boolean mBackSpace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setSubtitle(status);
        setSupportActionBar(toolbar);


        lvadd = (ListView) findViewById(R.id.lvadd);
        etadd = (EditText) findViewById(R.id.etadd);
        badd = (Button) findViewById(R.id.badd);

        mRef = new Firebase("https://chat-app-cc719.firebaseio.com/");


    }

    @Override
    protected void onStart() {
        super.onStart();


        final Firebase addRef = mRef.child("addlist");

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomNm = etadd.getText().toString();
                etadd.setText("");
                addRef.push().setValue(roomNm);
            }
        });
        FirebaseListAdapter<String> adapter =
                new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, addRef) {
                    @Override
                    protected void populateView(View view, String s, int i) {
                        TextView textview = (TextView) view.findViewById(android.R.id.text1);
                        textview.setText(s);
                    }
                };
        lvadd.setAdapter(adapter);

        etadd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
