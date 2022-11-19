package com.example.client_containment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;




public class SignUp extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedpreferences = getApplicationContext().getSharedPreferences("user_data", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        if(sharedpreferences.getAll().size() >= 3){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }

    public void signUp(View view) {
        if(!name.getText().equals("") || !email.getText().equals("") || !password.getText().equals("")){
            postDataUsingVolley(name.getText().toString(),email.getText().toString(),password.getText().toString());
        }
    }
    private void postDataUsingVolley(String name, String email,String password) {
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.161.115:5000/android_sign_up";

        JSONObject postparams = new JSONObject();
        try {
            postparams.put("name", name);
            postparams.put("email", email);
            postparams.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());

                        try {
                            int userId = response.getInt("id");

                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.putInt("id", userId );
                            editor.commit();
                            Intent intent = new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                    }
                });

        queue.add(jsonObjReq);
    }
}