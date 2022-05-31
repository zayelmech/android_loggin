package com.enhanceit.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    private boolean isEmailOk;
    private boolean isEmailExist;
    private boolean arePasswordsSimilar;
    private boolean flagPasswordValidation;

    private EditText initialPassword;
    private EditText repeatedPassword;
    private EditText emailField;
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

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
                isEmailOk = emailValidation(userEmail);
                if (isEmailOk) {
                    emailField.setError(null);

                } else {
                    emailField.setError("Enter a valid email");

                }
                // emailField.
            }
        });
        repeatedPassword.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String firstPass = initialPassword.getText().toString();
                String secondPass = repeatedPassword.getText().toString();
                 arePasswordsSimilar = passwordValidation(firstPass, secondPass);

                if (arePasswordsSimilar) {
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

    private boolean emailValidation(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}