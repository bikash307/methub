package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {

    TextInputEditText inputName;

    EditText inputEmail,inputPhone,inputPassword,inputConfirmPassword ;
    Button btnSignup ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inputName=findViewById(R.id.enterName);
        inputEmail=findViewById(R.id.sign_email);
        inputPhone=findViewById(R.id.sign_phone);
        inputPassword=findViewById(R.id.set_password);
        inputConfirmPassword=findViewById(R.id.re_enter_password);

        btnSignup=findViewById(R.id.login_btn);

        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAuth();
            }
        });

    }

    private void performAuth() {
        String name=inputName.getText().toString();
        String email=inputEmail.getText().toString();
        String phone=inputPhone.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmPassword=inputConfirmPassword.getText().toString();

        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email");
        }else if(password.isEmpty() || password.length()<6)
        {
            inputPassword.setError("Enter proper Password");
        }else if(!password.equals(confirmPassword))
        {
            inputConfirmPassword.setError("Password did not match");
        }else
        {
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(sign_in.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                   }else
                   {
                       progressDialog.dismiss();
                       Toast.makeText(sign_in.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(sign_in.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}