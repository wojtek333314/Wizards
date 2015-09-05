package com.brotherhood.wizards.player.dto;

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
    private int attackSpellId,
                attackSpell2Id,
                defenceSpellId,
                ultimatumSpellId;

    public int getAttackSpellId() {
        return attackSpellId;
    }

    public void setAttackSpellId(int attackSpellId) {
        this.attackSpellId = attackSpellId;
    }

    public int getAttackSpell2Id() {
        return attackSpell2Id;
    }

    public void setAttackSpell2Id(int attackSpell2Id) {
        this.attackSpell2Id = attackSpell2Id;
    }

    public int getDefenceSpellId() {
        return defenceSpellId;
    }

    public void setDefenceSpellId(int defenceSpellId) {
        this.defenceSpellId = defenceSpellId;
    }

    public int getUltimatumSpellId() {
        return ultimatumSpellId;
    }

    public void setUltimatumSpellId(int ultimatumSpellId) {
        this.ultimatumSpellId = ultimatumSpellId;
    }

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

}
