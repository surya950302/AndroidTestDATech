package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.ChatLogMessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A recycler view adapter used to display chat log messages in {@link ChatActivity}.

 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>
{
    //==============================================================================================
    // Class Properties
    //==============================================================================================
    Context context;
    private List<ChatLogMessageModel.Datum> chatLogMessageModelList;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ChatAdapter()
    {
        chatLogMessageModelList = new ArrayList<>();
    }

    public ChatAdapter(Context context, List<ChatLogMessageModel.Datum> datum){
        this.context = context;
        this. chatLogMessageModelList = datum;
    }

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================

    public void setChatLogMessageModelList(List<ChatLogMessageModel.Datum> chatLogMessageModelList)
    {
        this.chatLogMessageModelList = chatLogMessageModelList;
        notifyDataSetChanged();
    }

    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_chat, parent, false);

        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder viewHolder, int position)
    {
        ChatLogMessageModel.Datum chatLogMessageModel = chatLogMessageModelList.get(position);

        viewHolder.messageTextView.setText(chatLogMessageModel.message);
        viewHolder.nameTextView.setText(chatLogMessageModel.name);
        Glide.with(context)
                .load(chatLogMessageModel.avatarUrl)
                .transform(new CircleCrop())
                .into(viewHolder.avatarImageView);
    }

    @Override
    public int getItemCount()
    {
        return chatLogMessageModelList.size();
    }

    //==============================================================================================
    // ChatViewHolder Class
    //==============================================================================================

    public static class ChatViewHolder extends RecyclerView.ViewHolder
    {
        ImageView avatarImageView;
        TextView messageTextView;
        TextView nameTextView;


        public ChatViewHolder(View view)
        {
            super(view);
            avatarImageView = (ImageView)view.findViewById(R.id.avatarImageView);
            messageTextView = (TextView)view.findViewById(R.id.messageTextView);
            nameTextView = (TextView) view.findViewById(R.id.name);
        }

    }

}
