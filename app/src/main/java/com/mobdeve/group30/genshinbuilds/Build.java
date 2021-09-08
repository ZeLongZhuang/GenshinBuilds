package com.mobdeve.group30.genshinbuilds;

public class Build {

    private String username;
    private int userPic;
    private String character, weapon, artifactSet;
    int level, hp, atk, def, er, critRate, critDmg;

    public Build(String username, String character, int level, String weapon, String artifactSet, int hp, int atk, int def, int er, int critRate, int crtiDmg) {
        this.username = username;
        this.userPic = R.drawable.user_lumine;

        this.character = character;
        this.weapon = weapon;
        this.artifactSet = artifactSet;
        this.level = level;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.er = er;
        this.critRate = critRate;
        this.critDmg = crtiDmg;
    }

    public String getUsername() {
        return username;
    }

    public int getUserPic() {
        return userPic;
    }

    public String getCharacter() {
        return character;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArtifactSet() {
        return artifactSet;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getEr() {
        return er;
    }

    public int getCritRate() {
        return critRate;
    }

    public int getCritDmg() {
        return critDmg;
    }

}
