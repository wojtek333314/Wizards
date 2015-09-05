package com.brotherhood.wizards.spells.dto;

import com.brotherhood.wizards.enums.SpellType;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public class SpellDTO {
    private SpellType spellType;
    private String  name;
    private String desc;
    private int id;
    private int mpCost;
    private int hpCost;
    private double damagePoints;
    private double defencePoints;
    private double speed;
    private double duration;

    public SpellType getSpellType() {
        return spellType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMpCost() {
        return mpCost;
    }

    public void setMpCost(int mpCost) {
        this.mpCost = mpCost;
    }

    public int getHpCost() {
        return hpCost;
    }

    public void setHpCost(int hpCost) {
        this.hpCost = hpCost;
    }

    public double getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(double damagePoints) {
        this.damagePoints = damagePoints;
    }

    public double getDefencePoints() {
        return defencePoints;
    }

    public void setDefencePoints(double defencePoints) {
        this.defencePoints = defencePoints;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
