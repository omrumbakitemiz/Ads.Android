package com.immino.ads;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.immino.ads.models.SignInDto;
import com.immino.ads.models.SignInResource;
import com.immino.ads.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    String url = "http://10.0.2.2:5000/api/";
    private EditText usernameInput;
    private EditText passwordInput;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);
        Button signUpNavigateButton = findViewById(R.id.sign_up_navigate_button);

        sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        if (token != null) {
            navigateToExploreActivity();
        }

        loginButton.setOnClickListener(v -> login());
        signUpNavigateButton.setOnClickListener(v -> {
            Intent signUpIntent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(signUpIntent);
        });
    }

    private void login() {
        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        SignInDto signInDto = new SignInDto(
                usernameInput.getText().toString(),
                passwordInput.getText().toString()
        );

        Call<SignInResource> loginResourceCall = userService.signIn(signInDto);

        loginResourceCall.enqueue(new Callback<SignInResource>() {
            @Override
            public void onResponse(Call<SignInResource> call, Response<SignInResource> response) {
                progressDialog.dismiss();
                SignInResource userResponse = response.body();

                sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE);
                if (userResponse != null && userResponse.getToken() != null) {
                    String token = userResponse.getToken();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    editor.putString("token", token);
                    editor.apply();
                }

                if (response.code() == 200) {
                    Intent exploreIntent = new Intent(getApplicationContext(), ExploreActivity.class);
                    startActivity(exploreIntent);
                }
            }

            @Override
            public void onFailure(Call<SignInResource> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void navigateToExploreActivity() {
        Intent exploreActivity = new Intent(getApplicationContext(), ExploreActivity.class);
        startActivity(exploreActivity);
    }
}
