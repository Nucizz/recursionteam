package com.example.recursionteamapp.adapter;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.objects.Transaction;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Transaction> data;

    public PurchaseHistoryAdapter(Context ctx, ArrayList<Transaction> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public PurchaseHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_purchase_history, parent,false);
        return new PurchaseHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHistoryAdapter.ViewHolder holder, int position) {
        holder.matchTitle.setText(String.format("%s VS %s", data.get(position).getMatchInfo().getTeamName1(), data.get(position).getMatchInfo().getTeamName2()));
        holder.matchDateTime.setText(String.format("%s - %s", PublicFeature.shortenDate(data.get(position).getMatchInfo().getDate()), data.get(position).getMatchInfo().getTime()));
        holder.matchLocation.setText(data.get(position).getMatchInfo().getLocation());
        holder.transactionPrice.setText(PublicFeature.formatIDR(data.get(position).getTotalPrice()));

        if(data.get(position).getMatchInfo().getTeamLogoURL1() != null) {
            Picasso.get().load(data.get(position).getMatchInfo().getTeamLogoURL1()).into(holder.teamLogo1);
        }

        if(data.get(position).getMatchInfo().getTeamLogoURL2() != null) {
            Picasso.get().load(data.get(position).getMatchInfo().getTeamLogoURL2()).into(holder.teamLogo2);
        }

        holder.transcationDate.setText(PublicFeature.shortenDate(data.get(position).getDate()));
        holder.seatingZone.setText(data.get(position).getType());
        holder.ticketPrice.setText(PublicFeature.formatIDR(data.get(position).getMatchInfo().getTicketPrice()));
        holder.transactionQty.setText(String.format("x%d", data.get(position).getQuantity()));
        holder.transactionCode.setText(data.get(position).getCode());

        holder.detailsButton.setOnClickListener(n -> {seeDetails(holder, position);});
    }

    void seeDetails(PurchaseHistoryAdapter.ViewHolder holder, int position) {
        if(holder.detailsView.getVisibility() == View.GONE) {
            holder.detailsView.setVisibility(View.VISIBLE);
            holder.detailsButton.setText("CLOSE");

            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(data.get(position).getCode(), BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder encoder = new BarcodeEncoder();
                holder.qrCode.setImageBitmap(encoder.createBitmap(matrix));
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            holder.detailsButton.setText("DETAILS");
            holder.detailsView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView teamLogo1, teamLogo2, qrCode;
        TextView matchDateTime, matchLocation, matchTitle, transcationDate, seatingZone, transactionPrice, transactionCode, transactionQty, ticketPrice;
        LinearLayout detailsView;
        Button detailsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matchDateTime = itemView.findViewById(R.id.matchDateTime);
            matchLocation = itemView.findViewById(R.id.matchLocation);
            transactionPrice = itemView.findViewById(R.id.transcationTotalPrice);

            teamLogo1 = itemView.findViewById(R.id.teamLogo1);
            teamLogo2 = itemView.findViewById(R.id.teamLogo2);
            matchTitle = itemView.findViewById(R.id.matchTitle);

            ticketPrice = itemView.findViewById(R.id.ticketPrice);
            transcationDate = itemView.findViewById(R.id.transcationDate);
            seatingZone = itemView.findViewById(R.id.ticketZone);
            transactionCode = itemView.findViewById(R.id.transcationCode);
            transactionQty = itemView.findViewById(R.id.transcationQuantity);
            qrCode = itemView.findViewById(R.id.qrCode);

            detailsButton = itemView.findViewById(R.id.seeDetails);
            detailsView = itemView.findViewById(R.id.detailsView);
        }
    }

}
