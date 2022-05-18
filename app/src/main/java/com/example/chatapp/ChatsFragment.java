package com.example.chatapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter chatAdapter;
    private LinearLayoutManager  layoutManager;

    ArrayList<String> names = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_chats,container,false);
        recyclerView=v.findViewById(R.id.singleChatRecyclerView);



        names.clear();

        /* Put chat names taken from the database instead of this part */
        names.add("Shehan"); names.add("Madhusanka"); names.add("Kasun"); names.add("Nimal Peris Iphone");


        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatRecycleViewAdapter(names, getContext());
        recyclerView.setAdapter(chatAdapter);

        return v;
    }




}