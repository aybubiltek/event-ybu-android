package tr.edu.ybu.eventandroid;

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

import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "SignIn Activity";
    EditText schoolNumberEt;
    EditText passEt;
    TextView _signinButton;
    TextView _CreateTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/Lato-Light.ttf");

        schoolNumberEt = (EditText) findViewById(R.id.email);
        passEt = (EditText) findViewById(R.id.pass);
        _signinButton = (TextView) findViewById(R.id.signin);
        _CreateTextButton = (TextView) findViewById(R.id.Create);
        _CreateTextButton.setTypeface(custom_font);
        schoolNumberEt.setTypeface(custom_font);
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
                startActivityForResult(it, REQUEST_SIGNUP);
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        String schoolNumber = schoolNumberEt.getText().toString();
        String passwordStr = passEt.getText().toString();

        final ProgressDialog[] progressDialog = new ProgressDialog[1];
        JsonHelper jsonHelper = new JsonHelper(new JsonHelper.JsonRequest() {
            @Override
            public void onPreExecute() {
                _signinButton.setEnabled(false);

                progressDialog[0] = new ProgressDialog(SigninActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog[0].setIndeterminate(true);
                progressDialog[0].setMessage("Giriş Yapılıyor..");
                progressDialog[0].show();
            }

            @Override
            public JSONObject parse(String jsonString) throws JSONException {
                JSONObject jsonObject = new JSONObject(jsonString);
                if(!jsonObject.getBoolean("status")){
                    Welcome.log("Status false. Reason: " + jsonObject.getInt("reason"));
                    return null;
                }
                return jsonObject;
            }

            @Override
            public void onPostExecute(JSONObject json) {
                if(json == null){
                    Welcome.log("JSON null.");
                    return;
                }
                Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_LONG).show();
                Welcome.log(json.toString());
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                onLoginSuccess();
                                progressDialog[0].dismiss();
                            }
                        }, 3000);
            }
        });

        // TODO: giriş için yapılacaklar
        jsonHelper.execute("login", "email", schoolNumber+"@ybu.edu.tr", "pass", passwordStr);

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

        String schoolnumber = schoolNumberEt.getText().toString().trim();
        String passwordStr = passEt.getText().toString();

        if (schoolnumber.isEmpty() || schoolnumber.length() < 9 && schoolnumber.length() > 15) {
            schoolNumberEt.setError("Okul numarası 9-15 karakterlidir.");
            valid = false;
        } else {
            schoolNumberEt.setError(null);
        }

        if (passwordStr.isEmpty() || passwordStr.length() < 6 || passwordStr.length() > 32) {
            passEt.setError("Parola 8-32 karakterden oluşmalıdır.");
            valid = false;
        } else {
            passEt.setError(null);
        }

        return valid;
    }
}
