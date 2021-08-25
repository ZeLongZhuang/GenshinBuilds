package com.mobdeve.group30.genshinbuilds;

public class Build {
    private String character, weapon, artifactSet;
    int level, hp, atk, def, er, critRate, critDmg;

    public Build(String character, String weapon, String artifactSet, int level, int hp, int atk, int def, int critRate, int crtiDmg) {
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
