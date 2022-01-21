package com.example.loginregister.activity.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginregister.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText username, email, club, password;
    private TextView txtLogin ;
    private Button btnSignup;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.7/LoginRegister/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        club= (TextInputEditText) findViewById(R.id.club);
        username= (TextInputEditText) findViewById(R.id.username);
        password= (TextInputEditText) findViewById(R.id.password);
        email = (TextInputEditText) findViewById(R.id.email);
        btnSignup = (Button) findViewById(R.id.buttonSignUp);
        txtLogin = (TextView) findViewById(R.id.loginText);
        loading = (ProgressBar) findViewById(R.id.progress);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPassword = password.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mClub = club.getText().toString().trim();

                if(!mUsername.isEmpty() || !mPassword.isEmpty() || !mEmail.isEmpty() || !mClub.isEmpty()){
                   Regist();
                }else{
                    username.setError("Please insert username");
                    password.setError("Please insert password");
                    club.setError("Please insert club");
                    email.setError("Please insert email");
                }
            }
        });
    }
    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btnSignup.setVisibility(View.GONE);

        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String club = this.club.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(SignUpActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();

                                loading.setVisibility(View.GONE);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btnSignup.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btnSignup.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("club",club);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}