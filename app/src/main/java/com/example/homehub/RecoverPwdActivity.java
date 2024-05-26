package com.example.homehub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RecoverPwdActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSend;
    private ImageView btnBack;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pwd);
        Intent intent = new Intent(this, LoginActivity.class);

        etEmail = findViewById(R.id.recoverEtEmail);
        btnSend = findViewById(R.id.optionsBtnLogOut);
        btnBack = findViewById(R.id.optionsImgBack);
        mAuth = FirebaseAuth.getInstance();

        btnSend.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(RecoverPwdActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(RecoverPwdActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }
            resetPassword(email, intent);
        });

        btnBack.setOnClickListener(v -> {
            startActivity(intent);
        });
    }

    private void resetPassword(String email, Intent intent) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(RecoverPwdActivity.this, "Recovery email sent", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Toast.makeText(RecoverPwdActivity.this, "Error sending recovery email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
