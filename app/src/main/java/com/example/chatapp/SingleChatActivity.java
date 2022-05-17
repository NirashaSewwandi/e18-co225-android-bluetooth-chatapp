package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SingleChatActivity extends AppCompatActivity {

    public static TextView userState;

    Intent intent;
    Button listenButton, connectButton;
    TextView userName;
    EditText getMessage;
    ImageButton sendMessageButton, backButton;
    androidx.appcompat.widget.Toolbar chatToolbar;
    CardView sendMessageCardView;
    RecyclerView msgRecycleView;
    BluetoothDevice device;

    String enteredMessage;

    ChatUtils chatUtils;


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
        listenButton = findViewById(R.id.btn_listen);
        connectButton = findViewById(R.id.btn_connect);

        chatUtils = new ChatUtils();

        intent = getIntent();
        String receiverName = intent.getStringExtra("ReceiverName");

        userName.setText(receiverName);
        userState.setText(chatUtils.status);

         String receiverIndex = intent.getStringExtra("deviceIndex");
         String deviceType = intent.getStringExtra("deviceType");

         if(deviceType.equals("Paired")){
             device = MainActivity.myBluetooth.getPairedDevice(Integer.parseInt(receiverIndex));
         }else{
             device = MainActivity.myBluetooth.getAvailableDevice(Integer.parseInt(receiverIndex));
         }

        setSupportActionBar(chatToolbar);
        chatToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*Put here what happened when touch the tool bar*/
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread listen = new Thread(chatUtils.getServerClassInstance());
                listen.start();

            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread client = new Thread(chatUtils.getClientClassInstance(device));
                client.start();
                userState.setText("Connecting...");
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredMessage = getMessage.getText().toString();
                if(enteredMessage.isEmpty()){
                    Toast.makeText(SingleChatActivity.this, "Enter a message", Toast.LENGTH_SHORT).show();
                }
                else if(chatUtils.CURRENT_STATE == 3 || chatUtils.CURRENT_STATE == 5){
                    String send_msg = String.valueOf(getMessage.getText());
                    chatUtils.Write(send_msg);
                    getMessage.setText("");
                    getMessage.setHint("Type a message");
                }
                else{
                    Toast.makeText(SingleChatActivity.this, "Connection not established", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}