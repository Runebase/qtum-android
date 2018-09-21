package org.runebase.wallet.utils;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.RunebaseMainNetParams;
import org.bitcoinj.params.RunebaseTestNetParams;
import org.runebase.wallet.BuildConfig;

public class CurrentNetParams {

    public  CurrentNetParams(){}

    public static NetworkParameters getNetParams(){
        return BuildConfig.USE_MAIN_NET? RunebaseMainNetParams.get() : RunebaseTestNetParams.get();
    }

    public static String getUrl(){
        return "https://smart-api.runebase.io"; //BuildConfig.API_URL;
    }
}
