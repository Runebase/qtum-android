package org.runebase.wallet.ui.fragment.addresses_detail_fragment;

import android.content.Context;

import org.runebase.wallet.datastorage.HistoryList;
import org.runebase.wallet.model.gson.history.History;

import java.lang.ref.WeakReference;
import java.util.List;

class AddressesDetailInteractorImpl implements AddressesDetailInteractor {

    WeakReference<Context> mContext;

    public AddressesDetailInteractorImpl(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public History getHistory(int position) {
        List<History> historyList = HistoryList.getInstance().getHistoryList();
        if (historyList != null && historyList.size() > position) {
            return historyList.get(position);
        } else {
            return null;
        }
    }
}