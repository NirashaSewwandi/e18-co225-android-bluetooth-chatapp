package com.example.chatapp;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

public class ChatUtils {

    private BluetoothAdapter bluetoothAdapter;
    public Context context;
    public String status = "Not Connected";

    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING = 2;
    static final int STATE_CONNECTED = 3;
    static final int STATE_CONNECTION_FAILED = 4;
    static final int STATE_MESSAGE_RECEIVED = 5;


    private static final String APP_NAME = "SkyChat";
    private static final UUID MY_UUID = UUID.fromString("38fe6632-9620-4bd9-96c6-bc1f100827c3");

    public ChatUtils(){
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case STATE_LISTENING:
                    status = "Listening...";
                    break;
                case STATE_CONNECTING:
                    status = "Connecting...";
                    break;
                case STATE_CONNECTED:
                    status = "Connected";
                    break;
                case STATE_CONNECTION_FAILED:
                    status = "Connection Failed";
                    break;
                case STATE_MESSAGE_RECEIVED:
                    /*Do later*/
                    break;
            }
            SingleChatActivity.userState.setText(status);

            return true;
        }
    });


    public ServerClass getServerClassInstance(){

        return new ServerClass();
    }

    public ClientClass getClientClassInstance(BluetoothDevice device){
        return new ClientClass(device);
    }


     private class ServerClass extends Thread{

        private BluetoothServerSocket serverSocket;

        @SuppressLint("MissingPermission")
        public ServerClass(){
            try {
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME, MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run(){
            BluetoothSocket socket = null;
            while(socket==null){
                try {
                    Message message = Message.obtain();
                    message.what = STATE_CONNECTING;
                    handler.sendMessage(message);

                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();

                    Message message = Message.obtain();
                    message.what = STATE_CONNECTION_FAILED;
                    handler.sendMessage(message);
                }
                if(socket!=null){
                    Message message = Message.obtain();
                    message.what = STATE_CONNECTED;
                    handler.sendMessage(message);

                    /* Some code in here for send/receive */

                    break;
                }
            }
        }


    }

    private class ClientClass extends Thread{

        private BluetoothDevice device;
        private BluetoothSocket socket;

        @SuppressLint("MissingPermission")
        public ClientClass(BluetoothDevice device){
            this.device = device;
            try {
                this.socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @SuppressLint("MissingPermission")
        public void run(){
            try {
                socket.connect();
                Message message = Message.obtain();
                message.what = STATE_CONNECTED;
                handler.sendMessage(message);

            } catch (IOException e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what = STATE_CONNECTION_FAILED;
                handler.sendMessage(message);
            }
        }

    }

















}
