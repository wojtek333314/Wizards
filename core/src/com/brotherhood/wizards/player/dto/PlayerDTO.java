package com.brotherhood.wizards.player.dto;

import com.brotherhood.wizards.eq.Equipment;
import com.brotherhood.wizards.spells.SpellBook;

/**
 * Created by Wojciech Osak on 2015-09-03.
 */
public class PlayerDTO {
    private String nick;
    private int roundsToPlay;
    private int maxHP;
    private int maxMP;
    private long exp;
    private long gold;
    private Equipment equipment;
    private SpellBook spellBook;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getRoundsToPlay() {
        return roundsToPlay;
    }

    public void setRoundsToPlay(int roundsToPlay) {
        this.roundsToPlay = roundsToPlay;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public SpellBook getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(SpellBook spellBook) {
        this.spellBook = spellBook;
    }
}
