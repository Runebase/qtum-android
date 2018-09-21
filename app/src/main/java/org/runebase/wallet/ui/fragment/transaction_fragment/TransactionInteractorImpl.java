package org.runebase.wallet.ui.fragment.transaction_fragment;

import android.content.Context;

import org.runebase.wallet.R;
import org.runebase.wallet.model.gson.history.History;
import org.runebase.wallet.datastorage.HistoryList;
import org.runebase.wallet.utils.DateCalculator;

import java.lang.ref.WeakReference;

class TransactionInteractorImpl implements TransactionInteractor {

    private WeakReference<Context> mContext;

    public TransactionInteractorImpl(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    @Override
    public History getHistory(int position) {
        return HistoryList.getInstance().getHistoryList().get(position);
    }

    @Override
    public String getFullDate(long l) {
        return DateCalculator.getFullDate(l);
    }

    @Override
    public String getUnconfirmedDate() {
        return mContext.get().getString(R.string.unconfirmed);
    }
}
