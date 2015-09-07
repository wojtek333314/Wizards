package com.brotherhood.wizards.player.dao;

import com.brotherhood.wizards.player.dto.PlayerDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wojciech Osak on 2015-09-03.
 */
public class PlayerDAO {
    private static final String nick = "nick";
    private static final String roundsToPlay = "roundsToPlay";
    private static final String maxHP = "maxHP";
    private static final String maxMP = "maxMP";
    private static final String exp = "exp";
    private static final String gold = "gold";
    private static final String userProperties = "userProperties",
                                attackSpell = "attack",
                                attackSpell2= "attack2",
                                defenceSpell = "defence",
                                ultimatumSpell = "ultimatum";


    /**
     * UWAGA UWAGA UWAGA UWAGA UWAGA UWAGA UWAGA UWAGA UWAGA UWAGA
     * Wszelkie dodanie wartosci
     * do tego obiektu nalezy
     * uwzglednic w klasie
     * Player w metodzie copyVariablesFromPlayerDTO
     */
    public static PlayerDTO createPlayerDTO(String json) throws JSONException {
        PlayerDTO playerDTO = new PlayerDTO();
        JSONObject playerObject = new JSONObject(json);

        playerDTO.setNick(playerObject.getString(nick));
        playerDTO.setRoundsToPlay(playerObject.getInt(roundsToPlay));
        playerDTO.setMaxHP(playerObject.getInt(maxHP));
        playerDTO.setMaxMP(playerObject.getInt(maxMP));
        playerDTO.setExp(playerObject.getLong(exp));
        playerDTO.setGold(playerObject.getLong(gold));

        JSONObject playerPropertiesObject = playerObject.getJSONObject(userProperties);
        playerDTO.setAttackSpellId(playerPropertiesObject.getInt(attackSpell));
        playerDTO.setAttackSpell2Id(playerPropertiesObject.getInt(attackSpell2));
        playerDTO.setDefenceSpellId(playerPropertiesObject.getInt(defenceSpell));
        playerDTO.setUltimatumSpellId(playerPropertiesObject.getInt(ultimatumSpell));

        return playerDTO;
    }

}
