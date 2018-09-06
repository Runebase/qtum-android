package org.runebase.wallet.ui.fragment.profile_fragment;

import android.content.Context;

import org.runebase.wallet.datastorage.HistoryList;
import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.datastorage.TinyDB;
import org.runebase.wallet.datastorage.listeners.LanguageChangeListener;

class ProfileInteractorImpl implements ProfileInteractor {

    private Context mContext;

    ProfileInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void clearWallet() {
        RunebaseSharedPreference.getInstance().clear(mContext);
        KeyStorage.getInstance().clearKeyStorage();
        HistoryList.getInstance().clearHistoryList();
        TinyDB db = new TinyDB(mContext);
        db.clearTokenList();
        db.clearContractList();
        db.clearTemplateList();
    }

    @Override
    public void setupLanguageChangeListener(LanguageChangeListener listener) {
        RunebaseSharedPreference.getInstance().addLanguageListener(listener);
    }

    @Override
    public void removeLanguageListener(LanguageChangeListener listener) {
        RunebaseSharedPreference.getInstance().removeLanguageListener(listener);
    }

    @Override
    public boolean isTouchIdEnable() {
        return RunebaseSharedPreference.getInstance().isTouchIdEnable(mContext);
    }

    @Override
    public void saveTouchIdEnable(boolean isChecked) {
        RunebaseSharedPreference.getInstance().saveTouchIdEnable(mContext, isChecked);
    }
}
