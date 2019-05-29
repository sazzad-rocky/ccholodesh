/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onesignal.OneSignal;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import callbacks.AuthCallback;
import helpers.BaseURL;
import model.Auth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.emailTextView) EditText emailTextView;
    @BindView(R.id.phoneTextView) EditText phoneTextView;
    @BindView(R.id.passwordTextView) EditText passwordTextView;
    @BindView(R.id.firstname) EditText firstname;
    @BindView(R.id.lastname) EditText lastname;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.gotoLogin)
    TextView gotoLogin;
    @BindView(R.id.imageLogo)
    ImageView imageLogo;

    private AuthCallback authCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        gotoLogin.setVisibility(View.GONE);

        if (!BaseURL.LANGUAGE_ENG)
        {
            emailTextView.setHint("ইমেইল ");
            phoneTextView.setHint("মোবাইল নম্বর");
            passwordTextView.setHint("পাসওয়ার্ড");
            firstname.setHint("নামের প্রথমাংশ");
            lastname.setHint("নামের শেষাংশ");
            btnSignUp.setText("রেজিস্টার করুন");
            gotoLogin.setText("ইতিমধ্যে একটি একাউন্ট আছে?");
        }

        authCallback=new AuthCallback(this);

    }
    @OnClick(R.id.gotoLogin)
    public void gotoLogin(){
        Intent intent=new Intent(this,LoginActivity.class);
        finish();
        startActivity(intent);
    }
    @OnClick(R.id.btnSignUp)
    public  void SignUp(View viw){
        final String emailText=emailTextView.getText().toString();
        String phoneText=phoneTextView.getText().toString();
        String passwordText=passwordTextView.getText().toString();
        String name = firstname.getText().toString()+" "+lastname.getText().toString();
//        validation
        if(!validate()){
            return;
        }

        Auth auth=new Auth();
        auth.setCustomerEmail(emailText)
                .setPassword(passwordText)
                .setCustomerName(name)
                .setCustomerAddress("N\\A")
                .setCustomerPhone(phoneText);
        Gson gson = new Gson();
        String json = gson.toJson(auth);

        Log.e("JSON",json );

        authCallback.register(auth).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(RegistrationActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                if(response.body().equalsIgnoreCase("registration successfull")){

                    OneSignal.setEmail(emailText);
                    //Toast.makeText(RegistrationActivity.this, "YES", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegistrationActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String meesage ="Login Failed";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage ="লগইন ব্যর্থ";

                }
                Toast.makeText(RegistrationActivity.this,meesage,Toast.LENGTH_LONG).show();


            }
        });


    }

    private boolean validate(){
        boolean validate=true;

        String emailText=emailTextView.getText().toString();
        String phoneText=phoneTextView.getText().toString();
        String passwordText=passwordTextView.getText().toString();
        String firstName = firstname.getText().toString();
        if(phoneText.length()==0){
            phoneTextView.requestFocus();
            String meesage ="Phone is required";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage ="ফোন নম্বর প্রয়োজন  ";

            }
            Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();

            return false;
        }
        if(emailText.length()==0){
            emailTextView.requestFocus();
            String meesage ="Email is required";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage ="ইমেইল প্রয়োজন  ";

            }
            Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();

            return false;
        }
        if(passwordText.length()==0){
            phoneTextView.requestFocus();
            String meesage ="Password is required";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage ="পাসওয়ার্ড নম্বর প্রয়োজন  ";

            }
            Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();

            return false;
        }
        if(firstName.length()==0){
            String meesage ="First Name is required";
            if (!BaseURL.LANGUAGE_ENG)
            {
                meesage ="নামের প্রথমাংশ প্রয়োজন  ";

            }
            Toast.makeText(this,meesage,Toast.LENGTH_LONG).show();
            firstname.requestFocus();

            return false;
        }

        return validate;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            String path=uri.getPath();
            Log.e("PATH",path);
            // Log.d(TAG, String.valueOf(bitmap));
            imageLogo.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
