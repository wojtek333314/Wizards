package com.brotherhood.wizards.serverUtils;

/**
 * Created by Wojciech Osak on 2015-09-04.
 */
public abstract class ServerConstants {
    public static final String SERVER_URL = "http://datastore.waw.pl/wizards/";

    public static final String GET_USER_DETAILS_SERVICE = "userGET.php?nick=";
    public static final String GET_EQ_SERVICE = "eqGET.php?nick=";
    public static final String GET_SPELLBOOK_SERVICE = "spellBookGET.php?nick=";

    public static final String EQ_CACHE_KEY = "eqJsonCache";
    public static final String SPELLBOOK_CACHE_KEY = "spellBookJsonCache";
    public static final String USER_CACHE_KEY = "userJsonCache";

    public static String getCacheKeyByUrl(String url)
    {
        if(url.contains(GET_EQ_SERVICE)) return EQ_CACHE_KEY;
        if(url.contains(GET_SPELLBOOK_SERVICE)) return SPELLBOOK_CACHE_KEY;
        if(url.contains(GET_USER_DETAILS_SERVICE)) return USER_CACHE_KEY;
        return null;
    }
}
