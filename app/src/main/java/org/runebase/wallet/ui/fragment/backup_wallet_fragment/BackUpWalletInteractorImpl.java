package org.runebase.wallet.ui.fragment.backup_wallet_fragment;

import android.content.Context;
import android.util.Base64;

import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.utils.crypto.AESUtil;
import org.runebase.wallet.utils.crypto.KeyStoreHelper;

class BackUpWalletInteractorImpl implements BackUpWalletInteractor {

    private Context mContext;

    private final String RUNEBASE_PIN_ALIAS = "runebase_alias";

    BackUpWalletInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public String getPassphrase(String pin) {
       return KeyStoreHelper.getSeed(mContext, pin);
    }
}
