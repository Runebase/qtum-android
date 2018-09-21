package org.runebase.wallet.ui.fragment.runebase_cash_management_fragment;

import android.content.Context;

import org.runebase.wallet.dataprovider.rest_api.runebase.RunebaseService;
import org.runebase.wallet.datastorage.KeyStorage;

import org.runebase.wallet.model.gson.UnspentOutput;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observable;

public class AddressListInteractorImpl implements AddressListInteractor {

    private WeakReference<Context> mContext;

    AddressListInteractorImpl(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public List<String> getAddresses() {
        return KeyStorage.getInstance().getAddresses();
    }

    @Override
    public Observable<List<UnspentOutput>> getUnspentOutputs(List<String> addresses) {
        return RunebaseService.newInstance().getUnspentOutputsForSeveralAddresses(addresses);
    }
}
