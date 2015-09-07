package com.brotherhood.wizards.player;

import com.brotherhood.wizards.enums.ServiceType;
import com.brotherhood.wizards.eq.Equipment;
import com.brotherhood.wizards.player.dao.PlayerDAO;
import com.brotherhood.wizards.player.dto.PlayerDTO;
import com.brotherhood.wizards.serverUtils.JsonDownloader;
import com.brotherhood.wizards.serverUtils.ServiceLoader;
import com.brotherhood.wizards.spells.SpellBook;
import com.brotherhood.wizards.spells.dto.SpellDTO;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public class Player extends PlayerDTO implements ServiceLoader.JsonDownloaderListener{
    private boolean isEquipmentLoaded = false;
    private boolean isSpellBookLoaded = false;
    private boolean isUserDetailsLoaded = false;
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
     * Startuje trzy watki pobierajace: dane o uzytkowniku, dane o ekwipunku, dane o czarach uzytkownika.
     * Zapisuje je do cache jesli nick uzytkownika jest nickiem zapisanym w sharedPreferences pod kluczem
     * userNick.
     */
    private void downloadAndParseJSONData()
    {
        //download and parse PlayerDTO
        System.out.println("Player downloading data...");
        ServiceLoader detailsLoader =  new ServiceLoader(ServiceType.USER_DETAILS_GET,getNick());
        detailsLoader.setJsonDownloaderListener(new JsonDownloader.JsonDownloaderListener() {
            @Override
            public void onLoadFinished(String json) {
                copyVariablesFromPlayerDTO(PlayerDAO.createPlayerDTO(json));
                isUserDetailsLoaded = true;
                checkIsPlayerFullyLoaded();
            }

            @Override
            public void onError() {}
        });
        detailsLoader.execute();


        equipment = new Equipment(this)
        {
            @Override
            public void onLoadFinished(String json) {
                super.onLoadFinished(json);
                isEquipmentLoaded = true;
                checkIsPlayerFullyLoaded();
            }
        };

        spellBook = new SpellBook(this)
        {
            @Override
            public void onLoadFinished(String json) {
                super.onLoadFinished(json);
                isSpellBookLoaded = true;
                checkIsPlayerFullyLoaded();
            }
        };
    }

    private void checkIsPlayerFullyLoaded(){
        if(isUserDetailsLoaded && isSpellBookLoaded && isEquipmentLoaded)
        {
            System.out.println("player fully loaded");
            this.onLoadFinished(null);
        }

    }

    public Equipment getEquipment() {
        return equipment;
    }

    public SpellBook getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(SpellBook spellBook) {
        this.spellBook = spellBook;
    }

    public void loadChosedSpells(){
        attackSpell = getSpellBook().getSpellById(getAttackSpellId());
        attackSpell2 = getSpellBook().getSpellById(getAttackSpell2Id());
        defenceSpell = getSpellBook().getSpellById(getDefenceSpellId());
        ultimatumSpell = getSpellBook().getSpellById(getUltimatumSpellId());
    }

    private void copyVariablesFromPlayerDTO(PlayerDTO playerDTO){
        setNick(playerDTO.getNick());
        setRoundsToPlay(playerDTO.getRoundsToPlay());
        setMaxHP(playerDTO.getMaxHP());
        setMaxMP(playerDTO.getMaxMP());
        setExp(playerDTO.getExp());
        setGold(playerDTO.getGold());

        setAttackSpellId(playerDTO.getAttackSpellId());
        setAttackSpell2Id(playerDTO.getAttackSpell2Id());
        setDefenceSpellId(playerDTO.getDefenceSpellId());
        setUltimatumSpellId(playerDTO.getUltimatumSpellId());
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
        loadChosedSpells();
    }

    @Override
    public void onError() {

    }
}
