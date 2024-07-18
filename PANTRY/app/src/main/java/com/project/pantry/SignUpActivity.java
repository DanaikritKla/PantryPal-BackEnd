package com.project.pantry;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.project.pantry.entities.LoginObject;
import com.project.pantry.entities.SuccessObject;
import com.project.pantry.network.GsonRequest;
import com.project.pantry.network.VolleySingleton;
import com.project.pantry.utils.Helper;
import com.project.pantry.utils.PantryApplication;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private TextView displayError;
    private TextView txtLogin;
    private EditText username;
    private EditText email;
    private EditText password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        isUserLoggedIn();

        //displayError = (TextView)findViewById(R.id.login_error);

        username = (EditText)findViewById(R.id.edtUsername);
        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPassword);
        txtLogin = (TextView)findViewById(R.id.txtLogin);


        Button createAccountButton = (Button)findViewById(R.id.btnSignUp);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();
                String enteredEmail = email.getText().toString();

                if(TextUtils.isEmpty(enteredUsername) || TextUtils.isEmpty(enteredEmail) || TextUtils.isEmpty(enteredPassword)){
                    Helper.displayErrorMessage(SignUpActivity.this, getString(R.string.fill_all_fields));
                }

                if(!Helper.isValidEmail(enteredEmail)){
                    Helper.displayErrorMessage(SignUpActivity.this, getString(R.string.invalid_email));
                }


                //Add new user to the server
                addNewUserToRemoteServer(enteredUsername, enteredEmail, enteredPassword);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addNewUserToRemoteServer(String username, String email, String password){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Helper.USERNAME, username);
        params.put(Helper.PASSWORD, password);
        params.put(Helper.EMAIL, email);

        GsonRequest<SuccessObject> serverRequest = new GsonRequest<SuccessObject>(
                Request.Method.POST,
                Helper.PATH_TO_SAVE_REGISTER,
                SuccessObject.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(SignUpActivity.this).addToRequestQueue(serverRequest);
    }

    private Response.Listener<SuccessObject> createRequestSuccessListener() {
        return new Response.Listener<SuccessObject>() {
            @Override
            public void onResponse(SuccessObject response) {
                try {
                    Log.d(TAG, "Json Response " + response.getSuccess());
                    if(response.getSuccess() == 1){
                        //save login data to a shared preference
                        //String userData = ((MyFoodApplication)getApplication()).getGsonObject().toJson(response);
                        //((MyFoodApplication)getApplication()).getShared().setUserData(userData);

                        // navigate to restaurant home
                        Toast.makeText(SignUpActivity.this, R.string.success_registration, Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                    }else{
                        Toast.makeText(SignUpActivity.this, R.string.failed_registration, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

    private void isUserLoggedIn(){
        Gson mGson = ((PantryApplication)getApplication()).getGsonObject();
        String storedUser = ((PantryApplication)getApplication()).getShared().getUserData();
        LoginObject userObject = mGson.fromJson(storedUser, LoginObject.class);
        if(userObject != null){
            Intent intentMain = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intentMain);
        }
    }
}