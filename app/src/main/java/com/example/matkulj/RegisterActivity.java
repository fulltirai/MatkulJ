package com.example.matkulj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.signup_text);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> loginUser());

        registerText.setOnClickListener(this::onClick);
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validasi
        if (email.isEmpty()) {
            emailEditText.setError("Email harus diisi");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Email tidak valid");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password harus diisi");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password minimal 6 karakter");
            passwordEditText.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this::onComplete);
    }

    private void onClick(View view) {
            Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void onComplete(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Toast.makeText(RegisterActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
