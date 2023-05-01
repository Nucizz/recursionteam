package com.example.recursionteamapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.objects.Match;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Match> data;
    OnClickListener onClickListener;

    public MatchAdapter(Context ctx, ArrayList<Match> data, OnClickListener onClickListener) {
        this.ctx = ctx;
        this.data = data;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_match, parent,false);
        return new MatchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data.get(position).getTeamLogoURL1() != null) {
            Picasso.get().load(data.get(position).getTeamLogoURL1()).into(holder.teamLogo1);
        }

        if(data.get(position).getTeamLogoURL2() != null) {
            Picasso.get().load(data.get(position).getTeamLogoURL2()).into(holder.teamLogo2);
        }

        holder.matchDate.setText(PublicFeature.shortenDate(data.get(position).getDate()));

        holder.matchTime.setText(data.get(position).getTime());

        String location = data.get(position).getLocation();
        holder.matchLocation.setText(location.substring(0, Math.min(location.length(), 12)));

        holder.itemView.setOnClickListener(n -> onClickListener.onClick(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnClickListener {
        void onClick(Match item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView teamLogo1, teamLogo2;
        TextView matchDate, matchTime, matchLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.matchDate);
            matchTime = itemView.findViewById(R.id.matchTime);
            matchLocation = itemView.findViewById(R.id.matchLocation);

            teamLogo1 = itemView.findViewById(R.id.teamLogo1);
            teamLogo2 = itemView.findViewById(R.id.teamLogo2);
        }
    }

}
