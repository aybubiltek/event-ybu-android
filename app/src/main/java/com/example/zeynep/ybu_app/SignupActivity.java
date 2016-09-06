package com.example.zeynep.ybu_app;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    TextView signinTv;
    TextView createButton;
    TextView conti;
    EditText nameTv;
    EditText emailTv;
    EditText passTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");
        signinTv = (TextView)findViewById(R.id.terms);
        createButton = (TextView)findViewById(R.id.create);
        conti = (TextView)findViewById(R.id.conti);
        nameTv = (EditText)findViewById(R.id.name);
        emailTv = (EditText)findViewById(R.id.email);
        passTv = (EditText)findViewById(R.id.pass);
        passTv.setTypeface(custom_font);
        signinTv.setTypeface(custom_font);
        createButton.setTypeface(custom_font);
        conti.setTypeface(custom_font);
        nameTv.setTypeface(custom_font);
        emailTv.setTypeface(custom_font);
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        signinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kayıt aktivitesini bitir ve girişe dön
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        createButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Hesap Oluşturuluyor..");
        progressDialog.show();

        String name = nameTv.getText().toString();
        String email = emailTv.getText().toString();
        String password =passTv.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        createButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Kayıt yapılamadı..", Toast.LENGTH_LONG).show();

        createButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameTv.getText().toString();
        String email = emailTv.getText().toString();
        String password = passTv.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameTv.setError("en az 3 karakter giriniz");
            valid = false;
        } else {
            nameTv.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTv.setError("geçerli bir email adresi giriniz");
            valid = false;
        } else {
            emailTv.setError(null);
        }

        if (password.isEmpty() || password.length() !=11) {
            passTv.setError("şifre 11 karakterli olmalı");
            valid = false;
        } else {
            passTv.setError(null);
        }

        return valid;
    }



}
