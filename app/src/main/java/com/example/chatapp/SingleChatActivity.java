package com.example.chatapp;

import static com.example.chatapp.LoginActivity.getUserEmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SingleChatActivity extends AppCompatActivity {

    Intent intent;
    TextView userName, userState;
    EditText getMessage;
    ImageButton sendMessageButton, backButton;
    androidx.appcompat.widget.Toolbar chatToolbar;
    CardView sendMessageCardView;
    RecyclerView msgRecycleView;

    String enteredMessage;
    Database skyChatDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        userName = findViewById(R.id.Nameofspecificuser);
        userState = findViewById(R.id.userState);
        getMessage = findViewById(R.id.getmessage);
        sendMessageButton = findViewById(R.id.imageviewsendmessage);
        backButton = findViewById(R.id.backbuttonofspecificchat);
        chatToolbar = findViewById(R.id.toolbarofspecificchat);
        sendMessageCardView = findViewById(R.id.carviewofsendmessage);
        msgRecycleView = findViewById(R.id.recyclerviewofspecific);
        skyChatDB= new Database(this);

        intent = getIntent();
        String receiverName = intent.getStringExtra("ReceiverName");

        //To store in SQLite
        Update(receiverName);

        userName.setText(receiverName);
        userState.setText("Not Connected");

        setSupportActionBar(chatToolbar);
        chatToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredMessage = getMessage.getText().toString();
                if(enteredMessage.isEmpty()){

                }
                else{

                }
            }
        });

    }
    private void Update(String FRIEND){

        String USER= getUserEmail();

        Boolean insert = skyChatDB.insertChat(USER,FRIEND);









    }

}