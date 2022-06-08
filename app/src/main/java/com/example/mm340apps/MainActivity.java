package com.example.mm340apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText username, userpassword, useremail;
    Button Login, hw1;
    Toast t;
    String[] btnList = {"PARKS", "MOVIES", "CITIES", "TRAFFIC", "MUSIC", "LOCATION"};

    // helper class to manage writing to SharedPreferences.
    private SharedPreferencesHelper mSharedPreferencesHelper;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        useremail = findViewById(R.id.email);
        userpassword = findViewById(R.id.password);
        Login = findViewById(R.id.Login);
        hw1 = findViewById(R.id.goHW1);

        // Instantiate a SharedPreferencesHelper class
        mSharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        mSharedPreferencesHelper = new SharedPreferencesHelper(mSharedPreferences);

        username.setText(mSharedPreferencesHelper.getEntry("name"));
        useremail.setText(mSharedPreferencesHelper.getEntry("email"));
        userpassword.setText(mSharedPreferencesHelper.getEntry("password"));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (dataValidation()) {
                    t = Toast.makeText(getApplicationContext(), "User Data has been sent", Toast.LENGTH_SHORT);
                    t.show();
                }*/
                Log.d("FIREBASE", "click");
                signIn();
            }
        });


        hw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HW1.class);
                startActivity(intent);
            }
        });



        GridView btnGridView = (GridView) findViewById(R.id.btn_gridview);
        //final BtnAdapter btnAdapter = new BtnAdapter(this, btnList);
        btnGridView.setAdapter(new BtnAdapter(this, btnList));
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    static boolean validEmail(String text) {
        CharSequence email = text;
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean dataValidation() {
        boolean check = true;
        if (validEmail(useremail.getText().toString()) == false) {
            check = false;
            Toast t = Toast.makeText(this, "Enter user email", Toast.LENGTH_SHORT);
            useremail.setError("Enter valid email!");
            t.show();
        }
        else if (isEmpty(username)) {
            check = false;
            Toast t = Toast.makeText(this, "Enter user name", Toast.LENGTH_SHORT);
            username.setError("User name is required");
            t.show();
        }
        else if (isEmpty(userpassword)){
            check = false;
            Toast t = Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT);
            userpassword.setError("Password is required");
            t.show();
        }
        return check;
    }



    private void signIn() {
        Log.d("FIREBASE", "signIn");
        // 1 - validate display name, email, and password entries
        String displayname = username.getText().toString();
        String email = useremail.getText().toString();
        String password = userpassword.getText().toString();
        if (!dataValidation()){
            return;
        }

        // 2 - save valid entries to shared preferences
        // store shared preferences
        mSharedPreferencesHelper.saveEntry("name", displayname);
        mSharedPreferencesHelper.saveEntry("email", email);
        mSharedPreferencesHelper.saveEntry("password", password);


        // 3 - sign into Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            // update profile. displayname is the value entered in UI
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new
                                    UserProfileChangeRequest.Builder()
                                    .setDisplayName(displayname)
                                    .build();
                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("FIREBASE", "User profile updated.");
                                        // Go to FirebaseActivity
                                        startActivity(new Intent(MainActivity.this, FireBaseActivity.class));
                                    }
                                }
                            });
                        } else {
                            Log.d("FIREBASE", "sign-in failed");
                            Toast.makeText(MainActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}