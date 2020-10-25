package com.example.movil2020ii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CrearUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CrearUsuarioActivity";
    private FirebaseAuth mAuth;
    TextInputEditText email, passwordRegister;
    Button botonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        botonRegister = findViewById(R.id.registrar);
        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario(email.getText().toString(), passwordRegister.getText().toString());
            }
        });

    }
    public void crearUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(CrearUsuarioActivity.this, "Registrado con exito",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CrearUsuarioActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}