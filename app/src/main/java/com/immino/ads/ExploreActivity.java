package com.immino.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.immino.ads.adapters.CustomCampaignListViewAdapter;
import com.immino.ads.models.Campaign;
import com.immino.ads.models.CampaignResource;
import com.immino.ads.network.CampaignService;
import com.immino.ads.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText thresholdInput;
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<Campaign> campaigns = new ArrayList<>();
    SharedPreferences sharedPreferences;
    private String token;
    private ListView campaignsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Spinner categoriesSpinner = findViewById(R.id.categories_spinner);
        thresholdInput = findViewById(R.id.threshold_input);
        Button filterButton = findViewById(R.id.filter_button);
        campaignsListView = findViewById(R.id.campaigns_list_view);

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(categoriesAdapter);

        sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        sharedPreferences.edit().clear().apply();

        getAllCampaigns();

        categoriesSpinner.setOnItemSelectedListener(this);
        filterButton.setOnClickListener(v -> {
            // filter data
            Intent mapsIntent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(mapsIntent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Campaign campaign = (Campaign) parent.getItemAtPosition(position);
        Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(mapIntent);
        // TODO: send campaign to MapsActivity and show campaign in the map
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        thresholdInput.setText(getString(R.string.no_filter));
    }

    private void getAllCampaigns() {
        CampaignService campaignService = RetrofitClientInstance.getRetrofitInstance().create(CampaignService.class);
        Call<List<CampaignResource>> campaignResourceCall = campaignService.all("Bearer " + token);

        campaignResourceCall.enqueue(new Callback<List<CampaignResource>>() {
            @Override
            public void onResponse(Call<List<CampaignResource>> call, Response<List<CampaignResource>> response) {
                List<CampaignResource> campaignResources = response.body();
                for (CampaignResource resource: campaignResources) {
                    Campaign campaign = new Campaign();
                    campaign.setName(resource.getName());
                    campaign.setDescription(resource.getDescription());
                    campaigns.add(campaign);
                }
                CustomCampaignListViewAdapter campaignListViewAdapter = new CustomCampaignListViewAdapter(ExploreActivity.this, campaigns);
                campaignsListView.setAdapter(campaignListViewAdapter);
            }

            @Override
            public void onFailure(Call<List<CampaignResource>> call, Throwable t) {
            }
        });
    }
}
