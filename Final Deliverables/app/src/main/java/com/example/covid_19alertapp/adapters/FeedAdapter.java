package com.example.covid_19alertapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.activities.CommentFeedActivity;
import com.example.covid_19alertapp.models.Post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    Context context;
    ArrayList<Post> postList;
    public static Post POST;

    public FeedAdapter(Context c, ArrayList<Post> postList) {
        context = c;
        this.postList = postList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.post_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.userName.setText(postList.get(position).getUserName());
        //Date and Time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String today_date = dateFormat.format(cal.getTime()) + " " + monthFormat.format(cal.getTime());

        String post_date = postList.get(position).getDate();
        if (post_date.equals(today_date)) {
            post_date = "Today";
        }
        String dateNtime = post_date + ", " + postList.get(position).getTime();
        holder.dateTime.setText(dateNtime);


        holder.postBody.setText(postList.get(position).getPostBody());
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user phone_number to call
            }
        });

        if (postList.get(position).getPostType().equals("RELIEF")) {
            holder.relativeLayout1.setBackgroundColor(ContextCompat.getColor(context, R.color.color_item_view_relief_line));
            holder.relativeLayout2.setBackgroundColor(ContextCompat.getColor(context, R.color.color_item_view_relief_line));
            holder.contact.setVisibility(View.VISIBLE);
            holder.contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone_number = postList.get(position).getContactNO();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+phone_number));

                    context.startActivity(callIntent);
                }
            });
        }


        holder.comment.setText(postList.get(position).getCommentCount()+"");

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POST = postList.get(position);
                context.startActivity(new Intent(context, CommentFeedActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName,dateTime,postBody,comment,contact;
        RelativeLayout relativeLayout1,relativeLayout2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.textView_username);
            dateTime = itemView.findViewById(R.id.textView_DatenTime);
            postBody = itemView.findViewById(R.id.description_Text);
            comment = itemView.findViewById(R.id.Comment_Counter_PostView);
            contact = itemView.findViewById(R.id.Contact_PostView);
            relativeLayout1 = itemView.findViewById(R.id.relativeLayout_Color_Relief1);
            relativeLayout2 = itemView.findViewById(R.id.relativeLayout_Color_Relief2);
        }
    }
}
