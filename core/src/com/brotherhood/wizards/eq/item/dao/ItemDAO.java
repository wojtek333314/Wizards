package com.brotherhood.wizards.eq.item.dao;

import com.brotherhood.wizards.enums.ItemType;
import com.brotherhood.wizards.eq.item.dto.ItemDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wojciech Osak on 2015-09-05.
 */
public class ItemDAO {
    private static  final String
            name = "name",
            desc = "desc",
            type = "type",
            typeValue = "typeValue",
            type2 =  "type2",
            type2Value = "type2Value",
            type3 = "type3",
            type3Value = "type3Value",
            count = "count";

    public static ItemDTO createItemDTO(String json) throws JSONException {
        ItemDTO itemDTO = new ItemDTO();
        JSONObject playerObject = new JSONObject(json);

        itemDTO.setName(playerObject.getString(name));
        itemDTO.setDescription(playerObject.getString(desc));
        itemDTO.setType(defineType(playerObject.getString(type)));
        itemDTO.setType2(defineType(playerObject.getString(type2)));
        itemDTO.setType3(defineType(playerObject.getString(type3)));
        itemDTO.setTypeValue(playerObject.getLong(typeValue));
        itemDTO.setType2Value(playerObject.getLong(type2Value));
        itemDTO.setType3Value(playerObject.getLong(type3Value));
        itemDTO.setCount(playerObject.getInt(count));

        return itemDTO;
    }

    private static ItemType defineType(String value)
    {
        for(ItemType itemType : ItemType.values())
            if(value.equals(itemType.name()))
                return itemType;
        return null;
    }
}
