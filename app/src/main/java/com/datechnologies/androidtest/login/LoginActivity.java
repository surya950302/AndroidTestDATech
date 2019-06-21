package com.datechnologies.androidtest.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.Feedback;
import com.datechnologies.androidtest.api.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 *
 */
public class LoginActivity extends AppCompatActivity {


    String feedback = "";
    TextView adata;
    TextView adata1;
    Button abtn;
    Button abtn1;
    View aview;
    View aview1;

    AlertDialog.Builder abuilder;
    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    //creat retrofit instance
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://dev.datechnologies.co/Tests/scripts/login.php/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login_button);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //creating alert box
        abuilder = new AlertDialog.Builder(LoginActivity.this);
        aview = getLayoutInflater().inflate(R.layout.alert_dialogbox, null);
        aview1 = getLayoutInflater().inflate(R.layout.alert_dialogbox, null);
        adata = (TextView) aview.findViewById(R.id.data);
        adata1 = (TextView) aview1.findViewById(R.id.data);
        abtn =(Button) aview.findViewById(R.id.alertbtn);
        abtn1 =(Button) aview1.findViewById(R.id.alertbtn);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pass);
        Button lbutton = (Button) findViewById(R.id.loginbutton);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executeloginfeedback(email.getText().toString(),
                        password.getText().toString());

            }
        });

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked
        // TODO: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

        // TODO: Send 'email' and 'password' to http://dev.datechnologies.co/Tests/scripts/login.php
        // TODO: as FormUrlEncoded parameters.

        // TODO: When you receive a response from the login endpoint, display an AlertDialog.
        // TODO: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
        // TODO: The AlertDialog should also display how long the API call took in milliseconds.
        // TODO: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

        // TODO: The only valid login credentials are:
        // TODO: email: info@datechnologies.co
        // TODO: password: Test123
        // TODO: so please use those to test the login.
    }

    private void executeloginfeedback(String e_mail, String p_assword)
    {
        UserClient userClient = retrofit.create(UserClient.class);

        Call<Feedback> call = userClient.sendLogin(
                e_mail,
                p_assword);
        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                try{
                String mess = response.body().getMessage();
                String code = response.body().getCode();
                System.out.println("ye le "+mess+"ye le"+code); //debugging
                    Long time = response.raw().receivedResponseAtMillis();
                    Long startTime = response.raw().sentRequestAtMillis();


                   feedback = "Code: "+ code +"\n";
                   feedback += "Message: "+ mess +"\n" ;
                   feedback += "Response Time: "+ (time - startTime)+" ms" +"\n";
                    adata.setText(feedback);
                    abtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                    abuilder.setView( aview);
                    final AlertDialog ad = abuilder.create();
                    if(ad.getContext() != null){
                        ad.show();
                    }

                }catch (Exception e){
                    System.out.println("exception: "+e);
                    adata1.setText("Login failed");
                    abuilder.setView(aview1);
                    final AlertDialog dialog = abuilder.create();
                    if(dialog.getContext() != null){
                        dialog.show();
                    }
                    abtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {
                System.out.println("error: "+t.getMessage());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
