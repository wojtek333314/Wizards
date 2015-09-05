package com.brotherhood.wizards.spells.dao;

import com.brotherhood.wizards.enums.SpellType;
import com.brotherhood.wizards.spells.dto.SpellDTO;

import org.json.JSONObject;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public class SpellDAO {
    private static final String
            name = "name",
            desc = "desc",
            mpCost = "mpCost",
            hpCost = "hpCost",
            damagePoints = "damagePoints",
            defencePoints = "defencePoints",
            speed = "speed",
            duration = "duration",
            type = "type";

    public static SpellDTO createSpellDTO(String json)
    {
        JSONObject obj = new JSONObject(json);
        SpellDTO spellDTO = new SpellDTO();
        spellDTO.setName(obj.getString(name));
        spellDTO.setDesc(obj.getString(desc));
        spellDTO.setDamagePoints(obj.getDouble(damagePoints));
        spellDTO.setDefencePoints(obj.getDouble(defencePoints));
        spellDTO.setDuration(obj.getDouble(duration));
        spellDTO.setHpCost(obj.getInt(hpCost));
        spellDTO.setMpCost(obj.getInt(mpCost));
        spellDTO.setSpeed(obj.getDouble(speed));
        spellDTO.setSpellType(defineSpellType(obj.getString(type)));

        return spellDTO;
    }

    private static SpellType defineSpellType(String value)
    {
        for(SpellType itemType : SpellType.values())
            if(value.equals(itemType.name()))
                return itemType;
        return null;
    }
}
