package com.brotherhood.wizards.player;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.eq.Equipment;
import com.brotherhood.wizards.player.dao.PlayerDAO;
import com.brotherhood.wizards.player.dto.PlayerDTO;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServiceLoader;
import com.brotherhood.wizards.spells.SpellBook;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public class Player implements ServiceLoader.JsonDownloaderListener{
    private boolean isPlayerLoaded = false;
    private boolean isEquipmentLoaded = false;
    private boolean isSpellBookLoaded = false;
    private PlayerDTO playerProperties;
    private Equipment equipment;
    private SpellBook spellBook;

    public Player()
    {
        downloadAndParseJSONData();
    }

    private void downloadAndParseJSONData()
    {
        //download and parser PlayerDTO
        System.out.println("Player downloading data...");
        ServiceLoader detailsLoader =  new ServiceLoader(ServiceType.USER_DETAILS_GET);
        detailsLoader.setJsonDownloaderListener(new JsonDownloader.JsonDownloaderListener() {
            @Override
            public void onLoadFinished(String json) {
                playerProperties = PlayerDAO.createPlayerDTO(json);
            }

            @Override
            public void onError() {

            }
        });
        detailsLoader.execute();


        equipment = new Equipment()
        {
            @Override
            public void onLoadFinished(String json) {
                super.onLoadFinished(json);
                isEquipmentLoaded = true;
                if(isSpellBookLoaded){
                    isPlayerLoaded = true;
                    Player.this.onLoadFinished(null);
                }
            }
        };

        spellBook = new SpellBook()
        {
            @Override
            public void onLoadFinished(String json) {
                super.onLoadFinished(json);
                isSpellBookLoaded = true;
                if(isEquipmentLoaded){
                    isPlayerLoaded = true;
                    Player.this.onLoadFinished(null);
                }
            }
        };
    }

    public PlayerDTO getPlayerProperties() {
        return playerProperties;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public SpellBook getSpellBook() {
        return spellBook;
    }

    @Override
    public void onLoadFinished(String json) {
        isPlayerLoaded = true;
        System.out.println("Player created fully");
    }

    @Override
    public void onError() {

    }
}
