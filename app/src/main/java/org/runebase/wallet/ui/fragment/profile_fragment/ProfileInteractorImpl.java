package org.runebase.wallet.ui.fragment.profile_fragment;

import android.content.Context;

import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSettingSharedPreference;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.datastorage.TinyDB;
import org.runebase.wallet.datastorage.listeners.LanguageChangeListener;

import io.realm.Realm;

class ProfileInteractorImpl implements ProfileInteractor {

    private Context mContext;
    private Realm mRealm;

    ProfileInteractorImpl(Context context, Realm realm) {
        mContext = context;
        mRealm = realm;
    }

    @Override
    public void clearWallet() {
        RunebaseSharedPreference.getInstance().clear(mContext);
        KeyStorage.getInstance().clearKeyStorage();

        mRealm.removeAllChangeListeners();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        TinyDB db = new TinyDB(mContext);
        db.clearTokenList();
        db.clearContractList();
        db.clearTemplateList();
    }

    @Override
    public void setupLanguageChangeListener(LanguageChangeListener listener) {
        RunebaseSettingSharedPreference.getInstance().addLanguageListener(listener);
    }

    @Override
    public void removeLanguageListener(LanguageChangeListener listener) {
        RunebaseSettingSharedPreference.getInstance().removeLanguageListener(listener);
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
