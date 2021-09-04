package com.mobdeve.group30.genshinbuilds;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class BuildViewHolder extends RecyclerView.ViewHolder {

    private ImageButton ibUserPic;
    private TextView tvUsername;

    private TextView tvCharacter;
    private TextView tvLevel;
    private TextView tvWeapon;
    private TextView tvArtifactSet;
    private TextView tvHp;
    private TextView tvAtk;
    private TextView tvDef;
    private TextView tvEr;
    private TextView tvCritRate;
    private TextView tvCritDmg;

    private ImageButton ibDelete;

    public BuildViewHolder(@NonNull @NotNull View itemView) {

        super(itemView);

        this.ibUserPic = itemView.findViewById(R.id.ib_item_user_pic);
        this.tvUsername = itemView.findViewById(R.id.tv_item_username);
        this.tvCharacter = itemView.findViewById(R.id.tv_item_value_character);
        this.tvLevel = itemView.findViewById(R.id.tv_item_value_level);
        this.tvWeapon = itemView.findViewById(R.id.tv_item_value_weapon);
        this.tvArtifactSet = itemView.findViewById(R.id.tv_item_value_artifact);
        this.tvHp = itemView.findViewById(R.id.tv_item_value_hp);
        this.tvAtk = itemView.findViewById(R.id.tv_item_value_atk);
        this.tvDef = itemView.findViewById(R.id.tv_item_value_def);
        this.tvEr = itemView.findViewById(R.id.tv_item_value_er);
        this.tvCritRate = itemView.findViewById(R.id.tv_item_value_crate);
        this.tvCritDmg = itemView.findViewById(R.id.tv_item_value_cdmg);

        this.ibDelete = itemView.findViewById(R.id.ib_item_delete);
    }

    public void setIbUserPic(int ibUserPic) {
        this.ibUserPic.setImageResource(ibUserPic);
    }

    public void setTvUsername(String tvUsername) {
        this.tvUsername.setText(tvUsername);
    }

    public void setTvCharacter(String tvCharacter) {
        this.tvCharacter.setText(tvCharacter);
    }

    public void setTvLevel(String tvLevel) {
        this.tvLevel.setText(tvLevel);
    }

    public void setTvWeapon(String tvWeapon) {
        this.tvWeapon.setText(tvWeapon);
    }

    public void setTvArtifactSet(String tvArtifactSet) {
        this.tvArtifactSet.setText(tvArtifactSet);
    }

    public void setTvHp(String tvHp) {
        this.tvHp.setText(tvHp);
    }

    public void setTvAtk(String tvAtk) {
        this.tvAtk.setText(tvAtk);
    }

    public void setTvDef(String tvDef) {
        this.tvDef.setText(tvDef);
    }

    public void setTvEr(String tvEr) {
        this.tvEr.setText(tvEr);
    }

    public void setTvCritRate(String tvCritRate) {
        this.tvCritRate.setText(tvCritRate);
    }

    public void setTvCritDmg(String tvCritDmg) {
        this.tvCritDmg.setText(tvCritDmg);
    }

    public void setVisibilityDeleteButton(boolean isVisible) {
        if(isVisible)
            this.ibDelete.setVisibility(View.VISIBLE);
        else
            this.ibDelete.setVisibility(View.GONE);
    }

}
