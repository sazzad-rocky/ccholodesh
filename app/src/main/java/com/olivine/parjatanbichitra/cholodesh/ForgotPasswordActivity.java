/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.olivine.parjatanbichitra.cholodesh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import callbacks.AuthCallback;
import helpers.BaseURL;
import model.ForgotPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;
    Button submit;
    AuthCallback authCallBack;
    TextView frstTxtForLang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = (EditText) findViewById(R.id.email);
        submit = (Button) findViewById(R.id.btnSubmit);
        frstTxtForLang = (TextView) findViewById(R.id.frstTxtForLang);

        authCallBack = new AuthCallback(this);
        if (!BaseURL.LANGUAGE_ENG){

            frstTxtForLang.setText("পাসওয়ার্ড রিসেট করতে আপনার ইমেইল এড্রেস দিন  ");
            email.setHint("ইমেইল");
            submit.setText("সাবমিট");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gotEmail = "";
                gotEmail  = email.getText().toString();
                if (gotEmail.length() > 0){

                    postEmail(gotEmail);


                }else {
                    String error = "Enter a valid email address";
                    if (!BaseURL.LANGUAGE_ENG){
                        error = "সঠিক ইমেইল ঠিকনা লিখুন ";
                    }
                    Toast.makeText(ForgotPasswordActivity.this,error,Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void postEmail(String email){

        ForgotPassword forgotpass = new ForgotPassword();
        forgotpass.customerEmail = email;
        authCallBack.postForgotPassword(forgotpass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(ForgotPasswordActivity.this,response.body(),Toast.LENGTH_SHORT).show();
                if (response.body().equals("true")){
                    String error = "A reset password link has been sent to your email address.";
                    if (!BaseURL.LANGUAGE_ENG){

                        error = "একটি রিসেট পাসওয়ার্ড লিংক আপনার ইমেল ঠিকানায়  পাঠানো হয়েছে";
                    }
                    Toast.makeText(ForgotPasswordActivity.this,error,Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    String error = "Your entered email doesn't match with any account, Please try again.";
                    if (!BaseURL.LANGUAGE_ENG){
                        error = "আপনার প্রবেশ করা ইমেল কোনও অ্যাকাউন্টের সাথে মেলে না, দয়া করে আবার চেষ্টা করুন";
                    }
                    Toast.makeText(ForgotPasswordActivity.this,error,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String meesage ="Network Error";
                if (!BaseURL.LANGUAGE_ENG)
                {
                    meesage =" নেটওয়ার্ক ত্রুটি";

                }

                Toast.makeText(ForgotPasswordActivity.this,meesage,Toast.LENGTH_SHORT).show();
            }
        });

    }


}
