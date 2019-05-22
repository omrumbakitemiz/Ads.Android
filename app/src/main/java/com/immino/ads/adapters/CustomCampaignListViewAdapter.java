package com.immino.ads.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.immino.ads.R;
import com.immino.ads.models.Campaign;

import java.util.ArrayList;
import java.util.Date;

public class CustomCampaignListViewAdapter extends ArrayAdapter<Campaign> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Campaign> campaigns;

    public CustomCampaignListViewAdapter(Context context, ArrayList<Campaign> campaigns) {
        super(context, 0, campaigns);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.campaigns = campaigns;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.list_view_item, null);

            holder = new ViewHolder();
            holder.campaignName = convertView.findViewById(R.id.campaign_name);
            holder.campaignDescription = convertView.findViewById(R.id.campaing_description);
            holder.campaignStartDate = convertView.findViewById(R.id.campaign_start_date);
            holder.campaignEndDate = convertView.findViewById(R.id.campaign_end_date);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Campaign campaign = campaigns.get(position);
        if (campaign != null) {
            holder.campaignName.setText(campaign.getName());
            holder.campaignDescription.setText(campaign.getDescription());

            Date startDate = campaign.getStartDate();
            Date endDate = campaign.getEndDate();

            if (startDate != null) {
                holder.campaignStartDate.setText(startDate.toString());
            }
            if (endDate != null) {
                holder.campaignEndDate.setText(endDate.toString());
            }
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return campaigns.size();
    }

    @Override
    public Campaign getItem(int position) {
        return campaigns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return campaigns.get(position).hashCode();
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView campaignName;
        TextView campaignDescription;
        TextView campaignStartDate;
        TextView campaignEndDate;
    }
}
