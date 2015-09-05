package com.brotherhood.wizards.spells;

import com.brotherhood.wizards.enums.ServiceType;
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
 */
public class SpellBook implements JsonDownloader.JsonDownloaderListener {
    private List<SpellDTO> spellList;

    public SpellBook()
    {
        ServiceLoader spellBookLoader = new ServiceLoader(ServiceType.USER_SPELL_BOOK_GET);
        spellBookLoader.setJsonDownloaderListener(this);
        spellBookLoader.execute();
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

    @Override
    public void onLoadFinished(String json) {
        parseJsonToSpellList(json);
    }

    @Override
    public void onError() {

    }
}
