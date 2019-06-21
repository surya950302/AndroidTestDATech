package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.Api;
import com.datechnologies.androidtest.api.ChatLogMessageModel;
import com.datechnologies.androidtest.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Screen that displays a list of chats from a chat log.
 */
public class ChatActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    Api api;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, ChatActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle(R.string.chat_button);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ActionBar actionBar = getSupportActionBar();
        api = RetrofitClient.getInstance().create(Api.class);

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        chatAdapter = new ChatAdapter();

        recyclerView.setAdapter(chatAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        List<ChatLogMessageModel.Datum> tempList = new ArrayList<>();

        final ChatLogMessageModel.Datum chatLogMessageModel = new ChatLogMessageModel.Datum();
        /*chatLogMessageModel.message = "This is test data. Please retrieve real data.";

        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);
        tempList.add(chatLogMessageModel);*/

        chatAdapter.setChatLogMessageModelList(tempList);

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // TODO: Retrieve the chat data from http://dev.datechnologies.co/Tests/scripts/chat_log.php
        // TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.

        Call<ChatLogMessageModel> call = api.getData();
        call.enqueue(new Callback<ChatLogMessageModel>() {
            @Override
            public void onResponse(Call<ChatLogMessageModel> call, Response<ChatLogMessageModel> response) {
                ChatLogMessageModel chatLogMessageModel1 = response.body();
                List<ChatLogMessageModel.Datum> datumList = chatLogMessageModel1.data;
                recyclerView.setAdapter(new ChatAdapter(ChatActivity.this, datumList));

                /* Used to debug data
                for (ChatLogMessageModel.Datum datum : datumList) {

                    System.out.println("id : " + datum.userId + " name: " + datum.name + " avatar URL: " + datum.avatarUrl + " msg: " + datum.message);
                    Toast.makeText(getApplicationContext(), "id : " + datum.userId + " name: " + datum.name + " avatar URL: " + datum.avatarUrl + " msg: " + datum.message, Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<ChatLogMessageModel> call, Throwable t) {
                System.out.println("Error message: "+t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
