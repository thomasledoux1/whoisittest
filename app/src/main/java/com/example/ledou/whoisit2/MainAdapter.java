package com.example.ledou.whoisit2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ledou on 22/11/2016.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private ArrayList<PersoonBeschrijving> persoonBeschrijvings;

    public MainAdapter(ArrayList<PersoonBeschrijving> persoonBeschrijvings)
    {
        this.persoonBeschrijvings = persoonBeschrijvings;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);
        v.setOnClickListener(MainActivityFragment.mainOnClickListener);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        ImageView image = holder.characterImage;
        TextView name = holder.characterDiscription;
        Context context = holder.characterImage.getContext();
        Picasso.with(context).load(persoonBeschrijvings.get(position).getFoto());
        image.setImageResource(persoonBeschrijvings.get(position).getFoto());
        name.setText(persoonBeschrijvings.get(position).getBeschrijving());

    }

    @Override
    public int getItemCount() {
        return persoonBeschrijvings.size();
    }

    public static class MainViewHolder extends
            RecyclerView.ViewHolder {

        @Bind(R.id.card_image)
        public ImageView characterImage;

        @Bind(R.id.card_discription)
        public TextView characterDiscription;

        public MainViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }

    }

}
