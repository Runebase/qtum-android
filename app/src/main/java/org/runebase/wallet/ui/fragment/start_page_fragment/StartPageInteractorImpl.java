package org.runebase.wallet.ui.fragment.start_page_fragment;

import android.content.Context;

import org.runebase.wallet.datastorage.HistoryList;
import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.datastorage.TinyDB;

import java.lang.ref.WeakReference;

public class StartPageInteractorImpl implements StartPageInteractor {

    private WeakReference<Context> mContext;

    public StartPageInteractorImpl(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    public boolean getGeneratedKey() {
        return RunebaseSharedPreference.getInstance().getKeyGeneratedInstance(mContext.get());
    }

    @Override
    public void clearWallet() {
        RunebaseSharedPreference.getInstance().clear(mContext.get());
        KeyStorage.getInstance().clearKeyStorage();
        HistoryList.getInstance().clearHistoryList();
        TinyDB db = new TinyDB(mContext.get());
        db.clearTokenList();
        db.clearContractList();
        db.clearTemplateList();
    }
}
