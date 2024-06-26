package com.example.expensemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button btnLogin;
    private TextView mForgetPassword, mSignupHere;

    private AlertDialog mDialog;
    /**
     * Firebase...
     */
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        // câu lệnh if này cho vào thẳng trang home
        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mDialog = builder.create();
        loginDetails();
    }

    private void loginDetails(){
        mEmail = findViewById(R.id.email_login);
        mPass = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.btn_login);
        mForgetPassword = findViewById(R.id.forgot_password);
        mSignupHere = findViewById(R.id.sign_reg);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String pass = mPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email required ...");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPass.setError("Pass required ...");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            Toast.makeText(getApplicationContext(), "Login Successful..", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Login Failed..", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        // Registration Activity
        mSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
        // Reset password activity
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReseatActivity.class));
            }
        });

    }
}