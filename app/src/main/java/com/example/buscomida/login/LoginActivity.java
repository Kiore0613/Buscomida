package com.example.buscomida.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.buscomida.R;
import com.example.buscomida.main.MainActivity;
import com.example.buscomida.SharedPref;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser, editTextPassword;
    String user, password;
    SharedPref sharedPref;
    CheckBox checkBoxLogin;

    ConstraintLayout constraintLayout;

    Boolean shouldKeepLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = findViewById(R.id.edt_user);
        editTextPassword = findViewById(R.id.edt_password);
        checkBoxLogin = findViewById(R.id.checkbox_login);

        constraintLayout = findViewById(R.id.login_layout);

        checkBoxLogin.setChecked(sharedPref.getKeepMeLoggedIn());
        if(sharedPref.getKeepMeLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        checkBoxLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
               shouldKeepLogin = checked;
            }
        });
    }

    public void ingresarApp(View btn_ingresar) {

        user = editTextUser.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(user)) {
            requiredField();
            editTextUser.requestFocus();


        } else if (TextUtils.isEmpty(password)) {
            requiredField();
            editTextPassword.requestFocus();
        } else {

            if(user.equals("admin") && password.equals("1234")){

                sharedPref.setKeepMeLoggedIn(shouldKeepLogin);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                showDialog();
            }
        }

    }

    public void showDialog() {

        Snackbar snackbar = Snackbar
                .make(constraintLayout, getResources().getString(R.string.snack), Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    public void requiredField(){
        Snackbar snackbar = Snackbar
                .make(constraintLayout, getResources().getString(R.string.field), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
