package com.enhanceit.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    private boolean isEmailOk;
    private boolean isEmailExist;
    private boolean arePasswordsSimilar;
    private boolean isPasswordOk;
    private SharedPreferences shPrefs;
    private EditText initialPassword;
    private EditText repeatedPassword;
    private EditText emailField;
    private Button nextBtn;

    public static final Pattern PASSWORD_PATTERN
            = Pattern.compile("(?=.*?[A-Z])" + "(?=.*?[a-z])" + "(?=.*?[0-9])" + ".{8,}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initialPassword = findViewById(R.id.originalPassword);
        repeatedPassword = findViewById(R.id.repeatedPassword);
        emailField = findViewById(R.id.inputEmail);
        nextBtn = findViewById(R.id.nex_btn);


        //emailField.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_person, 0, R.drawable.tick, 0);

        shPrefs = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextBtn.setTextColor(Color.WHITE);

                if (!isEmailExist) {
                    String emailArray = shPrefs.getString("EMAIL", null);
                    String newArray;
                    if (emailArray != null){
                         newArray = emailArray + "," + emailField.getText().toString();
                    }else{
                        newArray = emailField.getText().toString();
                    }
                    shPrefs.edit()
                            .putString("EMAIL", newArray)
                            .apply();
                }
            }
        });
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
                    emailField.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_green));
                    emailField.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_person, 0, R.drawable.tick, 0);
                    isEmailExist = checkEmail(userEmail);
                    if (isEmailExist) {
                        emailField.setError("This email already exist");
                    } else {
                        emailField.setError(null);
                        unlockButton();
                    }
                } else {
                    emailField.setError("Enter a valid email");
                    emailField.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_red));
                    emailField.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.vector_person, 0, 0, 0);
                }
            }
        });

        initialPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String firstPass = initialPassword.getText().toString();
                isPasswordOk = passwordValidation(firstPass);

                if (isPasswordOk) {
                    initialPassword.setError(null);
                    initialPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_green));
                    unlockButton();

                } else {
                    initialPassword.setError("Enter a valid password");
                    initialPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_red));
                }
            }
        });

        repeatedPassword.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String firstPass = initialPassword.getText().toString();
                String secondPass = repeatedPassword.getText().toString();
                arePasswordsSimilar = passwordsValidation(firstPass, secondPass);
                //String userEmail = emailField.getText().toString();
                if (arePasswordsSimilar && !firstPass.isEmpty()) {
                    repeatedPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_green));
                    repeatedPassword.setError(null);
                    unlockButton();

                } else {
                    repeatedPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.edit_text_border_red));
                    repeatedPassword.setError("Your passwords doesn't match");

                }


            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    private void unlockButton() {
        if (isEmailOk && isPasswordOk && !isEmailExist && arePasswordsSimilar) {
            nextBtn.setEnabled(true);
            nextBtn.setAlpha(1);
        } else {
            nextBtn.setEnabled(false);
            nextBtn.setAlpha((float) 0.4);
        }
    }

    private boolean checkEmail(String userEmail) {

        boolean flag;
        String mailsArray;
        if (!shPrefs.getAll().isEmpty())
            mailsArray = shPrefs.getString("EMAIL", null);
        else
            mailsArray = "";

        if (!mailsArray.isEmpty()) {
            flag = mailsArray.contains(userEmail);
        } else {
            flag = false;
        }

        Log.d("MAILS--->", mailsArray);

        return flag;
    }

    private static boolean passwordsValidation(String pass1, String pass2) {
        return pass1.equals(pass2);

    }

    private boolean emailValidation(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    private boolean passwordValidation(String secret) {
        return PASSWORD_PATTERN.matcher(secret).matches();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int widthPx = Resources.getSystem().getDisplayMetrics().widthPixels;
        Log.d("WIDTH---->", widthPx + "px");
    }
}