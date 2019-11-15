package org.runebase.wallet.ui.activity.splash_activity;

import android.content.Context;

import org.runebase.wallet.datastorage.FileStorageManager;

class SplashActivityInteractorImpl implements SplashActivityInteractor {

    private Context mContext;

    SplashActivityInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void migrateDefaultContracts() {
        FileStorageManager.getInstance().migrateDefaultContracts(mContext);
    }
}
