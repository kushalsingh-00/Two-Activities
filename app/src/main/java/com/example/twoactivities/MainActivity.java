package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    private ActivityMainBinding message;
    public static final int TEXT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");

        message = ActivityMainBinding.inflate(getLayoutInflater());
        View view=message.getRoot();
        setContentView(view);

        if(savedInstanceState!=null)
        {
            if(savedInstanceState.getBoolean("reply_visible")) {
                message.textHeaderReply.setVisibility(View.VISIBLE);
                message.textMessageReply.setText(savedInstanceState.getString("reply_text"));
                message.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(message.textHeaderReply.getVisibility()==View.VISIBLE)
        {
            outState.putBoolean("reply_visible",true);
            outState.putString("reply_text", message.textMessageReply.getText().toString());
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent=new Intent(this,SecondActivity.class);
        String mTextMessage=message.editTextMain.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,mTextMessage);
        startActivityForResult(intent,TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TEXT_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                String reply=data.getStringExtra(SecondActivity.EXTRA_REPLY);
                message.textHeaderReply.setVisibility(View.VISIBLE);
                message.textMessageReply.setText(reply);
                message.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}