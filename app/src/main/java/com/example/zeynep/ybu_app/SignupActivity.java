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

    TextView signinTv,uyari;
    TextView createButton;
    TextView conti;
    EditText schoolNumberEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");
        signinTv = (TextView)findViewById(R.id.terms);
        uyari = (TextView)findViewById(R.id.uyari);

        createButton = (TextView)findViewById(R.id.create);
        conti = (TextView)findViewById(R.id.conti);
        schoolNumberEt = (EditText)findViewById(R.id.name);
        uyari.setTypeface(custom_font);
        signinTv.setTypeface(custom_font);
        createButton.setTypeface(custom_font);
        conti.setTypeface(custom_font);
        schoolNumberEt.setTypeface(custom_font);
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

        String schoolNumber = schoolNumberEt.getText().toString();


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

        String schoolnumber = schoolNumberEt.getText().toString();


        if (schoolnumber.isEmpty() || schoolnumber.length() < 9&&schoolnumber.length()>13) {
            schoolNumberEt.setError("Okul numarası 9-13 karakterlidir.");
            valid = false;
        } else {
            schoolNumberEt.setError(null);
        }


        return valid;
    }



}
