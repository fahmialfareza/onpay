package com.dinokeylas.onpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dinokeylas.onpay.model.UserModel;
import com.dinokeylas.onpay.util.Encript;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import static com.dinokeylas.onpay.util.Constant.COLLECTION.COLLECTION_USER;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent = new Intent();
    EditText editTextEmail, editTextPassword, editTextPasswordValidation;
    ProgressBar progressBar;

    UserModel usersModel = new UserModel();
    private String uid, email2, userName, fullName, profileImageUrl, phoneNumber, uPassword, uAddress;
    private int pin;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordValidation = (EditText) findViewById(R.id.editTextPasswordValidation);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;
            case R.id.textViewLogin:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordValidation = editTextPasswordValidation.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email tidak boleh kosong");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Format email tidak valid");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Sandi tidak boleh kosong");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Panjang sandi minimal 6");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(passwordValidation)) {
            editTextPassword.setError("Pastikan kata sandi yang dimasukkan sama");
            editTextPassword.requestFocus();
            return;
        }

        //encrypted password
        Encript encript = new Encript(password);
        uPassword = encript.getEcripted();

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mUser = FirebaseAuth.getInstance().getCurrentUser();

                    assert mUser != null;
                    email2 = mUser.getEmail();
                    uid = mUser.getUid();

                    usersModel = new UserModel(userName, fullName, email2, uAddress, profileImageUrl, phoneNumber, uPassword, pin);

                    db.collection(COLLECTION_USER).document(uid).
                            set(usersModel)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Data Anda telah tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    progressBar.setVisibility(View.GONE);
                    navigateToHomeActivity();
                } else {
                    progressBar.setVisibility(View.GONE);
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email sudah terdaftar, harap gunakan Email lain", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("PESAN", task.getException().getMessage());
                    }
                }
            }
        });
    }

    private void navigateToHomeActivity() {
        intent = new Intent(RegisterActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
