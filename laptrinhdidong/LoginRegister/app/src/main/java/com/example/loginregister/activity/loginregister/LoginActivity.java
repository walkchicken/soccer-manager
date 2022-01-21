package com.example.loginregister.activity.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText username,password;
    private Button btnLogin;
    private TextView txtSignUp;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://192.168.1.7/LoginRegister/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);

        username = (TextInputEditText) findViewById(R.id.username);
        password= (TextInputEditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        txtSignUp = (TextView) findViewById(R.id.signUpText);
        loading = (ProgressBar) findViewById(R.id.progress);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(!mUsername.isEmpty() || !mPassword.isEmpty()){
                    Login(mUsername,mPassword);
                }else{
                    username.setError("Please insert username");
                    password.setError("Please insert password");
                }
            }
        });
    }

    private void Login(String username, String password){
        loading.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String username = object.getString("username").trim();
                                    String email = object.getString("email").trim();
                                    String club = object.getString("club").trim();
                                    String id = object.getString("id").trim();

                                    sessionManager.createSession(username,email,club, id);

                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    intent.putExtra("username",username);
                                    intent.putExtra("email",email);
                                    intent.putExtra("club",club);
                                    startActivity(intent);
                                    finish();

                                    loading.setVisibility(View.GONE);

                                }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}