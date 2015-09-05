package com.brotherhood.wizards.serverUtils;

import com.brotherhood.wizards.enums.ServiceType;

/**
 * Created by Wojciech Osak on 2015-09-03.
 */
public class ServiceLoader extends JsonDownloader {

    public ServiceLoader(ServiceType serviceType,String nick) {
        String url = "";
        switch (serviceType)
        {
            case USER_DETAILS_GET:
                url = ServerConstants.SERVER_URL+ServerConstants.GET_USER_DETAILS_SERVICE+nick;
                break;
            case USER_EQ_GET:
                url = ServerConstants.SERVER_URL+ServerConstants.GET_EQ_SERVICE+nick;
                break;
            case USER_SPELL_BOOK_GET:
                url = ServerConstants.SERVER_URL+ServerConstants.GET_SPELLBOOK_SERVICE+nick;
                break;
        }
        setUrl(url);
    }

    public void execute()
    {
        try {
            call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
