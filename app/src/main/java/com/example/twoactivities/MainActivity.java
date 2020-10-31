package com.example.twoactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        message = ActivityMainBinding.inflate(getLayoutInflater());
        View view=message.getRoot();
        setContentView(view);
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
}