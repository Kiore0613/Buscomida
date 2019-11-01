package com.example.buscomida.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.buscomida.R;
import com.example.buscomida.main.MainActivity;
import com.example.buscomida.SharedPref;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser, editTextPassword;
    String user, password;
    SharedPref sharedPref;
    CheckBox checkBoxLogin;

    Boolean shouldKeepLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = findViewById(R.id.edt_user);
        editTextPassword = findViewById(R.id.edt_password);
        checkBoxLogin = findViewById(R.id.checkbox_login);

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
            editTextUser.setError("Campo no puede estar vacío");
            editTextUser.requestFocus();


        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Campo no puede estar vacío");
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
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta
                .setTitle("Alerta")
                .setMessage("El usuario/clave es incorrecto")
                .setCancelable(true)
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog mostrar = alerta.create();
        mostrar.show();
    }

}
