package com.example.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.ActivityOneEvent;
import com.example.android.R;
import com.example.android.enity.EventClass;
import com.example.android.enity.NoticeClass;
import com.example.android.utils.MyApplication;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ArrayList<EventClass> eventList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView EventTitle;
        TextView EventContent;
        TextView EventStartTime;
        TextView EventEndTime;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            EventTitle = (TextView) itemView.findViewById(R.id.item_event_title);
            EventContent = (TextView) itemView.findViewById(R.id.item_event_content);
            EventStartTime = (TextView) itemView.findViewById(R.id.item_event_start_time);
            EventEndTime = (TextView) itemView.findViewById(R.id.item_event_end_time);


        }
    }

    public EventAdapter(){

    }

    public EventAdapter(ArrayList<EventClass> eventClasses) {
        eventList = eventClasses;
    }


    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        EventAdapter.ViewHolder holder = new EventAdapter.ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                //点击进入详情
                EventClass eventClass = eventList.get(position);
                Context context = MyApplication.context;
                ActivityOneEvent.EventTitle = eventClass.getTitle();
                ActivityOneEvent.EventContent = eventClass.getContent();
                ActivityOneEvent.EventBegin = eventClass.getStartTime();
                ActivityOneEvent.EventEnd = eventClass.getEndTime();
                ActivityOneEvent.EventNeed = eventClass.getNeedCount();
                ActivityOneEvent.EventDuring = eventClass.getDuring();
                ActivityOneEvent.id = eventClass.getId();

                Intent intent = new Intent(context, ActivityOneEvent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventClass eventClass = eventList.get(position);
        holder.EventTitle.setText(eventClass.getTitle());
        holder.EventContent.setText(eventClass.getContent());
        holder.EventStartTime.setText(eventClass.getStartTime());
        holder.EventEndTime.setText(eventClass.getEndTime());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
