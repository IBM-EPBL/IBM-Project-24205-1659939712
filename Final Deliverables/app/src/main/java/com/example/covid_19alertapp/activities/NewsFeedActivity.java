package com.example.covid_19alertapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.adapters.FeedAdapter;
import com.example.covid_19alertapp.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class NewsFeedActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    Button homeBtnFeed;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Post> list;
    com.example.covid_19alertapp.adapters.FeedAdapter FeedAdapter;
    private Parcelable recyclerViewState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        homeBtnFeed = findViewById(R.id.home_button_newsfeed_page);
        recyclerView = findViewById(R.id.recyclerView_Feed);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PostActivity.class));
            }
        });

        homeBtnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Posts");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Post post = dataSnapshot1.getValue(Post.class);
                    list.add(post);
                }
                Collections.reverse(list);
                FeedAdapter = new FeedAdapter(NewsFeedActivity.this,list);
                recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                recyclerView.setAdapter(FeedAdapter);
                recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something Wrong!\nCheck Internet Connection.", Toast.LENGTH_LONG).show();
            }
        });


    }
}
