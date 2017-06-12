package com.sdaacademy.zientara.rafal.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText messageEditText;
    private ImageView sendImage;
    private LinearLayout messagesLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_chat);

        messageEditText = (EditText) findViewById(R.id.chat_messageEditText);
        sendImage = (ImageView) findViewById(R.id.chat_sendImage);
        messagesLinearLayout = (LinearLayout) findViewById(R.id.chat_messagesLinearLayout);

        sendImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendMessage(v);
    }

    public void sendMessage(View v) {
        String message = messageEditText.getText().toString();
        messageEditText.setText(null);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


        LayoutInflater inflater = getLayoutInflater();

        inflater.inflate(R.layout.my_message_item, messagesLinearLayout, true);
        View messageView = messagesLinearLayout.getChildAt(messagesLinearLayout.getChildCount() - 1);
        if(messageView instanceof TextView)
            ((TextView) messageView).setText(message);

        TextView textView = (TextView) inflater.inflate(R.layout.my_message_item, messagesLinearLayout, false);
        textView.setText(message);
        messagesLinearLayout.addView(textView);
    }
}
