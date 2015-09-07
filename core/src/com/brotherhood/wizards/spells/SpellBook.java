package com.brotherhood.wizards.spells;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.player.Player;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServiceLoader;
import com.brotherhood.wizards.spells.dao.SpellDAO;
import com.brotherhood.wizards.spells.dto.SpellDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech Osak on 2015-09-03.
 * Klasa wczytujaca wszystkie zaklecia dla danego player'a
 */
public class SpellBook implements JsonDownloader.JsonDownloaderListener {
    private List<SpellDTO> spellList;
    private Player playerHandle;

    public SpellBook(Player player)
    {
        playerHandle = player;
        ServiceLoader spellBookLoader = new ServiceLoader(ServiceType.USER_SPELL_BOOK_GET,player.getNick());
        spellBookLoader.setJsonDownloaderListener(this);
        spellBookLoader.execute();
    }

    public SpellBook(String jsonCache){
        parseJsonToSpellList(jsonCache);
    }

    private void parseJsonToSpellList(String jsonString)
    {
        JSONArray list = new JSONArray(jsonString);
        spellList = new ArrayList<SpellDTO>();

        for(int i=0;i<list.length();i++)
        {
            JSONObject itemJson = list.getJSONObject(i);
            SpellDTO item = SpellDAO.createSpellDTO(itemJson.toString());
            spellList.add(item);
        }
    }

    public List<SpellDTO> getSpellList() {
        return spellList;
    }

    public SpellDTO getSpell(int index)
    {
        return spellList.get(index);
    }

    public SpellDTO getSpellById(int id){
         for(SpellDTO spell : spellList)
         {
             if(spell.getId()==id)
                 return spell;
         }
        return null;
    }

    @Override
    public void onLoadFinished(String json) {
        playerHandle.setSpellBook(this);
        parseJsonToSpellList(json);
    }

    @Override
    public void onError() {

    }
}
