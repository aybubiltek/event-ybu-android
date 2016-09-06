package com.example.zeynep.ybu_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "SignIn Activity";
    EditText emailEt;
    EditText passEt;
    TextView _signinButton;
    TextView _CreateTextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");

        emailEt = (EditText)findViewById(R.id.email);
        passEt = (EditText)findViewById(R.id.pass);
        _signinButton = (TextView) findViewById(R.id.signin);
        _CreateTextButton = (TextView)findViewById(R.id.Create);
        _CreateTextButton.setTypeface(custom_font);
        emailEt.setTypeface(custom_font);
        passEt.setTypeface(custom_font);
        _signinButton.setTypeface(custom_font);
        _signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     login();
            }
        });
        _CreateTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SigninActivity.this, SignupActivity.class);
                startActivityForResult(it,REQUEST_SIGNUP);
            }
        });


    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _signinButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SigninActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Giriş Yapılıyor..");
        progressDialog.show();

        String emailStr = emailEt.getText().toString();
        String passwordStr = passEt.getText().toString();

        // TODO: giriş için yapılacaklar

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Kayıt için yapılacaklar
                //aktivite biter ve seçim sayfasına yönlendirilir
               this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // ana sayfaya gidişi engelle
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _signinButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Giriş Başarısız", Toast.LENGTH_LONG).show();
        _signinButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String emailStr = emailEt.getText().toString().trim();
        String passwordStr = passEt.getText().toString();

        if (emailStr.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            emailEt.setError("Geçerli bir mail adresi giriniz");
            valid = false;
        } else {
            emailEt.setError(null);
        }

        if (passwordStr.isEmpty() || passwordStr.length()<10||passwordStr.length()>11 ) {
            passEt.setError("Parola 11 karakterden az ya da fazla karakter içeremez");
            valid = false;
        } else {
            passEt.setError(null);
        }

        return valid;
    }
}
