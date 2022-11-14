package com.example.covid_19alertapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.extras.Constants;
import com.example.covid_19alertapp.extras.DateTimeHandler;
import com.example.covid_19alertapp.models.Comment;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CommentFeedAdapter extends RecyclerView.Adapter<CommentFeedAdapter.MyViewHolder> {


    SharedPreferences sharedPreferences;
    Context context;
    ArrayList<Comment> commentList;
    public CommentFeedAdapter(Context context,ArrayList<Comment> commentList)
    {
         this.commentList = commentList;
         this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        String date = DateTimeHandler.DateToday();
        if(commentList.get(position).getComment_date().equals(date)) { date = "Today"; }
        String date_time = date+ ", "+ commentList.get(position).getComment_time();
        holder.dateTime.setText(date_time);

        if(commentList.get(position).getUser_id().equals(FeedAdapter.POST.getUserID()))
        {
            holder.authTag.setVisibility(View.VISIBLE);
            holder.line.setBackgroundColor(ContextCompat.getColor(context, R.color.color_item_view_relief_line));
        }

        holder.comment_body.setText(commentList.get(position).getComment_text());

        sharedPreferences = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
        String user_ID = sharedPreferences.getString(Constants.uid_preference,null);

        if(user_ID.equals(commentList.get(position).getUser_id()))
        {
            holder.comment_author.setText("You");
        }
        else
        {
            holder.comment_author.setText(commentList.get(position).getUser_name());

        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime, comment_body, comment_author,authTag;
        LinearLayout line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTime  = itemView.findViewById(R.id.textView_DatenTime_comment_view);
            comment_author = itemView.findViewById(R.id.textView_username_comment_view);
            comment_body = itemView.findViewById(R.id.comment_Text);
            line = itemView.findViewById(R.id.line_comment_view);
            authTag = itemView.findViewById(R.id.comment_view_author_title);
        }
    }
}
