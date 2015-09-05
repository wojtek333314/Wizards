package com.brotherhood.wizards.player;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.eq.Equipment;
import com.brotherhood.wizards.player.dao.PlayerDAO;
import com.brotherhood.wizards.player.dto.PlayerDTO;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServerConstants;
import com.brotherhood.wizards.serverUtils.ServiceLoader;
import com.brotherhood.wizards.spells.SpellBook;
import com.brotherhood.wizards.spells.dto.SpellDTO;
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

    private SpellDTO    attackSpell,
                        attackSpell2,
                        defenceSpell,
                        ultimatumSpell;

    public Player(String nick)
    {
        setNick(nick);
        downloadAndParseJSONData();
    }

    /**
     * Konstruktor tworzacy player'a z cache. Wywolywany tylko jesli PlayerActor jest typu PLAYER1 czyli jest to nasz gracz
     * Cache jest uaktualniany po wejsciu do aplikacji
     */
    public Player(String cache,String nick){
        setNick(nick);
        isPlayerLoaded = true;
        isEquipmentLoaded = true;
        isSpellBookLoaded = true;
        playerDTO = PlayerDAO.createPlayerDTO(cache);
        System.out.println("Player created fully from cache!");
        equipment = new Equipment(SharedPreferences.getString(ServerConstants.EQ_CACHE_KEY));
        spellBook = new SpellBook(SharedPreferences.getString(ServerConstants.SPELLBOOK_CACHE_KEY));
    }

    private void downloadAndParseJSONData()
    {
        //download and parser PlayerDTO
        System.out.println("Player downloading data...");
        ServiceLoader detailsLoader =  new ServiceLoader(ServiceType.USER_DETAILS_GET,getNick());
        detailsLoader.setJsonDownloaderListener(new JsonDownloader.JsonDownloaderListener() {
            @Override
            public void onLoadFinished(String json) {
                playerDTO = PlayerDAO.createPlayerDTO(json);
                System.out.println("Player created fully from server!");
            }

            @Override
            public void onError() {

            }
        }
        ,getNick().equals(SharedPreferences.getString("userNick")));
        detailsLoader.execute();


        equipment = new Equipment(this)
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

        spellBook = new SpellBook(this)
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

    private void loadChosedSpells(){
        attackSpell = getSpellBook().getSpellById(getPlayerDTO().getAttackSpellId());
        attackSpell2 = getSpellBook().getSpellById(getPlayerDTO().getAttackSpell2Id());
        defenceSpell = getSpellBook().getSpellById(getPlayerDTO().getDefenceSpellId());
        ultimatumSpell = getSpellBook().getSpellById(getPlayerDTO().getUltimatumSpellId());
    }

    public SpellDTO getAttackSpell() {
        return attackSpell;
    }

    public SpellDTO getAttackSpell2() {
        return attackSpell2;
    }

    public SpellDTO getDefenceSpell() {
        return defenceSpell;
    }

    public SpellDTO getUltimatumSpell() {
        return ultimatumSpell;
    }

    @Override
    public void onLoadFinished(String json) {
        isPlayerLoaded = true;
    }

    @Override
    public void onError() {

    }
}
