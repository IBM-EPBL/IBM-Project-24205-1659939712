package com.example.covid_19alertapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.extras.DateTimeHandler;
import com.example.covid_19alertapp.models.Post;
import com.example.covid_19alertapp.extras.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PostActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button homeBtnPost, postBtn;
    EditText postText;
    RadioGroup radioGroup;
    String postType= "QUERY";
    String post_ID,user_name,user_ID,post_body,post_date,post_time,contact_NO;
    int reliefbtnID,radioID;
    DatabaseReference reference;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        reference = FirebaseDatabase.getInstance().getReference().child("Posts");
        homeBtnPost = findViewById(R.id.home_button_new_post_page);
        postBtn = findViewById(R.id.btn_post);
        postText = findViewById(R.id.editText_post);
        radioGroup = findViewById(R.id.radioGroup);
        reliefbtnID = R.id.radBtnRelief;
        homeBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 post_body = postText.getText().toString();
                if(post_body.length()==0)
                {
                    postText.setError("Post body can not be empty!");
                }
                else
                {
                    //Post It
                    sharedPreferences = getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);
                     post_ID = reference.push().getKey();
                     user_ID = sharedPreferences.getString(Constants.uid_preference,null);

                     contact_NO = sharedPreferences.getString(Constants.user_phone_no_preference,null);
                     post_date = DateTimeHandler.DateToday();
                     post_time = DateTimeHandler.TimeNow();
                    radioID = radioGroup.getCheckedRadioButtonId();
                    if(radioID==reliefbtnID)
                    {
                        user_name = "Anonymous";
                        postType = "RELIEF";
                        query = FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("userID").equalTo(user_ID);
                        query.addListenerForSingleValueEvent(valueEventListener);
                    }
                    else
                    {
                        user_name = sharedPreferences.getString(Constants.username_preference,null);
                        postType= "QUERY";
                        makePost();
                    }
                }
            }
        });

    }


    public void showCustomAlert(String text)
    {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.custom_toast, null);
        TextView textView = toastView.findViewById(R.id.toast_test);
        textView.setText(text);
        Toast toast = new Toast(context);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        Post postRelief=null;
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
            {
                Post post = dataSnapshot1.getValue(Post.class);
                if(post.getPostType().equals("RELIEF"))
                {
                    postRelief = post;
                    break;
                }
            }

            if(postRelief==null)
            {
                makePost();
            }
            else
            {
                if(DateTimeHandler.dayInterval(postRelief.getDate())<3)
                {
                    showCustomAlert("You Have to Wait 3 Days to Post Another Relief Request!");
                }
                else
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Posts");
                    reference.child(postRelief.getPostID()).removeValue();
                    makePost();
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) { }
    };

    public void makePost()
    {
        Post post = new Post(post_ID,user_name,user_ID,post_body,post_date,post_time,contact_NO,postType);
        reference.child(post_ID).setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showCustomAlert("Posted to Feed!");
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showCustomAlert("Failed: Check Internet Connection!");

            }
        });
    }
}
