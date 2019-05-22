package com.immino.ads.network;

import com.immino.ads.models.CampaignResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CampaignService {

    @GET("campaign/all")
    Call<List<CampaignResource>> all(@Header("Authorization") String header);
}
