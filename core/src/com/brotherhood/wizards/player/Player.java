package com.brotherhood.wizards.player;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.eq.Equipment;
import com.brotherhood.wizards.player.dao.PlayerDAO;
import com.brotherhood.wizards.player.dto.PlayerDTO;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServerConstants;
import com.brotherhood.wizards.serverUtils.ServiceLoader;
import com.brotherhood.wizards.spells.SpellBook;
import com.brotherhood.wizards.utils.SharedPreferences;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public class Player extends PlayerDTO implements ServiceLoader.JsonDownloaderListener{
    private boolean isPlayerLoaded = false;
    private boolean isEquipmentLoaded = false;
    private boolean isSpellBookLoaded = false;
    private PlayerDTO playerDTO;
    private Equipment equipment;
    private SpellBook spellBook;

    public Player()
    {
        downloadAndParseJSONData();
    }

    /**
     * Konstruktor tworzacy player'a z cache.
     */
    public Player(String cache){
        isPlayerLoaded = true;
        isEquipmentLoaded = true;
        isSpellBookLoaded = true;
        playerDTO = PlayerDAO.createPlayerDTO(cache);
        equipment = new Equipment(SharedPreferences.getString(ServerConstants.EQ_CACHE_KEY));
        spellBook = new SpellBook(SharedPreferences.getString(ServerConstants.SPELLBOOK_CACHE_KEY));
    }

    private void downloadAndParseJSONData()
    {
        //download and parser PlayerDTO
        System.out.println("Player downloading data...");
        ServiceLoader detailsLoader =  new ServiceLoader(ServiceType.USER_DETAILS_GET);
        detailsLoader.setJsonDownloaderListener(new JsonDownloader.JsonDownloaderListener() {
            @Override
            public void onLoadFinished(String json) {
                playerDTO = PlayerDAO.createPlayerDTO(json);
                System.out.println("Player created fully from cache!");
                System.out.println(json);
            }

            @Override
            public void onError() {

            }
        }
        ,true);
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

    public PlayerDTO getPlayerDTO() {
        return playerDTO;
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
