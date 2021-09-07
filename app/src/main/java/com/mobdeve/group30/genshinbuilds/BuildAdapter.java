package com.mobdeve.group30.genshinbuilds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class BuildAdapter extends RecyclerView.Adapter<BuildViewHolder> {

    private ArrayList<Build> dataBuilds;
    private Activity activity;
    private Context context;

    private GenshinDatabaseHelper myDB;

    public BuildAdapter(Activity activity, Context context, ArrayList<Build> dataBuilds) {
        this.dataBuilds = dataBuilds;
        this.activity = activity;
        this.context = context;

        myDB = new GenshinDatabaseHelper(context);
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

        String currentUsername = activity.getIntent().getStringExtra("KEY_USERNAME");

//      REMOVE THIS COMMENT TO ONLY ENABLE DELETE BUILD ON THE PROFILE DETAILS SCREEN
//        if(activity.toString().contains("HomeActivity"))
//            holder.setVisibilityDeleteButton(false);
//        else if(activity.toString().contains("ProfileActivity"))
//            holder.setVisibilityDeleteButton(true);

        if(currentUsername.equals(currentBuild.getUsername()))
            holder.setVisibilityDeleteButton(true);
        else
            holder.setVisibilityDeleteButton(false);

        holder.deleteBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Build selectedBuild = new Build(dataBuilds.get(position).getUsername(),
                                                dataBuilds.get(position).getCharacter(),
                        dataBuilds.get(position).getLevel(),
                        dataBuilds.get(position).getWeapon(),
                        dataBuilds.get(position).getArtifactSet(),
                        dataBuilds.get(position).getHp(),
                        dataBuilds.get(position).getAtk(),
                        dataBuilds.get(position).getDef(),
                        dataBuilds.get(position).getEr(),
                        dataBuilds.get(position).getCritRate(),
                        dataBuilds.get(position).getCritDmg());

                if(myDB.deleteBuild(selectedBuild)) {
                    Toast.makeText(context, "Delete Build Successfully. Refresh this page to see changes.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Delete Build Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataBuilds.size();
    }
}
