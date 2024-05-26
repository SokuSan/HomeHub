package com.example.homehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login;
    private TextView register;
    private TextView recover;
    private EditText emailEditText;
    private CheckBox rememberDevice;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        rememberDevice = findViewById(R.id.loginCheckRememberDevice);
        login = findViewById(R.id.registerBtnLogOut);
        register = findViewById(R.id.loginEtNotAMember);
        recover = findViewById(R.id.loginEtForgotPwd);
        emailEditText = findViewById(R.id.registerEtEmail);
        passwordEditText = findViewById(R.id.registerEtPassword);

        SharedPreferences preferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        boolean rememberMe = preferences.getBoolean("rememberMe", false);
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (rememberMe && currentUser != null) {
            updateUI(currentUser);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (validateInput(email, password)) {
                    loginUser(email, password);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RecoverPwdActivity.class);
                startActivity(register);
            }
        });
    }

    private boolean validateInput(String email, String password) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Empty email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Empty password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (rememberDevice.isChecked()) {
                                savePreferences(true);
                            } else {
                                savePreferences(false);
                            }
                            updateUI(user);
                        } else {
                            handleLoginError(task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void handleLoginError(Exception exception) {
        if (exception instanceof FirebaseAuthInvalidUserException) {
            Toast.makeText(LoginActivity.this, "Unregistered user", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Authentication failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void savePreferences(boolean remember) {
        SharedPreferences preferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("rememberMe", remember);
        editor.apply();
    }
}
