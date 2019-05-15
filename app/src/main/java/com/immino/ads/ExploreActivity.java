package com.immino.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.immino.ads.models.Campaign;
import com.immino.ads.models.Company;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExploreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText thresholdInput;
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<Campaign> campaigns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        categories.add("Cat1");
        categories.add("Cat2");
        categories.add("Cat3");
        categories.add("Cat4");
        categories.add("Cat5");
        categories.add("Cat6");

        Company company = new Company();
        company.setName("Company1");
        company.setAddress("Company1Address");
        company.setDefaultThreshold(Long.valueOf("100"));
        company.setLocation("Company1Location");

        Campaign campaign = new Campaign();
        campaign.setCompany(company);
        campaign.setName("Muazzam Kampanya");
        campaign.setDescription("Muazzam Açıklama");
        campaign.setStartDate(Calendar.getInstance().getTime());
        campaign.setEndDate(Date.from(Instant.ofEpochSecond(86400* 7)));

        campaigns.add(campaign);

        Spinner categoriesSpinner = findViewById(R.id.categories_spinner);
        thresholdInput = findViewById(R.id.threshold_input);
        Button filterButton = findViewById(R.id.filter_button);

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(categoriesAdapter);


        categoriesSpinner.setOnItemSelectedListener(this);
        filterButton.setOnClickListener(v -> {
            // filter data
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        thresholdInput.setText(getString(R.string.no_filter));
    }
}
