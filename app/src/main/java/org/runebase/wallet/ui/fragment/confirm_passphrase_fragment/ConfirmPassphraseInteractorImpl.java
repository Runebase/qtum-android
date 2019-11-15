package org.runebase.wallet.ui.fragment.confirm_passphrase_fragment;


import android.content.Context;

import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import rx.Observable;

public class ConfirmPassphraseInteractorImpl implements ConfirmPassphraseInteractor {

    WeakReference<Context> mContext;

    ConfirmPassphraseInteractorImpl(Context context){
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public Observable<String> createWallet(final String passphrase) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return KeyStorage.getInstance().loadWallet(passphrase);
            }
        });
    }

    @Override
    public void setKeyGeneratedInstance(boolean isKeyGenerated) {
        RunebaseSharedPreference.getInstance().setKeyGeneratedInstance(mContext.get(), isKeyGenerated);
    }
}
