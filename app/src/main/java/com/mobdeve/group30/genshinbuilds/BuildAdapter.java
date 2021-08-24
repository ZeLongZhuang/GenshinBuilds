package com.mobdeve.group30.genshinbuilds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BuildAdapter extends RecyclerView.Adapter<BuildViewHolder> {

    private ArrayList<Build> dataBuilds;

    public BuildAdapter(ArrayList<Build> dataBuilds) { this.dataBuilds = dataBuilds; }

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
        Build currentBuild = this.dataBuilds.get(position);

        // call functions from Build.java
        // currentBuild.set
    }

    @Override
    public int getItemCount() {
        return this.dataBuilds.size();
    }
}
