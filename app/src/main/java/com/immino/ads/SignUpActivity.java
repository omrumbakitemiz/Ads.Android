package com.immino.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.immino.ads.models.SignUpDto;
import com.immino.ads.models.SignUpResource;
import com.immino.ads.network.RetrofitClientInstance;
import com.immino.ads.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

public class SignUpActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText repeatPasswordInput;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        repeatPasswordInput = findViewById(R.id.repeat_password_input);
        signUpButton = findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(v -> {
            progressDialog = new ProgressDialog(SignUpActivity.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();

            UserService userService = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);

            String password = passwordInput.getText().toString();
            String repeatPassword = repeatPasswordInput.getText().toString();

            if (password.equals(repeatPassword)) {
                SignUpDto signUpDto = new SignUpDto();
                signUpDto.setUsername(usernameInput.getText().toString());
                signUpDto.setPassword(passwordInput.getText().toString());
                Call<Void> signUpCall = userService.signUp(signUpDto);

                signUpCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Sign Up Success, Redireting To Main Application", Toast.LENGTH_LONG).show();

                            Intent exploreIntent = new Intent(getApplicationContext(), ExploreActivity.class);
                            startActivity(exploreIntent);
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Passwords does not equal", Toast.LENGTH_LONG).show();
            }
        });
    }
}
