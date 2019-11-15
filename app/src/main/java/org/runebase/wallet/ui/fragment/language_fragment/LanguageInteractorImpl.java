package org.runebase.wallet.ui.fragment.language_fragment;

import android.content.Context;
import android.util.Pair;

import org.runebase.wallet.datastorage.RunebaseSettingSharedPreference;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.datastorage.listeners.LanguageChangeListener;

import java.util.ArrayList;
import java.util.List;

class LanguageInteractorImpl implements LanguageInteractor {

    private Context mContext;
    private List<Pair<String, String>> mLanguagesList;

    LanguageInteractorImpl(Context context) {
        mContext = context;
        mLanguagesList = new ArrayList<>();
        mLanguagesList.add(new Pair<>("us", "English"));
        mLanguagesList.add(new Pair<>("zh", "Chinese"));
        mLanguagesList.add(new Pair<>("pt", "Portuguese"));
        mLanguagesList.add(new Pair<>("swe", "Swedish"));
        mLanguagesList.add(new Pair<>("nl", "Dutch"));
    }

    @Override
    public String getLanguage() {
        return RunebaseSettingSharedPreference.getInstance().getLanguage(mContext);
    }

    @Override
    public void setLanguage(String language) {
        RunebaseSettingSharedPreference.getInstance().saveLanguage(mContext, language);
    }

    @Override
    public List<Pair<String, String>> getLanguagesList() {
        return mLanguagesList;
    }

    @Override
    public void removeLanguageListener(LanguageChangeListener languageChangeListener) {
        RunebaseSettingSharedPreference.getInstance().removeLanguageListener(languageChangeListener);
    }

    @Override
    public void addLanguageListener(LanguageChangeListener languageChangeListener) {
        RunebaseSettingSharedPreference.getInstance().addLanguageListener(languageChangeListener);
    }
}