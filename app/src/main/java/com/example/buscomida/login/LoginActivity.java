package com.example.buscomida.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.buscomida.AdapterRecyclerView;
import com.example.buscomida.R;
import com.example.buscomida.apiFiles.BuscomidaApi;
import com.example.buscomida.apiFiles.Restaurant;
import com.example.buscomida.apiFiles.User;
import com.example.buscomida.main.MainActivity;
import com.example.buscomida.SharedPref;
import com.example.buscomida.restaurant.RestaurantActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser, editTextPassword;
    SharedPref sharedPref;
    CheckBox checkBoxLogin;
    ProgressBar progressBarLogin;

    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClient;

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
        progressBarLogin = findViewById(R.id.progress_bar);
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

       String user = editTextUser.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(user)) {
            requiredField();
            editTextUser.requestFocus();


        } else if (TextUtils.isEmpty(password)) {
            requiredField();
            editTextPassword.requestFocus();
        } else {

            retrofitData(user, password);

        }

    }

    public void retrofitData(String user, String password) {

                progressBarLogin.setVisibility(View.VISIBLE);
                loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
                BuscomidaApi buscomidaApi = retrofit.create(BuscomidaApi.class);
                Call<List<User>> call = buscomidaApi.checkUser(user, password);
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        if(response.isSuccessful()){

                            sharedPref.setKeepMeLoggedIn(shouldKeepLogin);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            showDialog();
                            progressBarLogin.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });



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
