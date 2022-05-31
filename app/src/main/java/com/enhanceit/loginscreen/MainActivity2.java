package com.enhanceit.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    private boolean flagEmailValidation;
    private boolean flagPasswordsSimilar;
    private boolean flagPasswordValidation;

    private EditText initialPassword;
    private EditText repeatedPassword;
    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initialPassword = findViewById(R.id.originalPassword);
        repeatedPassword = findViewById(R.id.repeatedPassword);
        emailField = findViewById(R.id.inputEmail);

        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String userEmail = emailField.getText().toString();

               // emailField.
            }
        });
        repeatedPassword.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String firstPass = initialPassword.getText().toString();
                String secondPass = repeatedPassword.getText().toString();
                boolean flag = passwordValidation(firstPass, secondPass);

                if (flag) {
                    repeatedPassword.setError(null);
                } else {
                    repeatedPassword.setError("Your passwords doesn't match");
                }


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

    }

    private static boolean passwordValidation(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}