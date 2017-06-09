package com.sdaacademy.zientara.rafal.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private EditText messageEditText;
    private ImageView sendImage;
    private LinearLayout messagesLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_chat);

        messageEditText = (EditText)findViewById(R.id.chat_messageEditText);
        sendImage = (ImageView)findViewById(R.id.chat_sendImage);
        messagesLinearLayout = (LinearLayout)findViewById(R.id.chat_messagesLinearLayout);

        Toast.makeText(this, "Wpisujemy tekst" ,Toast.LENGTH_LONG).show();

    }
}
