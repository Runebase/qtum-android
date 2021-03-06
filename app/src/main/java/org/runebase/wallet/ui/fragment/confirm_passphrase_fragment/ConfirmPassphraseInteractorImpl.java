package org.runebase.wallet.ui.fragment.confirm_passphrase_fragment;


import android.content.Context;

import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;

import java.lang.ref.WeakReference;

import rx.Observable;

public class ConfirmPassphraseInteractorImpl implements ConfirmPassphraseInteractor {

    WeakReference<Context> mContext;

    ConfirmPassphraseInteractorImpl(Context context){
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public Observable<String> createWallet(String passphrase) {
        return KeyStorage.getInstance().createWallet(passphrase);
    }

    @Override
    public void setKeyGeneratedInstance(boolean isKeyGenerated) {
        RunebaseSharedPreference.getInstance().setKeyGeneratedInstance(mContext.get(), isKeyGenerated);
    }
}
