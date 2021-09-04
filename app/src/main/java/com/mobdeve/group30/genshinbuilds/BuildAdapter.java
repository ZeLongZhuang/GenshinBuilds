package com.mobdeve.group30.genshinbuilds;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class BuildAdapter extends RecyclerView.Adapter<BuildViewHolder> {

    private ArrayList<Build> dataBuilds;
    private Activity activity;
    private Context context;

    public BuildAdapter(Activity activity, Context context, ArrayList<Build> dataBuilds) {
        this.dataBuilds = dataBuilds;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public BuildViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_build, parent, false);
        BuildViewHolder buildViewHolder = new BuildViewHolder(itemView);

        return buildViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BuildViewHolder holder, int position) {
        // call functions from Build.java

        Build currentBuild = this.dataBuilds.get(position);

        holder.setIbUserPic(currentBuild.getUserPic());
        holder.setTvUsername(currentBuild.getUsername());

        holder.setTvCharacter(currentBuild.getCharacter());
        holder.setTvLevel(Integer.toString(currentBuild.getLevel()));
        holder.setTvWeapon(currentBuild.getWeapon());
        holder.setTvArtifactSet(currentBuild.getArtifactSet());
        holder.setTvHp(Integer.toString(currentBuild.getHp()));
        holder.setTvAtk(Integer.toString(currentBuild.getAtk()));
        holder.setTvDef(Integer.toString(currentBuild.getDef()));
        holder.setTvEr(Integer.toString(currentBuild.getEr()));
        holder.setTvCritRate(Integer.toString(currentBuild.getCritRate()));
        holder.setTvCritDmg(Integer.toString(currentBuild.getCritDmg()));

        if(activity.toString().contains("HomeActivity"))
            holder.setVisibilityDeleteButton(false);
        else if(activity.toString().contains("ProfileActivity"))
            holder.setVisibilityDeleteButton(true);
    }

    @Override
    public int getItemCount() {
        return this.dataBuilds.size();
    }
}
