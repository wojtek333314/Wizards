package com.brotherhood.wizards.eq;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.eq.item.dao.ItemDAO;
import com.brotherhood.wizards.eq.item.dto.ItemDTO;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServiceLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech Osak on 2015-09-03.
 */
public class Equipment implements JsonDownloader.JsonDownloaderListener {
    private List<ItemDTO> itemList;

    public Equipment()
    {
        ServiceLoader eqLoader = new ServiceLoader(ServiceType.USER_EQ_GET);
        eqLoader.setJsonDownloaderListener(this,true);
        eqLoader.execute();
    }

    public Equipment(String cacheJson){
        parseJsonToItemList(cacheJson);
    }

    private void parseJsonToItemList(String json)
    {
        JSONArray list = new JSONArray(json);
        itemList = new ArrayList<ItemDTO>();

        for(int i=0;i<list.length();i++)
        {
            JSONObject itemJson = list.getJSONObject(i);
            ItemDTO item = ItemDAO.createItemDTO(itemJson.toString());
            itemList.add(item);
        }
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    @Override
    public void onLoadFinished(String json) {
        parseJsonToItemList(json);
    }

    @Override
    public void onError() {

    }
}
