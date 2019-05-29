package com.olivine.parjatanbichitra.cholodesh;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import helpers.BaseURL;
import listeners.AuthenticationListener;
import model.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
    private AuthenticationListener listener;

    private EditText etName, etEmail, etPhone, etMassage;
    private String etNamestr="", etEmailstr="", etPhonestr="", etMassagestr="";
    private Button btnsubmit;
    private TextView tvcontact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etMassage = findViewById(R.id.etMassage);
        listener = ApiUtils.getAPIService();
        btnsubmit =findViewById(R.id.btnsubmit);
        tvcontact=findViewById(R.id.tvcontact);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Contact Cholodesh");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (!BaseURL.LANGUAGE_ENG){
            getSupportActionBar().setTitle("চলদেশ যোগাযোগ");
            etName.setHint("নাম");
            etEmail.setHint("ইমেইল");
            etPhone.setHint("ফোন");
            etMassage.setHint("বার্তা লিখুন");
            btnsubmit.setText("জমাদিন");
            tvcontact.setText("বার্তা পাঠান");
        }

    }
    public void submitMassageServer(View view) {

        etNamestr=etName.getText().toString();
        etEmailstr=etEmail.getText().toString();
        etPhonestr=etPhone.getText().toString();
        etMassagestr=etMassage.getText().toString();
        listener.sendContact(etNamestr,etEmailstr,etPhonestr,etMassagestr).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(AboutUsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                if (response.code()==200){
                    finish();
                    //Toast.makeText(AboutUsActivity.this, "Success "+response.body().get, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AboutUsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void call(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AboutUsActivity.this);
        alertDialog = new AlertDialog.Builder(AboutUsActivity.this);
        alertDialog.setTitle("Do you want to call ?");

        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String contact_nostr= "+88 02 58610418";
//        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                if (!TextUtils.isEmpty(contact_nostr)) {
                    String dial = "tel:" + contact_nostr;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(AboutUsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
// Setting Negative "NO" Button
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();        dialog.cancel();
            }
        });


        alertDialog.show();



    }

    public void calltwo(View view) {








        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AboutUsActivity.this);
        alertDialog = new AlertDialog.Builder(AboutUsActivity.this);
        alertDialog.setTitle("Do you want to call ?");

        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String contact_nostr= "+88 02 58610418";
//        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                if (!TextUtils.isEmpty(contact_nostr)) {
                    String dial = "tel:" + contact_nostr;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(AboutUsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
// Setting Negative "NO" Button
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();        dialog.cancel();
            }
        });


        alertDialog.show();


    }

    public void Email(View view) {


    }
}
