package org.runebase.wallet.datastorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import org.runebase.wallet.datastorage.listeners.LanguageChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RunebaseSettingSharedPreference {

    private final String RUNEBASE_SETTING = "runebase_setting";
    private final String SHOW_CONTRACTS_DELETE_UNSUBSCRIBE_WIZARD = "show_contracts_delete_unsubscribe_wizard";
    private final String MIGRATION_VERSION_STRING = "migration_version_string";
    private final String FEE_PER_KB = "fee_per_kb";
    private final String RUNEBASE_LANGUAGE = "runebase_language";

    private List<LanguageChangeListener> mLanguageChangeListeners;
    private static RunebaseSettingSharedPreference sInstance = null;

    public static RunebaseSettingSharedPreference getInstance() {
        if (sInstance == null) {
            sInstance = new RunebaseSettingSharedPreference();
        }
        return sInstance;
    }

    private RunebaseSettingSharedPreference(){
        mLanguageChangeListeners = new ArrayList<>();
    }

    public void setShowContractsDeleteUnsubscribeWizard(Context context, boolean isShow) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(SHOW_CONTRACTS_DELETE_UNSUBSCRIBE_WIZARD, isShow);
        mEditor.apply();
    }

    public boolean getShowContractsDeleteUnsubscribeWizard(Context context) {
        return context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE).getBoolean(SHOW_CONTRACTS_DELETE_UNSUBSCRIBE_WIZARD, true);
    }

    public void setMigrationCodeVersionString(Context context, int codeVersion){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt(MIGRATION_VERSION_STRING, codeVersion);
        mEditor.apply();
    }

    public int getCodeVersion(Context context){
        return context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE).getInt(MIGRATION_VERSION_STRING, 0);
    }

    public void setFeePerKb(Context context, String feePerKb) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(FEE_PER_KB, feePerKb);
        mEditor.apply();
    }

    public String getFeePerKb(Context context) {
        return context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE).getString(FEE_PER_KB, "0.006");
    }

    public String getLanguage(Context context) {
        return context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE).getString(RUNEBASE_LANGUAGE, "us");
    }

    public void saveLanguage(Context context, String language) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(RUNEBASE_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(RUNEBASE_LANGUAGE, language);
        mEditor.apply();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, null);
        for (LanguageChangeListener languageChangeListener : mLanguageChangeListeners) {
            languageChangeListener.onLanguageChange();
        }
    }

    public void addLanguageListener(LanguageChangeListener languageChangeListener) {
        mLanguageChangeListeners.add(languageChangeListener);
    }

    public void removeLanguageListener(LanguageChangeListener languageChangeListener) {
        mLanguageChangeListeners.remove(languageChangeListener);
    }
}
