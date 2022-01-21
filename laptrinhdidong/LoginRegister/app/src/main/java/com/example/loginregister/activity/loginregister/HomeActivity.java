package com.example.loginregister.activity.loginregister;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.loginregister.R;
import com.example.loginregister.activity.loginregister.Clubs.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private TextView username,email,club;
    private Button btn_logout, btn_photo_upload,btn_photo_choose, btn_list;
    SessionManager sessionManager;
    String getId;
    private static String URL_READ = "http://192.168.1.7/LoginRegister/read_detail.php";
    private static String URL_EDIT = "http://192.168.1.7/LoginRegister/edit_detail.php";
    private static String URL_UPLOAD = "http://192.168.1.7/LoginRegister/upload.php";

    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;
    final int CODE_GALLERY_REQUEST = 999;
    private Uri filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        username = findViewById(R.id.homeusername);
        email = findViewById(R.id.homeemail);
        club = findViewById(R.id.homeclub);
        btn_logout = findViewById(R.id.btnlogout);
        btn_photo_upload = findViewById(R.id.btn_upload);
        btn_photo_choose = findViewById(R.id.btn_choose);
        profile_image = findViewById(R.id.profile);
        btn_list = findViewById(R.id.btn_list);


        HashMap<String,String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_photo_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(HomeActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},CODE_GALLERY_REQUEST);
            }
        });
        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_UPLOAD, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parms=new HashMap<String, String>();
                        String imgdata=imgToString(bitmap);
                        parms.put("imageurl",imgdata);
                        return parms;

                    }
                };
                RequestQueue rq= Volley.newRequestQueue(HomeActivity.this);
                rq.add(stringRequest);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==CODE_GALLERY_REQUEST){
            if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(new Intent(Intent.ACTION_PICK));
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"select image"),CODE_GALLERY_REQUEST);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CODE_GALLERY_REQUEST && resultCode==RESULT_OK && data!=null){
            filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                profile_image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imgToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes=byteArrayOutputStream.toByteArray();
        String encodeimg= Base64.encodeToString(imgbytes,Base64.DEFAULT);
        return encodeimg;
    }


    //getUser
    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            if(success.equals("1")){
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strUsername = object.getString("username").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strClub = object.getString("club").trim();
                                    String strPhoto  = object.getString("photo").trim();

                                    username.setText(strUsername);
                                    email.setText(strEmail);
                                    club.setText(strClub);

//                                    Picasso.with(getApplicationContext()).load(strPhoto).into(profile_image);
//                                    Glide.with(getApplicationContext())
//                                            .load(strPhoto)
//                                            .into(profile_image);

                                    RequestOptions requestOptions = new RequestOptions();
                                    requestOptions.skipMemoryCache(true);
                                    requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);

                                    Glide.with(HomeActivity.this)
                                            .load(strPhoto)
                                            .apply(requestOptions)
                                            .into(profile_image);



                                    Toast.makeText(HomeActivity.this, strUsername + strEmail + strClub, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(HomeActivity.this, "Error reading detail " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Error reading detail " + error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action, menu);

        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_edit:
                username.setFocusableInTouchMode(true);
                email.setFocusableInTouchMode(true);
                club.setFocusableInTouchMode(true);

                InputMethodManager ipm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                ipm.showSoftInput(username,InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:

                SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);
                username.setFocusableInTouchMode(false);
                email.setFocusableInTouchMode(false);
                club.setFocusableInTouchMode(false);
                username.setFocusable(false);
                email.setFocusable(false);
                club.setFocusable(false);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //save
    private void SaveEditDetail() {
        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String club = this.club.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(HomeActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(username,email,club,id);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(HomeActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("club", club);
                params.put("id", id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}