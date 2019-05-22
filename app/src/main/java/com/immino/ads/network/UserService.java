package com.immino.ads.network;

import com.immino.ads.models.SignUpDto;
import com.immino.ads.models.SignInDto;
import com.immino.ads.models.SignInResource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user/signIn")
    Call<SignInResource> signIn(@Body SignInDto signInDto);

    @POST("user/signUp")
    Call<Void> signUp(@Body SignUpDto signUpDto);
}
